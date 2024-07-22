package twisk.monde;

import java.util.Iterator;

public class Activite extends Etape{

    private int temps;
    private int ecartTemps;
    public Activite(String nom) {
        super(nom);
//        this.temps = 10;
//        this.ecartTemps = 4;
    }

    public Activite(String nom, int temps, int ecartTemps) {
        super(nom);
        this.temps = temps;
        this.ecartTemps = ecartTemps;
    }

    public boolean estUneActivite(){
        return true;
    }

    public int getTemps() {
        return temps;
    }

    public int getEcartTemps() {
        return ecartTemps;
    }

    public String toC(){
        StringBuilder sb = new StringBuilder();
        int nbSucc = this.getSucc().nbEtapes();
        int i = 0;
        sb.append("delai(").append(this.temps).append(",").append(this.ecartTemps).append(");\n");
        if (nbSucc == 1){
            sb.append("transfert(").append(this.getIdEtape()).append(",").append(this.getSucc().getEtapeSucc().getIdEtape()).append(");\n");
            sb.append(this.getSucc().getEtapeSucc().toC());
        }
        else
        {
            sb.append("srand(time(NULL));\nint choice").append(this.getIdEtape()).append(";\nchoice").append(this.getIdEtape()).append(" = rand() % ").append(nbSucc).append(";\n");
            for (Iterator<Etape> it = this.getSucc().iterator(); it.hasNext(); ) {
                Etape e = it.next();
                //Etape e = this.getSucc().iterator().next();
                sb.append("if (choice").append(this.getIdEtape()).append(" == ").append(i).append("){\n");
                sb.append("transfert(").append(this.getIdEtape()).append(",").append(e.getIdEtape()).append(");\n");
                sb.append(e.toC());
                sb.append("}\n");
                i++;
            }
        }
        return sb.toString();

    }

    @Override
    public int getNbJetons() {
        return 0;
    }
    @Override
    public boolean estUnSasEntree() {
        return false;
    }
}
