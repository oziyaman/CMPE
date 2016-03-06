#include <stdio.h>

void harfs(char*);

int main(){
		
	char the_sentence[100];	
	
	scanf("%s", the_sentence);
	harfs(the_sentence);
	return 0;
}

void harfs(char *sentence){
	int i;
	i=0;
	while(sentence[i] != '\0'){
		if(sentence[i] == 'e')
			sentence[i] = 'a';	
	i++;
	}
	
	printf("%s", sentence);
}