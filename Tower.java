import java.awt.*;
import javax.swing.*;
import java.util.Random; 
import java.util.ArrayList;

/**
 * Panel
 * 
 * @author @bnmathews
 * @version 30 March 2015
 */
public class Tower extends JPanel
{
    // the dimensions of the Tree Panel
    private final int PANEL_WIDTH = 1000;
    private final int PANEL_HEIGHT = 1000;

    //resident variables
    private int res_minTimeOut;
    private int res_maxTimeOut;

    private int startingFloors;
    private int startingRooms;

    private int startingX;
    private int startingY;

    private int budget;

    private Room[][] rooms;

    private ArrayList<Resident> awayResidents = new ArrayList<Resident>();

    private int elevatorDelay = 3;

    private JFrame containingFrame;

    public Tower(JFrame frame, int floors,int rooms, int x, int y,int b, int minT, int maxT)
    {
        containingFrame = frame;

        res_minTimeOut = minT;
        res_maxTimeOut = maxT;

        budget = b;

        if (budget >= 3)
        {
            startingFloors = 5;
            startingRooms = 3;
        }
        else if (budget>= 2)
        {
            startingFloors = 2;
            startingRooms = 2;
        }
        else
        {
            startingFloors = 1;
            startingRooms = 2;
        }

        startingX = x;
        startingY = y;

        setBackground(Color.black);
        makeFloors(startingFloors,startingRooms);
        addResident();
        displayResidents();
    }

    public void addResident()
    {
        boolean residentAdded = false;
        //int row = (int)(Math.random() * rooms.length);
        int row = 4;
        //int col = (int)(Math.random() * rooms[0].length);
        int col = 0;
        if (rooms[row][col] != null)
        {
            if (rooms[row][col].getOccupation() == false && ( !rooms[row][col].getType().equals("e")))
            {
                if (residentAdded == false)
                {
                    Resident r = new Resident(res_minTimeOut,res_maxTimeOut,row,col);
                    rooms[row][col].setResident(r);
                    rooms[row][col].setOccupied(true);
                    rooms[row][col].setColor(r.getColor());
                    residentAdded = true;

                    System.out.println(r.getName() 
                        + " is staying in " + rooms[row][col].getName() + " for " 
                        + r.getStayTime() + " days.");
                }
            }
        }
    }

    public void displayResidents()
    {
        for (int row = 0; row < rooms.length; row++) 
        {
            for (int col = 0; col < rooms[row].length; col++)
            {
                Room currentRoom = rooms[row][col];
                if (currentRoom != null)
                {
                    if (!currentRoom.getType().equals("e") && currentRoom.getResident() != null)
                    {
                        //System.out.println(currentRoom.getResident().getName() 
                        //    + " is staying in " + currentRoom.getName() + " for " 
                        //    + currentRoom.getResident().getStayTime() + " days.");
                    }
                    else if (!rooms[row][col].getType().equals("e"))
                    {
                        //System.out.println("Nobody is staying in " + rooms[row][col].getName());
                    }
                }
            }
        }
    }

    public int[] getNearestElevator(int r, int c)
    {
        boolean elevatorFound = false;
        int[] loc = new int[2]; //first position is the elevator's row, the second is the column
        for (int row = r; row < rooms.length; row++) 
        {
            for (int col = c; col < rooms[row].length; col++)
            {
                if (rooms[row][col] != null && rooms[row][col].getType().equals("e"))
                {
                    if (!elevatorFound) //if an elevator is found, return its coordinates in the room array
                    {
                        loc[0] = row;
                        loc[1] = col;
                        elevatorFound = true;
                    }
                }
            }
        }
        return loc;
    }

    public void getAwayResidents()
    {
        //System.out.print("Away Residents: ");
        for (Resident r : awayResidents)
        {
            System.out.println(r.getName());
        }
    }

    public boolean checkElevator(int col)
    {
        boolean open = true;
        for (int row = 0; row < rooms.length; row++)
        {
            if (rooms[row][col] != null && rooms[row][col].getType().equals("e"))
            {
                Elevator currentElevator = (Elevator) (rooms[row][col]);
                if (currentElevator.getResident() != null)
                    open = false;
            }
        }
        System.out.println(open);
        return open;
    }

