package twisk.ecouteurs;

import javafx.event.Event;
import javafx.event.EventHandler;
import twisk.mondeIG.MondeIG;

public class EcouteurBouton implements EventHandler<Event> {

    private MondeIG m;
    private int noE;

    public EcouteurBouton(MondeIG m) {
        this.m = m;
    }

    @Override
    public void handle(Event event) {
        noE++;
        m.ajouter("Etape "+noE);

    }
}
