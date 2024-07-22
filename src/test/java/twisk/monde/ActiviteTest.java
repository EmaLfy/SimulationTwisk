package twisk.monde;

import org.junit.jupiter.api.Test;
import twisk.outils.FabriqueNumero;

import static org.junit.jupiter.api.Assertions.*;

class ActiviteTest {


    @Test
    void constActivite() {
        FabriqueNumero.getInstance().reset();
        Activite a = new Activite("test");
        assertTrue(a.getNom().equals("test"));
        assertEquals(5, a.getEcartTemps());
        assertEquals(10, a.getTemps());
        Activite a2 = new Activite("test2", 3, 2);
        assertTrue(a2.getNom().equals("test2"));
        assertEquals(2, a2.getEcartTemps());
        assertEquals(3, a2.getTemps());
        //assertEquals(0, a.getIdEtape());
        assertEquals(1, a2.getIdEtape());

    }

    @Test
    void estUneActivite() {
        Activite a = new Activite("zbeul");
        assertTrue(a.estUneActivite());
        Etape e= new Guichet("zbeul2");
        assertFalse(e.estUneActivite());
    }

    @Test
    void ajouterSuccesseur() {
        Etape a = new Activite("Act1");
        Etape a1 = new Activite("Act2");
        Etape a2 = new Activite("Act3");
        a.ajouterSuccesseur(a1, a2);
        assertEquals(2, a.getNbSucc());
    }
}