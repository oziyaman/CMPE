/*
	CMPE312 : Operating Systems
	Author: Furkan Karakoyunlu / 112200036
	week2
	Question#2
*/

#include <stdio.h>

void printIdentityMatrix(){
	int size;
	printf("Size of matrix: ");
	scanf("%d", &size);

	int i;
	int j;
	int position = 0;
	for(i = 0; i < size; i++){
		for(j = 0; j < size; j++){
			if(j == position){
				printf("1");
			}
			if(j != position){
				printf("0");
			}
		}
		position++;
		printf("\n");
	}
}


int main(void){
	printIdentityMatrix();
	return 0;
}