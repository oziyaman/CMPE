/* 
Comp-306:Operating Systems (2013-Spring)
Week-1: Introduction to C Language
Author: Ceyhun Aytekin / IBU-Computer Science Department
Topics: scanf() - The Relational Operators - The Logical Operators - Conditional Statement
*/

#include <stdio.h>

void scanFunction(){
  char name[10];
  char surname[10];
  float gpa;
  int id;

  printf("Name: ");
  scanf("%s",name);

  printf("Surname: ");
  scanf("%s",surname);
  
  printf("School id: ");
  scanf("%d", &id);

  printf("GPA: ");
  scanf("%f", &gpa);
  
  printf("Student: %s, %s, %d, %.3f\n",name,surname,id,gpa);
 
}

void relOptFunction(int a,int c){
  if(a==c)
    printf( "%d is equal %d\n",a,c);
  else if(a>c)
    printf( "%d is bigger than %d\n",a,c);
  else
    printf( "%d is smaller than %d.\n",a,c);
}

void logOptFunction(int a,int b,int c){
    if((a>b)&&(a>c))
      printf( "%d is the biggest one!\n",a);
    else if((a<b)||(a<c))
      printf( "%d is not the biggest one!\n",a); 
    else 
      printf("%d is not unique!\n",a);
}

int main(void){
 
  //scanFunction();
  //relOptFunction(0,2);
  logOptFunction(1,3,0);
  return 0;
}