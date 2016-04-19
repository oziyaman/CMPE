% Furkan Karakoyunlu
% 112200036
% Quiz#1

%Question1
%---------
mother(a,b).
mother(a,c).
mother(b,d).
mother(b,e).
mother(c,f). 

%sister
sister(X,Y) :- mother(Z,X), mother(Z,Y), not(X=Y).

%cousin
cousin(X,Y) :- mother(Z,X), mother(W,Y), sister(Z,W).

%aunt
aunt(X,Y) :- mother(Z,Y), sister(X,Z).

%descendent
descendent(X,Y) :- mother(Y,X).
descendent(X,Y) :- mother(Z,X), descendent(Z,Y).


%Question2
%---------
cTimes(_,[],0).
cTimes(A,[A|T],C) :- cTimes(A,T,M), C is M+1.
cTimes(A,[B|T],C) :- not(A=B), 
cTimes(A,T,C).


%Question3
%--------
sumList([],0).
sumList([H|T],Sum) :- sumList(T,S1), Sum is S1+H.
sum(A,B) :- sumList(A, Sum), B is Sum.


%Question4
%---------
% Celcius to Fahrenheit formula:
% (F-32)*1.8 = C
convert(C,F) :- C is ((F-32)/1.8).

