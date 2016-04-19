 /* 
Comp-306:Operating Systems (2011-Spring)
Week-5: Introduction to C Language
Author: Ceyhun Aytekin / IBU-Computer Science Department
Topics: Threads: Basic Pthread Implementation Example
Hint: In order to compile the code includes thread use: gcc -phread Example1.c command 
*/
#include <pthread.h>
#include <stdio.h>
#include <stdlib.h>
#define NUM_THREADS	5

void *PrintHello(void *tid){
	sleep(1);
	printf("Hello World! It's me, thread #%d !\n", tid);
	pthread_exit(NULL);

}

int main(int argc, char *argv[]){
	pthread_t threads[NUM_THREADS];
	int rc,t;
	//int a=5;
	
	for(t=0;t<NUM_THREADS;t++){
  		printf("In main: creating thread %d \n", t);
		sleep(1);
  		rc = pthread_create(&threads[t], NULL, PrintHello, (void *)t);
  		
  		if (rc){
    			printf("ERROR; return code from pthread_create() is %d\n", rc);
    			exit(-1);
    		}
  	}
	pthread_exit(NULL);
}
