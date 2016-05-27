/*
	Problem:
		THE DINING PHILOSOPHERS PROBLEM
		Arbitrator(Conductor) Solution

	Author:
		+--------------------+
		| Furkan KARAKOYUNLU |
		|     112200036      |
		+--------------------+
*/

#include <pthread.h>
#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h> /* need it for booleans */
#define PHIL_COUNT 5

typedef struct {
	pthread_mutex_t *fork_left, *fork_right;
	pthread_t thread;
	int position;
} Philosopher;

typedef struct {
	pthread_t thread;
} Waiter;

void *waiterWork(void *params);
void think(int p);
void eat(int p);

/* checking the availability of eating action */
bool isAvailable = 1;

void *waiterWork(void *p){
	Waiter *wtr = (Waiter*)p;
	pthread_mutex_t *fork_right, *fork_left;

	/* If no one is eating */
	if(isAvailable){
		int i;
		Philosopher *phil = (Philosopher*)p;
		for(i = 0; i < PHIL_COUNT; i++){			
			fork_left = phil->fork_left;
			fork_right = phil->fork_right;

			think(phil->position);

			/* Locking forks for one philosopher */
			pthread_mutex_lock(fork_right);
			pthread_mutex_lock(fork_left);
			/* Eating action*/
			eat(phil->position);
			/* Unlocking the forks for other philosophers*/
			pthread_mutex_unlock(fork_right);
			pthread_mutex_unlock(fork_left);

			isAvailable = 0;
		}
		think(phil->position);
		pthread_exit(NULL);
	}
}

/* Thinking process */
void think(int p){
	printf("Philosopher%d is thinking..\n", p);
}

/* Eating process */
void eat(int p){
	printf("Philosopher%d is eating..\n", p);
	isAvailable = 1;
}

int main(){
	/* creating philosophers array */
	pthread_t philosophers[PHIL_COUNT];

	int i;
	for(i = 0; i< PHIL_COUNT; i++){
		Philosopher *phil = malloc(sizeof(Philosopher));
		phil->position = i;

		/* creating threads */
		pthread_create(&philosophers[i], NULL, waiterWork, (void *)phil);
	}

	pthread_exit(NULL);
	return 0;
}