package twisk.monde;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class TestGuichet {
    @Test
    public void testConstructionAvecNbJetonsParDefaut() {
        Guichet guichet = new Guichet("Guichet1");
        assertEquals(3, guichet.getNbJetons());
    }

    @Test
    public void testConstructionAvecNbJetonsPersonnalise() {
        Guichet guichet = new Guichet("Guichet2", 5);
        assertEquals(5, guichet.getNbJetons());
    }

    @Test
    public void testEstUnGuichet() {
        Guichet guichet = new Guichet("Guichet3");
        assertTrue(guichet.estUnGuichet());
    }

    @Test
    public void testNumeroSemaphore() {
        Guichet guichet1 = new Guichet("Guichet1");
        Guichet guichet2 = new Guichet("Guichet2");

        assertEquals(1, guichet1.getIdSemaphore());
        assertEquals(2, guichet2.getIdSemaphore());
    }
}
