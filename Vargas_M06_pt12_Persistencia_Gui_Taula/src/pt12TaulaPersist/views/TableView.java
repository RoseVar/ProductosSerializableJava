/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt12TaulaPersist.views;

import javax.swing.JTable;
import pt12TaulaPersist.model.Model;

/**
 *
 * @author tarda
 */
public class TableView extends JTable{
    
    //Atributes
    private Model data;
    
    //constructor
    public TableView(Model data) {
        //Set data from the model given
        this.data = data;         
        //Set ModelTable
        setModel(new ModelTable(data));
        //Set atributes of this view
        setAutoCreateRowSorter(true);       
        setFillsViewportHeight(true); 
        setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
    }
}
