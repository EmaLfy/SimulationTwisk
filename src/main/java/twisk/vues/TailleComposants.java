package twisk.vues;

import twisk.outils.FabriqueIdentifiant;

public class TailleComposants {
    private static final TailleComposants instance = new TailleComposants();

    private TailleComposants(){
    }

    public static TailleComposants getInstance() {
        return instance;
    }

    public int hBoxHeight(){
        return 50;
    }
    public int VBoxHeigth(){
        return 100;
    }
    public int VBoxWidth(){
        return 200;
    }
}
