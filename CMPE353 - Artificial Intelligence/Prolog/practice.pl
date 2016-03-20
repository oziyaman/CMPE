%Print all elements of a list
print_list([]):- nl. %nl = newline
print_list([H|T]):-write(H),write(' '),print_list(T).

%query:		print_list([a,b,c]).
%result:	a b c

%========================================================

%Reverse all elements of a list 
addtoend(H,[],[H]).
addtoend(X,[H|T],[H|T1]):-addtoend(X,T,T1).
reversex([],[]).
reversex([H|T],Y):- reversex(T,T1), addtoend(H,T1,Y).

%query:		reversex([a,b,c],X).
%result:	X = [c, b, a]
%========================================================

%Create list
create_list(X,X,[X]).
create_list(A,X,[A|T]):- AA is A+1, create_list(AA,X,T).

%query:		create_list(5, 12, S).
%result:	S = [5, 6, 7, 8, 9, 10, 11, 12] 

%========================================================

%Mean value
sum_list([],0,0).
sum_list([H|T],Length,Sum):-sum_list(T,L1,S1), Length is L1+1, Sum is S1+H.
mean(L,M):-sum_list(L,Length,Sum), M is Sum/Length.

%query:		mean([1,2,3,4,5], X).
%result:	X = 3

%========================================================

%Detect whether list contains a number.
numberinlist([]):-fail.
numberinlist([X|T]):-number(X).
numberinlist([X|T]):-numberinlist(T).

%query: 	numberinlist([a,b,c,d,e,1,f]).
%result:	true.

%========================================================

%increment elements of list
increment([],[]).
increment([H|T],[X|Y]):-increment(T,Y),X is H+1.

%query: 	increment([5,6,7,8], X).
%result:	X = [6, 7, 8, 9].

%========================================================

%factorial function
factorial(0,1).
factorial(N,X):-N>0, N1 is N-1, factorial(N1,S), X is S*N.

%query: 	factorial(5, X).
%result:	X = 120

%========================================================

%implement append function
appendx([],A,A).
appendx([H|T],A,[H|U]):-appendx(T,A,U).

%query: 	
%result:	

%========================================================