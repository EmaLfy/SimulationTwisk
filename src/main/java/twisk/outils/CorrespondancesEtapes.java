package twisk.outils;

import twisk.monde.Etape;
import twisk.mondeIG.EtapeIG;

import java.util.HashMap;

public class CorrespondancesEtapes {
    private HashMap<EtapeIG, Etape> correspondances;

    public CorrespondancesEtapes(){
        this.correspondances = new HashMap<>();
    }

    public void ajouter(EtapeIG etapeIG, Etape etape){
        this.correspondances.put(etapeIG, etape);
    }

    public Etape getEtape(EtapeIG etapeIG){
        return this.correspondances.get(etapeIG);
    }
    public EtapeIG getEtapeIG(Etape etape){
        for(EtapeIG etapeIG : correspondances.keySet()){
            if(correspondances.get(etapeIG).equals(etape)){
                return etapeIG;
            }
        }
        return null;
    }
}
