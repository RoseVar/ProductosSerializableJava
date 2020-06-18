/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt12TaulaPersist.views;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

/**
 *
 * @author Roser
 */
public class TableListener implements TableModelListener{

    @Override
    public void tableChanged(TableModelEvent e) {
        //if the event is update
        if (e.getType()== TableModelEvent.UPDATE) {
            //recover the Table Model
            TableModel thisModel = (TableModel) e.getSource();
            //recover row and column
            int row = e.getFirstRow();
            int col= e.getColumn();
            //ask for value in the cell
            Object value = thisModel.getValueAt(row, col);
            //show message
            System.out.format("Changed row %d colum %d to value %s\n", row, col, value.toString());
        }
    }
    
}
