/*
	CMPE312 : Operating Systems
	Author: Furkan Karakoyunlu / 112200036
	week3
	Question#1
*/

#include <stdio.h>

void patternFinder(){
	char letter[1];
	char pattern[20];
	char *p;
	p = pattern;

	printf("Enter a letter: ");
	scanf("%s", letter);

	printf("Enter a pattern: ");
	scanf("%s", pattern);

	int counter = 0;
	int i = 0;
	while(*(p+i) != '\0'){
		if(*(p+i) == letter[0]){
			printf("Letter < %c > is found in pattern '%s'\n", letter[0], p);
			printf(" in address: %p\n", p);
			printf(" index: %d\n", i);
			counter++;
		}
		i++;
	}
	if(counter == 0){
		printf("There is no < %c > in '%s'\n", letter[0], p);
	}
	
}

int main(void){
	patternFinder();
	return 0;
}