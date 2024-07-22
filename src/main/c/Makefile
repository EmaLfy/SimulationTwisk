CC=gcc
CFLAGSCLI=-Wall -fPIC -c
CFLAGSLIB=-shared
CFLAGSMAIN=-Wall -L.

all: client libTwisk main

client:
	$(CC) $(CFLAGSCLI) client.c

client2:
	$(CC) $(CFLAGSCLI) client2.c

libTwisk:
	$(CC) $(CFLAGSLIB) ../ressources/codeC/programmeC.o client.o -o libTwisk.so
	export LD_LIBRARY_PATH=$$LD_LIBRARY_PATH:.

lib2:
	$(CC) $(CFLAGSLIB) ../ressources/codeC/programmeC.o client2.o -o libTwisk.so
	export LD_LIBRARY_PATH=$$LD_LIBRARY_PATH:.

main:
	$(CC) $(CFLAGSMAIN) main.c -o main -lTwisk

clean:
	rm -f client client.o libTwisk.so main
