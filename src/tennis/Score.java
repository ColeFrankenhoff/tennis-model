package tennis;

import java.util.Random;

/*
Seems to have more functionality than neccessary, but custom initialization will be useful later
*/
class Score {
    //Score within current game
    private int p1GameScore;
    private int p2GameScore;
    //Score within current set
    private int p1SetScore;
    private int p2SetScore;
    //You get the point
    private int p1MatchScore;
    private int p2MatchScore;

    private int p1TieBreakScore;
    private int p2TieBreakScore;

    private boolean isTieBreak; 
    private boolean isGrandSlam;
    private boolean playerOneServe;
    private boolean playerOneServedTiebreak;
    //Return 0 if match still going on, 1 if p1 wins, 2 if p2 wins
    protected Score(){
        this.p1GameScore = 0;
        this.p2GameScore = 0;

        this.p1SetScore = 0;
        this.p2SetScore = 0;

        this.p1MatchScore = 0;
        this.p2MatchScore = 0;

        this.p1TieBreakScore = 0;
        this.p2TieBreakScore = 0;

        this.isTieBreak = false;
        this.isGrandSlam = false;

        //Set server
        Random random = new Random();
        int coin = random.nextInt(2);
        if (coin < 1){
            playerOneServe = false;
        }
        else{
            playerOneServe = true;
        }
    }
    protected PlayerID getServer(){
        if (this.playerOneServe == true){
            return PlayerID.PLAYER_ONE;
        }
        else{
            return PlayerID.PLAYER_TWO;
        }
    }
    //Return 0 if match not over, 1 if p1 wins, 2 if p2 wins
    protected int addPoint(PlayerID player){

        //If this is a tiebreak handle tiebreak stuff
        if (this.isTieBreak){
            this.addTiebreakPoint(player);
            return this.isMatchOver();
        }
        //Would be much easier to make dueces go down to 40-30,
        //but need state stored for later

        /*
         * Cases to handle: 
         * ----------------
         * Is the game over?
         *  if one player has five and the other has three then yes
         *  if one player has four and the other doesn't have three then yes
         *      Addgame and return for both cases
         * if both players have four go back down to duece
         * otherwise return
         * Go back to duece
         */


        if (player == PlayerID.PLAYER_ONE){
            this.p1GameScore++;
        }
        else{
            this.p2GameScore++;
        }


        if (this.p1GameScore == 4 && this.p2GameScore <= 2){
            addGame(PlayerID.PLAYER_ONE);
        }

        if (this.p1GameScore <= 2 && this.p2GameScore == 4){
            addGame(PlayerID.PLAYER_TWO);
        }

        if (this.p1GameScore == 5 || this.p2GameScore == 5){
            addGame(player);
        }

        if (this.p1GameScore == 4 && this.p2GameScore == 4){
            this.p1GameScore = 3;
            this.p2GameScore = 3;
        }
        
        return this.isMatchOver();
    }
    
    private void addGame(PlayerID player){
        /*
         * Set game scores to zero
         * Increment setscore
         * Change server 
         * Check if set is over
         *  if p1SetScore >=6 & (p1SetScore - p2setScore) >= 2
         *  or reverse of that condition addset
         *  
         */
        
         //Update game variables
        this.p1GameScore = 0;
        this.p2GameScore = 0;
        this.playerOneServe = !this.playerOneServe;

        //Update set score
        if (player == PlayerID.PLAYER_ONE){
            this.p1SetScore++;
        }
        else{
            this.p2SetScore++;
        }
        if (this.p1SetScore >= 6 && (this.p1SetScore - this.p2SetScore) >= 2){
            this.addSet(PlayerID.PLAYER_ONE);
        }
        if (this.p2SetScore >= 6 && (this.p2SetScore - this.p1SetScore) >= 2){
            this.addSet(PlayerID.PLAYER_TWO);
        }
        if (this.p1SetScore == 6 && this.p2SetScore == 6){
            // this.playerOneServedTiebreak = this.playerOneServe; 
            this.isTieBreak = true;
        }
    }
    private void addTiebreakPoint(PlayerID player){
        if (player == PlayerID.PLAYER_ONE){
            this.p1TieBreakScore++;
        }
        else{
            this.p2TieBreakScore++;
        }
        
        if ((this.p1TieBreakScore - this.p2TieBreakScore)%2 == 1){
            this.playerOneServe = !this.playerOneServe;
        }
        if (this.p1TieBreakScore >= 7 && 
         (this.p1TieBreakScore - this.p2TieBreakScore) >= 2){
            this.playerOneServe = !this.playerOneServedTiebreak;
            this.addSet(PlayerID.PLAYER_ONE);
        }
        if (this.p2TieBreakScore >= 7 && 
        (this.p2TieBreakScore - this.p1TieBreakScore) >= 2){
           this.playerOneServe = !this.playerOneServedTiebreak;
           this.addSet(PlayerID.PLAYER_TWO);
       }
    }
    private void addSet(PlayerID player){
        this.p1SetScore = 0;
        this.p2SetScore = 0;
        this.p1TieBreakScore = 0;
        this.p2TieBreakScore = 0;
        this.isTieBreak = false;
        if (player == PlayerID.PLAYER_ONE){
            this.p1MatchScore++;
        }
        else{
            this.p2MatchScore++;
        }
    }
    private int isMatchOver(){
        if (this.isGrandSlam){
            if (this.p1MatchScore == 3){
                return 1;
            }
            if (this.p2MatchScore == 3){
                return 2;
            }
        }
        else{
            if (this.p1MatchScore == 2){
                return 1;
            }
            if (this.p2MatchScore == 2){
                return 2;
            }            
        }
        return 0;
    }

    @Override
    public String toString() {
        return 
            ("Game score is: " + p1GameScore + "," + p2GameScore +
            "\nSet score is: " + p1SetScore + ", " + p2SetScore + " " +
            "\nMatch score is: "+ p1MatchScore + "," + p2MatchScore + "" +
            "\nTiebreak score is: " + p1TieBreakScore + ", " + p2TieBreakScore+
            "\nisTiebreak is: "+ this.isTieBreak + 
            "\nPlayer one serve is: " + this.playerOneServe +"\n");
    }

}

