package twisk.ecouteurs;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import twisk.mondeIG.MondeIG;

public class EcouteurSupprimer implements EventHandler<ActionEvent> {
    private MondeIG mondeIG;

    public EcouteurSupprimer(MondeIG mondeIG) {
        this.mondeIG = mondeIG;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        mondeIG.suppEtapSelect();
        mondeIG.supprimerArc();
    }
}
