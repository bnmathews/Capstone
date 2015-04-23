import java.util.Random;
import java.awt.*;
public class Resident
{
    private int money;
    private int timeToStay;
    private Color color;
    private String name;
    private String gender;
    private String[] namePrefix = {"Apple","Bil","Kick","Head","Work","Shell","Rod","Care","Hipp","Flipp","Still","Tru","Happ","Fell","Fist"};
    private String[] nameSuffix = {"long","iam","worker","maker","breaker","age","man","mack","back","land","ings","-Bo","ins","-Millar","beef"};
    public Resident()
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
        
        makeColor();
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
        if (timeToStay > 0)
        {
            timeToStay--;
        }
    }
    
    public String doAction()
    {
        updateStayTime();
        if (Math.random()*3 > 2)
        {
            return "nothing";
        }
        else
        {
            return "work";
        }
    }
    
    public String getName()
    {
        return name;
    }
    
    public int getStayTime()
    {
        return timeToStay;
    }
}