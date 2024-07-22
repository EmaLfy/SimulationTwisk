package twisk;

import twisk.monde.*;
import twisk.outils.ClassLoaderPerso;
import twisk.outils.FabriqueNumero;
import twisk.simulation.Simulation;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ClientTwisk {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Monde m = new Monde();
        ClassLoaderPerso cLP = new ClassLoaderPerso(ClientTwisk.class.getClassLoader());
        Class<?> cs = cLP.loadClass("twisk.simulation.Simulation");
        Constructor cons = cs.getConstructor();
        Object s =  cons.newInstance();
        Method setNbCli = cs.getMethod("setNbClients", int.class);
        Method simuler = cs.getMethod("simuler", Monde.class);
        Etape e1 = new Activite("Act1");
        Etape e2 = new Activite("Act2");
        Etape e3 = new Activite("Act3");
        //Etape e4 = new Activite("Act3Bis");
        Etape e4 = new Guichet("Billet");
        Etape e5 = new ActiviteRestreinte("Toboggan");

        m.ajouter(e1, e2, e3, e5, e4);
        m.aCommeEntree(e1);
        m.aCommeSortie(e5);

        e1.ajouterSuccesseur(e2);
        e2.ajouterSuccesseur(e3);
        e3.ajouterSuccesseur(e4);
        e4.ajouterSuccesseur(e5);

        System.out.println("Monde 1");
        setNbCli.invoke(s, 5);
        simuler.invoke(s, m);

        System.gc();
        FabriqueNumero.getInstance().reset();
        Monde m2 = new Monde();

        Etape e6 = new Activite("SSSSS");
        Etape e7 = new Guichet("gneugneu");
        Etape e8 = new ActiviteRestreinte("zebbi");
        Etape e9 = new Activite("dssd");
        Etape e10 = new Activite("fez");
        Etape e11 = new Activite("fsdfsd");
        e6.ajouterSuccesseur(e7);
        e7.ajouterSuccesseur(e8);
        e8.ajouterSuccesseur(e9);
        e9.ajouterSuccesseur(e10);
        e10.ajouterSuccesseur(e11);

        m2.ajouter(e6, e7, e8, e9, e10, e11);
        m2.aCommeEntree(e6);
        m2.aCommeSortie(e11);



        ClassLoaderPerso cLP2 = new ClassLoaderPerso(ClientTwisk.class.getClassLoader());
        Class<?> cs2 = cLP2.loadClass("twisk.simulation.Simulation");
        Constructor cons2 = cs2.getConstructor();
        Object s2 =  cons2.newInstance();
        Method setNbCli2 = cs2.getMethod("setNbClients", int.class);
        Method simuler2 = cs2.getMethod("simuler", Monde.class);

        System.out.println("Monde 2");

        setNbCli2.invoke(s2, 10);
        simuler2.invoke(s2, m2);
    }
}
