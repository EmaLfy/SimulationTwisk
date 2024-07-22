package twisk.mondeIG;

import twisk.ClientTwisk;
import twisk.exceptions.MondeException;
import twisk.monde.Activite;
import twisk.monde.ActiviteRestreinte;
import twisk.monde.Guichet;
import twisk.monde.Monde;
import twisk.outils.ClassLoaderPerso;
import twisk.outils.CorrespondancesEtapes;
import twisk.outils.ThreadsManager;
import twisk.simulation.Client;
import twisk.simulation.GestionnaireClients;
import twisk.simulation.Simulation;
import twisk.vues.Observateur;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class SimulationIG implements Observateur {

    private MondeIG mondeIG;
    private CorrespondancesEtapes correspondancesEtapes;
    private GestionnaireClients clients;
    private ClassLoaderPerso cLP;
    private Class<?> cs;
    private Object s;
    private Method getClients;


    public SimulationIG(MondeIG mondeIG) {
        this.mondeIG = mondeIG;
    }

    public void simuler() throws MondeException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {

        Observateur obs = this;
        verifierMonde();
        Monde monde = creerMonde();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    cLP = new ClassLoaderPerso(ClientTwisk.class.getClassLoader());
                    cs = cLP.loadClass("twisk.simulation.Simulation");
                    Constructor cons = cs.getConstructor();
                    s =  cons.newInstance();
                    Method setNbCli = cs.getMethod("setNbClients", int.class);
                    Method simuler = cs.getMethod("simuler", Monde.class);
                    getClients = cs.getMethod("getClients");
                    cs = cLP.loadClass("twisk.mondeIG.SujetObserver");
                    Method ajObs = cs.getMethod("ajouterObservateur", Observateur.class);
                    ajObs.invoke(s, obs);
                    setNbCli.invoke(s, mondeIG.getNbClients());
                    mondeIG.setRun(true);
                    simuler.invoke(s, monde);
                    mondeIG.setRun(false);

                    System.gc();

                } catch (ClassNotFoundException | NoSuchMethodException | InvocationTargetException | IllegalAccessException | InstantiationException | RuntimeException e) {
                    e.printStackTrace();
                }
            }
        };
        ThreadsManager.getInstance().lancer(runnable);


//        cLP = new ClassLoaderPerso(ClientTwisk.class.getClassLoader());
//        cs = cLP.loadClass("twisk.simulation.Simulation");
//        Constructor cons = cs.getConstructor();
//        s =  cons.newInstance();
//        Method setNbCli = cs.getMethod("setNbClients", int.class);
//        Method simuler = cs.getMethod("simuler", Monde.class);
//        getClients = cs.getMethod("getClients");
//
//        cs = cLP.loadClass("twisk.mondeIG.SujetObserver");
//        Method ajObs = cs.getMethod("ajouterObservateur", Observateur.class);
//        ajObs.invoke(s, this);
//        setNbCli.invoke(s, 5);
//        simuler.invoke(s, monde);
//        System.gc();
    }

    /**
     * Vérifie si le monde est valide
     * @throws MondeException si le monde n'est pas valide*

     * au moins une entree / sortie OK
     * toutes les activités et sont accessibles ont des successeurs (hormis la sortie)
     * un guichet ne peut être une sortie OK
     * un guichet n'a qu'un successeur (activité) OK
     */
    private void verifierMonde() throws MondeException {
        if (!mondeIG.aEntree()) {
            throw new MondeException("Il n'y a pas d'entrée");
        }
        if (!mondeIG.aSortie()) {
            throw new MondeException("Il n'y a pas de sortie");
        }
        for (EtapeIG etapeIG : mondeIG) {
            if (!etapeIG.isIn() && etapeIG.getPredecesseurs().isEmpty()) {
                throw new MondeException("L'activité " + etapeIG.getNom() + " n'a pas de successeur");
            }
            if (!etapeIG.isOut()) {
                if (etapeIG.getSuccesseurs().isEmpty()) {
                    throw new MondeException("L'activité " + etapeIG.getNom() + " n'a pas de successeur");
                }
            }
            if (etapeIG.isGuichet()) {
                if(etapeIG.getSuccesseurs().get(0).isGuichet()){
                    throw new MondeException("Le guichet " + etapeIG.getNom() + " ne peut être suivi que d'une activité");
                }
                if (etapeIG.getSuccesseurs().size() != 1) {

                    throw new MondeException("Le guichet " + etapeIG.getNom() + " a un nombre de successeurs != 1");
                }
                if (etapeIG.isOut()){
                    throw new MondeException("Le guichet " + etapeIG.getNom() + " ne peut être une sortie");

                }
            }
        }
    }

    //Faire en fonction des successseurs
    private Monde creerMonde() {
        Monde monde = new Monde();
        switch (mondeIG.getLoi()) {
            case 0:
                monde.setLoi(0);
                break;
            case 1:
                monde.setLoi(1);
                break;
            case 2:
                monde.setLoi(2);
                break;
        }
        this.correspondancesEtapes = new CorrespondancesEtapes();
        for (EtapeIG etapeIG : mondeIG) {
            if (etapeIG.isGuichet()) {
                Guichet g = new Guichet(etapeIG.getNom(), etapeIG.getNbJetons());
                this.correspondancesEtapes.ajouter(etapeIG, g);
                monde.ajouter(g);
            } else {
                if(etapeIG.getNbPredecesseurs() != 0 && etapeIG.getPredecesseurs().get(0).isGuichet()){
                    ActiviteRestreinte aR = new ActiviteRestreinte(etapeIG.getNom(), etapeIG.getDelai(), etapeIG.getEcart());
                    this.correspondancesEtapes.ajouter(etapeIG, aR);
                    monde.ajouter(aR);
                }
                else{
                    Activite act = new Activite(etapeIG.getNom(), etapeIG.getDelai(), etapeIG.getEcart());
                    this.correspondancesEtapes.ajouter(etapeIG, act);
                    monde.ajouter(act);
                }
            }
        }
        for (EtapeIG e : mondeIG){
            if (e.isIn()){
                monde.aCommeEntree(correspondancesEtapes.getEtape(e));
            }
            if (e.isOut()){
                monde.aCommeSortie(correspondancesEtapes.getEtape(e));
            } else {
                for (EtapeIG eS : e.getSuccesseurs()){
                    this.correspondancesEtapes.getEtape(e).ajouterSuccesseur(correspondancesEtapes.getEtape(eS));
                }
            }

        }
        return monde;
    }

    public void setClients() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        this.clients = (GestionnaireClients) getClients.invoke(s);
    }

    @Override
    public void reagir() {
        try {
            setClients();
            mondeIG.cleanClients();
            for(Client c: clients){
                ClientIG client=new ClientIG(c.getNumeroClient(), c.getRang(),correspondancesEtapes.getEtapeIG(c.getEtape()));
                mondeIG.ajouter(client);
            }
        } catch (ClassNotFoundException | NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }
//        for (Client client : clients) {
//            System.out.println("Client " + client.getNumeroClient() + " rang " + client.getRang() + " à l'étape " + client.getEtape().getNom());
//        }
    }
}
