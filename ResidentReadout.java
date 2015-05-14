import java.awt.*;
import javax.swing.*;
import java.util.Random; 
import java.util.ArrayList;

/**
 * Resident Readout
 * 
 * @author @bnmathews
 * @version 14 May 2015
 */
public class ResidentReadout extends JPanel
{
    
    // the dimensions of the readout Panel
    private final int PANEL_WIDTH = 1000;
    private final int PANEL_HEIGHT = 1000;

    private ArrayList<Resident> myResidents = new ArrayList<Resident>();

    private ArrayList<rLabel> residentLabels = new ArrayList<rLabel>();
    
    
    private class rLabel extends JLabel
    {
        private ArrayList<Resident> myResidents = new ArrayList<Resident>();

        private Resident myResident;

        private boolean isAdded = false;
        
        private boolean shouldRemove = false;

        public rLabel()
        {
            super();
        }

        public void setResident(Resident r)
        {
            myResident = r;
            setForeground(r.getColor());
        }

        public Resident getResident()
        {
            return myResident;
        }

        public void setAdded(boolean b)
        {
            isAdded = b;
        }

        public boolean getAdded()
        {
            return isAdded;
        }

        public void updateText()
        {
            setText(myResident.getActionReadout());
        }
        
        public void setRemove()
        {
            shouldRemove = true;
        }
        
        public boolean getRemove()
        {
            return shouldRemove;
        }
    }

    public ResidentReadout()
    {
        setBackground (Color.black);
    }

    public void setResidents(ArrayList<Resident> r)
    {
        myResidents = r;
    }

    public void paintComponent (Graphics page)
    {
        super.paintComponent(page);

        ArrayList<Resident> labeledResidents = new ArrayList<Resident>();

        for (rLabel rL : residentLabels)
        {
            labeledResidents.add(rL.getResident());
        }

        for (Resident r : myResidents)
        {
            if (labeledResidents.indexOf(r) == -1 && r != null) //check if the resident needs a label
            { 
                rLabel rL = new rLabel(); //set up a new label for the current resident
                rL.setResident(r);
                residentLabels.add(rL);
            }
        }

        for (rLabel r : residentLabels)
        {
            r.updateText();
            if (!r.getAdded()) //check if there is already a label on screen
            {
                r.setAdded(true); 
                add(r);
            }
            else
            {
                // check if the resident is no longer in the building, and thus no longer on our list
                if (myResidents.indexOf(r.getResident()) == -1)
                {
                    remove(r);
                }
            }
            
        }
    }
}
