import java.awt.*;
public class Room
{
    private String name;
    public Room()
    {
        name = "r";
    }
    
    public Room(String n)
    {
        name = n;
    }
    
    public void constructRoom(Graphics page, int increase, int heightIncrease)
    {
        page.drawLine (20+increase, 20+heightIncrease, 50+increase, 20+heightIncrease);
        page.drawLine (20+increase, 50+heightIncrease, 50+increase, 50+heightIncrease);
        page.drawLine (20+increase, 20+heightIncrease, 20+increase, 50+heightIncrease);
        page.drawLine (50+increase, 20+heightIncrease, 50+increase, 50+heightIncrease);
    }
    
    public String getName()
    {
        return name;
    }
}
