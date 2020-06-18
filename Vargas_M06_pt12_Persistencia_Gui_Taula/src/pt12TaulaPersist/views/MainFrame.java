
package pt12TaulaPersist.views;

import java.awt.BorderLayout;
;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import pt12TaulaPersist.model.Model;
import pt12TaulaPersist.model.Product;

/**
 *
 * @author Roser
 */
public class MainFrame extends JFrame implements ActionListener{
    
    //attributes
    Model myModel;
    ActionListener myListener;
        
    JMenuBar myBar;
    JMenu myOptionMenu;
    JMenuItem myOptionItem;
    
    JFileChooser myFileChooser;
    
    //constructor
    public MainFrame (Model model) {
        myListener= this;
        this.myModel = model;
        initComponents();
    }
    

    /**
     * Method for initiate basic components of the frame
     * and ask for creating detail components
     */
    private void initComponents() {
         //set a title
        this.setTitle("MyStore load data program");
        // set no default action on closing window...
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        //as we have our own closing method  we add a window listener type WindowAdapter
        this.addWindowListener(new WindowAdapter() {
            //override method for Window Closing
            @Override
            public void windowClosing(WindowEvent we) {
                askForConfirmationExit();
            }
        });
        //create components
        createComponents();
        
        //load test data and repaint panel with them
        loadData();
        setContentPane(new ViewPanel(myModel)); 
        validate();

        //set size of the frame
        this.setSize(500, 200);
        setVisible(true);
        
        }

    /**
     * Method for creating all components all the frame
     */
    private void createComponents() {
        //create a background layout
        this.setLayout(new BorderLayout());
        //create JMenuBar
        myBar = new JMenuBar();
        
        //create each option of the JMenuBar, with its options of JMenuItem, and add it to the JMenuBar
        myOptionMenu = new JMenu("Options");

            //Menu Item: create with name, set ActionCommand, add to listener, and add it to the JMenu
            myOptionItem = new JMenuItem("Search file to open");
            myOptionItem.setActionCommand("open");
            myOptionItem.addActionListener(myListener);
            myOptionMenu.add(myOptionItem);
            
            //Menu Item: create with name, set ActionCommand, add to listener, and add it to the JMenu
            myOptionItem = new JMenuItem("Save data to file");
            myOptionItem.setActionCommand("save");
            myOptionItem.addActionListener(myListener);
            myOptionMenu.add(myOptionItem);
        myBar.add(myOptionMenu);
       
        //create each option of the JMenuBar, with its options of JMenuItem, and add it to the JMenuBar
        myOptionMenu = new JMenu("Exit");
            //Menu Item: create with name, set ActionCommand, add to listener, and add it to the JMenu
            myOptionItem = new JMenuItem("Exit");
            myOptionItem.setActionCommand("exit");
            myOptionItem.addActionListener(myListener);
            myOptionMenu.add(myOptionItem);
        myBar.add(myOptionMenu);
        
        //Add this JMenuBar to the Frame
        this.setJMenuBar(myBar); 
    }
    
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        //to know which action selected
        String action = e.getActionCommand();
        //and create the actions to do 
        switch (action) {
            case "exit":
                askForConfirmationExit();
                break; 
            case "open":
                openFromFile(myModel);
                break;
            case "save":
                writeToFile(myModel);
                break;                
            default: 
                break;
        }
    }
    
    /**
     * Method to create a Confirm Dialog that ask the user to confirm if he/she wants to exit or not
     * if it is OK answer, it will exit
     */
    private void askForConfirmationExit() {
        //create a string with the question.
        String question ="Are you sure you want to exit?";
        //Create Message Dialog and receive the answer in a var
        int answer= JOptionPane.showConfirmDialog(this, question);
        if (answer==JOptionPane.OK_OPTION) {
            System.exit(0);
        }
    }

    /**
     * Method for loading test data
     */
    private void loadData() {
        myModel.addProduct(new Product ("Test01", "desc01", 101.0, 11));
        myModel.addProduct(new Product ("Test02", "desc02", 45.5, 12));
        myModel.addProduct(new Product ("Test03", "desc03", 75.5, 13));
        myModel.addProduct(new Product ("Test04", "desc04", 20.2, 14));
        myModel.addProduct(new Product ("Test05", "desc05", 100.0, 15));
        myModel.addProduct(new Product ("Test06", "desc06", 100.0, 15));
        myModel.addProduct(new Product ("Test07", "desc07", 100.0, 15));
        myModel.addProduct(new Product ("Test08", "desc08", 100.0, 15));
        myModel.addProduct(new Product ("Test09", "desc09", 100.0, 15));        
        myModel.addProduct(new Product ("Test10", "desc10", 100.0, 15)); 
    }
    
    /**
     * Method for asking a file path, and save Product data into it
     * @param myModel Model used with data
     */
    private void writeToFile(Model myModel) {
        //if a variable to deal with selection of files does not exist, create one
        if (myFileChooser == null) {
            myFileChooser = new JFileChooser();
            myFileChooser.setAcceptAllFileFilterUsed(true);
        }

        //Show it and get the result 
        int returnVal = myFileChooser.showSaveDialog(this);
        
        //process the result
        if(returnVal == JFileChooser.APPROVE_OPTION) {
            //the file is the selected one
            File file = myFileChooser.getSelectedFile();
            //recover the path of the file
            String filePath= file.getPath();
            //write data in the path
            myModel.writeFile(filePath);            
        }
        
        //Reset the file chooser for the next time it's shown.
        myFileChooser.setSelectedFile(null);
       
    }

    private void openFromFile(Model myModel) {
        //if a variable to deal with selection of files does not exist, create one
        if (myFileChooser == null) {
            myFileChooser = new JFileChooser();
            myFileChooser.setAcceptAllFileFilterUsed(true);
        }

        //Show it and get the result 
        int returnVal = myFileChooser.showSaveDialog(this);
        
        //process the result
        if(returnVal == JFileChooser.APPROVE_OPTION) {
            //the file is the selected one
            File file = myFileChooser.getSelectedFile();
            //recover the path of the file
            String filePath= file.getPath();
            //recover data from the file and save it in the model
            myModel.readFile(filePath);
            //repaint the panel with new data
            setContentPane(new ViewPanel(myModel)); 
            validate();           
        }
        
        //Reset the file chooser for the next time it's shown.
        myFileChooser.setSelectedFile(null);
    }
}
