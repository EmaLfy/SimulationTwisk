package twisk.monde;

import java.util.Iterator;

public class SasEntree extends Activite{
    private int loi; // 0: uniforme, 1: gaussienne, 2: exponentielle

    public SasEntree(){
        super("Sas Entrée",10,4);
        loi = 0;
    }

    @Override
    public boolean estUnSasEntree() {
        return true;
    }

    public void setLoi(int loi) {
        this.loi = loi;
    }

    public int getLoi() {
        return loi;
    }

    @Override
    public String toC(){
        StringBuilder sb = new StringBuilder();
        int nbSucc = this.getSucc().nbEtapes();
        int i = 0;
        switch (loi) {
            case 0:
                sb.append(" delaiUniforme(").append(this.getTemps()).append(", ").append(this.getEcartTemps()).append(");\n");
                break;

            case 1:
                sb.append(" delaiGauss(").append(this.getTemps()).append(".0, ").append(this.getEcartTemps()).append(".0);\n");
                break;
            case 2:
                sb.append(" delaiExponentiel(1.0/").append(this.getTemps()).append(".0);\n");
                break;
        }
        if (nbSucc == 1){
            sb.append(" transfert(").append(this.getIdEtape()).append(",").append(this.getSucc().getEtapeSucc().getIdEtape()).append(");\n");
            sb.append(this.getSucc().getEtapeSucc().toC());
        }
        else
        {
            sb.append("srand(time(NULL));\nint choice").append(this.getIdEtape()).append(";\nchoice").append(this.getIdEtape()).append(" = rand() % ").append(nbSucc).append(";\n");
            for (Iterator<Etape> it = this.getSucc().iterator(); it.hasNext(); ) {
                Etape e = it.next();
                //Etape e = this.getSucc().iterator().next();
                sb.append("if (choice").append(this.getIdEtape()).append(" == ").append(i).append("){\n");
                sb.append(" transfert(").append(this.getIdEtape()).append(",").append(e.getIdEtape()).append(");\n");
                sb.append(e.toC());
                sb.append("}\n");
                i++;
            }
        }
        return sb.toString();

    }

}
