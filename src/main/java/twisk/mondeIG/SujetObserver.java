package twisk.mondeIG;

import twisk.vues.Observateur;

import java.util.ArrayList;

public class SujetObserver {
    private ArrayList<Observateur> obs = new ArrayList<>();
    public void ajouterObservateur(Observateur v){
        this.obs.add(v);
    }
    public void notifierObservateurs(){
        for(Observateur o : obs){
            o.reagir();
        }
    }
}
