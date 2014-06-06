
import java.awt.Dimension;
import java.io.IOException;
import javax.swing.JFrame;

//DO NOT MODIFY THIS PROGRAM!!!
/**
This is the driver program for the shortest path project
 */
public class DijkstraDriver
{
    public static void main(String[] args) throws IOException
    {
        DijkstraGUI p5 = new DijkstraGUI();
        p5.setSize(new Dimension(500, 400));
        p5.setVisible(true);
        p5.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
