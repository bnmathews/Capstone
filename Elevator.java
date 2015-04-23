import java.awt.*;
import java.util.ArrayList;
public class Elevator extends Room
{
    private ArrayList<Resident> occupants;
    private String type;
    public Elevator(String n)
    {
        super(n,"e",20,30);
    }
    
    public void addOccupant(Resident r)
    {
        occupants.add(r);
    }
}
