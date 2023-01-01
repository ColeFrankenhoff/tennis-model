package tennis;
import java.util.Random;

class Player
{
    protected PlayerID id;
    private String name;
    private float firstServeChance;
    private float secondServeChance;

    private float firstServeWinChance;
    private float secondServeWinChance;

    //Not sure if two line header is bad design, but it looks awful. 
    //Eventually this will have a database of players and just take the name as input
    public Player(String name, float serveOnePercentage, float serveTwoPercentage,
     float serveOneWinRate, float serveTwoWinRate){
        
        this.name = name;

        this.firstServeChance = serveOnePercentage/100;
        this.secondServeChance = serveTwoPercentage/100;
        
        this.firstServeWinChance = serveOneWinRate/100;
        this.secondServeWinChance = serveTwoWinRate/100;
    }

    public String getName(){
        return this.name;
    }

    //Return true if this player wins, return false if he doesn't
    protected boolean serve(){
        //I lose braincells every time I type this line
        Random random = new Random();
        float pointResult; 
        float firstServeResult = random.nextFloat();
        
        if (firstServeResult < firstServeChance){
            pointResult = random.nextFloat();
            if (pointResult < firstServeWinChance){ 
                return true;
            }
            return false;
            }
        
        float secondServeResult = random.nextFloat();
        if (secondServeResult < secondServeChance){
            pointResult = random.nextFloat();
            if (pointResult < secondServeWinChance) {
                return true;}
            }
        return false;
    }
}
