#include <math.h>
#include <stdlib.h>
#include <stdio.h>
#include <time.h>
#include <math.h>
#include <stdbool.h>
#include "lois.h"
#include <unistd.h>

#define M_PI 3.14159265358979323846


void delaiUniforme(int temps, int delta) {
    int bi, bs;
    int n, nbSec;

    bi = temps - delta;
    if (bi < 0) bi = 0;

    bs = temps + delta;
    n = bs - bi + 1;

    nbSec = (rand() / (float)RAND_MAX) * n;
    nbSec += bi;

    sleep(nbSec);
}

/* Fonction pour générer un délai selon une loi normale (gaussienne)*/
void delaiGauss(double moyenne, double ecartype) {
    double U1 = (double)rand() / RAND_MAX; /* Génération d'un nombre aléatoire uniforme entre 0 et 1*/
    double U2 = (double)rand() / RAND_MAX; /* Génération d'un autre nombre aléatoire uniforme entre 0 et 1*/

    /* Transformation de Box-Muller pour générer un délai selon une loi normale*/
    double X = moyenne + ecartype * sqrt(-2 * log(U1)) * cos(2 * M_PI * U2);
    sleep(X);
}

void delaiExponentiel(double lambda) {
    double U = (double)rand() / RAND_MAX;
    double X = -log(U) / lambda;
    sleep(X);
}
