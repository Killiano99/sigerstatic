import java.text.DecimalFormat; // Import the DecimalFormat class to format decimal numbers

import java.util.ArrayList; // Import the ArrayList class to store previous prices

import java.util.Random; // Import the Random class to generate random numbers

public class Stock {
    String name; // The name of the stock
    double price; // The current price of the stock
    double eqPrice; // The equilibrium price of the stock
    double randomness; // A factor that determines how much the stock price can deviate from the equilibrium price
    double priceChange = 0; // The change in price from the previous day
    Random rand = new Random(); // An instance of the Random class to generate random numbers
    ArrayList<Double> previousPrices = new ArrayList<Double>(); // A list to store previous prices
    double sdChange; // The standard deviation of the price change
    double originalPrice; // The original price of the stock

    // Constructor to initialize a new stock
    public Stock(String name, double originalPrice, double randomness) {
        this.name = name;
        this.randomness = randomness;
        this.originalPrice = originalPrice;
        // Initialize the equilibrium price with a random deviation from the original price
        eqPrice = originalPrice + (originalPrice * rand.nextGaussian() * 0.2);
        // Initialize the stock price with a random deviation from the equilibrium price based on the randomness factor
        price = rand.nextGaussian() * eqPrice * randomness * randomness / 10 + eqPrice;
        // Initialize the standard deviation of the price change based on the randomness factor
        sdChange = Math.abs(rand.nextGaussian() * randomness / 16);
        // Print out the initial values
        System.out.println("original: " + originalPrice);
        System.out.println("randomness: " + randomness);
        System.out.println("eqPrice: " + eqPrice);
        System.out.println("sdChange: " + sdChange);
        System.out.println();
    }

    // Method to calculate the price change for the next day
    public void priceChange() {
        // Generate a random price change based on the standard deviation
        double change = rand.nextGaussian() * sdChange;
        // Adjust the price change if it's moving away from the equilibrium price
        if ((price - eqPrice < 0 && change > 0) || (price - eqPrice > 0 && change < 0)) {
            change *= 1 + (((((80.0 / 3) * Math.pow(randomness, 3) - 28 * Math.pow(randomness, 2) + 34 * randomness / 3))) * Math.abs(eqPrice - price) / eqPrice);
        }
        // Add the current price to the list of previous prices
        previousPrices.add(price);
        // Update the stock price with the price change
        price *= 1 + change;
        // If the price becomes negative, set it to a small positive value
        if (price < 0) {
            price = eqPrice * 0.01;
        }
        // Print out the price change
        System.out.println(name + "'s price changed by " + toDecimal(change * 100, 4) + "% to " + toPriceDecimal(price));
    }

    // Helper method to format a double as a price string
    public static String toPriceDecimal(double d) {
        DecimalFormat df = new DecimalFormat("#.00");
        return "$" + df.format(d);
    }

    // Helper method to format a double with a specified number of decimal places
    public static String toDecimal(double d, int zerosAfterDecimal) {
        String z = "#.";
        for (int i = 0; i < zerosAfterDecimal; i++) {
            z += "0";
        }
        DecimalFormat df = new DecimalFormat(z);
        return df.format(d);
    }

    // Getter method to retrieve the current stock price
    public double getPrice() {
        return price;
    }

    // Method to advance the stock price to the next day
    public void advanceDay() {
        priceChange(); // Calculate the price change for the day
        randomEqChange(); // Update the equilibrium price with a random change
        System.out.println("eqPrice: " + toPriceDecimal(eqPrice));
        System.out.println("original: " + toPriceDecimal(originalPrice));
        System.out.println("\n");
    }

    // Method to update the equilibrium price with a random change
    private void randomEqChange() {
        // Generate a random change in the equilibrium price based on the randomness factor
        double eqChange = rand.nextGaussian() * randomness / 20;
        // Adjust the equilibrium price change if it's moving away from the original price
        if (((((80.0 / 3) * Math.pow(randomness, 3) - 28 * Math.pow(randomness, 2) + 34 * randomness / 3)) > (Math.abs(originalPrice - eqPrice) / originalPrice)) && ((eqPrice - originalPrice < 0 && eqChange > 0) || (eqPrice - originalPrice > 0 && eqChange < 0))) {
            eqChange *= ((((80.0 / 3) * Math.pow(randomness, 3) - 28 * Math.pow(randomness, 2) + 34 * randomness / 3))) * Math.abs(originalPrice - eqPrice) / originalPrice;
        }
        System.out.println("eqchange: " + toDecimal(eqChange * 100, 4) + "%");
        eqPrice *= eqChange + 1;
    }

    // Getter method to retrieve the list of previous prices
    public ArrayList<Double> getPreviousPrices() {
        return previousPrices;
    }
}
