import service.MarketConsole;
import service.ProductService;
import service.SellService;

public class Main {

    public static void main(String[] args) {

        ProductService productService = new ProductService();
        SellService sellService = new SellService();
        productService.defaultProductList();

        MarketConsole marketConsole = new MarketConsole(productService, sellService);
        marketConsole.consoleFirstMenu();


    }


}
