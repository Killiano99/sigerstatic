import java.text.DecimalFormat;

public class Price {
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
}
