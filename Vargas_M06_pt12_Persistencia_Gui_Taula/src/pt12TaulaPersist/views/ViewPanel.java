
package pt12TaulaPersist.views;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import pt12TaulaPersist.model.Model;

/**
 *
 * @author Roser
 */
public class ViewPanel extends JPanel{
    //Attibutes
    private JTable myTable;
    private Model data;
    
    //constructor
    public ViewPanel(Model data) {
        this.data= data;
        initComponents();
        
    }

    /**
     * Method for creating all components
     */
    private void initComponents() {
        //set Layout
        setLayout(new BorderLayout());
        //Create new Jtable
        myTable= new TableView(data);
        //Create a Jscrollpane from the table
        JScrollPane scrollPane = new JScrollPane(myTable);   
        //add it to the layout
        add(scrollPane, BorderLayout.CENTER);
    }
    
}
