package twisk.ecouteurs;

import javafx.event.EventHandler;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import twisk.mondeIG.MondeIG;
import twisk.vues.VueEtapeIG;
import twisk.vues.VueMondeIG;

public class EcouteurDnDMondeFini implements EventHandler<DragEvent> {

    private MondeIG mondeIG;
    private VueMondeIG vueMondeIG;

    public EcouteurDnDMondeFini(MondeIG mondeIG, VueMondeIG vueMondeIG) {
        this.mondeIG = mondeIG;
        this.vueMondeIG = vueMondeIG;
    }

    @Override
    public void handle(DragEvent dragEvent) {
        Dragboard dragboard = dragEvent.getDragboard();
        if (dragboard.hasString()) {
            String nodeID = dragboard.getString();
            VueEtapeIG vueEtape = (VueEtapeIG) vueMondeIG.lookup("#" + nodeID);
            vueEtape.getEtapeIG().setPosX((int) dragEvent.getX());
            vueEtape.getEtapeIG().setPosY((int) dragEvent.getY());
            vueEtape.getEtapeIG().reinitPdc();
            vueEtape.relocate(vueEtape.getEtapeIG().getPosX(), vueEtape.getEtapeIG().getPosY());
        }
        dragEvent.consume();
        this.mondeIG.notifierObservateurs();
    }
}
