package interfaces;

import model.Product;
import model.enums.Category;

import java.util.ArrayList;

public interface IProductService {

    boolean addProduct(Product product);

    Product updateProduct(Product product, int index);

    ArrayList<Product> getProductList();

    ArrayList<Product> getWithCategory(Category category);

    ArrayList<Product> getWithPriceRange(double min, double max);

    Product getProduct(int id);

    boolean removeProduct(int id);

    int getIndex(Product product);

    ArrayList<Product> returnList(String name);


}
