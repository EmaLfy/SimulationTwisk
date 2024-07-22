#ifndef LOIS_H
#define LOIS_H

#include <time.h>
#include <math.h>
#include <stdbool.h>
#include <unistd.h>


void delaiUniforme(int temps, int delta);

void delaiGauss(double moyenne, double ecartype);

void delaiExponentiel(double lambda);

#endif