package twisk.outils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class KitC {
    public KitC(){
        creerEnvironnement();
    }
    //Créée repertoire twisk sous /tmp/ et recopie programmeC.o et def.h
    public void creerEnvironnement(){
        Path directory = Paths.get("/tmp/twisk");
        try {
            // Création du répertoire twisk sous /tmp.
            // Ne déclenche pas d’erreur si le répertoire existe déjà
            Files.createDirectories(directory);
            // copie des fichiers programmeC.o et def.h sous /tmp/twisk
            String[] liste = {"programmeC.o", "def.h","lois.c", "lois.h"};
            for (String nom : liste) {
                InputStream src = getClass().getResourceAsStream("/codeC/" + nom);
                Path dest = directory.resolve(nom);
                Files.copy(src, dest, StandardCopyOption.REPLACE_EXISTING);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void creerCodeNatif(){
        ProcessBuilder pb2 = new ProcessBuilder("gcc", "-std=c99", "-Wall", "-ansi", "-pedantic", "-fPIC", "-I", "/usr/lib/jvm/java-21-openjdk-amd64/include/", "-I", "/usr/lib/jvm/java-21-openjdk-amd64/include/linux/", "-c", "./src/main/ressources/codeC/codeNatif.c", "-o", "./src/main/ressources/codeC/codeNatif.o");

        try {
            pb2.start().waitFor();
        } catch (InterruptedException | IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void copieCodeNatif(){
        Path directory = Paths.get("/tmp/twisk");
        InputStream src = getClass().getResourceAsStream("/codeC/codeNatif.o");
        Path dest = directory.resolve("codeNatif.o");
        try {
            Files.copy(src, dest, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
//        ProcessBuilder pb = new ProcessBuilder("cp", "./src/main/ressources/codeC/codeNatif.o", "/tmp/twisk");
//        try {
//            pb.inheritIO().start().waitFor();
//        } catch (InterruptedException | IOException e) {
//            throw new RuntimeException(e);
//        }
    }
    public void creerFichier(String codeC) {
        String filePath = "/tmp/twisk/client.c"; // Chemin du fichier client.c

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(codeC); // Écrire le code C dans le fichier
        } catch (IOException e) {
            e.printStackTrace(); // Gérer les exceptions IO
        }
    }

    public void compiler(){

        //ProcessBuilder pb2 = new ProcessBuilder("gcc", "-Wall", "-ansi", "-pedantic", "-fPIC", "-I", "/usr/lib/jvm/java-21-openjdk-amd64/include/", "-I", "/usr/lib/jvm/java-21-openjdk-amd64/include/linux/", "-c", "./src/main/ressources/codeC/codeNatif.c");
        ProcessBuilder pb = new ProcessBuilder("gcc", "-std=c99", "-Wall", "-ansi", "-pedantic", "-fPIC", "-c", "/tmp/twisk/client.c", "-o", "/tmp/twisk/client.o");
        try {
            pb.start().waitFor();
        }
        catch (IOException | InterruptedException e){
            e.printStackTrace();
        }
        ProcessBuilder pb2 = new ProcessBuilder("gcc", "-std=c99", "-Wall", "-ansi", "-pedantic", "-fPIC",
                "-I", "/usr/lib/jvm/java-21-openjdk/include/",
                "-I", "/usr/lib/jvm/java-21-openjdk/include/linux/",
                "-c", "/tmp/twisk/lois.c", "-o", "/tmp/twisk/lois.o", "-lm");
        //ProcessBuilder pb2 = new ProcessBuilder("gcc -o /tmp/twisk/lois.c /tmp/twisk/lois.o -lm");
        try {
            pb2.start().waitFor();
//            Process process = pb2.start();
//            process.waitFor();
//            if (process.exitValue() != 0) {
//                System.err.println("Error compiling lois.c");
//                printProcessErrors(process);
//            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void construireLaBibliotheque(){
        String path = "/tmp/twisk/libTwisk"+ FabriqueNumero.getInstance().getCptLib() +".so";
        ProcessBuilder pb = new ProcessBuilder("gcc", "-shared", "/tmp/twisk/programmeC.o", "/tmp/twisk/codeNatif.o", "/tmp/twisk/lois.o", "/tmp/twisk/client.o", "-o", path);
        try {
            pb.start().waitFor();
        }
        catch (IOException | InterruptedException e){
            e.printStackTrace();
        }
    }

    public void killThreads(int pid){
        ProcessBuilder pb = new ProcessBuilder("kill", "-9", String.valueOf(pid));
        try {
            pb.start().waitFor();
        }
        catch (InterruptedException | IOException e){
            e.printStackTrace();
        }
    }

    private void printProcessErrors(Process process) throws IOException {
        try (var reader = process.getErrorStream()) {
            reader.transferTo(System.err);
        }
    }
}
