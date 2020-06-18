
package pt12TaulaPersist.model;

import java.util.List;
import pt12TaulaPersist.model.persistance.ProductPersistance;


/**
 *
 * @author Roser
 */
public class Model {
    //Attributes
    ProductPersistance myProductPersistance;
    
    //Constructor
    public Model(){
        myProductPersistance= new ProductPersistance();
    }
    
    /**
     * retrieves all products from the data store
     * @return list of all products or null in case of error
     */
    public List<Product> listAllProducts(){
        List<Product> found = myProductPersistance.getMyProducts();
        return found;
    }
    
    /**
     * Method for adding a product
     * @param product 
     */
    public void addProduct(Product product) {
        myProductPersistance.addProduct(product);
    }
    
    /**
     * Method for returning one product of the model
     * @param position Index of position of the product
     * @return The product
     */
    public Product getProduct(int position) {
        return myProductPersistance.getProduct(position);
    }

    
    /**
     * Method for reading products of a file
     * @param file The path to open
     * @return true if it has been readed, false otherwise
     */
    public Boolean readFile(String file) {
        Boolean control= false;
        control= myProductPersistance.readFile(file);
        return control;
    }
    
    /**
     * Method for writing products on a file
     * @param file The path to be saved in
     * @return true if it has been saved, false otherwise
     */
    public Boolean writeFile(String file) {
        Boolean control= false;
        control= myProductPersistance.writeFile(file);
        return control;
    }
    
    /**
     * Method for returning how many products we have
     * @return the number of products
     */
    public int countProducts() {
        return myProductPersistance.getCounterData();
    }
}
