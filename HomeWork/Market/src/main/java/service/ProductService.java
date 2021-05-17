package service;

import interfaces.IProductService;
import model.Product;
import model.enums.Category;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class ProductService implements IProductService {

    protected ArrayList<Product> products = new ArrayList<>();

    @Override
    public boolean addProduct(Product product) {
        if (products.add(product)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Product updateProduct(Product product, int index) {

        Scanner sc = new Scanner(System.in);
        System.out.println("1: Change Product Name | 2: Change Product Price | 3: Change Product Category | 4: Change Product QTY");
        int menu = sc.nextInt();

        switch (menu) {
            case 1:
                System.out.println("Enter New Name: ");
                product.setName(String.valueOf(sc.next()));
                break;
            case 2:
                System.out.println("Enter New Price");
                product.setPrice(sc.nextDouble());
                break;
            case 3:
                System.out.println("Enter New Category");
                product.setCategory(Category.valueOf(sc.nextLine().toUpperCase(Locale.ROOT)));
                break;
            case 4:
                System.out.println("Enter New Quantity");
                product.setQty(sc.nextInt());
                break;

        }

        products.set(index, product);

        return product;
    }


    @Override
    public ArrayList<Product> getProductList() {

        for (Product product : products) {
            System.out.print("Product ID: ");
            System.out.print(product.getId());
            System.out.print(" , ");
            System.out.print("Product Name: ");
            System.out.print(product.getName());
            System.out.print(" , ");
            System.out.print("Product Category: ");
            System.out.print(product.getCategory());
            System.out.print(" , ");
            System.out.print("Product Price: ");
            System.out.print(product.getPrice());
            System.out.print(" , ");
            System.out.print("Product Quantity: ");
            System.out.print(product.getQty());
            System.out.println();
        }

        return products;
    }

    @Override
    public ArrayList<Product> getWithCategory(Category category) {
        ArrayList<Product> newList = new ArrayList<>();

        System.out.println("ID | Name | Price | Qty");

        for (Product product : products) {
            if (product.getCategory() == category) {
                newList.add(product);

                System.out.print(product.getId() + " | ");
                System.out.print(product.getName() + " | ");
                System.out.print(product.getPrice() + " | ");
                System.out.print(product.getQty() + " | ");
                System.out.println();
            }
        }
        return newList;
    }

    @Override
    public ArrayList<Product> getWithPriceRange(double min, double max) {
        ArrayList<Product> newProducts = new ArrayList<>();

        System.out.println("ID | Name | Price | Qty");

        for (Product product : products) {
            if (product.getPrice() >= min && product.getPrice() <= max) {
                newProducts.add(product);

                System.out.print(product.getId() + " | ");
                System.out.print(product.getName() + " | ");
                System.out.print(product.getPrice() + " | ");
                System.out.print(product.getQty() + " | ");
                System.out.println();
            }
        }

        return newProducts;
    }

    @Override
    public Product getProduct(int id) {
        Product product = new Product();
        for (Product i : products) {
            if (i.getId() == id) {
                product = i;
            }
        }
        return product;
    }

    @Override
    public boolean removeProduct(int id) {

        for (Product i : products) {
            if (i.getId() == id) {
                products.remove(i);
                return true;
            }
        }

        return false;
    }

    @Override
    public int getIndex(Product product) {
        return products.indexOf(product);
    }

    @Override
    public ArrayList<Product> returnList(String name) {
        int count = 0;

        ArrayList<Product> productWithName = new ArrayList<>();
        System.out.println("ID | Name | Price | Qty | Category");

        for (Product products : products) {
            if (products.getName().equals(name)) {

                System.out.print(products.getId() + " | ");
                System.out.print(products.getName() + " | ");
                System.out.print(products.getPrice() + " | ");
                System.out.print(products.getQty() + " | ");
                System.out.print(products.getCategory() + " | ");
                System.out.println();

                productWithName.add(products);
                count++;
            }
        }
        System.out.println("Product count: " + count);

        return productWithName;
    }


    public void defaultProductList() {

        Product test1 = new Product();
        test1.setName("snickers");
        test1.setQty(99);
        test1.setId(303);
        test1.setPrice(33);
        test1.setCategory(Category.SNACKS);

        Product test2 = new Product();
        test2.setName("salmon");
        test2.setQty(66);
        test2.setId(306);
        test2.setPrice(99);
        test2.setCategory(Category.MEAT);

        Product test3 = new Product();
        test3.setName("apple");
        test3.setQty(999);
        test3.setId(309);
        test3.setPrice(0.5);
        test3.setCategory(Category.VEGETABLES);


        products.add(test1);
        products.add(test2);
        products.add(test3);
    }

}
