package twisk.outils;

public class FabriqueNumero {
    private static final FabriqueNumero instance = new FabriqueNumero();
    private int cptEtape;
    private int cptSemaphore;
    private int cptLib;


    private FabriqueNumero(){
        cptEtape = 0;
        cptSemaphore=1;
        cptLib =0;
    }

    public static FabriqueNumero getInstance(){
        return instance;
    }

    public int getCptEtape(){
        cptEtape++;
        return cptEtape-1;
    }

    public int getCptSemaphore(){
        cptSemaphore++;
        return cptSemaphore-1;
    }

    public int getCptLib() {
        cptLib++;
        return cptLib-1;
    }
    public int getCptLibNoIncrement() {
        return cptLib-1;
    }


    public void reset(){
        cptEtape = 0;
        cptSemaphore = 1;
    }
}
