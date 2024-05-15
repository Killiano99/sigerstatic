import java.util.*;

public class CoinFlip extends Game{
	
	public static double makeBet(double bet) {
		Random random = new Random();
		if (random.nextBoolean()) {
			return bet *= 2;
			
		}
		return 0;
	}
	
}
