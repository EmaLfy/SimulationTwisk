#include <stdlib.h>
#include <stdio.h>
#include "../ressources/codeC/def.h"

#define sasEntree 0
#define sasSortie 1
#define activite1 4
#define activite 2
#define guichet 3
#define num_sem_guichet 1

void simulation(int ids){
    entrer(sasEntree);
    delai(6,3);
    transfert(sasEntree, activite);
    delai(5,3);
    transfert(activite, guichet);

    P(ids, num_sem_guichet);
        transfert(guichet,activite1);
        delai(4,2);
    V(ids, num_sem_guichet);
    transfert(activite1, sasSortie);
}
