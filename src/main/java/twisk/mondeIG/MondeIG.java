package twisk.mondeIG;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;
import twisk.exceptions.ArcException;
import twisk.exceptions.EcartDelaiException;
import twisk.exceptions.JetonsException;
import twisk.outils.*;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;

public class MondeIG extends SujetObserver implements Iterable<EtapeIG>{

    @Expose
    private HashMap<String, EtapeIG> etapes;

    @Expose
    private ArrayList<ArcIG> arcIGs;

    private ArrayList<PointDeControleIG> tempPDC;

    private int nbClients;
    private ArrayList<ClientIG> clients;

    private boolean run;

    private GsonBuilder gson;

    private int loi;

    public MondeIG() {
        nbClients = 5;
        this.etapes = new HashMap<>();
        this.gson = new GsonBuilder();
        gson.setPrettyPrinting();
        gson.registerTypeAdapter(EtapeIG.class, new EtapeIGTypeAdapter());
        this.loi= 0;//initialisation à 0 (uniforme)


        this.arcIGs = new ArrayList<>();
        this.tempPDC = new ArrayList<>(2);
        this.clients = new ArrayList<>();
        EtapeIG e = new ActiviteIG("Etape 0", 200, 100);
        this.etapes.put(e.getIdentifiant(), e);
    }
    public void ajouter(String type){
        EtapeIG e = new ActiviteIG(type, 200, 100);
        this.etapes.put(e.getIdentifiant(), e);
        notifierObservateurs();

    }


    /**
     * Méthode qui permet de savoir si une étape est accessible depuis une autre étape.
     * @param candidat l'étape candidat
     * @param racine l'étape racine
     * @return un booléen
     */
    public boolean estAccessibleDepuis(EtapeIG candidat, EtapeIG racine){
        if (candidat == racine){
            return true;
        }
        else {
            for (EtapeIG e : racine.getSuccesseurs()){
                if (estAccessibleDepuis(candidat, e)){
                    return true;
                }
            }
        }
        return false;
    }


    public boolean isRun() {
        return run;
    }

    public void setRun(boolean run) {
        this.run = run;
        notifierObservateurs();
    }


    public void setNbClients(String nbClients) throws EcartDelaiException {
        int cl = 0;

        try {
            cl = Integer.parseInt(nbClients);
        } catch (NumberFormatException e){
            throw new EcartDelaiException();
        }
        if (cl < 1 || cl > 50){
            throw new EcartDelaiException();
        }
        else {
            this.nbClients = cl;
        }
    }

    public int getNbClients() {
        return nbClients;
    }

    /**
     * Méthode qui permet d'ajouter un guichet.
     * @param nom le nom de l'activité
     */
    public void ajouterGuichet(String nom){
        EtapeIG e = new GuichetIG(nom, 250, 70);
        this.etapes.put(e.getIdentifiant(), e);
        notifierObservateurs();
    }

    /**
     * Méthode qui permet d'ajouter un point de contrôle temporaire.
     * @param p le point de contrôle
     */
    public void ajouterPDCTempo(PointDeControleIG p) throws ArcException {
        this.tempPDC.add(p);
        if (this.tempPDC.size() == 2){
            if(this.tempPDC.get(0).getEtapeIG() != this.tempPDC.get(1).getEtapeIG()) {
                if (this.tempPDC.get(1).getEtapeIG().isGuichet()){
                    GuichetIG g = (GuichetIG) this.tempPDC.get(1).getEtapeIG();
                    if (g.getPdcGauche() == this.tempPDC.get(1)){
                        this.tempPDC.get(1).getEtapeIG().setSens(true);
                        //System.out.println("Sens false");
                    }
                    else {
                        this.tempPDC.get(1).getEtapeIG().setSens(false);
                        //System.out.println("Sens true");
                    }
                }

                    this.ajouter(this.tempPDC.get(0), this.tempPDC.get(1));
                    this.tempPDC.remove(1);
                    this.tempPDC.remove(0);


            }
            else{
                this.tempPDC.remove(1);
                this.tempPDC.remove(0);
                throw new ArcException();
            }
        }

    }

    /**
     * Méthode qui permet d'ajouter un arc.
     * @param pt1 le premier point de contrôle
     * @param pt2 le deuxième point de contrôle
     */
    public void ajouter(PointDeControleIG pt1, PointDeControleIG pt2) throws ArcException {
        ArcIG arc = new ArcIG(pt1, pt2);

        for (ArcIG a: arcIGs){
            if (estAccessibleDepuis(pt1.getEtapeIG(), pt2.getEtapeIG())){
                this.tempPDC.remove(1);
                this.tempPDC.remove(0);
                throw new ArcException();
            }
            if ((arc.getPdc1().getEtapeIG() == a.getPdc1().getEtapeIG()) && (arc.getPdc2().getEtapeIG() == a.getPdc2().getEtapeIG())){
                this.tempPDC.remove(1);
                this.tempPDC.remove(0);
                throw new ArcException();
            }
            if (!(((a.getPdc1() != pt1) && (a.getPdc1() != pt2)) || ((a.getPdc2() != pt1) && (a.getPdc2() != pt2)))){
                this.tempPDC.remove(1);
                this.tempPDC.remove(0);
                throw new ArcException();
            }
        }
        pt1.getEtapeIG().addSuccesseur(pt2.getEtapeIG());
        pt2.getEtapeIG().addPredecesseur(pt1.getEtapeIG());
        this.arcIGs.add(arc);
        notifierObservateurs();
    }

