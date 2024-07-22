package twisk.simulation;

import org.junit.jupiter.api.Test;
import twisk.monde.Activite;
import twisk.monde.Etape;

import static org.junit.jupiter.api.Assertions.*;

class GestionnaireClientsTest {

    @Test
    void setClients() {
        GestionnaireClients iencli=new GestionnaireClients();
        iencli.setClients(5);
        assert(iencli.nbClients()==1):"Bug pour GestionnaireClients.setClients() avec un seul client";
        GestionnaireClients iencli2=new GestionnaireClients();
        iencli2.setClients(5,8,3);
        assert(iencli2.nbClients()==3):"Bug pour GestionnaireClients.setClients() avec un plusieurs clients";
    }

    @Test
    void nettoyer() {
        GestionnaireClients iencli2=new GestionnaireClients();
        iencli2.setClients(5,8,3);
        iencli2.nettoyer();
        assert(iencli2.nbClients()==0):"Bug pour GestionnaireClients.nettoyer() avec un plusieurs clients";
    }
}