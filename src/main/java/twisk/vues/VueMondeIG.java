package twisk.vues;

import javafx.application.Platform;
import javafx.scene.layout.Pane;
import twisk.ecouteurs.EcouteurDnDMonde;
import twisk.ecouteurs.EcouteurDnDMondeFini;
import twisk.mondeIG.*;
import twisk.simulation.Client;
import twisk.simulation.GestionnaireClients;

import java.util.Iterator;

public class VueMondeIG extends Pane implements Observateur {
    private MondeIG m;
    private SimulationIG sim;


    public VueMondeIG(MondeIG m) {
        this.m = m;
        //this.sim = new SimulationIG(this.m);
        setOnDragOver(new EcouteurDnDMonde());
        setOnDragDropped(new EcouteurDnDMondeFini(this.m, this));
        this.setStyle("-fx-background-color: #e3e3e3; -fx-border-radius: 20px; -fx-background-radius: 20px;");
        for(EtapeIG e : m){
            VueEtapeIG vue = new VueActiviteIG(this.m, e);
            vue.relocate(e.getPosX(), e.getPosY());
            this.getChildren().add(vue);
        }

        reagir();
    }

    @Override
    public void reagir() {

        Pane pane = this;
        MondeIG mo = this.m;

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    pane.getChildren().clear();


                    for (Iterator<ArcIG> iter = m.iteratorArc(); iter.hasNext(); ) {
                        ArcIG s = iter.next();
                        VueArcIG vAIG = new VueArcIG(mo,s);
                        pane.getChildren().add(vAIG);
                    }
                    for(EtapeIG e : mo){
                        if (e.estGuichet()) {
                            e.resetClient();
                            VueEtapeIG vue = new VueGuichet(mo, e);
                            vue.relocate(e.getPosX(), e.getPosY());
                            pane.getChildren().add(vue);

                        }
                        else {
                            VueEtapeIG vue = new VueActiviteIG(mo, e);
                            vue.relocate(e.getPosX(), e.getPosY());
                            pane.getChildren().add(vue);

                        }
                        for (PointDeControleIG p : e){
                            VuePointDeControleIG vpdc = new VuePointDeControleIG(mo, p);
                            pane.getChildren().add(vpdc);
                        }
                    }
                    //System.out.println("Nombres clientsIG dans mondeIG : "+mo.getClients().size()+"________________________________________");
                    for (ClientIG c : mo.getClients()) {
                        //System.out.println("Client créé dans VueMondeIG");
                        VueClientIG vci = new VueClientIG(c,mo);
                        pane.getChildren().add(vci);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        if(Platform.isFxApplicationThread()){
            runnable.run();
        }else{
            Platform.runLater(runnable);

        }
    }
}
