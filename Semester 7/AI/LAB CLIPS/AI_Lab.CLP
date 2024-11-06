         CLIPS (6.31 6/12/19)
CLIPS> assert (grade 90)
assert
CLIPS> (assert (grade 90))
<Fact-1>
CLIPS> facts
facts
CLIPS> (assert (grade 90)(grade 85))
<Fact-2>
CLIPS> retract 2
retract
CLIPS> (retract 2)
CLIPS> (defrule result
(grade 90)
=>
(assert (pass)))
CLIPS> (assert (grade 80))
<Fact-3>
CLIPS> (printout t"Usama Sarwar"crlf)
Usama Sarwar
CLIPS> (defrule result1
(grade 90)
(grade 70)
=>
(assert (sample)))
CLIPS> 
