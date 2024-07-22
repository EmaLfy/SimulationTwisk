package twisk.monde;

import twisk.outils.FabriqueNumero;

public class Guichet extends Etape{

    private int nbJetons; // Le nombre de jetons, pouvant accéder à une activité (nb personnes en gros)
    private int semaphore; // ID du guichet

    public Guichet(String nom) {
        super(nom);
        this.nbJetons=3; // Le nombre de jetons par défault est de 3 (c'est moi qu'ai decidé et toc)
        this.semaphore=FabriqueNumero.getInstance().getCptSemaphore();
    }

    public Guichet(String nom, int nb){
        super(nom);
        this.semaphore=FabriqueNumero.getInstance().getCptSemaphore();

        this.nbJetons=nb; // On met le nombre de jetons donné en paramètre
    }
    public boolean estUnGuichet(){
        return true; // C'est bien un guichet
    }

    @Override
    public String toC() {
        StringBuilder sb = new StringBuilder();
        int nbSucc = this.getSucc().getEtapeSucc().getSucc().nbEtapes();
        int i = 0;
        sb.append("P(ids,").append(this.semaphore).append(");\n");
        sb.append("transfert(").append(this.getIdEtape()).append(",").append(this.getSucc().getEtapeSucc().getIdEtape()).append(");\n"); //revoir pour la gestion du délai de l'activité
        sb.append("delai(").append(this.getSucc().getEtapeSucc().getTemps()).append(",").append(this.getSucc().getEtapeSucc().getEcartTemps()).append(");\n");
        sb.append("V(ids,").append(this.semaphore).append(");\n");
        if (nbSucc == 1){
            sb.append("transfert(").append(this.getSucc().getEtapeSucc().getIdEtape()).append(",").append(this.getSucc().getEtapeSucc().getSucc().getEtapeSucc().getIdEtape()).append(");\n");
            sb.append(this.getSucc().getEtapeSucc().getSucc().getEtapeSucc().toC());
        }
        else {
            sb.append("srand(time(NULL));\nint choice").append(this.getSucc().getEtapeSucc().getIdEtape()).append(" = rand() % ").append(nbSucc).append(";\n");
            for (Etape e : this.getSucc().getEtapeSucc().getSucc()) {
                sb.append("if (choice2 == ").append(i).append("){\n");
                sb.append("transfert(").append(this.getSucc().getEtapeSucc().getIdEtape()).append(",").append(e.getIdEtape()).append(");\n");
                sb.append(e.toC());
                sb.append("}\n");
                i++;
            }
        }

        return sb.toString();
    }

    public int getNbJetons() {
        return nbJetons;
    }
    public int getIdSemaphore(){
        return this.semaphore;
    }
}
