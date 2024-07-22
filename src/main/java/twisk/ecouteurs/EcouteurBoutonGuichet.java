package twisk.ecouteurs;

import javafx.event.Event;
import javafx.event.EventHandler;
import twisk.mondeIG.MondeIG;

public class EcouteurBoutonGuichet implements EventHandler<Event> {

    private MondeIG mondeIG;
    private int noE;

    public EcouteurBoutonGuichet(MondeIG m) {
        this.mondeIG = m;
        this.noE = 0;
    }

    @Override
    public void handle(Event event) {
        this.noE++;
        this.mondeIG.ajouterGuichet("Guichet " + this.noE);
    }
}
