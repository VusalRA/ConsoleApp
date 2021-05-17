package service;

import model.Product;
import model.Sell;
import model.enums.Category;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;
import java.util.regex.Pattern;


public class MarketConsole {
    private ProductService productService;
    private SellService sellService;
    private boolean ifSellConfirm;
    private double totalPrice;
    private Pattern pattern = Pattern.compile("[0-9]");


    public MarketConsole(ProductService productService, SellService sellService) {
        this.productService = productService;
        this.sellService = sellService;
    }


    Scanner scanner = new Scanner(System.in);
    private String name = "";

    public void consoleFirstMenu() {

        startMenuForConsole();
        int menu = 0;
        try {
            menu = scanner.nextInt();
            switch (menu) {
                case 1:
                    consoleProductMenu();
                    break;
                case 2:
                    consoleSellMenu();
                    break;
                case 3:
                    break;
            }
        } catch (Exception e) {
            System.out.println("Enter only numbers");
        }


    }


    public void consoleProductMenu() {
        productMenuForConsole();
        int menu = 0;
        try {
            menu = scanner.nextInt();
            switch (menu) {
                case 1:
                    productService.addProduct(addNewProductMenu());
                    consoleFirstMenu();
                    break;
                case 2:
                    productService.getProductList();
                    consoleFirstMenu();
                    break;
                case 3:
                    System.out.println("Enter Name");
                    name = String.valueOf(scanner.next());
                    productService.returnList(name);
                    System.out.println();
                    consoleFirstMenu();
                    break;
                case 4:
                    name = String.valueOf(scanner.next());
                    productService.returnList(name);
                    System.out.println("Enter Product Id");
                    int id = scanner.nextInt();
                    productService.removeProduct(id);
                    consoleFirstMenu();
                    break;

                case 5:
                    System.out.println("Enter product name: ");
                    name = String.valueOf(scanner.next());
                    productService.returnList(name);
                    System.out.println("Enter ID: ");
                    int idForUpdate = scanner.nextInt();
                    Product product = productService.getProduct(idForUpdate);
                    productService.updateProduct(product, productService.getIndex(product));
                    consoleFirstMenu();
                    break;
                case 6:
                    System.out.println("FRUITS, DAIRY, SPICES, VEGETABLES, FROZENFOOD, BREADS, PETITEMS, SNACKS, MEAT");
                    String category = String.valueOf(scanner.next());
                    productService.getWithCategory(Category.valueOf(category.toUpperCase(Locale.ROOT)));
                    consoleFirstMenu();
                    break;
                case 7:
                    System.out.println("Enter min :");
                    double min = scanner.nextDouble();
                    System.out.println("Enter max :");
                    double max = scanner.nextDouble();
                    productService.getWithPriceRange(min, max);
                    break;
            }
        } catch (Exception e) {
            System.out.println("Enter only numbers");
        }

    }


    public Product addNewProductMenu() {
        Product product = new Product();
        product.setId(RandomNumberGenerator.getRandomNumber(100000, 999999));
        System.out.println("Enter product category: ");
        product.setCategory(createProduct(product));
        System.out.println("Enter product name: ");
        product.setName(String.valueOf(scanner.next()));
        System.out.println("Enter product price: ");
        product.setPrice(scanner.nextInt());
        System.out.println("Enter product quantity: ");
        product.setQty(scanner.nextInt());

        return product;

    }

    public void consoleSellMenu() {

        sellMenuForConsole();

        int menu = scanner.nextInt();
        switch (menu) {

            case 1:
                sellProductMenu();
                while (askForNext()) {
                    sellProductMenu();
                }
                consoleFirstMenu();
                break;
            case 2:
                sellService.orderList();
                consoleFirstMenu();
                break;
            case 3:
                sellService.orderList();
                System.out.println("Enter Sell Id");
                int id = scanner.nextInt();
                sellService.removeOrder(id);
                consoleFirstMenu();
                break;
            case 4:
                System.out.println("Enter min :");
                double min = scanner.nextDouble();
                System.out.println("Enter max :");
                double max = scanner.nextDouble();
                sellService.getOrdersWithPriceRange(min, max);
                consoleFirstMenu();
                break;

            case 5:
                System.out.println("Enter ID:");
                int orderId = scanner.nextInt();
                sellService.getOrdersWithItem(orderId);
                consoleFirstMenu();
                break;
            case 6:
                sellService.getListByDate();
                consoleFirstMenu();
                break;
            case 7:
                sellService.getListSpecificDate();
                consoleFirstMenu();
                break;
            case 8:
                System.out.println("Enter id: ");
                int idForRemove = scanner.nextInt();
                sellService.returnProduct(idForRemove);
                consoleFirstMenu();
                break;
        }
    }

