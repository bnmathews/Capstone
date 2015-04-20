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
                rooms[row][col] = new Room();
                if (col % 2 == 0)
                {
                    Room elevator = new Elevator();
                    
                    System.out.println("Elevator added");
                }
            }
        }
        
        /*
        for(int col = 0; col < rooms[1].length; col++)
           {
               for (int row = 0; row < rooms.length; row++)
               {
                    Room elevator = new Elevator();
                    if (col % 2 == 0)
                    {
                        insertRoom(elevator,col);
                    }
                    else
                    {
                        rooms[row][col] = new Room();
                    }
               }
           }
           */
        Room elevator = new Elevator();
        //insertRoom(elevator,2);
        insertRoom(elevator,1,0);
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
        
        Room[][] firstHalf = new Room[rooms.length][insertionPoint+1];
        System.out.println("First half width: " + firstHalf[1].length);
        
        Room[][] secondHalf = new Room[rooms.length][rooms[1].length - (firstHalf[1].length-1)];
        System.out.println("Second half width: " + secondHalf[1].length);
        
        Room[][] newRooms = new Room[rooms.length][rooms[1].length + 1];
        System.out.println("Final width: " + newRooms[1].length);
        
        int pos = 0;
        
        for (int row = 0; row < firstHalf.length; row++)
        {
            pos = 0;
            for (int col = 0; col <= firstHalf[row].length-1; col++)
            {
                if ( col == (firstHalf[1].length-1) )
                {
                    if (secondPoint != row)
                    {
                        firstHalf[row][col] = rooms[row][col];
                    }
                    else
                    {
                        firstHalf[secondPoint][col] = roomToInsert;
                    }
                }
                else
                {
                    firstHalf[row][col] = rooms[row][col];
                    pos++; //increment here to keep track of the position in 'rooms'
                }
            }
        }
        
        System.out.println("Current position in rooms is: " + pos);
        
        for (int row = 0; row < secondHalf.length; row++)
        {
            int col2 = 0;
            for (int col = pos; col < rooms[row].length; col++)
            {
                secondHalf[row][col2] = rooms[row][col];
                col2++;
            }
        }
        
        System.out.println("First Half Array:");
        for (int row = 0; row < firstHalf.length; row++)
        {
            for (int col = 0; col < firstHalf[row].length; col++)
            {
                if (firstHalf[row][col] != null)
                {
                    System.out.print(firstHalf[row][col].getName());
                }
                else
                    System.out.print("n");
            }
            System.out.println();
        }
        
        System.out.println("Second Half Array:");
        for (int row = 0; row < secondHalf.length; row++)
        {
            for (int col = 0; col < secondHalf[row].length; col++)
            {
                if (secondHalf[row][col] != null)
                {
                    System.out.print(secondHalf[row][col].getName());
                }
                else
                    System.out.print("n");
            }
            System.out.println();
        }
        
        
        for (int row = 0; row < firstHalf.length; row++)
        {
            pos = 0; //this variable makes sure we start from the beginning of the second array, rather than
                     //just using the current column value, which would likely be greater than the length of
                     //a column in the second array
            for (int col = 0; col < newRooms[row].length; col++)
            {
                if (col < firstHalf[row].length)
                {
                    if (firstHalf[row][col] != null)
                    {
                        newRooms[row][pos] = firstHalf[row][pos];
                        pos++;
                    }
                }   
                else
                {
                   pos = 0;
                   if (secondHalf[row][pos] != null)
                    {
                        newRooms[row][col] = secondHalf[row][pos];
                        pos++;
                    } 
                }
            }
        }
        
        for (int row = 0; row < newRooms.length; row++)
        {
            for (int col = 0; col < newRooms[row].length; col++)
            {
                if (newRooms[row][col] != null)
                {
                    System.out.print(newRooms[row][col].getName());
                }
                else
                    System.out.print("n");
            }
            System.out.println();
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
        page.setColor (Color.white);
        drawBlock(page);
    }
    
    public void drawBlock(Graphics page)
    {
        int nextX = 0;
        int nextY = 0;
        //System.out.println("Current Floor Plan:");
        //displayFloors();
        for (int row = 0; row < rooms.length; row++)
        {
            for (int col = 0; col < rooms[row].length; col++)
            {
                if (rooms[row][col] != null)
                {
                    rooms[row][col].constructRoom(page,20,20,nextX,nextY);
                    nextX+=rooms[row][col].getWidth(); // go right as wide as the last drawn room was
                }
            }
            nextX = 0;
            nextY+=rooms[row][1].getHeight(); // go up another block
        }
        //System.out.println("Updated Floor Plan:");
        //displayFloors();
    }
}
