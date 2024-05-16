import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Screen.run();
        Stock gamestop = new Stock("Gamestop", 100, 0.5);
        advanceManyDays(gamestop, 1000);
        
    }

    public static void advanceManyDays(Stock stock, int days) {
        for (int i=0; i<days; i++)
        {
            stock.advanceDay();
        }
        ArrayList<Double> prices = stock.getPreviousPrices();
        System.out.println(Stock.toPriceDecimal(prices.get(0)));
        for(int i = 1; i < prices.size(); i++) {
            if(prices.get(i) > prices.get(i - 1)) {
                System.out.println("\u001B[32m" + Stock.toPriceDecimal(prices.get(i)) + "\u001B[0m");
            }
            else {
                System.out.println("\u001B[31m" + Stock.toPriceDecimal(prices.get(i)) + "\u001B[0m");
            }
        }
    }
}
