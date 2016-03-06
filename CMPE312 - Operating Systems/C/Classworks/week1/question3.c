/*
	CMPE312 : Operating Systems
	Author: Furkan Karakoyunlu / 112200036
	Question#2
*/

#include <stdio.h>

void makePattern(){
	int count;
	printf("Give me a number: ");
	scanf("%d", &count);

	int i;
	int j;

	int seperator = count;

	for(i = 0; i < count; i++){
		for (j = 0; j < count + 1; j++){
			if(j == seperator){
				printf(" ");
				seperator--;
			}
			printf("*");
		}
		printf("\n");
	}

}

int main(void){
	makePattern();
	return 0;
}