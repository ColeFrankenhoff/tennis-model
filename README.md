# tennis-model

Work in progress commandline live tennis odds calculator
Models probability of tennis match outcomes by simulating thousands of matches
Match simulations are played point by point, and can start at any score
Accounts for duece/add, tiebreakers, win by two scoring at 5-5, tiebreakers at 6-6, the scoring differences
between grand slam matches and other matches, and all other irritating tennis rules

Although currently each point is simulated solely based on each player's serve win percentage,
The design is very modular and the play_point function can easily be made more sophisticated

Files
player: defines a player class, and also has functions for keeping score and adding a point. 
match: defines a class match which contains a player and the score, and also functions for simulating matches
main: takes keyboard input, simulates a match according to it, and prints output
