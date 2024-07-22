#include <stdlib.h>
#include <stdio.h>
#include "/tmp/twisk/def.h"

#define sasEntree 0
#define sasSortie 1
#define activite 3
#define guichet 2
#define num_sem_guichet 1

void simulation(int ids){
    entrer(sasEntree);
    delai(6,3);
    transfert(sasEntree, guichet);
    P(ids, num_sem_guichet);
        transfert(guichet,activite);
        delai(8,2);
    V(ids, num_sem_guichet);
    transfert(activite, sasSortie);
}

