package twisk.ecouteurs;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import twisk.mondeIG.MondeIG;

public class EcouteurGauss implements EventHandler<ActionEvent> {
    MondeIG monde;
    public EcouteurGauss(MondeIG m) {
        monde = m;
    }
    @Override
    public void handle(ActionEvent actionEvent) {
        monde.setLoi(1);
    }
}
