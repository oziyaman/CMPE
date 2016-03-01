/* 
Comp-306:Operating Systems (2010-Spring)
Week-2: Introduction to C Language
Author: Ceyhun Aytekin / IBU-Computer Science Department
Topics: Multidimensional Arrays, Character Sequences
*/

#include <stdio.h>
#define SIZE 5

void zeroMatrix(){
  int i,j;
  int a [SIZE][SIZE];
  
  for(i=0;i<SIZE;i++){
    for (j=0;j<SIZE;j++){
      a[i][j]=0;
    }
  }  
  printf("%d \n",a[0][0]);
  
}


void charArray(){
 
char word0 [] = { 'H', 'e', 'l', 'l', 'o','\0'};
char word1 [] = "Hello";

printf("%s\n",word0);
printf("%s\n",word1);
printf("%c\n",word0[4]);

}

int main(void){

  int myMat [SIZE][SIZE];
  myMat[0][0]=4;
  printf("%d \n",myMat[0][0]);
  
  zeroMatrix();



int m [3][3]={{1,2,3},{4,5,6},{7,8,9}};
printf("%d \n",m[0][1]);


charArray();


}