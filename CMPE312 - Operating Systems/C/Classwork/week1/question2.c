/*
	CMPE312 : Operating Systems
	Author: Furkan Karakoyunlu / 112200036
	Question#2
*/

#include <stdio.h>

void takeFactorial(){
	int n;
	printf("Give me a number:");
	scanf("%d", &n);

	int result = 1;
	int i;
	for(i = 1; i <= n; i++){
		result *= i;
	}

	printf("%d factorial is: %d\n", n, result);
}

int main(void){
	takeFactorial();
	return 0;
}