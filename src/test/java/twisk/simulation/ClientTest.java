package twisk.simulation;

import org.junit.jupiter.api.Test;
import twisk.monde.Activite;
import twisk.monde.Etape;

import static org.junit.jupiter.api.Assertions.*;

class ClientTest {

    @Test
    void allerA() {
        Etape etape=new Activite("Activité", 6,2);
        Etape etape2=new Activite("Blabla", 9,3);
        Client c=new Client(1);
        c.allerA(etape,3);
        assert((c.getEtape().getNom()== etape.getNom()) && (c.getEtape().getIdEtape()==etape.getIdEtape())):"Bug Client.AllerA() lors de la premiere activité";
        c.allerA(etape2,8);
        assert((c.getEtape().getNom()== etape2.getNom()) && (c.getEtape().getIdEtape()==etape2.getIdEtape())):"Bug Client.AllerA() lors de la deuxieme activité";
    }
}