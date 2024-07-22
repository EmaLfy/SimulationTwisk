package twisk.monde;

import twisk.outils.FabriqueNumero;

import java.util.Iterator;

public abstract class Etape implements Iterable<Etape>{

    private GestionnaireEtapes succ;
    private String nom;

    private int idEtape;

    public Etape(String nom) {
        this.nom = nom;
        this.succ = new GestionnaireEtapes();
        this.idEtape = FabriqueNumero.getInstance().getCptEtape();
    }

    public void ajouterSuccesseur(Etape... successeur){
        this.succ.ajouter(successeur);
    }

    public boolean estUneActivite(){
        return false;
    }

    public boolean estUnGuichet(){
        return false;
    }

    public int getNbSucc() {
        return succ.nbEtapes();
    }

    public String getNom() {
        return nom;
    }

    public GestionnaireEtapes getSucc() {
        return succ;
    }

    public int getIdEtape() {
        return idEtape;
    }

    public int getTemps() {
        return 0;
    }

    public int getEcartTemps() {
        return 0;
    }

    public int getIdSemaphore() {
        return -1;
    }

    public abstract String toC();

    public abstract int getNbJetons();


    @Override
    public Iterator<Etape> iterator() {
        return this.succ.iterator();
    }
    @Override
    public String toString() {
        StringBuilder sB = new StringBuilder();
        sB.append(this.getNom());
        sB.append(" : ");
        sB.append(this.getNbSucc());
        sB.append(" successeur(s) - ");
        for(Etape e : this.getSucc()){
            sB.append(e.getNom());
            sB.append("; ");

        }
        return sB.toString();
    }

    public boolean estUnSasEntree() {
        return false;
    }

}
