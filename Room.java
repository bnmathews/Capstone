import java.awt.*;
public class Room
{
    private String name;
    private int width = 30;
    private int height = 30;
    public Room()
    {
        name = "r";
    }
    
    public Room(String n, int w, int h)
    {
        name = n;
        width = w;
        height = h;
    }
    
    public int getWidth()
    {
        return width;
    }
    
    public int getHeight()
    {
        return height;
    }
    
    public void constructRoom(Graphics page, int startingX, int startingY, int increase, int heightIncrease)
    {
        /*
        page.drawLine (20+increase, 20+heightIncrease, 50+increase, 20+heightIncrease); //draw top line
        page.drawLine (20+increase, 20+heightIncrease, 20+increase, 50+heightIncrease); //draw left line
        page.drawLine (50+increase, 20+heightIncrease, 50+increase, 50+heightIncrease); //draw right line
        page.drawLine (20+increase, 50+heightIncrease, 50+increase, 50+heightIncrease); //draw bottom line
        */
        page.drawLine (startingX+increase, startingY+heightIncrease, startingX+width+increase, startingY+heightIncrease); //draw top line
        page.drawLine (startingX+increase, startingY+heightIncrease, startingX+increase, startingY+height+heightIncrease); //draw left line
        page.drawLine (startingX+width+increase, startingY+heightIncrease, startingX+width+increase, startingY+height+heightIncrease); //draw right line
        page.drawLine (startingX+increase, startingY+height+heightIncrease, startingX+width+increase, startingY+height+heightIncrease); //draw bottom line
    }
    
    public String getName()
    {
        return name;
    }
}
