import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Screen.run();
    }

    public static void advanceManyDays(Stock stock, int days) {
        for (int i=0; i<days; i++)
        {
            stock.advanceDay();
        }
        ArrayList<Double> prices = stock.getPreviousPrices();
        System.out.println(Price.toPriceDecimal(prices.get(0)));
        for(int i = 1; i < prices.size(); i++) {
            if(prices.get(i) > prices.get(i - 1)) {
                System.out.println(Price.toPriceDecimal(prices.get(i)));
            }
            else {
                System.out.println(Price.toPriceDecimal(prices.get(i)));
            }
        }
    }
}
