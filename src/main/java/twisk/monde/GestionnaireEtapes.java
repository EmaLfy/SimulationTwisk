package twisk.monde;

import twisk.monde.Etape;

import java.util.ArrayList;
import java.util.Iterator;

public class GestionnaireEtapes implements Iterable<Etape> {
    private ArrayList<Etape> all_etapes;
    public GestionnaireEtapes(){
        this.all_etapes=new ArrayList<Etape>(15); // On initialise une Arraylist de 15 éléments pour commencer (molo Arnaud)
    }
    public void ajouter(Etape... etapes){
        for (Etape etape : etapes) {
            this.all_etapes.add(etape);// On ajoute chaque etapes données en paramètre dans this.all_etapes
        }
    }
    public int nbEtapes(){
        return this.all_etapes.size();
    }

    public Etape getEtapeSucc(){
        return this.all_etapes.get(0);
    }

    public Iterator<Etape> iterator(){
        return this.all_etapes.iterator(); // renvoie l'itérateur des etapes
    }
}
