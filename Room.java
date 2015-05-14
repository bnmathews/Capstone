import java.awt.*;
import java.util.Random;
import javax.swing.JPanel;
public class Room
{
    private String type;
    private String name;
    private Color myColor = new Color(155,155,155);
    private Resident resident;
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
    
    public void setColor(Color c)
    {
        myColor = c;
    }

    public int getWidth()
    {
        return width;
    }

    public int getHeight()
    {
        return height;
    }
    
    public void setWidth(int w)
    {
        width = w;
    }

    public void setHeight(int h)
    {
        height = h;
    }
    
    public void constructRoom(Graphics page, int startingX, int startingY, int increase, int heightIncrease, JPanel panel)
    {
        int panelWidth = (int)(panel.getWidth() / 7.5);
        int panelHeight = (int)(panel.getHeight() / 7.5);
        
        //draw the room
        page.setColor(myColor);
        page.fillRect (startingX+increase, startingY+heightIncrease, width, height);

        //draw the outline of the room
        page.setColor(Color.white);
        page.drawLine (startingX+increase, startingY+heightIncrease, startingX+width+increase, startingY+heightIncrease); //draw top line
        page.drawLine (startingX+increase, startingY+heightIncrease, startingX+increase, startingY+height+heightIncrease); //draw left line
        page.drawLine (startingX+width+increase, startingY+heightIncrease, startingX+width+increase, startingY+height+heightIncrease); //draw right line
        page.drawLine (startingX+increase, startingY+height+heightIncrease, startingX+width+increase, startingY+height+heightIncrease); //draw bottom line
    }

    public void setOccupied(boolean o)
    {
        isOccupied = o;
    }

    public void setResident(Resident r)
    {
        resident = r;
        setOccupied(true);
    }

    public Resident getResident()
    {
        if (resident != null)
        {
            return resident;
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
