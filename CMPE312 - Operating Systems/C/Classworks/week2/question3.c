/*
	CMPE312 : Operating Systems
	Author: Furkan Karakoyunlu / 112200036
	week2
	Question#3
*/

#include <stdio.h>

void changeCharArray(){
	char c[100];
	printf("Give me a string: ");
	scanf("%c", c);

	int i;
	i = 0;
	while(c[i] != '\0'){
		if(c[i] == 'e'){
			c[i] = 'a';
		}
		i++;
	}
	printf("%s\n", c);
}

int main(){
	changeCharArray();
	return 0;
}
