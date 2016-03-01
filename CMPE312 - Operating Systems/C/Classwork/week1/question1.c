/*
	CMPE312 : Operating Systems
	Author: Furkan Karakoyunlu / 112200036
	Question#1
*/

#include <stdio.h>


void determineGrade(){
	int grade;
	printf("Enter student number grade: ");
	scanf("%d", &grade);

	if(grade > 100){
		printf("Please enter a valid grade!\n");
	}else if(grade <= 100 && grade >= 80){
		printf("Student letter grade is A\n");
	}else if(grade <= 79 && grade >= 70){
		printf("Student letter grade is B\n");
	}else if(grade <= 69 && grade >= 60){
		printf("Student letter grade is C\n");
	}else if(grade <= 59 && grade >= 50){
		printf("Student letter grade is D\n");
	}else if(grade <= 49 && grade >= 0){
		printf("Student letter grade is F\n");
	}else{
		printf("Please enter a valid grade!\n");
	}
}


int main(void){
	determineGrade();
	return 0;
}
