/*	PROJECT #1			*/
/*	GUESSING GAME		*/
/*	FURKAN KARAKOYUNLU	*/
/*	112200036			*/

/*	======== How to play ========	*/
/*	To start type <run.>			*/
/* 	Answer each question with 		*/
/* 	only <yes.> or <no.> 			*/
/* 	=============================	*/

run :- guess_who(Celebrity),
		write('I guess you picked '),
		write(Celebrity),
		nl,
		undo.

guess_who(davidBeckham) :- davidBeckham, !.
guess_who(sabri) :- sabri, !.
guess_who(mirandaKerr) :- mirandaKerr, !.
guess_who(serdarOrtac) :- serdarOrtac, !.
guess_who(kimKardashian) :- kimKardashian, !.
guess_who(ankaraliNamik) :- ankaraliNamik, !.
guess_who(sedaSayan) :- sedaSayan, !.
guess_who(yilmazMorgul) :- yilmazMorgul, !.
guess_who(unknown).

/*	Person identifier rules	*/
davidBeckham :- male,
				footballPlayer,
				verify(married_with_Victoria_Beckham),
				verify(is_handsome).

sabri :- male,
		footballPlayer,
		verify(played_at_Galatasaray),
		verify(nickname_reyiz).

mirandaKerr :- 	female,
				verify(is_model),
				verify(victoria_secret_angel).

serdarOrtac :- 	male,
				singer,
				verify(has_song_called_Karabiberim).

kimKardashian :- 	female,
					verify(has_big_useless_booty).

ankaraliNamik :- 	male,
					singer,
					verify(has_song_called_Arabada_Bes_Evde_Onbes).

sedaSayan :- 	female,
				verify(announcer_of_wedding_program).

yilmazMorgul :- male,
				singer,
				verify(attended_survivor_2016),
				verify(az_yumsaklik_varmi_adamda).

/*	Classification rules */
male :- verify(is_male), !.
female :- verify(is_female), !.
footballPlayer :- verify(plays_football), !.
singer :- verify(is_singer), !.

/*	Ask question */
ask(Question) :- write('Does the celebrity has the following: '),
				write(Question),
				write('? '),
				read(Response),
				nl,
				((Response == yes)
					->
					assert(yes(Question));
					assert(no(Question)), fail
					).

:- dynamic yes/1,no/1.

verify(Q) :-
	(yes(Q) -> true ;
	(no(Q) -> fail ;
	ask(Q))).
	
/*  undo all yes no assertions  */
undo :- retract(yes(_)),fail.
undo :- retract(no(_)),fail.
undo.
