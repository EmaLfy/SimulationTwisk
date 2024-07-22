package twisk.simulation;

import twisk.monde.Etape;

import java.util.ArrayList;
import java.util.Iterator;

public class GestionnaireClients implements Iterable<Client> {
    ArrayList<Client> clients;
    public GestionnaireClients(){this.clients=new ArrayList<>(20);} // On initialise une liste à 20 parce que pourquoi pas

    // On ajoute un iencli
    public void setClients(int ... tabClients){
        Client client;
        for(int r:tabClients){
            client=new Client(r);
            this.clients.add(client);
        }
    }
    // Met à jours attribut etape et rang d'un client
     public void allerA(int numeroClient, Etape etape, int rang){
        for(Client client:this.clients){
            if(client.getNumeroClient()==numeroClient){
                client.allerA(etape,rang);
            }
        }
     }

     // On balaie devant notre porte ici
    public void nettoyer(){
        this.clients.clear();
    }

    // On retourne un itérateur sur la liste de clients
    public Iterator<Client> iterator(){return this.clients.iterator();}

    // On retourne le nombre de clients
    public int nbClients(){
        return this.clients.size();
    }
}
