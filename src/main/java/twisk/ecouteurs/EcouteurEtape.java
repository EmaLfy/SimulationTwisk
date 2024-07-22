package twisk.ecouteurs;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import twisk.mondeIG.EtapeIG;
import twisk.mondeIG.MondeIG;

public class EcouteurEtape implements EventHandler<MouseEvent> {

    private MondeIG m;

    private EtapeIG etape;

    public EcouteurEtape(MondeIG m, EtapeIG etapeIG) {
        this.m = m;
        this.etape = etapeIG;
    }

    @Override
    public void handle(MouseEvent mouseEvent) {

        this.m.etpSelect(etape.getIdentifiant());
    }
}
