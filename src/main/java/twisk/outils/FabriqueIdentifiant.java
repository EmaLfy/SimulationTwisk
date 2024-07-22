package twisk.outils;

public class FabriqueIdentifiant {
    private static final FabriqueIdentifiant instance = new FabriqueIdentifiant();
    private int noEtape;

    private FabriqueIdentifiant() {
        noEtape = 0;
    }

    public static FabriqueIdentifiant getInstance() {
        return instance;
    }

    public String getCptEtape() {
        noEtape++;
        return Integer.toString(noEtape - 1);
    }
    public void reset(){
        noEtape = 0;
    }

    /**
     * Permet de setter le numéro de l'étape
     * @param noEtape le numéro de l'étape
     */
    public void setNoEtape(int noEtape){
        this.noEtape = noEtape+1;
    }
}



