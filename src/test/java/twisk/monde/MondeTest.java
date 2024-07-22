package twisk.monde;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MondeTest {

    @Test
    void aCommeEntree() {
        Monde m = new Monde();
        Activite a = new Activite("test");
        Activite a2 = new Activite("test2");
        m.ajouter(a, a2);
        m.aCommeEntree(a, a2);
        assertEquals(2, m.getEntree().getNbSucc());
    }

    @Test
    void aCommeSortie() {
        Monde m = new Monde();
        Activite a = new Activite("test");
        Activite a2 = new Activite("test2");
        m.ajouter(a, a2);
        m.aCommeSortie(a2, a);
        assertEquals(1, a.getNbSucc());
        assertEquals(1, a2.getNbSucc());
    }

    @Test
    void ajouter() {
        Monde m =new Monde();
        Activite a = new Activite("test");
        Activite a2 = new Activite("test2");
        m.ajouter(a, a2);
        assertEquals(4, m.nbEtapes());
    }


    @Test
    void nbGuichets() {
        Monde m =new Monde();
        Etape a = new Guichet("test");
        Etape a2 = new Guichet("test2");
        Activite a3 = new Activite("test2");
        m.ajouter(a, a2, a3);
        assertEquals(2, m.nbGuichets());

    }
}