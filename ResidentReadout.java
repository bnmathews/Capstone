import java.awt.*;
import javax.swing.*;
import java.util.Random; 
import java.util.ArrayList;

/**
 * Readout
 * 
 * @author @bnmathews
 * @version 30 March 2015
 */
public class ResidentReadout extends JPanel
{
    private class rLabel extends JLabel
    {
        private ArrayList<Resident> myResidents = new ArrayList<Resident>();

        private Resident myResident;

        private boolean isAdded = false;

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
    }

    // the dimensions of the Tree Panel
    private final int PANEL_WIDTH = 1000;
    private final int PANEL_HEIGHT = 1000;

    private ArrayList<Resident> myResidents = new ArrayList<Resident>();

    private ArrayList<rLabel> residentLabels = new ArrayList<rLabel>();

    public ResidentReadout()
    {
        setBackground (Color.black);
        //this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
    }

    public void setResidents(ArrayList<Resident> r)
    {
        myResidents = r;
        //this.setText(myResidents.get(0).getName()+"\nMORE RESIDENTS");
    }

    public void paintComponent (Graphics page)
    {
        super.paintComponent(page);
        /*
        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setLineWrap(true);
        textArea.setOpaque(false);
        textArea.setForeground(Color.white);
        textArea.setText("AAA");
        add(textArea, BorderLayout.CENTER);
         */

        ArrayList<Resident> labeledResidents = new ArrayList<Resident>();

        for (rLabel rL : residentLabels)
        {
            labeledResidents.add(rL.getResident());
        }

        for (Resident r : myResidents)
        {
            if (labeledResidents.indexOf(r) == -1) //check if the resident needs a label
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
                //if not, add the current label to the screen
                r.setAdded(true); 
                add(r);
                
            }
            else
            {
                // check if the resident is no longer in the building
                if (myResidents.indexOf(r.getResident()) == -1)
                {
                    remove(r);
                }
            }
            
        }
    }
}