    public void ajouter(ClientIG cl){
        //System.out.println("Ajout de clients MondeIG");
        this.clients.add(cl);
        notifierObservateurs();
    }

    public void cleanClients(){
        this.clients.clear();
        notifierObservateurs();
    }

    @Override
    public Iterator<EtapeIG> iterator() {
        return etapes.values().iterator();
    }

    public Iterator<ArcIG> iteratorArc(){
        return this.arcIGs.iterator();
    }

    public int getNbEtapes(){
        return this.etapes.size();
    }

    /**
     * Méthode qui permet de selectionner ou deselectionner une étape.
     * @param add id de l'étape
     */
    public void etpSelect(String add){
        this.etapes.get(add).setSelct(!this.etapes.get(add).isSelct());
        this.notifierObservateurs();
    }

    public boolean aEntree(){
        boolean res = false;
        for (EtapeIG e : etapes.values()){
            if (e.isIn()){
                res = true;
            }
        }
        return res;
    }

    public boolean aSortie(){
        boolean res = false;
        for (EtapeIG e : etapes.values()){
            if (e.isOut()){
                res = true;
            }
        }
        return res;
    }



    /**
     * Méthode qui permet de supprimer un arc.
     */
    public void supprimerArc(){
        ArrayList<ArcIG> temp = new ArrayList<>();
        for(ArcIG arc : arcIGs){
            if (arc.isEstSelectionne()){
                temp.add(arc);
            }
        }
        for (ArcIG arc : temp){
            arcIGs.get(arcIGs.indexOf(arc)).rm();
            arcIGs.remove(arc);
        }
        this.notifierObservateurs();
    }

    /**
     * Méthode qui permet de tout désélectionner.
     */
    public void deselectTout(){
        for (EtapeIG e : etapes.values()){
            e.setSelct(false);
        }
        for(ArcIG arcIG : arcIGs){
            arcIG.setEstSelectionne(false);
        }
        this.notifierObservateurs();
    }

    /**
     * Méthode qui permet de tout sélectionner.
     */
    public void selectTout(){
        for (EtapeIG e : etapes.values()){
            e.setSelct(true);
        }
        for(ArcIG arcIG : arcIGs){
            arcIG.setEstSelectionne(true);
        }
        this.notifierObservateurs();
    }

    /**
     * Methode qui permet de changer le delai d'une étape selectionnée.
     * @param newDelai
     * @throws EcartDelaiException
     */
    public void changeDelaiEtapeSelct(String newDelai) throws EcartDelaiException {
        int delai = 0;

        try {
            delai = Integer.parseInt(newDelai);
        } catch (NumberFormatException e){
            throw new EcartDelaiException();
        }
        if (delai < 0){
            throw new EcartDelaiException();
        }
        else {
            for(EtapeIG e: this.etapes.values()){
                if (e.isSelct()){
                    if (e.getEcart() >= delai){
                        throw new EcartDelaiException();
                    }
                    else {
                        e.setDelai(delai);
                        e.setSelct(false);
                    }
                }
            }
            notifierObservateurs();
        }
    }

    /**
     * Méthode qui permet de changer l'écart d'une étape selectionnée.
     * @param newEcart
     * @throws EcartDelaiException
     */
    public void changeEcartEtapeSelct(String newEcart) throws EcartDelaiException {
        int ecart = 0;

        try {
            ecart = Integer.parseInt(newEcart);
        } catch (NumberFormatException e){
            throw new EcartDelaiException();
        }
        if (ecart < 0){
            throw new EcartDelaiException();
        }
        else {
            for(EtapeIG e: this.etapes.values()){
                if (e.isSelct()){
                    if (e.getDelai() <= ecart){
                        throw new EcartDelaiException();
                    }
                    else {
                        e.setEcart(ecart);
                        e.setSelct(false);
                    }
                }
            }
            notifierObservateurs();
        }
    }

    /**
     * Méthode qui permet de renommer une étape selectionnée.
     * @param newName
     */
    public void renommeEtapeSelct(String newName){
        for (EtapeIG e : etapes.values()){
            if (e.isSelct()){
                e.setNom(newName);
            }
        }
        notifierObservateurs();
    }

    /**
     * Méthode qui permet de savoir si un arc est selectionnée.
     * @return un booléen
     */
    public boolean arcSelct(){
        boolean res = false;
        Iterator<ArcIG> it = iteratorArc();
        while (it.hasNext() && !res){
            if (it.next().isEstSelectionne())
                res=true;

        }
        return res;
    }



