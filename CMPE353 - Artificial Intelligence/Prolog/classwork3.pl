% Furkan Karakoyunlu
% 112200036

%Question1

%brother
brother(X,Y) :- father(Z,X), 
father(Z,Y), not(X=Y).

%cousin
cousin(X,Y) :- father(Z,X),
father(W,Y), brother(Z,W).

%grandson
grandson(X,Y) :- father(Z,X), 
father(Y,Z). 

%descendent
descendent(X,Y) :- father(Y,X).
descendent(X,Y) :- father(Z,X), descendent(Z,Y).

%Consider following genealogical tree
father(a,b).
father(a,c).
father(b,d).
father(b,e).
father(c,f).

?- brother(X,Y).
X = b,
Y = c ;
X = c,
Y = b ;
X = d,
Y = e ;
X = e,
Y = d ;
false.

?- cousin(X,Y).
X = d,
Y = f ;
X = e,
Y = f ;
X = f,
Y = d ;
X = f,
Y = e ;
false.

?- grandson(X,Y).
X = d,
Y = a ;
X = e,
Y = a ;
X = f,
Y = a.

?- descendent(X,Y).
X = b,
Y = a ;
X = c,
Y = a ;
X = d,
Y = b ;
X = e,
Y = b ;
X = f,
Y = c ;
X = d,
Y = a ;
X = e,
Y = a ;
X = f,
Y = a ;
false.


%Question2

member(X,L):- 
redefine_system_predicate(member(_,_)).
member(X,[X|_]).
member(X,[_|L]) :- member(X,L).

subset(L,K):- redefine_system_predicate(subset(_,_)).
subset([],_).
subset([X|L],K) :- member(X,K), subset(L,K).

disjoint([],_).
disjoint([X|L],K) :- not(member(X,K)), 
disjoint(L,K).

union(L,K,M):- redefine_system_predicate(union(_,_,_)).
union(L,K,M) :- append(L,K,M).

intersection(L,K,M):- redefine_system_predicate(intersection(_,_,_)).
intersection([],_,[]).
intersection([X|L],K,M) :- not(member(X,K)), 
intersection(L,K,M).
intersection([X|L],K,[X|M]) :- member(X,K), 
intersection(L,K,M).

difference([],_,[]).
difference([X|L],K,M) :- member(X,K), 
difference(L,K,M).
difference([X|L],K,[X|M]) :- not(member(X,K)), 
difference(L,K,M).


%Question3

occurred(_,[],0).
occurred(E,[E|K],N) :- occurred(E,K,M), N is M+1.
occurred(E,[B|K],N) :- not(E=B), occurred(E,K,N).


%Question4

member1(X,[H|_]) :- X==H,!.
member1(X,[_|T]) :- member1(X,T).

distinct([],[]).
distinct([H|T],C) :- member1(H,T),!, distinct(T,C).
distinct([H|T],[H|C]) :- distinct(T,C).
