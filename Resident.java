import java.util.Random;
import java.awt.*;
public class Resident
{
    private int money;
    private int timeToStay;
    
    private int minTimeOut;
    private int maxTimeOut;
    
    private int[] roomLocation = new int[2];
    
    private int timeOut = 0; //the time to spend out of the building
    
    private boolean isOut = false;
    private boolean isOnElevator = false;
    private Color color;
    private String name;
    private String gender;
    private String[] namePrefix = {"Apple","Bil","Kick","Head","Work","Shell","Rod","Care","Hipp","Flipp","Still","Tru","Happ","Fell","Fist"};
    private String[] nameSuffix = {"long","iam","worker","maker","breaker","age","man","mack","back","land","ings","-Bo","ins","-Millar","beef"};
    public Resident(int minTimeOut, int maxTimeOut, int roomRow, int roomCol)
    {
        if ( (int) (Math.random() * 2) == 1)
            gender = "Mr. ";
        else
        {
            if ( (int) (Math.random() * 2) == 1)
                gender = "Mrs. ";
            else
                gender = "Miss ";
        }
        name = gender + namePrefix[ (int) (Math.random()*namePrefix.length) ] + nameSuffix[ (int) (Math.random()*nameSuffix.length) ];
        
        timeToStay = (int)(Math.random() * 30) + 1;
        
        this.minTimeOut = minTimeOut;
        this.maxTimeOut = maxTimeOut;
        
        roomLocation[0] = roomRow;
        roomLocation[1] = roomCol;
        
        makeColor();
    }
    
    public int[] getRoomLocation()
    {
        return roomLocation;
    }
    
    public void resetTimeOut()
    {
        Random r = new Random();
        timeOut = r.nextInt((maxTimeOut - minTimeOut) + 1) + minTimeOut;
    }
    
    public void decreaseTimeOut()
    {
        timeOut--;
    }
    
    public int getTimeOut()
    {
        return timeOut;
    }
    
    public void makeColor()
    {
        Random r = new Random();
        color = new Color(r.nextFloat(),r.nextFloat(),r.nextFloat());
    }
    
    public Color getColor()
    {
        return color;
    }
    
    public void updateStayTime()
    {
        if (timeToStay > 0 && isOut == false)
        {
            timeToStay--;
        }
    }
    
    public String doAction()
    {
        Double chance = Math.random() * 100; //decides what the resident will do
        if (chance > 50)
        {
            return "nothing";
        }
        else
        {
            return "out";
        }
    }
    
    public void setOnElevator(boolean b)
    {
        isOnElevator = b;
        if (b == true)
        {
            isOut = true;
        }
    }
    
    public boolean getOnElevator()
    {
        return isOnElevator;
    }
    
    public String getName()
    {
        return name;
    }
    
    public int getStayTime()
    {
        return timeToStay;
    }
    
    public boolean getIsOut()
    {
        return isOut;
    }
}