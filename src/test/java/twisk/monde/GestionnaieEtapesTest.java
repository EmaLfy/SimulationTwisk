package twisk.monde;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
class GestionnaireEtapesTest {

    @Test
    public void testAjouterEtapes() {
        GestionnaireEtapes gestionnaire = new GestionnaireEtapes();
        Etape etape1 = new Activite("Etape1");
        Etape etape2 = new Guichet("Etape2");

        gestionnaire.ajouter(etape1, etape2);

        assertEquals(2, gestionnaire.nbEtapes());
    }

    @Test
    public void testNbEtapes() {
        GestionnaireEtapes gestionnaire = new GestionnaireEtapes();
        assertEquals(0, gestionnaire.nbEtapes());

        Etape etape1 = new Activite("Etape1");
        gestionnaire.ajouter(etape1);
        assertEquals(1, gestionnaire.nbEtapes());
    }

    @Test
    public void testIterator() {
        GestionnaireEtapes gestionnaire = new GestionnaireEtapes();
        Etape etape1 = new Guichet("Etape1");
        Etape etape2 = new Activite("Etape2");

        gestionnaire.ajouter(etape1, etape2);

        int count = 0;
        for (Etape etape : gestionnaire) {
            count++;
        }

        assertEquals(2, count);
    }
}
