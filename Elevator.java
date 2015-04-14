import java.awt.*;
public class Elevator extends Room
{
    private String name;
    public Elevator()
    {
        name = "e";
    }
    
    public void constructElevator(Graphics page, int startX, int startY)
    {
        page.drawLine (startX+10, 20, 20, 20);
        page.drawLine (startX+10, 50, 20, 50);
        page.drawLine (20, 20, 20, 50);
        page.drawLine (50, 20, 50, 50);
    }
}
