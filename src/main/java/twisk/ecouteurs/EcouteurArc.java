package twisk.ecouteurs;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import twisk.mondeIG.ArcIG;
import twisk.mondeIG.MondeIG;

public class EcouteurArc implements EventHandler<MouseEvent> {
    private MondeIG m;
    private ArcIG arcIG;

    public EcouteurArc(MondeIG m, ArcIG arcIG) {
        this.m = m;
        this.arcIG = arcIG;
    }

    @Override
    public void handle(MouseEvent mouseEvent) {
        this.arcIG.setEstSelectionne(!this.arcIG.isEstSelectionne());
        this.m.notifierObservateurs();

    }
}
