/* 
Comp-306:Operating Systems (2010-Spring)
Week-5: Introduction to C Language
Author: Ceyhun Aytekin / IBU-Computer Science Department
Topics: Threads: Basic Implementation Example 
Hint: In order to compile the code includes thread use: <gcc -phread Example2.c> command
*/
#include <pthread.h>
#include <stdio.h>
#include <stdlib.h>
#define NUM_THREADS	8

char *messages[NUM_THREADS];

void *PrintHello(void *threadid){
   //sleep(1);
   printf("Thread %ld: %s\n",(long)threadid,messages[(long)threadid]);
   pthread_exit(NULL);
   
}

int main(void){
	pthread_t threads[NUM_THREADS];
	int rc;
	long t;

	messages[0] = "English: Hello World!";
	messages[1] = "French: Bonjour, le monde!";
	messages[2] = "Spanish: Hola al mundo";
	messages[3] = "Klingon: Nuq neH!";
	messages[4] = "German: Guten Tag, Welt!"; 
	messages[5] = "Russian: Zdravstvytye, mir!";
	messages[6] = "Japan: Sekai e konnichiwa!";
	messages[7] = "Turkish: Selam Dünya!";

	for(t=0;t<NUM_THREADS;t++) {
	  printf("Creating thread %ld\n", t);
	  rc = pthread_create(&threads[t], NULL, PrintHello, (void *)t);
	  if (rc) {
	    printf("ERROR; return code from pthread_create() is %d\n", rc);
	    exit(-1);
	    }
	  }

	pthread_exit(NULL);
}
