/*
	CMPE312 : Operating Systems
	Author: Furkan Karakoyunlu / 112200036
	week3
	Question#1
*/

#include <stdio.h>

void patternFinder(char letter, char[] pattern){
	char letter[1];
	char pattern[100];
	int *p;
	p = pattern;

	printf("Enter a letter: ");
	scanf("%s", &letter);

	printf("You entered: %c\n", letter);

	printf("Enter a pattern: ");
	scanf("%s", pattern);
	
	printf("You entered: \n", pattern);
}

int main(void){
	patternFinder();
	return 0;
}