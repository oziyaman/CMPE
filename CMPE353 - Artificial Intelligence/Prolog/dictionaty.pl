mean(kedi, cat).
mean(köpek, dog).
mean(araba, car).


translate([], []).

translate([Head|Tail], [Head2|Tail2]) :-
	mean(Head, Head2),
	translate(Tail, Tail2).