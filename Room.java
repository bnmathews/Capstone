import java.awt.*;
import java.util.Random;
public class Room
{
    private String type;
    private String name;
    private Resident occupant;
    private boolean isOccupied = false;
    private int width = 30;
    private int height = 30;
    public Room(String n)
    {
        name = n;
        type = "r";
    }
    
    public Room(String n, String t, int w, int h)
    {
        name = n;
        type = t;
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
            page.setColor(occupant.getColor());
            page.fillRect (startingX+increase, startingY+heightIncrease, width, height);
        }
        
        page.setColor(Color.white);
        page.drawLine (startingX+increase, startingY+heightIncrease, startingX+width+increase, startingY+heightIncrease); //draw top line
        page.drawLine (startingX+increase, startingY+heightIncrease, startingX+increase, startingY+height+heightIncrease); //draw left line
        page.drawLine (startingX+width+increase, startingY+heightIncrease, startingX+width+increase, startingY+height+heightIncrease); //draw right line
        page.drawLine (startingX+increase, startingY+height+heightIncrease, startingX+width+increase, startingY+height+heightIncrease); //draw bottom line
    }
    
    public void setOccupied(Resident r)
    {
        occupant = r;
        if (r != null)
            isOccupied = true;
        else
            isOccupied = false;
    }
    
    public Resident getResident()
    {
        if (occupant != null)
        {
            return occupant;
        }
        else
            return null;
    }
    
    public boolean getOccupation()
    {
        return isOccupied;
    }
    
    public String getName()
    {
        return name;
    }
    
    public String getType()
    {
        return type;
    }
}