    public void sellProductMenu() {


        ArrayList<Product> list = new ArrayList<>();
        int id = RandomNumberGenerator.getRandomNumber(100000, 999999);
        Product sellProduct = sellProduct();

        list.add(sellProduct);
        if (ifSellConfirm) {
            Sell sell = new Sell();
            sell.setSellId(id);
            sell.setPurchasedProducts(list);
            sell.setTotalPrice(totalPrice);
            sell.setDate(LocalDate.now());
            sellService.sells.add(sell);
        } else {
            System.out.println("Sorry");
        }

    }

    public Product sellProduct() {
        Product newProduct = new Product();

        System.out.println("Enter name: ");
        String name = String.valueOf(scanner.next());
        productService.returnList(name);
        System.out.println("Enter Id: ");
        int id = scanner.nextInt();
        int max = productService.getProduct(id).getQty();
        System.out.println("Enter Qty: ");
        int qty = scanner.nextInt();
        Product product = productService.getProduct(id);

        if (max >= qty) {
            newProduct.setCategory(product.getCategory());
            newProduct.setId(product.getId());
            newProduct.setName(product.getName());
            newProduct.setQty(qty);
            newProduct.setPrice(product.getPrice());
            totalPrice = product.getPrice() * qty;
            product.setQty(max - qty);
            ifSellConfirm = true;
        } else {
            System.out.println("We don't have it");
        }
        return newProduct;
    }

    public boolean askForNext() {
        System.out.print("1: Confirm and finish | 2: Next | 3: Exit ");
        System.out.println();
        int next = scanner.nextInt();
        if (next == 1) {
            return false;
        } else if (next == 2) {
            return true;
        } else {
            return false;
        }
    }

    public Category createProduct(Product product) {
        Category category = null;

        System.out.println("1: FRUITS");
        System.out.println("2: DAIRY");
        System.out.println("3: SPICES");
        System.out.println("4: VEGETABLES");
        System.out.println("5: FROZENFOOD");
        System.out.println("6: BREADS");
        System.out.println("7: PETITEMS");
        System.out.println("8: SNACKS");
        System.out.println("9: MEAT");
        System.out.println();
        System.out.println("Please Choose Category of Product: ");

        int category2 = scanner.nextInt();
        switch (category2) {
            case 1:
                category = Category.FRUITS;
                break;
            case 2:
                category = Category.DAIRY;
                break;
            case 3:
                category = Category.SPICES;
                break;
            case 4:
                category = Category.VEGETABLES;
                break;
            case 5:
                category = Category.FROZENFOOD;
                break;
            case 6:
                category = Category.BREADS;
                break;
            case 7:
                category = Category.PETITEMS;
                break;
            case 8:
                category = Category.SNACKS;
                break;
            case 9:
                category = Category.MEAT;
        }


        return category;
    }

    public void productMenuForConsole() {
        System.out.println("1: Add New Product");
        System.out.println("2: Get All Product");
        System.out.println("3: Find Product With Name");
        System.out.println("4: Remove Product");
        System.out.println("5: Update Product");
        System.out.println("6: Find Products with Category");
        System.out.println("7: Find Products with min max range");
        System.out.println("8: Exit");
        System.out.println();
        System.out.println("Please choose one of the above: ");
    }

    public void startMenuForConsole() {
        System.out.println();
        System.out.println("1: Product Menu");
        System.out.println("2: Sell Menu");
        System.out.println("3: Exit");
        System.out.println();
        System.out.println("Please choose one of the above: ");
    }

    public void sellMenuForConsole() {
        System.out.println("1: Sell Item: ");
        System.out.println("2: List of products sold: ");
        System.out.println("3: Remove products of sold: ");
        System.out.println("4: A list of price ranges: ");
        System.out.println("5: Find sold products with Sell ID: ");
        System.out.println("6: A list of date ranges: ");
        System.out.println("7: List by date: ");
        System.out.println("8: Return the product sold: ");
        System.out.println();
        System.out.println("Please choose one of the above: ");
    }

}
