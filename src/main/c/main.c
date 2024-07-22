#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include "/tmp/twisk/def.h"

int afficher_proc(int* liste, int nbEtapes, int nbClients){
    int out=0;
    for(int j=0; j<nbEtapes;j++){
        printf("\n");

        if(liste[j*(nbClients+1)]>0){
            if(j==0){
                printf("Etape %d (entrée) %d clients : ",j, liste[j*(nbClients+1)]);
            }else if(j==1){
                if(liste[j*(nbClients+1)]==nbClients){
                    out=1; // si tt le monde est dans le sas de sortie
                }
                printf("Etape %d (sortie) %d clients : ",j, liste[j*(nbClients+1)]);
            }else if(j==2){
                printf("Etape %d (activité) %d clients : ",j, liste[j*(nbClients+1)]);
            }else if(j==3){
                printf("Etape %d (guichet) %d clients : ",j, liste[j*(nbClients+1)]);
            }else{
                printf("Etape %d : %d clients : ",j, liste[j*(nbClients+1)]);
            }

            for(int i=1; i<=liste[j*(nbClients+1)];i++){
                if(i==liste[j*(nbClients+1)]){
                    printf("%d", liste[(j*(nbClients+1))+i]); // affichage joli au dernier élément( sans le `,`)
                }else{
                    printf("%d , ", liste[(j*(nbClients+1))+i]);
                }
            }
        }else{
            if(j==0){
                printf("Etape %d (entrée) %d client : ",j, liste[j*(nbClients+1)]);
            }else if(j==1){
                printf("Etape %d (sortie) %d client : ",j, liste[j*(nbClients+1)]);
            }else if(j==2){
                printf("Etape %d (activité) %d client : ",j, liste[j*(nbClients+1)]);
            }else if(j==3){
                printf("Etape %d (guichet) %d client : ",j, liste[j*(nbClients+1)]);
            }else{
                printf("Etape %d : %d clients : ",j, liste[j*(nbClients+1)]);
            }
        }
    }
    printf("\n");
    return out;
}

int main(int argc,  char** argv){
    int nbClients = 6;
    int nbEtapes = 5;
    int nbGuichets = 1;
    int tabJetons[] = {1};
    int* tabProc;


    tabProc = start_simulation(nbEtapes, nbGuichets, nbClients, tabJetons);
    printf("Les clients :");
    for(int i = 0; i<6; i++){
        printf("%d, ", tabProc[i]);
    }
    int* where=ou_sont_les_clients(nbEtapes,nbClients);
    while(!afficher_proc(where, nbEtapes, nbClients)){
        afficher_proc(where, nbEtapes, nbClients);
        sleep(2); // on attends
        where=ou_sont_les_clients(nbEtapes,nbClients); // On met à jour la liste des emplacements des clients
    }
    //afficher_proc(where, nbEtapes, nbClients);

    nettoyage();
    return 0;
}

