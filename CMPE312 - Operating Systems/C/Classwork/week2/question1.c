/*
	CMPE312 : Operating Systems
	Author: Furkan Karakoyunlu / 112200036
	week2
	Question#1
*/

#include <stdio.h>
#define SIZE 10

void compareArr(int a[], int b[]){
	int i;
	int j;
	int count = 0;
	int catch = 0;
	for(i = 0; i < SIZE; i++){
		for(j = 0; j < SIZE; j++){
			if(a[i] == b[j]){
				catch++;
			}
		}
		if(catch == 0){
			count++;
		}
		// resetting the counter for every element
		catch = 0;
	}
	printf("Number of Different Elements: %d\n", count);
}


int main(void) {
	int arr1[SIZE] = {1,3,5,7,9,10,20,22,12,45};
	int arr2[SIZE] = {3,5,1,7,10,21,22,15,35,0};

	compareArr(arr1, arr2);

	return 0;
}