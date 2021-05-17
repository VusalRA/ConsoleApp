package service;

import interfaces.ISellService;
import model.Product;
import model.Sell;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;


public class SellService extends ProductService implements ISellService {

    ArrayList<Sell> sells = new ArrayList<>();

    public ArrayList<Sell> orderList() {
        int count = 0;

        System.out.println("ID | TotalPrice | OrderDate | Name | Price | Quantity");
        System.out.println();

        for (Sell sell : sells) {
            System.out.print("Sell ID: " + sell.getSellId() + " | ");
            System.out.print("Total price: " + sell.getTotalPrice() + " | ");
            System.out.print("Sell Date: " + getDateFromString(sell.getDate()) + " | ");
            for (Product product : sell.getPurchasedProducts()) {
                System.out.print(product.getName() + " | ");
                System.out.print(product.getPrice() + " | ");
                System.out.print(product.getQty() + " | ");
                System.out.println();
                count++;

            }
        }
        System.out.println();
        System.out.println("Total Orders: " + count);

        return sells;
    }

    public boolean removeOrder(int id) {
        for (Sell i : sells) {
            if (i.getSellId() == id) {
                sells.remove(i);
                System.out.println("Successfully deleted.");
                return true;
            }
        }
        return false;
    }

    public ArrayList<Sell> getOrdersWithPriceRange(double min, double max) {
        ArrayList<Sell> newSells = new ArrayList<>();

        System.out.println("ID | Name | Price | Qty");

        for (Sell sell : sells) {
            if (sell.getTotalPrice() >= min && sell.getTotalPrice() <= max) {
                newSells.add(sell);
                for (Product product : sell.getPurchasedProducts()) {
                    System.out.print(sell.getSellId() + " | ");
                    System.out.print(product.getName() + " | ");
                    System.out.print(product.getPrice() + " | ");
                    System.out.print(product.getQty() + " | ");
                    System.out.println();
                }

            }
        }

        return newSells;
    }

    public ArrayList<Sell> getOrdersWithItem(int orderId) {
        ArrayList<Sell> newSells = new ArrayList<>();

        for (Sell sell : sells) {
            if (sell.getSellId() == orderId) {
                newSells.add(sell);
                System.out.println("Sell ID: " + sell.getSellId());
                System.out.println("Sell TotalPrice: " + sell.getTotalPrice());
                for (Product product : sell.getPurchasedProducts()) {
                    System.out.println(product.getName());
                    System.out.println(product.getPrice());
                }
            }
        }
        return newSells;
    }


    public void getListByDate() {

        System.out.println("ID | TotalPrice | OrderDate | Name | Price | Quantity");
        System.out.println();
        for (Sell sell : showOrdersByDateRange()) {
            System.out.print("Sell ID: " + sell.getSellId() + " | ");
            System.out.print("Total price: " + sell.getTotalPrice() + " | ");
            System.out.print("Sell Date: " + getDateFromString(sell.getDate()) + " | ");
            for (Product product : sell.getPurchasedProducts()) {
                System.out.print(product.getName() + " | ");
                System.out.print(product.getPrice() + " | ");
                System.out.print(product.getQty() + " | ");
                System.out.println();

            }
        }
    }

    public ArrayList<Sell> getListSpecificDate() {

        LocalDate specificDate = readDate("EnterDate :");
        System.out.println();
        ArrayList<Sell> newSells = new ArrayList<>();
        System.out.println("ID | TotalPrice | OrderDate | Name | Price | Quantity");
        System.out.println();
        for (Sell sell : sells) {
            if (sell.getDate().equals(specificDate)) {
                newSells.add(sell);
                System.out.print("Sell ID: " + sell.getSellId() + " | ");
                System.out.print("Total price: " + sell.getTotalPrice() + " | ");
                for (Product product : sell.getPurchasedProducts()) {
                    System.out.print(product.getName() + " | ");
                    System.out.print(product.getPrice() + " | ");
                    System.out.print(product.getQty() + " | ");
                    System.out.println();
                }
            }
        }
        return newSells;
    }

    public List<Sell> showOrdersByDateRange() {
        LocalDate startDate = readDate("Enter Date From: ");
        LocalDate endDate = readDate("Enter To Date: ");
        return getOrdersByDateRange(startDate, endDate);
    }

    public List<Sell> getOrdersByDateRange(LocalDate dateFrom, LocalDate dateTo) {
        List<Sell> sells = this.sells.stream().filter(s -> s.getDate().isAfter(dateFrom) && s.getDate().isBefore(dateTo)).collect(Collectors.toList());
        return sells;
    }

    public static String getDateFromString(LocalDate dateTime) {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String formatDateTime = dateTime.format(format);
        return formatDateTime;
    }

    public static LocalDate readDate(String message) {
        LocalDate date;

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print(message);
            String dateInput = scanner.nextLine();
            try {
                DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                date = LocalDate.parse(dateInput, dateFormat);
                break;
            } catch (Exception e) {
                System.out.println("Please type date in correct format : dd-MM-yyyy" + " you enter " + dateInput);
            }
        }
        return date;
    }

    public void returnProduct(int id) {
        int returnQty = 0;
        int productId = 0;

        for (Sell sell : sells) {
            if (sell.getSellId() == id) {
                for (Product product : sell.getPurchasedProducts()) {
                    returnQty = product.getQty();
                    productId = product.getId();
                }
//                sells.remove(sell);
            }
        }
        int oldVal = getProduct(productId).getQty();

    }

}