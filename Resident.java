import java.util.Random;
import java.awt.*;
public class Resident
{
    private int money;
    private int timeToStay;

    private int minTimeOut;
    private int maxTimeOut;

    private int[] roomLocation = new int[2];

    private int timeOut = 0; //the time to spend out of the building

    private boolean isOut = false;
    private boolean isOnElevator = false;
    private boolean actionIsChanged = false;
    private boolean isOffBuilding = false;
    private Color color;

    // name related stuff
    private String gender;
    private String name;
    private String previousReadout = "N/A";
    private String[] namePrefix = {"Apple","Bil","Kick","Head","Work","Shell","Rod","Care","Hipp","Flipp","Still","Tru","Happ","Fell","Fist"};
    private String[] nameSuffix = {"long","iam","worker","maker","breaker","age","man","mack","back","land","ings","-Bo","ins","-Millar","beef"};

    // action related stuff
    private String action; 
    private String actionReadout;
    private String[] actions = {"exercising","exorcising a demon","praying","playing video games","drinking","dancing",
            "hanging out","eating","using the bathroom","working out","pondering life's mysteries",
            "making poor decisions","getting advice","loafing","singing","playing music","writing poetry",
            "passed out","searching for Bigfoot","playing dead","building a model kit","making cookies","doing squats"};

    private String[] outActions = {"doing odd jobs","at work","working","performing","doing maintenance work","helping out","stopping a robbery",
            "playing pranks on people","on a date","loitering","littering"};

    private String[] outLocations = {"the club","a local bar","their parents' house","their friend's house","the gym","the coffee house","the comedy club",
            "the theatre","the cemetery","the hospital","the office","the public pool","the spookhouse"};

    public Resident(int minTimeOut, int maxTimeOut, int roomRow, int roomCol)
    {
        if ( (int) (Math.random() * 2) == 1)
            gender = "Mr. ";
        else
        {
            if ( (int) (Math.random() * 2) == 1)
                gender = "Mrs. ";
            else
                gender = "Miss ";
        }
        name = gender + namePrefix[ (int) (Math.random()*namePrefix.length) ] + nameSuffix[ (int) (Math.random()*nameSuffix.length) ];

        timeToStay = (int)(Math.random() * 30) + 1;

        this.minTimeOut = minTimeOut;
        this.maxTimeOut = maxTimeOut;

        roomLocation[0] = roomRow;
        roomLocation[1] = roomCol;

        makeColor();
    }

    public int[] getRoomLocation()
    {
        return roomLocation;
    }

    public void resetTimeOut()
    {
        Random r = new Random();
        timeOut = r.nextInt((maxTimeOut - minTimeOut) + 1) + minTimeOut;
    }

    public void decreaseTimeOut()
    {
        timeOut--;
    }

    public int getTimeOut()
    {
        return timeOut;
    }

    public void makeColor()
    {
        Random r = new Random();
        color = new Color(r.nextInt((255 - 70) + 1) + 70,r.nextInt((255 - 70) + 1) + 70,r.nextInt((255 - 70) + 1) + 70);
    }

    public Color getColor()
    {
        return color;
    }

    public void updateStayTime()
    {
        if (timeToStay > 0 && isOut == false)
        {
            timeToStay--;
        }
    }

    public String doAction()
    {
        Double chance = Math.random() * 100; //decides what the resident will do
        if (chance > 50)
        {
            action = "nothing";
        }
        else
        {
            action = "out"; //NOTE: this only sends the request for leaving, if the Resident can't go out, it resets
        }

        setActionReadout();

        return action;
    }

    public void setActionReadout()
    {
        String readout = name + " is ";

        if (action.equals("out"))
        {
            if (isOut == true)
            {
                if (action.equals("out")) // check to make sure if the Resident requested to leave the building and they have actually left
                {
                    //normally the resident can only do regular actions, but being out of the building gives them the ability to use extra actions as well.

                    String[] actionListToUse;
                    if ((int)Math.random()*2 == 0)
                    {
                        actionListToUse = actions;
                    }
                    else
                    {
                        actionListToUse = outActions;
                    }

                    readout += (actionListToUse[(int)(Math.random()*actionListToUse.length)] + " at " + outLocations[(int)(Math.random()*outLocations.length)]) + ".";
                }
            }
            else
            {
                if (previousReadout.equals("N/A"))
                {
                    readout += (actions[(int)(Math.random()*actions.length)] + " in their room.");
                    previousReadout = readout;
                }
                else
                {
                    readout = previousReadout;
                }
            }
        }
        else
        {
            if (previousReadout.equals("N/A"))
            {
                readout += (actions[(int)(Math.random()*actions.length)] + " in their room.");
                previousReadout = readout;
            }
            else
            {
                readout = previousReadout;
            }
        }

        actionReadout = readout;

        if (isOffBuilding == false)
        {
            if (isOnElevator)
            {
                actionReadout = name + " is on the elevator.";
            }
        }
    }
    
    public void resetPreviousReadout()
    {
        previousReadout = "N/A";
    }

    public void setOffBuilding(boolean b)
    {
        isOffBuilding = b;
    }

    public String getActionReadout()
    {
        return actionReadout;
    }

    public void setOnElevator(boolean b)
    {
        isOnElevator = b;
        if (b == true)
        {
            setIsOut(b);
            setActionReadout();
        }
    }

    public boolean getOnElevator()
    {
        return isOnElevator;
    }

    public String getName()
    {
        return name;
    }

    public int getStayTime()
    {
        return timeToStay;
    }

    public boolean getIsOut()
    {
        return isOut;
    }

    public void setIsOut( boolean b )
    {
        isOut = b;
    }
}