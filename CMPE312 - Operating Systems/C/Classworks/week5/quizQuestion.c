/*
	Furkan Karakoyunlu
	112200036
	Quiz#1
*/

#include <stdio.h>
#define SIZE 50

void vovelFinder(){
	char pattern[SIZE];
	char *p;
	
	p = pattern;

	char vovels[5] = {'a', 'e', 'i', 'o', 'u'};
	char *v;

	v = vovels;

	printf("Enter a word: ");
	scanf("%s", pattern);

	int i;
	int j;
	int count = 0;

	for(i = 0; pattern[i] != '\0'; i++){
		for(j = 0; j < 5; j++){
			if(*(p + i) == *(v + j)){
				count++;
				printf("%c in the address %p\n", *(v + j), &vovels[j]);
				pattern[i] = '*';
			}
		}
	}

	printf("New pattern is: %s\n", pattern);
	printf("Number of vowels: %d\n", count);


}

int main(void){
	vovelFinder();
	return 0;
}