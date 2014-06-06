import javax.swing.*;
import java.awt.*;
import java.io.*;

//DO NOT MODIFY THIS PROGRAM!!!

/**
	This is the driver program for the shortest path project
*/
public class DijkstraDriver 
{	
	public static void main(String[] args) throws IOException {		
		DijkstraGUI p5 = new DijkstraGUI();
		p5.setSize(new Dimension(500,600));
		p5.setVisible(true);
		p5.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
