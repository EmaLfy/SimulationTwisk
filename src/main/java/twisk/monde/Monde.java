package twisk.monde;

import java.util.Iterator;

public class Monde implements Iterable<Etape>{

    private SasEntree entree;
    private SasSortie sortie;

    private GestionnaireEtapes lesEtapes;


    public Monde() {
        this.entree = new SasEntree();
        this.sortie = new SasSortie();
        this.lesEtapes = new GestionnaireEtapes();
        this.lesEtapes.ajouter(this.entree, this.sortie);
    }

    public void aCommeEntree(Etape... etapes){
        this.entree.ajouterSuccesseur(etapes);
    }

    public void aCommeSortie(Etape... etapes){
        for (Etape e : etapes){
            e.ajouterSuccesseur(sortie);
        }
    }

    public void ajouter(Etape... etapes){
        this.lesEtapes.ajouter(etapes);
    }

    public int nbEtapes(){
        return this.lesEtapes.nbEtapes();
    }

    public int nbGuichets(){
        int nbG = 0;
        for(Etape e : lesEtapes){
            if (e.estUnGuichet()){
                nbG++;
            }
        }
        return nbG;
    }

    public SasEntree getEntree() {
        return entree;
    }

    public SasSortie getSortie() {
        return sortie;
    }

    public GestionnaireEtapes getLesEtapes() {
        return lesEtapes;
    }




//    public String toC(){
//        StringBuilder sb = new StringBuilder();
//        sb.append("#include <stdlib.h>\n#include <stdio.h>\n#include <time.h>\n#include \"/tmp/twisk/def.h\n#include \"/tmp/twisk/lois.h\"\n#define sasEntree 0\n#define sasSortie 1\nvoid simulation(int ids){\nentrer(0);\n"); //int choice, choice2;
//        sb.append(this.entree.toC());
//        sb.append("}");
//        return sb.toString();
//    }

    public String toC() {
        StringBuilder sb = new StringBuilder();
        sb.append("#include <stdlib.h>\n");
        sb.append("#include <stdio.h>\n");
        sb.append("#include <time.h>\n");
        sb.append("#include \"/tmp/twisk/def.h\"\n");
        sb.append("#include \"/tmp/twisk/lois.h\"\n");
        sb.append("#define sasEntree 0\n");
        sb.append("#define sasSortie 1\n");
        sb.append("void simulation(int ids) {\n");
        sb.append("    entrer(sasEntree);\n");
        sb.append(this.entree.toC());
        sb.append("}\n");
        return sb.toString();
    }

    @Override
    public Iterator<Etape> iterator() {
        return this.lesEtapes.iterator();
    }

    public void setLoi(int loi){
        this.entree.setLoi(loi);
    }
}
