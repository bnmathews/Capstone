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
    private Room[][] rooms;
    
    public Tower(int floors,int rooms)
    {
        startingFloors = floors;
        startingRooms = rooms;
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
                /*
                if (col % 2 == 0)
                {
                    Room elevator = new Elevator();
                    insertRoom(elevator,1);
                    System.out.println("Elevator added");
                }
                else
                {
                    rooms[row][col] = new Room();
                }
                */
                rooms[row][col] = new Room();
            }
        }
        Room elevator = new Elevator();
        insertRoom(elevator,1);
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
    
    public void insertRoom(Room roomToInsert, int insertionPoint)
    {
        System.out.println("Rooms width: " + rooms[1].length);
        Room[][] firstHalf = new Room[rooms.length][insertionPoint+1];
        System.out.println("First half width: " + firstHalf[1].length);
        Room[][] secondHalf = new Room[rooms.length][rooms[1].length - (firstHalf[1].length-1)];
        System.out.println("Second half width: " + secondHalf[1].length);
        Room[][] newRoom = new Room[rooms.length][rooms[1].length + 1];
        System.out.println("Final width: " + newRoom[1].length);
        
        for (int row = 0; row < firstHalf.length; row++)
        {
            for (int col = 0; col <= firstHalf[row].length-1; col++)
            {
                if ( col == (firstHalf[1].length-1) )
                {
                    firstHalf[row][col] = roomToInsert;
                }
                else
                {
                    firstHalf[row][col] = rooms[row][col];
                }
            }
        }
        
        for (int row = 0; row < secondHalf.length; row++)
        {
            for (int col = 0; col < secondHalf[row].length; col++)
            {
                secondHalf[row][col] = rooms[row][col];
            }
        }
        
        for (int row = 0; row < firstHalf.length; row++)
        {
            for (int col = 0; col < (firstHalf[row].length + secondHalf[row].length) - 1; col++)
            {
                if (col < firstHalf[row].length)
                {
                    if (firstHalf[row][col] != null)
                    {
                        newRoom[row][col] = firstHalf[row][col];
                    }
                }   
                else
                {
                   if (secondHalf[row][col] != null)
                    {
                        newRoom[row][col] = secondHalf[row][col];
                    } 
                }
            }
        }

        System.out.println("Final Array:");
        for (int row = 0; row < newRoom.length; row++)
        {
            for (int col = 0; col < newRoom[row].length; col++)
            {
                if (newRoom[row][col] != null)
                {
                    System.out.print(newRoom[row][col].getName());
                }
                else
                    System.out.print("n");
            }
            System.out.println();
        }
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
        page.setColor (Color.white);
        drawBlock(page);
    }
    
    public void drawBlock(Graphics page)
    {
        int increase = 0;
        int heightIncrease = 0;
        System.out.println("Current Floor Plan:");
        displayFloors();
        for (int row = 0; row < rooms.length; row++)
        {
            for (int col = 0; col < rooms[row].length; col++)
            {
                /*
                if (col % 2 == 0)
                {
                    extendFloors();
                    Room elevator = new Elevator();
                    insertRoom(elevator,col);
                    System.out.println("Elevator added");
                }
                */
                //else
                //{
                    if (rooms[row][col] != null)
                    {
                        rooms[row][col].constructRoom(page,increase,heightIncrease);
                        increase+=30;
                    }
                    
                //}
            }
            increase = 0;
            heightIncrease+=30;
        }
        System.out.println("Updated Floor Plan:");
        displayFloors();
    }
}
