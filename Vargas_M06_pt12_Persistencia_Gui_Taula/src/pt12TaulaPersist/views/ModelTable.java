/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt12TaulaPersist.views;


import javax.swing.table.AbstractTableModel;
import pt12TaulaPersist.model.Model;
import pt12TaulaPersist.model.Product;

/**
 *
 * @author tarda
 */
public class ModelTable extends AbstractTableModel {
    
    //Attributes
        Model data;
        String [] colNames = {"code", "description", "price", "stock"};
    
    //Constructor    
    public ModelTable(Model data) {
        this.data= data;
        this.addTableModelListener(new TableListener());
    }
    
    //Override methods
    
    @Override
    public int getRowCount() {
        return data.countProducts();
    }

    @Override
    public int getColumnCount() {
        return colNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Object result = null; 
        Product product= data.getProduct(rowIndex);
        switch (columnIndex) {
            case 0://"code"
                result= product.getCode();
                break;
            case 1: //"description"
                result= product.getDescription();
                break;
            case 2: //"price"
                result= product.getPrice();              
                break;
            case 3: //"stock"
                result= product.getStock();            
                break;                           
        }
        return result;
    }
    
    @Override
    public void setValueAt(Object aValue,int rowIndex, int columnIndex) {
        Product product= data.getProduct(rowIndex);
        switch (columnIndex) {
            case 0://"code"
                product.setCode((String)aValue);
                break;
            case 1: //"description"
                product.setDescription((String) aValue);
                break;
            case 2: //"price"
                product.setPrice((double)aValue);
                break;
            case 3: //"stock"
                product.setStock((int)aValue);
                break;                        
        }  
        fireTableCellUpdated(rowIndex, columnIndex);
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex){
        return true;
    }   
    
    @Override
    public Class getColumnClass(int col) {
        return getValueAt(0, col).getClass();
    } 
    
}
