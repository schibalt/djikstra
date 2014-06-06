
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;

/**
COPYRIGHT (C) 2012 Andrew Tilisky. All Rights Reserved.
 * This object is instantiated by the driver class and is the user's
 * starting point.  Their input will trigger the reading and hashing
 * in the solver object.
@author Andrew Tilisky
 */

/*
 * This listener is registered with everything in the GUI and uses a system
 * of conditions to trigger the correct functions.
 */
public class SolverListener implements ActionListener
{
    private Dijkstra dijkstraLogic;
    private DijkstraGUI GUI;
    
    SolverListener(DijkstraGUI GUI)
    {
        this.GUI=GUI;
         dijkstraLogic = new Dijkstra();
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (e.getActionCommand().equals("New Map"))
        {
            final JFileChooser mapChooser = new JFileChooser("dictionary");
            mapChooser.showOpenDialog(mapChooser);

            if (mapChooser.getSelectedFile() != null)
            {
                dijkstraLogic.readMap(mapChooser.getSelectedFile());
            }
            GUI.updateCombo(dijkstraLogic.getCities());
            
        } else if (e.getActionCommand().equals("Exit"))
        {
            System.exit(1);

        } else if (e.getActionCommand().equals("Search"))
        { // filter
            Date time = new Date();

            long startTime = time.getTime();

            try
            {
                GUI.drawOutcome(dijkstraLogic.getShortestPath(GUI.getOrigin(), GUI.getDestination()));
                
            } catch (NullPointerException t)
            {
                Logger.getLogger(SolverListener.class.getName()).log(Level.SEVERE, null, t);
            }

            time = new Date();
            long elapsedtime = time.getTime() - startTime;

            System.out.println("Elapsed search time: " + elapsedtime + " ms");
            System.out.println("\t\t\t" + (time.getTime() - startTime) / 1000 + " s");
            System.out.println("\t\t\t" + (time.getTime() - startTime) / 60000 + " m");
        }
    }
} // SolverListener