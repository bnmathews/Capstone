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
    
    private Room[][] rooms;

    public Tower(int floors,int rooms, int x, int y)
    {
        startingFloors = floors;
        startingRooms = rooms;
        
        startingX = x;
        startingY = y;
        
        setBackground(Color.black);
        makeFloors(floors,rooms);
    }

    public void makeNewColor()
    {
        // create a new Random object
        Random r = new Random();

        // make a new Color based on a full range of random integers created by object r
        Color newColor = new Color(r.nextInt(255-1),r.nextInt(255-1),r.nextInt(255-1));
    }

    public Room[][] makeFloors(int numFloors, int numRooms)
    {
        rooms = new Room[numFloors][numRooms];

        for (int row = 0; row < rooms.length; row++)
        {
            for (int col = 0; col < rooms[row].length; col++)
            {
                rooms[row][col] = new Room();
            }
        }
        
        int height = rooms.length;
        int width = rooms[1].length*2;
        
        for (int row = 0; row < height; row++)
        {
            for (int col = 0; col < width; col++)
            {
                if (col % 2 != 0) //for every other room, add an elevator
                {
                    Room elevator = new Elevator();
                    insertRoom(elevator,row,col);
                    System.out.println("Elevator added");
                }
            }
        }
        System.out.println("Floors Made");
        displayFloors();

        return rooms;
    }

    public void extendFloors() //may or may not be needed
    {
        Room[][] newRooms = new Room[rooms.length][rooms[1].length+1];

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
        System.out.println("Rooms width: " + rooms[1].length);
        System.out.println("Rooms height: " + rooms.length);

        Room[][] newRooms = new Room[rooms.length][rooms[1].length + 1];
        System.out.println("Final width: " + newRooms[1].length);

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
                    System.out.print(rooms[row][col].getName());
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
        drawBlock(page);
    }

    public void drawBlock(Graphics page)
    {
        int nextX = 0;
        int nextY = 0;
        for (int row = 0; row < rooms.length; row++)
        {
            for (int col = 0; col < rooms[row].length; col++)
            {
                if (rooms[row][col] != null)
                {
                    rooms[row][col].constructRoom(page,startingX,startingY,nextX,nextY); //draws the room
                    nextX+=rooms[row][col].getWidth(); // go right as wide as the last drawn room was
                }
            }
            nextX = 0;
            nextY-=rooms[row][1].getHeight(); // go up another block
        }
    }
}
