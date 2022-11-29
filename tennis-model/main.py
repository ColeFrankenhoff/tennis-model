from player import *
from match import *

def main():
    
    print("Enter data below")
    player_one_name = input("Player one name: ")
    player_two_name = input("Player two name: ")

    p1 = float(input("Player one serve win chance : "))
    p2 = float(input("Player two serve win chance : "))

    p1 /= 100
    p2 /= 100

    player_one = Player(player_one_name, p1, PLAYER_ONE)
    player_two = Player(player_two_name, p2, PLAYER_TWO)

    match = Match(player_one, player_two)
    simulate_matches(match)
    
    
main()
