package twisk.outils;

import org.junit.jupiter.api.Test;
import twisk.outils.KitC;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class KitCTest {

    @Test
    public void testCreerFichier() throws IOException {
        // Créer une instance de KitC
        KitC kitC = new KitC();

        // Appeler la méthode creerFichier avec un code C factice
        String codeC = "#include <stdio.h>\nint main() { printf(\"Hello, World!\"); return 0; }";
        kitC.creerFichier(codeC);

    }
}
