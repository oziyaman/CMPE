/* 
Comp-306:Operating Systems (2015-Spring)
Week-5: Introduction to C Language
Author: Ceyhun Aytekin / IBU-Computer Science Department
Topics: Pointers: Simple Form
*/

#include <stdio.h>

int main(void){
  
  printf("\n");

  //Assigning p the address of q
  int *p,q;
  q=300;
  p=&q;
  printf("Value which pointer p points: %d\n", *p);
  printf("Value of q is: %d\n", q);

  //Ass ;
  int *p2,q2;
  p2=&q2;
  *p2=100;
  printf("Pointer p2 assigns %d to the q2.\n", q2);  
  (*p2)++; // add 1 to the value which pointer points
  printf("Pointer own value p2: %p\n", p2);
  printf("Address of variable q2: %p\n", &q2);
  printf("Value which pointer p points %d\n", *p2);
  
  //An error!!!
  int *p3;
  //*p3=100; 
  
  //An error!!!
  int *p4;
  double q4,temp;
  temp=123.23;
  //p4=&temp;
  //q4=*p4;
  
  printf("\n");
  
  return 0;

}