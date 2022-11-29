from enum import Enum, auto

class PlayerID(Enum):
    """Stores whether or not the player is player one or player two.
    More robust than constant string comparisons"""
    PLAYER_ONE = auto()
    PLAYER_TWO = auto()

PLAYER_ONE = PlayerID.PLAYER_ONE
PLAYER_TWO = PlayerID.PLAYER_TWO

class Player:
    """
    Represents a player, complete with all data our model will consider.
    Will probably be much bigger later

    ...

    Attributes:
    -----------
    name: a text string that stores playername
    id: stores whether this is playerone or playertwo
    serve_win_percentage: stores the percent chance the player has of holding
        his serve, as an integer between 0 and 1

    """


    def __init__(self, name: str, serve_win_percentage: float, id: PlayerID):

        self.name = name
        self.id = id
        self.serve_win_percentage = serve_win_percentage
    def get_serve_percentage (self):
        return self.serve_win_percentage

#Note to self: use getters and setters next time you retard
class Score:
    """Stores the match score and contains methods for incrementing scoreboard

    Attributes
    ----------
    match_score: list of size 2, stores sets won by each player. PLAYER_ONE
        score held in index 0

    set_score: list of size 2, stores score within the set. PLAYER_TWO score
        help in index 1

    game_score: list size 2, stores game_score

    is_tiebreaker: pretty fucking self evident

    tiebreaker_score: list size 2, etc etc

    player_to_serve: stores whichever player is serving

    Methods
    -------
    add_point : takes player ID as argument, and increments score accordingly
    """

    match_score : int
    set_score : int
    game_score : int

    is_tiebreaker : bool

    tiebreaker_score : int

    player_to_serve : PlayerID

    first_tiebreak_server : PlayerID

    def __init__(self,  game_score, set_score, match_score, player_to_serve,
    is_tiebreaker = False, tiebreaker_score = [0,0]):


        self.match_score = match_score
        self.game_score = game_score
        self.set_score = set_score
        self.is_tiebreaker = is_tiebreaker
        self.tiebreaker_score = tiebreaker_score
        self.player_to_serve = player_to_serve

    def add_point(self, player : PlayerID):
        #Increment game score unless duece or game point or tiebreak
        #If game point, set gamescore to 0 and _add_game
        #If duece, increase gamescore to 4-3. If player loses
        #next point, decrease gamescore back to 3-3


        winner_index = 0
        loser_index = 1

        if (player == PLAYER_TWO):
            winner_index = 1
            loser_index = 0

        if self.is_tiebreaker:
    
            self._add_tiebreak_point(player, winner_index, loser_index)
            return

        #Handles non ad, non game point cases
        if (self.game_score[winner_index] < 3):
            self.game_score[winner_index] += 1

        #Handles duece and non ad game points
        elif (self.game_score[winner_index] == 3):

            if (self.game_score[loser_index] == 3):
                self.game_score[winner_index] += 1
            elif (self.game_score[loser_index] == 4):
                self.game_score[loser_index] = 3
            else:
                self._add_game(player, winner_index, loser_index)
        #Handles ad in
        else:
            self._add_game(player, winner_index, loser_index)



    def _add_game(self, player : PlayerID, winner_index, loser_index):

        self._switch_serve()

        if (self.set_score[winner_index] < 5):
            self.set_score[winner_index] += 1

        elif (self.set_score[loser_index] == 5):
            self.set_score[winner_index] += 1

        elif (self.set_score[loser_index] == 6):
            self.set_score[winner_index] += 1
            self.is_tiebreaker = True
        else:
            self._add_set(player, winner_index, loser_index)

        self.game_score[0] = 0
        self.game_score[1] = 0

        #Increment set score unless set is over
        #If set is over, add set


    def _add_set(self, player : PlayerID, winner_index, loser_index):
        self.match_score[winner_index] += 1
        self.set_score[0] = 0
        self.set_score[1] = 0

    def _add_tiebreak_point(self, player : PlayerID, winner_index, loser_index):

        if (self.tiebreaker_score[0] == 0 and 0 == self.tiebreaker_score[1]):
            self.first_tiebreak_server = self.player_to_serve

        if (self.tiebreaker_score[winner_index] < 6):
            self.tiebreaker_score[winner_index] += 1
        elif (self.tiebreaker_score[winner_index] <= self.tiebreaker_score[loser_index]):
            self.tiebreaker_score[winner_index] += 1
        else:
            self._add_set(player, winner_index, loser_index)
            self.tiebreaker_score[0] = 0
            self.tiebreaker_score[1] = 0
            self.player_to_serve = self.first_tiebreak_server
            self._switch_serve()


        total = self.tiebreaker_score[0] + self.tiebreaker_score[1]

        if (total % 2 == 1):
            self._switch_serve()


    def _switch_serve(self):
        if (self.player_to_serve == PLAYER_ONE):
            self.player_to_serve = PLAYER_TWO
        else:
            self.player_to_serve = PLAYER_ONE

    """
    Dream format:

    Player one is serving
    15-40
    3-3
    2-1

    """
    def __str__(self):
        server_index = 0
        returner_index = 1

        if (self.player_to_serve == PLAYER_TWO):
            server_index = 1
            returner_index = 0

        #String version of who's serving
        server_string = "Player one is serving\n"
        if (self.player_to_serve == PLAYER_TWO):
            server_string = "Player two is serving\n"

        #String version of server and returners game score
        server_game_score = "0"
        returner_game_score = "0"

        x = self.game_score[server_index]
        y = self.game_score[returner_index]

        if (x == 1):
            server_game_score = "15"
        if (x == 2):
            server_game_score = "30"
        if (x == 3):
            server_game_score = "40"
        if (x == 4):
            server_game_score = "ad"


        if (y == 1):
            returner_game_score = "15"
        if (y == 2):
            returner_game_score = "30"
        if (y == 3):
            returner_game_score = "40"


        game_score_string = server_game_score + "-" + returner_game_score + "\n"
        if (x == 4):
            game_score_string = "ad in \n"

        if (y == 4):
            game_score_string = "ad out \n"

        set_score_string = ''
        set_score_string += str(self.set_score[server_index]) + "-"
        set_score_string += str(self.set_score[returner_index]) + "\n"

        tiebreaker_string = ''
        if self.is_tiebreaker:
            tiebreaker_string += "Tiebreak: "
            tiebreaker_string += str(self.tiebreaker_score[server_index]) + "-"
            tiebreaker_string += str(self.tiebreaker_score[returner_index]) +"\n"

        match_string = ''
        match_string += str(self.match_score[server_index]) + ","
        match_string += str(self.match_score[returner_index])

        output = server_string + game_score_string + set_score_string
        output += match_string + tiebreaker_string
        return output
