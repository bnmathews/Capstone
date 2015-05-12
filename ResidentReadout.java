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
public class ResidentReadout extends JTextPane
{
    // the dimensions of the Tree Panel
    private final int PANEL_WIDTH = 1000;
    private final int PANEL_HEIGHT = 1000;

    private ArrayList<Resident> myResidents = new ArrayList<Resident>();

    public ResidentReadout()
    {
        setForeground (Color.white);
    }
    
    public void setResidents(ArrayList<Resident> r)
    {
        myResidents = r;
        this.setText(myResidents.get(0).getName()+"\nMORE RESIDENTS");
    }

    public void paintComponent (Graphics page)
    {
        this.setText(myResidents.get(0).getName());
        super.paintComponent(page);
    }
}
