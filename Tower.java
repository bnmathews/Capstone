import java.awt.*;
import javax.swing.JPanel;
import java.util.Random; 

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

    private int startingFloors;
    private int startingRooms;
    
    private int startingX;
    private int startingY;
    
    private int budget;
    
    private Room[][] rooms;

    public Tower(int floors,int rooms, int x, int y,int b)
    {
        budget = b;
        
        if (budget >= 3)
        {
            startingFloors = 3;
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
        System.out.println("The nearest elevator to room 0,2 is at " + getNearestElevator(0, 2)[0] + "," + getNearestElevator(0, 2)[1]);
        displayResidents();
    }
    
    public void addResident()
    {
        Resident r = new Resident();
        boolean residentAdded = false;
        for (int row = 0; row < rooms.length; row++) 
        {
            for (int col = 0; col < rooms[row].length; col++)
            {
                if (rooms[row][col] != null)
                {
                    if (rooms[row][col].getOccupation() == false && ( !rooms[row][col].getType().equals("e")))
                    {
                        if (residentAdded == false)
                        {
                            rooms[row][col].setResident(r);
                            rooms[row][col].setOccupied(true);
                            residentAdded = true;
                            
                            System.out.println(r.getName() 
                            + " is staying in " + rooms[row][col].getName() + " for " 
                            + r.getStayTime() + " days.");
                        }
                    }
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
                        System.out.println(currentRoom.getResident().getName() 
                        + " is staying in " + currentRoom.getName() + " for " 
                        + currentRoom.getResident().getStayTime() + " days.");
                    }
                    else if (!rooms[row][col].getType().equals("e"))
                    {
                        System.out.println("Nobody is staying in " + rooms[row][col].getName());
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
    
    public void updateResidents() //make the residents perform actions
    {
        for (int row = 0; row < rooms.length; row++) 
        {
            for (int col = 0; col < rooms[row].length; col++)
            {
                Room currentRoom = rooms[row][col];
                if (currentRoom != null)
                {
                    Resident currentResident = currentRoom.getResident();
                    if (!currentRoom.getType().equals("e") && currentResident != null)
                    {
                        if (currentResident.doAction().equals("nothing"))
                        {
                            System.out.println(currentResident.getName() + " is hanging out in their room.");
                        }
                        else if (currentResident.doAction().equals("work"))
                        {
                            int[] elevatorPos = getNearestElevator(row,col);
                            Room currentElevatorRoom = rooms[elevatorPos[0]][elevatorPos[1]];
                            Elevator currentElevator = (Elevator)(currentElevatorRoom);
                            currentRoom.setOccupied(false);
                            System.out.println(currentResident.getName() + " is going to work.");
                            System.out.println("The nearest elevator the room is" + getNearestElevator(row, col)[0] + "," + getNearestElevator(row, col)[1]);
                            currentElevator.addOccupant(currentResident); //find the most convenient elevator to the current room
                        }
                        
                        if (currentResident.getStayTime() < 1)
                        {
                            System.out.println(currentResident.getName() + " has moved out!");
                            currentRoom.setResident(null); //the resident is no longer tied to that room
                            currentRoom.setOccupied(false); //no one is in the room
                        }
                    }
                }
            }
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
                if (rooms[row][col] != null) //only make the elevators if there is more than one floor
                {
                    Color currColor = new Color(155,155,155);
                    if (rooms[row][col].getType().equals("e"))
                    {
                        currColor = new Color(100,100,100);
                    }
                    
                    if (rooms[row][col].getResident() != null)
                    {
                        currColor = rooms[row][col].getResident().getColor();
                    }
                    rooms[row][col].constructRoom(page,startingX,startingY,nextX,nextY,currColor); //draws the room
                    nextX+=rooms[row][col].getWidth(); // go right as wide as the last drawn room was
                }
            }
            nextX = 0;
            nextY-=rooms[row][0].getHeight(); // go up another block
        }
    }
}
