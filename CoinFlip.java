import java.util.*;

public class CoinFlip extends Game{
	
	public static double makeBet(double bet) {
		Scanner input = new Scanner(System.in);
		
		Random random = new Random();
		while (true)
		{
			System.out.print("Choose heads(h) or tails(t): ");
			char choice = input.nextChar();
			if (choice == 'h')
			{
				boolean choiceBool = true;
				break;
			}
			else if (choice == 't')
			{
				boolean choiceBool = false;
				break;
			}
			System.out.println("Please enter 'h' or 't'");
		}
		System.out.println("Flipping coin...");

		TimeUnit.SECONDS.sleep(2);

		boolean result = random.nextBoolean();
		if (result) {
			System.out.println("The coin is heads!");
		}
		else
		{
			System.out.println("The coin is tails!");
		}

		if (choiceBool == result){
			System.out.println("You bet right!");
			System.out.println(bet*2 + " has been added to your balance");
			return bet*2;
		else
		//return negative input
			//in main, add result of this to balance
	}
	
}
