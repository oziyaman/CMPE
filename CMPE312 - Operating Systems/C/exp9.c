/* 
Comp-306:Operating Systems (2015-Spring)
Week-5: Introduction to C Language
Author: Ceyhun Aytekin / IBU-Computer Science Department
Topics: Pointers: Relation with Arrays
*/

#include <stdio.h>

int main(void){
  
  //int *m={1,4,3}; it is not possible...
  int a[10]={10,20,30,40,50,60,70,80,90,100};
  int *p;
  p=a;
  // array için adres göstermeye gerek yok
  // çünkü a zaten adres tutuyor. (arraydeki ilk elemanın adresi)

  printf("%p\n",p+1);
  printf("%p\n",p+2);
  printf("%p\n",&a); //Name of array is the first element address at the same time.
  printf("%p\n",p); //Same!!
  printf("%d %d %d \n",*p,*(p+1),*(p+2));
  printf("%d %d\n",a[0],a[1]);
  printf("%p\n",&a[1]);

  
  
  
  return 0;

}