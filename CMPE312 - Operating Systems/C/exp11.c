/* 	
Comp-306:Operating Systems (2015-Spring)
Week-5: Introduction to C Language
Author: Ceyhun Aytekin / IBU-Computer Science Department
Topics: Pointers: Character Strings
*/

#include <stdio.h>

int main(void){
	


  char woman[]="I love Cats.";
  char *man="I love Dogs.";
  int i;
    
  for(i=0;i<6;i++)
    putchar(woman[i]);
  putchar('\n');
  
  for(i=0;i<6;i++)
    putchar(man[i]);
  putchar('\n');
  
  for(i=0;i<6;i++)
    putchar(*(woman+i));
  putchar('\n');
  
  for(i=0;i<6;i++)
    putchar(*(man+i));
  putchar('\n');
  
  while(*(man) != '\0')
    putchar(*(man++));    // only pointer version can use increment operator.
  putchar('\n');  
  return 0;

}