/* 
Comp-306:Operating Systems (2015-Spring)
Week-5: Introduction to C Language
Author: Ceyhun Aytekin / IBU-Computer Science Department
Topics: Pointers: Functions with array
*/

#include <stdio.h>

int sumPtr(int * ar){

  int i;
  int total=0;
  
  for(i=0;i<10;i++)
    total+=*(ar + i);
  return total;
}

int sumArr(int ar[]){

  int i;
  int total=0;
  
  for(i=0;i<10;i++)
    total+=ar[i];
  return total;
}


int main(void){
  
  int a[10]={10,20,30,40,50,60,70,80,90,100};
  int *p;
  
  p=a;
  printf("%d\n",sumPtr(a));
  printf("%d\n",sumArr(a));
  
  printf("%d\n",sumPtr(p));
  printf("%d\n",sumArr(p));
  
  printf("%d\n",sumPtr(&a[0]));
  printf("%d\n",sumArr(&a[0]));
  
  putchar('g');
  putchar('\n');
  return 0;

}