package pt12TaulaPersist.model.persistance;

import java.io.EOFException;;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import pt12TaulaPersist.model.Product;




/**
 *
 * @author Roser
 */
public class ProductPersistance {
    //Attributes
    List<Product> myProducts;
    
    //constructor
    public ProductPersistance(){
        myProducts= new ArrayList<Product>();
    }

    //Getter of all data
    public List<Product> getMyProducts() {
        return myProducts;
    }
    
    /**
     * Method for returning 1 object in a given position
     * @param position Index of position of the object
     * @return Object in Product format
     */
    public Product getProduct(int position){
        return myProducts.get(position);
    }
       
    /**
     * Method for adding a product in store
     * @param product to be added
     * @return true if added, false otherwise
     */
    public boolean addProduct(Product product) {
        Boolean control= myProducts.add(product);
        return control;
    }
    
    
    /**
     * Method for reading a file of Products
     * @param file to open and read
     * @return 
     */
    public Boolean readFile(String file) {
        Boolean control= true;
        myProducts= new ArrayList<Product>();
        //open a ObjectInputStream in a try with resources
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            Object obj;
            //The condition never will be null, because if it has something will read,
            //it will exit when find EOFException
            while ( ( obj=ois.readObject() ) != null)
            {
		//confirm the object is the type we need
                if ( obj instanceof Product )
                {
                    Product provProd = (Product) obj;
                    myProducts.add(provProd);
                }
            }
        } catch (ClassNotFoundException ex) {
            //Exception is the readed class is not Product
            control= false;
        } catch (EOFException eofe) {
            //Exception to exit the reading
            control=true;
 	}
        catch (FileNotFoundException fnfe) {
            //Exception if does not find the file
            control=false;
        }
        catch (IOException ioe) {
            //Other types of excepction
            control=false;
        }
        return control;
    }
    
    /**
     * Method for writing Products in a file
     * @param file to be saved in
     * @return 
     */
    public Boolean writeFile(String file) {
        Boolean control=false;
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            //bucle for each
            for (Product provProd: myProducts) {
                //the object must implement the interface Serializable to be able to save
                //do not save static attributes or transcient attributes
                oos.writeObject(provProd);
            }
            control= true;
    	}
    	//Excepction: not founf file
    	catch (FileNotFoundException fnfe) {
            control= false;
    	}
    	//Generic Exception
    	catch (IOException ioe) {
            control= false;
    	}
        return control;
    }
 
    /**
     * Method to return hoy many products we have
     * @return 
     */
    public int getCounterData() {
        return myProducts.size();
    }
}
