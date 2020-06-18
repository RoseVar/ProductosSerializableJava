package pt12TaulaPersist;

import pt12TaulaPersist.model.Model;
import pt12TaulaPersist.views.MainFrame;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author tarda
 */
public class Mainpt12TaulaPersist {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Mainpt12TaulaPersist myMain= new Mainpt12TaulaPersist();
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                //call our method to start
                myMain.run();
            }
        });
    }

    private void run() {
        //Instantiate model and main frame
        Model myProductsModel= new Model();
        MainFrame myStoreMainFrame = new MainFrame(myProductsModel);
        //Put it in the center of screen
        myStoreMainFrame.setLocationRelativeTo(null);
        //set visible the main frame
        myStoreMainFrame.setVisible(true);
        
    }
    
}
