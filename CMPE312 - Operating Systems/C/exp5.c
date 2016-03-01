/* 
Comp-306:Operating Systems (2010-Spring)
Week-2: Introduction to C Language
Author: Ceyhun Aytekin / IBU-Computer Science Department
Topics: New Library: <math.h>, 
*/

#include <stdio.h>
#include <math.h>

  int main(void){

    printf("Power of two number: %.2f\n",pow(5,3));
    printf("Square root of a number: %.2f\n",sqrt(16));
    printf("Cosine of a number: %.2f\n",cos(0));
    printf("Sine of a number: %.2f\n",sin(0));
    printf("Round up value of a number: %.2f\n",ceil(5.3));
    printf("Round down value of a number: %.2f\n",floor(5.3));
    printf("Absolute value of a number: %.2f\n",fabs(-6+2));
    
    return 0;	
  }