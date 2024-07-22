package twisk.monde;

public class ActiviteRestreinte extends Activite{
    public ActiviteRestreinte(String nom) {
        super(nom);
    }

    public ActiviteRestreinte(String nom, int temps, int ecartTemps) {
        super(nom, temps, ecartTemps);
    }

    @Override
    public String toC() {
        StringBuilder sb = new StringBuilder();
        //sb.append("delai(").append(this.getTemps()).append(",").append(this.getEcartTemps()).append(");\n");
        //sb.append("transfert(").append(this.getIdEtape()).append(",").append(this.getSucc().getEtapeSucc().getIdEtape()).append(");\n");

        sb.append(this.getSucc().getEtapeSucc().toC());
        return sb.toString();
    }
}
