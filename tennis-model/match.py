import random
from player import *

"""
Simulates a point, given whatever variables we determine are relevant
"""

class Match:
    """
    Contains all functionality for actually playing a match
    ....

    Attributes:
    -----------
    playerone : a player playing the match
    playertwo : the other dude
    score: the fucking score
    is_grand_slam : all of this shit is self-evident

    Methods
    -------
    play_match: simulates a match given the players and score.
    Returns a winner of type PlayerID

    """
    player_one : Player
    player_two : Player

    score : Score

    is_grand_slam : bool

    def __init__(self, player_one, player_two,
        score = Score([0,0],[0,0],[0,0], PLAYER_ONE), is_grand_slam = False):

        self.player_one = player_one
        self.player_two = player_two
        self.score = score
        self.is_grand_slam = is_grand_slam

    def play_match(self):
        #While true, play a point and update score accordingly
        #Check if match is over and if it is return the winner

        sets_to_win = 2
        if self.is_grand_slam:
            sets_to_win = 3

        while True:
            #Temp variable that stores winner of the last point
            point_winner = self._play_point()

            self.score.add_point(point_winner)

            if (self.score.match_score[0] == sets_to_win):
                self.score = Score([0,0],[0,0],[0,0], PLAYER_ONE)
                return PLAYER_ONE

            if (self.score.match_score[1] == sets_to_win):
                self.score = Score([0,0],[0,0],[0,0], PLAYER_ONE)
                return PLAYER_TWO

    def _play_point(self):

        player_one_odds = 0
        if (self.score.player_to_serve == self.player_one.id):
            player_one_odds = self.player_one.get_serve_percentage()
        else:
            player_one_odds = 1 - self.player_two.get_serve_percentage()

        result = random.random()

        if (result < player_one_odds):
            return PLAYER_ONE
        else:
            return PLAYER_TWO

def simulate_matches(match : Match, number_of_simulations = 10000):
    player_one_total = 0

    for i in range(number_of_simulations):
        winner = match.play_match()
        if (winner == PLAYER_ONE):
            player_one_total += 1

    player_one_percentage = player_one_total/(number_of_simulations/100)
    player_one_name = match.player_one.name

    print(player_one_name + " has won " + str(player_one_percentage) + "% of matches")
