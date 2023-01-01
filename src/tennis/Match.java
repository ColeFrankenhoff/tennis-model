package tennis;

class Match {
    private Player playerOne;
    private Player playerTwo;
    private Score score;
    
    protected Match(Player p1, Player p2){
        this.playerOne = p1;
        this.playerTwo = p2;
        this.score = new Score();
    }


    protected PlayerID playMatch(){
        int matchStatus = 0;
        PlayerID pointWinner;
        while (matchStatus == 0){
            pointWinner = this.playPoint();
            matchStatus = this.score.addPoint(pointWinner);
        }
        if (matchStatus == 1){
            return PlayerID.PLAYER_ONE;
        }
        return PlayerID.PLAYER_TWO;
    }

    /*
     * Boolean-related techdebt makes this more 
     * complicated than neccesary. The serve function
     * and the who's serving variable should have dealt with
     * PlayerID's instead, and now I'm paying the price
     */
    private PlayerID playPoint(){
        if (this.score.getServer() == PlayerID.PLAYER_ONE){
            boolean result = playerOne.serve();
            if (result == true){
                return PlayerID.PLAYER_ONE;
            }
            else{
                return PlayerID.PLAYER_TWO;
            }
        }
        else{
            boolean result = playerTwo.serve();
            if (result == true){
                return PlayerID.PLAYER_TWO;
            }
            return PlayerID.PLAYER_ONE;
        }
    }
}
