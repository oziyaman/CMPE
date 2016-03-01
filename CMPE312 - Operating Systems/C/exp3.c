/*
Comp-306:Operating Systems (2013-Spring)
Week-1: Introduction to C Language
Author: Ceyhun Aytekin / IBU-Computer Science Department
Topics: Loops (for / while)
*/

#include<stdio.h>

void myLoop1(){
  int i;
  for (i = 0; i < 10; i++)
  {
    printf ("Hello\n");
    printf ("World\n");
  }
}

void myLoop2(){
  int counter, howmuch;
  
  scanf("%d", &howmuch);
  counter = 0;
  while ( counter < howmuch)
  {
    counter++;
    printf("%d\n", counter);
  }
  
}

int main(void){
  
  //myLoop1();
  myLoop2();
  return 0;
}