    public void moveElevatorDown()
    {
        boolean found = false;
        for (int row = 0; row < rooms.length; row++)
        {
            for (int col = 0; col < rooms[row].length; col++)
            {
                found = false;
                if (rooms[row][col] != null && rooms[row][col].getType().equals("e") && found == false)
                {
                    Elevator currentElevator = (Elevator)(rooms[row][col]);
                    if (currentElevator.getOccupation() == true && currentElevator.getMovingUp() == false) 
                    {
                        found = true;
                        if (row != 0)
                        {
                            Elevator nextElevator = (Elevator) (rooms[row-1][col]);
                            if (nextElevator.getOccupation() == false)
                            {
                                currentElevator.transferElevators(nextElevator);
                            }
                        }
                        else
                        {
                            for (Resident r : currentElevator.getAllResidents())
                            {
                                awayResidents.add(r); //the residents are leaving the elevator, and leaving the building.
                                if (r != null)
                                {
                                    r.resetTimeOut();
                                }
                            }
                            currentElevator.removeAllResidents();
                        }
                    }
                }
            }
        }
    }

    public void moveElevatorUp(Resident r, int row, int col)
    throws InterruptedException
    {
        Elevator currentElevator = (Elevator)(rooms[row][col]);
        int maxRow = r.getRoomLocation()[0];
        System.out.println("ROW: " + row);
        if (row < maxRow)
        {
            if (currentElevator.getOccupation() == false)
            {
                currentElevator.setMovingUp(true);
                Elevator nextElevator = (Elevator) (rooms[row+1][col]);
                //System.out.println("Next elevator is @ " + (row+1));
                nextElevator.setColor(r.getColor());
                currentElevator.transferElevators(nextElevator);
                currentElevator.setMovingUp(false);
            }
        }
        else if (row == 0)
        {
            if (currentElevator.getOccupation() == false)
            {
                System.out.println("Row is 0");
                currentElevator.setMovingUp(true);
                currentElevator.addOccupant(r); //this has to happen earlier
                ((Elevator)(rooms[0][col])).setColor(r.getColor());
                //currentElevator.setColor(r.getColor());
                Elevator nextElevator = (Elevator) (rooms[row+1][col]);
                currentElevator.transferElevators(nextElevator);
                currentElevator.setMovingUp(false);
            }
        }
        else
        {
            currentElevator.removeAllResidents();
            currentElevator.setMovingUp(false);
            r.setOnElevator(false);
            r.setIsOut(false);
            rooms[r.getRoomLocation()[0]][r.getRoomLocation()[1]].setColor(r.getColor());
        }
    }

    public void updateResidents() //make the residents perform actions
    throws InterruptedException
    {
        for (int row = 0; row < rooms.length; row++) 
        {
            for (int col = 0; col < rooms[row].length; col++)
            {
                Room currentRoom = rooms[row][col];
                if (currentRoom != null && !currentRoom.getType().equals("e"))
                {
                    Resident currentResident = currentRoom.getResident();
                    if (currentResident != null)
                    {
                        if (currentResident.getOnElevator() == false) //make sure this fires only once
                        {
                            if (currentResident.getStayTime() < 1)
                            {
                                System.out.println(currentResident.getName() + " has moved out!");
                                currentRoom.setResident(null); //the resident is no longer tied to that room
                                currentRoom.setColor(new Color(155,155,155));
                                currentRoom.setOccupied(false); //no one is in the room
                            }
                            else
                            {
                                if (currentResident.doAction().equals("nothing"))
                                {
                                    System.out.println(currentResident.getName() + " is hanging out in their room.");
                                    System.out.println(currentResident.getName() + " has " + currentResident.getStayTime() + " days remaining.");
                                    currentResident.updateStayTime();
                                }
                                else if (currentResident.doAction().equals("out") && checkElevator(col+1) == true)
                                {
                                    Room currentElevatorRoom = rooms[getNearestElevator(row,col)[0]][getNearestElevator(row,col)[1]]; //find the most convenient elevator to the current room
                                    Elevator currentElevator = (Elevator)(currentElevatorRoom);
                                    //System.out.println(currentResident.getName() + " will leave for a while.");
                                    currentResident.setOnElevator(true);
                                    currentElevator.addOccupant(currentResident); 
                                    currentRoom.setColor(currentResident.getColor().darker());
                                }
                            }
                        }
                    }
                }
            }
        }

        ArrayList<Resident> residentsToBringBack = new ArrayList<Resident>();

        for (Resident r : awayResidents) //the people not in the building are going about their days
        {
            if (r != null) 
            {
                if (r.getTimeOut() > 0)
                {
                    r.decreaseTimeOut();
                }
                else
                {
                    //rooms[r.getRoomLocation()[0]][r.getRoomLocation()[1]].setColor(r.getColor());
                    System.out.println(r.getName() + " is back.");
                    //r.setOnElevator(false);
                    //awayResidents.set(awayResidents.indexOf(r),null);
                    //residentsToBringBack.add(r);

                    System.out.println("Checking if the elevator is clear...");
                    if (checkElevator(r.getRoomLocation()[1] + 1) == true)
                    {
                        System.out.println("Elevator at " + (r.getRoomLocation()[1] + 1) + " is open."); //check the elevator rowcol to the right of the room
                        for (int step = 0; step < r.getRoomLocation()[0] + 1; step++) //move the elevator up one space for every floor
                        {
                            moveElevatorUp(r,step, (r.getRoomLocation()[1]) + 1 ); 
                            containingFrame.repaint();
                            //moveElevatorDown();
                            Thread.sleep(100);
                        }
                        awayResidents.set(awayResidents.indexOf(r),null);
                        residentsToBringBack.add(r);
                    }
                }
            }
        }

        for (Resident r : residentsToBringBack)
        {
            awayResidents.remove(r);
        }
    }

