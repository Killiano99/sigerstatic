import java.util.*;
import java.text.DecimalFormat;
public class Stock {
    String name;
    double price;
    double eqPrice;
    double randomness;
    double priceChange = 0;
    Random rand = new Random();
    ArrayList<String> previous5Prices = new ArrayList<String>();
    double sdChange;

    public Stock(String name, double eqPrice, double randomness) {
        this.name = name;
        this.randomness = randomness;
        this.eqPrice = eqPrice;
        price = rand.nextGaussian(eqPrice, eqPrice * randomness / 2);
        sdChange = Math.abs(rand.nextGaussian(0, randomness / 16));
        //System.out.println("randomness: " + randomness);
        //System.out.println("eqPrice: " + eqPrice);
        //System.out.println("sdChange: " + sdChange);
    }
    
    public void priceChange() {
        double change = rand.nextGaussian(0,sdChange);
        if(price - eqPrice > 0 && change < 0) {
            change *= 1 + ((price/(eqPrice * rand.nextInt(2,5))) * (1.25-randomness));
        }
        else if(price - eqPrice < 0 && change > 0) {
            change *= 1 + ((price/(eqPrice * rand.nextInt(2,5))) * (1.25-randomness));
        }
        previous5Prices.add(toPriceDecimal(price));
        if(previous5Prices.size() > 5) {
            previous5Prices.remove(5);
        }
        price *= 1 + change;
        if((int)(price *10) == 0) {
            price = eqPrice * 0.10 + 1;
            if(eqPrice < 1) {
                eqPrice *= 10;
            }
        }
        //System.out.println(name + "'s price changed by " + ((int)(change * 10000) / 100) + "% to " + toPriceDecimal(price) + "\n");
    
    }
    private String toPriceDecimal(double d) {
        DecimalFormat df = new DecimalFormat("#.00");
        return "$" + df.format(d);
    }

    public double getPrice() {
        return price;
    }

    public void advanceDay() {
        priceChange();
        randomEqChange();
    }

    private void randomEqChange() {   
        if(rand.nextInt(1,100) <= randomness * 5 + 1) {
            
            eqPrice *= rand.nextGaussian(1, randomness / 10);
            if(rand.nextInt(0,20) == 0) {
                eqPrice *= rand.nextGaussian(1, randomness / 2);
            }
        }
    }
    
}
