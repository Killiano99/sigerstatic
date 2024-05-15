import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Random;
public class Stock {
    String name;
    double price;
    double eqPrice;
    double randomness;
    double priceChange = 0;
    Random rand = new Random();
    ArrayList<Double> previousPrices = new ArrayList<Double>();
    double sdChange;
    double originalPrice;

    public Stock(String name, double originalPrice, double randomness) {
        this.name = name;
        this.randomness = randomness;
        this.originalPrice = originalPrice;
        eqPrice = originalPrice + originalPrice * rand.nextGaussian() * 0.2;
        price = rand.nextGaussian() * eqPrice * randomness * randomness / 10 + eqPrice;
        sdChange = Math.abs(rand.nextGaussian() * randomness / 16);
        System.out.println("original: " + originalPrice);
        System.out.println("randomness: " + randomness);
        System.out.println("eqPrice: " + eqPrice);
        System.out.println("sdChange: " + sdChange);
        System.out.println();
    }
    
    public void priceChange() {
        double change = rand.nextGaussian() * sdChange;
        if((price - eqPrice < 0 && change > 0) || (price - eqPrice > 0 && change < 0)) {
            change *= 1 + ((((80.0/3) * Math.pow(randomness, 3) - 28 * Math.pow(randomness, 2) + 34 * randomness / 3)) * Math.abs(eqPrice - price) / eqPrice);
            //System.out.println("change: " +change);
        }
        previousPrices.add(price);
        price *= 1 + change;
        if(price < 0) {
            price = eqPrice * 0.01;
        }
        System.out.println(name + "'s price changed by " + toDecimal(change * 100, 4) + "% to " + toPriceDecimal(price));
    }
    public static String toPriceDecimal(double d) {
        DecimalFormat df = new DecimalFormat("#.00");
        return "$" + df.format(d);
    }
    public static String toDecimal(double d, int zerosAfterDecimal) {
        String z = "#.";
        for(int i = 0; i < zerosAfterDecimal; i++) {
            z += "0";
        }
        DecimalFormat df = new DecimalFormat(z);
        return df.format(d);
    }

    public double getPrice() {
        return price;
    }

    public void advanceDay() {
        priceChange();
        randomEqChange();
        System.out.println("eqPrice: " + toPriceDecimal(eqPrice));
        System.out.println("original: " + toPriceDecimal(originalPrice));
        System.out.println("\n");
    }

    private void randomEqChange() {   
        //implement business success into eq change later

        double eqChange = rand.nextGaussian() * randomness / 20;
        //System.out.println(eqChange);
        if(((((80.0/3) * Math.pow(randomness, 3) - 28 * Math.pow(randomness, 2) + 34 * randomness / 3)) > (Math.abs(originalPrice - eqPrice)/originalPrice)) && ((eqPrice - originalPrice < 0 && eqChange > 0) || (eqPrice - originalPrice > 0 && eqChange < 0))) {
            //System.out.println(((((80.0/3) * Math.pow(randomness, 3) - 28 * Math.pow(randomness, 2) + 34 * randomness / 3)) * Math.abs(originalPrice - eqPrice) / originalPrice));
            eqChange *= ((((80.0/3) * Math.pow(randomness, 3) - 28 * Math.pow(randomness, 2) + 34 * randomness / 3)) * Math.abs(originalPrice - eqPrice) / originalPrice);
        }
        System.out.println("eqchange: " + toDecimal(eqChange * 100,4) + "%");
        eqPrice *= eqChange + 1;
    }
    public ArrayList<Double> getPreviousPrices() {
        return previousPrices;
    }
    
}
