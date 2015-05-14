import java.awt.*;
import java.util.ArrayList;
public class Elevator extends Room
{
    private ArrayList<Resident> occupants;
    private String type;
    private Color defaultColor = new Color(100,100,100);
    private boolean movingUp = false;
    public Elevator(String n)
    {
        super(n,"e",20,30);
        occupants = new ArrayList<Resident>();
        super.setColor(defaultColor);
    }
    
    public void addOccupant(Resident r)
    {
        occupants.add(r);
        super.setOccupied(true);
        setColor(r.getColor());
    }
    
    public ArrayList<Resident> getAllResidents()
    {
        return occupants;
    }
    
    public Resident getResident()
    {
        if (getOccupation() == true)
        {
            return occupants.get(occupants.size()-1);
        }
        else
        {
            return null;
        }
    }
    
    public void removeAllResidents()
    {
        setColor(defaultColor);
        occupants.clear();
        setOccupied(false);
    }
    
    public void transferElevators(Elevator e)
    {
        for (Resident r : occupants)
        {
            e.addOccupant(r);
        }
        removeAllResidents();
    }
    
    public void setMovingUp(boolean b)
    {
        movingUp = b;
    }
    
    public boolean getMovingUp()
    {
        return movingUp;
    }
}
