package tennis;
import java.util.Scanner;

public class Main {
    private static Player playerOne;
    private static Player playerTwo;
    private static int matchesToSimulate;

    public static void main(String[] args) throws Exception {
        getInputData();
        simulateMatches(matchesToSimulate);
    }

    private static void simulateMatches(int nMatches){
        int p1Wins = 0;
        int p2Wins = 0;
        for (int i = 0; i < nMatches; i++){
            Match match = new Match(playerOne, playerTwo);
            PlayerID winner = match.playMatch();
            if (winner == PlayerID.PLAYER_ONE){
                p1Wins++;
            }
            else{
                p2Wins++;
            }
        }
        System.out.println("Player 1 has won "+p1Wins);
        System.out.println("Player 2 has won "+p2Wins);
        String p1Name = playerOne.getName();
        float p1Percent = ((float) p1Wins/nMatches) * 100;
        System.out.println(p1Name + " has won " + p1Percent + "% of matches");
    }
    
    private static void getInputData(){
        //Collect all playerOne data
        Scanner myScan = new Scanner(System.in);
        System.out.println("Player One Info\n---------------");
        System.out.print("Name: ");
        String name = myScan.nextLine();
        System.out.print("First serve percentage: ");
        float p1FirstServePercentage = myScan.nextFloat();
        System.out.print("First serve win percentage: ");
        float p1FirstServeWinPercentage = myScan.nextFloat();
        System.out.print("Second serve percentage: ");
        float p1SecondServePercentage = myScan.nextFloat();
        System.out.print("Second serve win percentage: ");
        float p1SecondServeWinPercentage = myScan.nextFloat();
        
        playerOne = new Player(name, p1FirstServePercentage, p1SecondServePercentage,
        p1FirstServeWinPercentage, p1SecondServeWinPercentage);

        //Collect all playerTwo data
        System.out.println("\nPlayer Two Info\n---------------");
        System.out.print("Name: ");
        String p2Name = myScan.next();
        System.out.print("First serve percentage: ");
        
        float p2FirstServePercentage = myScan.nextFloat();
        System.out.print("First serve win percentage: ");
        float p2FirstServeWinPercentage = myScan.nextFloat();
        System.out.print("Second serve percentage: ");
        float p2SecondServePercentage = myScan.nextFloat();
        System.out.print("Second serve win percentage: ");
        float p2SecondServeWinPercentage = myScan.nextFloat();
        
        playerTwo = new Player(p2Name, p2FirstServePercentage, p2SecondServePercentage,
        p2FirstServeWinPercentage, p2SecondServeWinPercentage);
        
        System.out.println("\nNumber of matches to simulate: ");
        matchesToSimulate = myScan.nextInt();
        myScan.close(); 
    }

}

/*Classes:
 * Playmatch
 * Player
 * Match
 */