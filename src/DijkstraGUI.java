
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.KeyEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

/**
COPYRIGHT (C) 2012 Andrew Tilisky. All Rights Reserved.
 * This object is instantiated by the driver class and is the user's
 * starting point.  Their input will trigger the reading and hashing
 * in the solver object.
@author Andrew Tilisky
 */
/**
 *
 * @author Andrew Tilisky
 */
public class DijkstraGUI extends JFrame
{
    private SolverListener actionList;
    private JScrollPane listScroller;
    
        private JComboBox originBox;
        private JComboBox destinationBox ;
        
        private JPanel controlPane = new JPanel(); // pane for controls
    private JPanel outcomePane;

    /*this sets up the main app screen that will persist until the application 
     * is terminated.  
     */
    DijkstraGUI()
    {

        JMenuBar menuBar = new JMenuBar(); // bar
        JMenu fileMenu = new JMenu("File"); //file options (new, save, quit)
        fileMenu.setMnemonic(KeyEvent.VK_F);
        JMenuItem newDict = new JMenuItem("New Map");
        newDict.setMnemonic(KeyEvent.VK_D);
        JMenuItem exitItem = new JMenuItem("Exit");
        exitItem.setMnemonic(KeyEvent.VK_E);

        // add files options to menu
        fileMenu.add(newDict);
        fileMenu.add(exitItem);

        JButton goButton = new JButton("Search");

        goButton.setMnemonic(KeyEvent.VK_S);

        controlPane.add(goButton);

        actionList = new SolverListener(this);

        //component listener registration
        newDict.addActionListener(actionList);
        exitItem.addActionListener(actionList);
        goButton.addActionListener(actionList);

        controlPane.setLayout(new FlowLayout()); // controls added sequentially
        setJMenuBar(menuBar); //menuBar for the app
        menuBar.add(fileMenu); // add file option button for bar

        add(controlPane, BorderLayout.NORTH);
        outcomePane = new JPanel();
    }

    /*
     * this is called if the puzzle search operation succeeds.  a text area is used not 
     * a JList but regardless it's added to its new scrollpane and then to the southern border.
     * the string's derived from within the while loops in the Solver's solve() method.
     */
    public void drawOutcome(String report)
    {
        outcomePane.removeAll();
        try
        {
            JTextArea ta = null;
                ta = new JTextArea(report, 5, 50);
            ta.setLineWrap(true);
            JScrollPane sbrText = new JScrollPane(ta);
            sbrText.setPreferredSize(new Dimension(160, 160));
            sbrText.setAlignmentX(LEFT_ALIGNMENT);

            outcomePane.setLayout(new BoxLayout(outcomePane, BoxLayout.PAGE_AXIS));
            outcomePane.add(sbrText);
            outcomePane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

            add(outcomePane, BorderLayout.CENTER);

            validate();
            repaint();

        } catch (NullPointerException ex)
        {
            Logger.getLogger(SolverListener.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public String getOrigin(){
        return originBox.getSelectedItem().toString();
    }
    
    public String getDestination(){
        return this.destinationBox.getSelectedItem().toString();
    }

    public void updateCombo(String[] cities)
    {
        originBox=new JComboBox(cities);
        this.destinationBox=new JComboBox(cities);
        controlPane.add(originBox);
        controlPane.add(destinationBox);
        super.validate();
    }
}
