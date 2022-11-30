# tennis-model

This is a work in progress commandline tool to calculate live tennis odds. It
Models probability of tennis match outcomes by simulating thousands of matches, with the match simulated at more grandular detail than similar algorithms. The match is played point by point, and acounts for tons of tennis peculiarities: duece/add, tiebreakers, win by two scoring at 5-5, tiebreakers at 6-6, the scoring differencesbetween grand slam matches and other matches, and everything else.

Although currently each point is simulated solely based on each player's serve win percentage,
The design is very modular and the play_point function can easily be made more sophisticated

Files\
player: defines a player class, and also has functions for keeping score and adding a point. \
match: defines a class match which contains a player and the score, and also functions for simulating matches\
main: takes keyboard input, simulates a match according to it, and prints output
