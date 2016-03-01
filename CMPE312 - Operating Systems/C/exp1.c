/* 
Comp-306:Operating Systems (2013-Spring)
Week-1: Introduction to C Language
Author: Ceyhun Aytekin / IBU-Computer Science Department
Topics: Standard Library - Printf() - Simple Function
*/

#include <stdio.h>	/* includes C library */

void helloFunction(){		/* simple function without parameters and return statement */
  printf("Hello World from helloFunction!\n");
}

int modCalculater(int a,int b){		/* simple function with parameters and return statement */
  return a%b;
}

int main(void){		/* main() funtion */

  int num;	/* define a variable called num */
  num = 11;	/* assign a value to num */  

  printf("My favorite number is");		/* use printf() function without new line */
  printf(" %d and %d.\n",num,num);		/* use printf() function with new line */

  helloFunction();		
  
  printf("The answer of 5 mod 2 is: %d\n",modCalculater(5,2));		
  
  int ans = modCalculater(num,2);		
  printf("The answer of %d mod 2 is: %d\n",num,ans);
  

return 0;	/* a return statement. Return 0 on success and non-zero for error */
  
}