    public Room[][] makeFloors(int numFloors, int numRooms)
    {
        rooms = new Room[numFloors][numRooms];

        for (int row = 0; row < rooms.length; row++)
        {
            for (int col = 0; col < rooms[row].length; col++)
            {
                rooms[row][col] = new Room("Room " + ((col + 1) + (row))); //do this so we get room 1, room 2, room 3, etc
            }
        }

        int height = rooms.length;
        int width = rooms[0].length*2;

        for (int row = 0; row < height; row++)
        {
            for (int col = 0; col < width; col++)
            {
                if (col % 2 != 0 && (rooms.length > 1)) //for every other room, add an elevator
                {
                    Room elevator = new Elevator("Elevator " + ((col + 1) + (row)));
                    insertRoom(elevator,row,col);
                }
            }
        }
        System.out.println("Floors Made");
        displayFloors();

        return rooms;
    }

    public void extendFloors() //may or may not be needed
    {
        Room[][] newRooms = new Room[rooms.length][rooms[0].length+1];

        for (int row = 0; row < rooms.length; row++)
        {
            for(int col = 0; col < rooms[row].length; col++)
            {
                newRooms[row][col] = rooms[row][col];
            }
        }

        rooms = newRooms;
    }

    public void insertRoom(Room roomToInsert, int insertionPoint, int secondPoint) //not for adding rooms, as such it will only be able
    //to splice a room into the current array, no more
    {
        Room[][] newRooms = new Room[rooms.length][rooms[0].length + 1];

        boolean inserted = false;

        for (int row = 0; row < newRooms.length; row++)
        {
            int pos = 0;
            for (int col = 0; col < newRooms[row].length; col++)
            {
                if (row == insertionPoint)
                {
                    if (col == secondPoint)
                    {
                        newRooms[row][col] = roomToInsert;
                    }
                    else
                    {
                        newRooms[row][col] = rooms[row][pos];
                        pos++;
                    }
                }
                else
                {
                    if (col < newRooms[row].length - 1)
                    {
                        newRooms[row][col] = rooms[row][pos];
                        pos++;
                    }
                }
            }
        }

        rooms = newRooms;
    }

    public void displayFloors()
    {
        System.out.println();
        for (int row = 0; row < rooms.length; row++)
        {
            for (int col = 0; col < rooms[row].length; col++)
            {
                if (rooms[row][col] != null)
                {
                    System.out.print(rooms[row][col].getType());
                }
                else
                    System.out.print("n");
            }
            System.out.println();
        }
    }

    public void paintComponent (Graphics page)
    {
        super.paintComponent(page);
        drawBuilding(page);
    }

    public void drawBuilding(Graphics page)
    {
        int nextX = 0;
        int nextY = 0;
        for (int row = 0; row < rooms.length; row++)
        {
            for (int col = 0; col < rooms[row].length; col++)
            {
                if (rooms[row][col] != null)
                {
                    rooms[row][col].constructRoom(page,startingX,startingY,nextX,nextY, this); //draws the room
                    nextX+=rooms[row][col].getWidth(); // go right as wide as the last drawn room was
                }
            }
            nextX = 0;
            nextY-=rooms[row][0].getHeight(); // go up another block
        }
    }
}
