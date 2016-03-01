% fact or rule
woman(mia).
woman(jody).
woman(yolanda).
playsAirGuitar(jody).
party.


who_gets_what(harry, bread).
who_gets_what(jamie, bread).
who_gets_what(ron, parfume).
who_gets_what(michael, salad).

jaelous(X, Y):-
    who_gets_what(X, Z),
    who_gets_what(Y, Z).

% predicate(id,name)

title(ID,X).
author(ID,Y).
page_number(ID,Z).
cover_color(ID,C).

title(2,nlp).
author(2,yolanda).
page_number(2,50).
cover_color(2,red).

book(1, incognito, 200).
book(2, holes, 200).
book(3, holmes, 200).

same(X,Y):-
    book(X,C,Z),
    book(Y,V,Z),
    Z<250.