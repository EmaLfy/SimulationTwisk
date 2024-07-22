package twisk.simulation;

import twisk.monde.Etape;
import twisk.monde.GestionnaireEtapes;
import twisk.monde.Guichet;
import twisk.monde.Monde;
import twisk.mondeIG.SujetObserver;
import twisk.outils.FabriqueNumero;
import twisk.outils.KitC;
import twisk.outils.ThreadsManager;

import static java.lang.Thread.sleep;

public class Simulation extends SujetObserver {

    private int nbClients;
    private GestionnaireClients clients;
    public Simulation() {}

    public void simuler(Monde m){
        FabriqueNumero.getInstance().reset();
        KitC ficha= new KitC();
//        System.out.println(m.getEntree().toString());
//        System.out.println(m.getSortie().toString());
//        for (Etape e : m.getLesEtapes()){
//            System.out.println(e.toString());
//        }
        // code pour la construction du fichier C à ajouter
//        System.out.println(m.toC());
        //ficha.creerCodeNatif();
        ficha.copieCodeNatif();
        ficha.creerFichier(m.toC());
        ficha.compiler();
        ficha.construireLaBibliotheque();
        System.load("/tmp/twisk/libTwisk"+ FabriqueNumero.getInstance().getCptLibNoIncrement() +".so");
        try {
            main(m);
        } catch (InterruptedException e) {
            //Thread.currentThread().interrupt();
            for (Client c : clients){
                ficha.killThreads(c.getNumeroClient());
            }
            //System.out.println("Arret prematuré de la simulation");
        }
    }

    public int getNbClients() {
        return nbClients;
    }

    public void setNbClients(int nbClient) {
        this.nbClients = nbClient;
    }

    public native int[] start_simulation(int nbEtapes, int nbGuichets, int nbClients, int[] tabJetons);
    public native int[] ou_sont_les_clients(int nbEtape, int nbClients);
    public native void nettoyage();

    public int[] nombre_jeton(Monde m){
        GestionnaireEtapes etapes=m.getLesEtapes();
        int[] jeton=new int[m.nbGuichets()];
        for(Etape e : etapes){
            if(e.estUnGuichet()){
                jeton[e.getIdSemaphore()-1]= e.getNbJetons();
            }
        }
        return jeton;
    }

    public int afficher_proc(int[] liste, int nbEtapes, int nbClients, Monde m){
        int out=0;
        int j = 0;
        for (Etape e : m){
            j=e.getIdEtape();

            //System.out.print("Etape " + e.getIdEtape() + " " + e.getNom() + " " + liste[j*(nbClients+1)] + " client(s) : ");
            if ((e.getIdEtape() == 1) && (liste[j*(nbClients+1)]==nbClients)){
                out = 1;
            }

            for(int i=1; i<=liste[j*(nbClients+1)];i++){
                    if(i==liste[j*(nbClients+1)]){
                        //System.out.print(liste[(j*(nbClients+1))+i]); // affichage joli au dernier élément( sans le `,`)
                    }else{
                        //System.out.print(liste[(j*(nbClients+1))+i]+" , ");
                    }
                    this.clients.allerA(liste[j*(nbClients+1)+i], e, i);
                }

            //System.out.println();
        }

        //System.out.println("\n");
        return out;
    }

    int main(Monde m) throws InterruptedException {

        int nbEtapes = m.nbEtapes();
        int nbGuichets = m.nbGuichets();
        int tabJetons[] = nombre_jeton(m);
        int[] tabProc;

        this.clients=new GestionnaireClients();

        tabProc = start_simulation(nbEtapes, nbGuichets, getNbClients(), tabJetons);
        //System.out.println("Les clients :");
        for(int i = 0; i<getNbClients(); i++){
            //System.out.println(tabProc[i]+", ");
            this.clients.setClients(tabProc[i]);
        }
        int[] where= ou_sont_les_clients(nbEtapes, getNbClients());
        while(this.afficher_proc(where, nbEtapes, getNbClients(), m) == 0){

            afficher_proc(where, nbEtapes, getNbClients(), m);
            Thread.sleep(300); // on attends
            where=ou_sont_les_clients(nbEtapes,getNbClients()); // On met à jour la liste des emplacements des clients
            notifierObservateurs();

        }
        //afficher_proc(where, nbEtapes, nbClients);
        notifierObservateurs();
        nettoyage();
        return 0;
    }

    public GestionnaireClients getClients() {
        return clients;
    }

}


