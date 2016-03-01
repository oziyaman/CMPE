/* 
Comp-306:Operating Systems (2010-Spring)
Week-2: Introduction to C Language
Author: Ceyhun Aytekin / IBU-Computer Science Department
Topics: Arrays, Nested Loop 
*/

#include <stdio.h>
//#include <math.h>
#define SIZE 10

  void printArr(int a []){
    
    int i=0;

    for(i=0;i<SIZE;i++){
      printf("%d, ",a[i]);
    }
    printf("\n");
  }

  void remEven(int a []){
    
    int i=0;
    for(i=0;i<SIZE;i++){
      if(a[i]%2==0)
	a[i]=0;
    }  
      
  }
  
  void compareArr(int a [],int b []){
   
    int i;
    int j;
    int same=0;
    
    for(i=0;i<SIZE;i++)
    {
      for (j=0;j<SIZE;j++)
      {
	  if(a[i]==b[j]){
	    same++;
	    printf("same element(s) is: %d\n",a[i]);
	  }  
      }
    }
  }
  
  int main(void){
   
    int arr0 [SIZE] ={1,2,3,4,5,6,7,8,9,10};

	printArr(arr0);
    remEven(arr0);
    printArr(arr0);
    printf("----------------------\n");

   int arr1[SIZE]={23,54,22,12,77,1,32,5,7,10};
   int arr2[SIZE]={1,2,3,4,5,6,7,8,9,10};
   printArr(arr1);
   printArr(arr2);
   compareArr(arr1,arr2);
   
   return 0;	
  }