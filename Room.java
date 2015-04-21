import java.awt.*;
import java.util.Random;
public class Room
{
    private String name;
    private boolean isOccupied = true;
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
        if (isOccupied)
        {
            Color c = new Color(155,125,0);
            page.setColor(c);
            page.fillRect (startingX+increase, startingY+heightIncrease, width, height);
        }
        
        page.setColor(Color.white);
        page.drawLine (startingX+increase, startingY+heightIncrease, startingX+width+increase, startingY+heightIncrease); //draw top line
        page.drawLine (startingX+increase, startingY+heightIncrease, startingX+increase, startingY+height+heightIncrease); //draw left line
        page.drawLine (startingX+width+increase, startingY+heightIncrease, startingX+width+increase, startingY+height+heightIncrease); //draw right line
        page.drawLine (startingX+increase, startingY+height+heightIncrease, startingX+width+increase, startingY+height+heightIncrease); //draw bottom line
    }
    
    public boolean getOccupation()
    {
        return isOccupied;
    }
    
    public String getName()
    {
        return name;
    }
}
