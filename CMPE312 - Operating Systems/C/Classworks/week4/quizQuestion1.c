/*
	CMPE312 : Operating Systems
	Author: Furkan Karakoyunlu / 112200036
	week4
	Quiz Question#1
*/

#include <stdio.h>

void horizontalLuckyNumberPyramid(){
	int luckyNumber;
	int rowNumber;

	printf("Enter your lucky number: ");
	scanf("%d", &luckyNumber);

	printf("Enter number of row: ");
	scanf("%d", &rowNumber);


	int i;
	int j;
	int k;
	int l;
	int countHead = 1;
	int countTail = rowNumber - 1;
	// Head for pyramid
	for(i = 0; i < rowNumber; i++){
		for(j = 0; j < countHead; j++){
			printf("%d", luckyNumber);
		}
		countHead++;
		printf("\n");
	}

	// Tail for pyramid
	for(k = 0; k < rowNumber - 1; k++){
		for(l = 0; l < countTail; l++){
			printf("%d", luckyNumber);
		}
		countTail--;
		printf("\n");
	}
}

int main(void){
	horizontalLuckyNumberPyramid();
	return 0;
}