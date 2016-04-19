/*
	Furkan Karakoyunlu
	112200036

	THREADS
	-------
	Producer Consumer Problem
*/

#include <stdio.h>
#include <pthread.h>
#define BUFFER_SIZE 10
#define THREAD_COUNT 3

int in = 0;
int out = 0;
int counter = 0;
int buffer[BUFFER_SIZE];
pthread_mutex_t mutex;
pthread_t threads[THREAD_COUNT];


void *producer(){
	
	// c treats all non zero expressions as booleans
	// so while(1) equals while(true)
	while(1){
		//sleep(1);
		// locking the mutex
		pthread_mutex_lock(&mutex);
		while(counter == BUFFER_SIZE) //do nothing
		// unlocking the mutex
		pthread_mutex_unlock(&mutex);

		buffer[in] = 999;
		in = (in + 1) % BUFFER_SIZE;
		
		// locking the mutex
		pthread_mutex_lock(&mutex);
		
		counter++;
		printf("# Producer: counter=%d, in=%d, buffer[%d]=%d\n", counter,in, in, buffer[in]);
		
		// unlocking the mutex
		pthread_mutex_unlock(&mutex);
	}
	
}

void *consumer(){
	
	while(1){
		//sleep(1);

		// locking the mutex
		pthread_mutex_lock(&mutex);
		while(counter == 0) // do nothing
		// unlocking the mutex
		pthread_mutex_unlock(&mutex);

		// consume the item
		buffer[out] = 0;
		out = (out + 1) % BUFFER_SIZE;

		// locking the mutex
		pthread_mutex_lock(&mutex);

		counter--;
		printf("# Consumer: counter=%d, out=%d, buffer[%d]=%d\n", counter, out, out, buffer[out]);
		
		// unlocking the mutex
		pthread_mutex_unlock(&mutex);
	}
}

int main(void){

	// initializing the mutex
	pthread_mutex_init(&mutex, NULL);
	
	// pthread_create();
	/* pthread_create usage:
	int pthread_create(pthread_t *thread, const pthread_attr_t *attr,
						void *(*start_routine) (void *), void *arg);
	*/
	pthread_create(&threads[0], NULL, producer, NULL);
	pthread_create(&threads[1], NULL, consumer, NULL);
	pthread_create(&threads[2], NULL, producer, NULL);

	// destroying the mutex
	pthread_mutex_destroy(&mutex);

	// type pthread_exit(NULL); to make sure
	// that the program wont closed untill
	// all threads finished
	pthread_exit(NULL);

	return 0;
}
