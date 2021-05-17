package interfaces;

import model.Sell;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public interface ISellService {

    ArrayList<Sell> orderList();

    boolean removeOrder(int id);

    ArrayList<Sell> getOrdersWithPriceRange(double min, double max);

    ArrayList<Sell> getOrdersWithItem(int orderId);

    void getListByDate();

    ArrayList<Sell> getListSpecificDate();

    List<Sell> getOrdersByDateRange(LocalDate dateFrom, LocalDate dateTo);

    void returnProduct(int id);

}
