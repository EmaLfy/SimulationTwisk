package twisk.mondeIG;

public class ClientIG {
    private int numeroClient;
    private int rang;
    private EtapeIG etape;

    public ClientIG(int numero, int rang, EtapeIG etape){
        this.numeroClient=numero;
        this.rang=rang;
        this.etape=etape;
    }
    public void allerA(EtapeIG etape, int rang){
        this.etape=etape;
        this.rang=rang;
    }

    public int getNumeroClient() {
        return numeroClient;
    }

    public EtapeIG getEtapeIG() {
        return etape;
    }

    public int getRang() {
        return rang;
    }
}