    public void setEntree(){
        for(EtapeIG e : this.etapes.values()){
            if(e.isSelct()){
                e.setIn(!e.isIn());
                e.setSelct(false);
            }
        }
        notifierObservateurs();
    }

    public void setSortie(){
        for(EtapeIG e : this.etapes.values()){
            if(e.isSelct()){
                e.setOut(!e.isOut());
                e.setSelct(false);
            }
        }
        notifierObservateurs();
    }

    public int getNbEtapeSlect(){
        int nbSel = 0;
        for (EtapeIG e : this.etapes.values()){
            if (e.isSelct()){
                nbSel++;
            }
        }
        return nbSel;
    }

    /**
     * Méthode qui permet de supprimer une ou plusieurs étape selectionnée.
     */
    public void suppEtapSelect(){
        ArrayList<String> eTD = new ArrayList<>(this.getNbEtapes());
        ArrayList<ArcIG> temp = new ArrayList<>();
        this.tempPDC.clear();
        for (EtapeIG e : etapes.values()){
                if (e.isSelct()){
                    eTD.add(e.getIdentifiant());
                    for (PointDeControleIG p : e){
                        for (ArcIG a : arcIGs){
                            if ((a.getPdc1() == p) || (a.getPdc2() == p)){
                                temp.add(a);
                            }
                        }
                    }
                }
            }

        for (String s : eTD){
            this.etapes.remove(s);
        }
        for (ArcIG a : temp){
            a.rm();
            this.arcIGs.remove(a);
        }

        notifierObservateurs();
    }

    public int nbGuichetSelect(){
        int i=0;
        for(EtapeIG e:etapes.values()){
            if(e.isSelct() && e.isGuichet()){
                i++;
            }
        }
        return i;
    }

    public int nbActSelect(){
        int i=0;
        for(EtapeIG e:etapes.values()){
            if(e.isSelct() && !e.isGuichet()){
                i++;
            }
        }
        return i;
    }

    public void setJetonsGuichetSelct(String jeton) throws JetonsException {
        int nb = 0;

        try {
            nb = Integer.parseInt(jeton);
        } catch (NumberFormatException e){
            throw new JetonsException();
        }
        if (nb < 0){
            throw new JetonsException();
        }
        else {
            for(EtapeIG e: this.etapes.values()){
                if (e.isSelct() && e.isGuichet()){
                    e.setNbJeton(nb);
                    e.setSelct(false);
                }
            }
            notifierObservateurs();
        }
    }

    public ArrayList<ClientIG> getClients(){
        ArrayList<ClientIG> listTemp = new ArrayList<>();
        listTemp.addAll(this.clients);
        return listTemp;
    }


    /**
     * Méthode qui permet de sauvegarder le monde.
     * @param nomFichier le nom du fichier
     * @throws IOException
     */
    public void sauver(String nomFichier) throws IOException {

        Gson test = gson.excludeFieldsWithoutExposeAnnotation().create();
        Writer writer = new FileWriter(nomFichier);
        test.toJson(this, writer);
        writer.flush();
        writer.close();
    }

    /**
     * Méthode qui permet de charger un monde.
     * @param nomFichier le nom du fichier
     * @throws IOException
     */
    public void load(String nomFichier) throws IOException {
        Gson test = gson.create();
        String content = new String(Files.readAllBytes(Paths.get(nomFichier)));
        MondeIG m = test.fromJson(content, MondeIG.class);
        this.etapes = m.etapes;
        FabriqueIdentifiant.getInstance().setNoEtape(Integer.parseInt(Collections.max(m.etapes.keySet())));
        for (EtapeIG e : this.etapes.values()){
            e.resetSuccesseursPredecesseurs();
            for (PointDeControleIG p : e){
                p.setEtapeIG(e);

            }
            e.reinitPdc();
        }
        this.arcIGs = m.arcIGs;
        for (ArcIG a : this.arcIGs){
            PointDeControleIG pdc1 = a.getPdc1();
            PointDeControleIG pdc2 = a.getPdc2();
            for (EtapeIG e : this.etapes.values()){
                for (PointDeControleIG p : e){
                    if (p.getId().equals(pdc1.getId())){
                        a.setPdc1(p);
                    }
                    if (p.getId().equals(pdc2.getId())){
                        a.setPdc2(p);
                    }
                }
            }
        }
        for (ArcIG a : this.arcIGs){
            a.getPdc1().getEtapeIG().addSuccesseur(a.getPdc2().getEtapeIG());
            a.getPdc2().getEtapeIG().addPredecesseur(a.getPdc1().getEtapeIG());
        }
        this.notifierObservateurs();
    }

    public void setLoi(int loi){
        this.loi = loi;
        this.notifierObservateurs();
    }

    public int getLoi(){
        return this.loi;
    }

}
