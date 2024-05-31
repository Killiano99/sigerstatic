import java.util.*;

public class CoinFlip extends Game{
	public static String makeBet(double bet, String side) {
		Random random = new Random();
        Random random2 = new Random(random.nextInt(100000));
        boolean b = random2.nextBoolean();
        String coinSide;
		if (b) {
            coinSide = "Heads";
        }
        else {
            coinSide = "Tails";
        }
		Screen.displayCoinFlip(coinSide);
        if(coinSide.equals(side)) {
            coinSide = Price.toDecimal(bet * 1.9, 2);
            Player.changeMoney(Double.parseDouble(coinSide));
            return coinSide;
        }
        else {
            coinSide = Price.toDecimal(bet, 2);
            Player.changeMoney(Double.parseDouble(coinSide));
            return coinSide;
        }
	}
    
}