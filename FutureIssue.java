import java.util.Random;
import java.util.Scanner;

public class Casino {
    private int credits;
    private final Random random = new Random();
    private final Scanner scanner = new Scanner(System.in);

    // Declare symbols as a class member to make it accessible to all methods
    private String[][] symbols = {
            { "Cher", "Lem", "Ora", "Gra", "Bel", "Bar" },
            { "Bar", "Bel", "Gra", "Ora", "Lem", "Cher" },
            { "Lem", "Cher", "Bar", "Gra", "Bel", "Ora" }
    };

    public Casino(int initialCredits) {
        this.credits = initialCredits;
    }

    public void play() {
        System.out.println("Welcome to the Casino! Your current credits: " + credits);
        while (credits > 0) {
            System.out.println("\nChoose your game:");
            System.out.println("1. Slots");
            System.out.println("2. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine(); 
            int remainingCredits = switch (choice) {
                case 1 -> { playSlots(); yield credits; }
                case 2 -> {
                    System.out.println("Thanks for playing! You leave with " + credits + " credits.");
                    yield credits; 
                }
                default -> {
                    System.out.println("Invalid choice.");
                    yield credits;
                }
            };

            // Rest of the play method
            if (remainingCredits == 0) {
                System.out.println("You're out of credits. Please come back later!");
            }
        }
    }

    private void playSlots() {
        System.out.println("\nWelcome to Slots!");
        System.out.print("Enter bet amount for each spin (or 'cashout' to quit): ");
        String input = scanner.nextLine().toLowerCase();
        if (input.equals("cashout")) {
            System.out.println("\nCashing out with " + credits + " credits.");
            return;
        }

        int bet;
        try {
            bet = Integer.parseInt(input);
            if (bet <= 0 || bet > credits) {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid bet amount. Please enter a positive integer less than or equal to your credits.");
            return;
        }
        
        // Removed the declaration of symbols here
        
        while (credits >= bet) {
            System.out.print("\nPress Enter to spin, or type 'legend' to see the payout table, or 'cashout' to quit: ");
            input = scanner.nextLine().toLowerCase();

            if (input.equals("legend")) {
                showPayoutTable(bet);
            } else if (input.equals("cashout")) {
                break;
            } else {
                credits -= bet;

                String[] spinResult = new String[3];
                for (int i = 0; i < spinResult.length; i++) {
                    spinResult[i] = symbols[i][random.nextInt(symbols[i].length)];
                }

                // Print the entire slot machine layout
                System.out.println("+-----+-----+-----+");
                for (String[] reel : symbols) {
                    System.out.print("| ");
                    for (int i = 0; i < spinResult.length; i++) {
                        if (spinResult[i].equals(reel[i])) {
                            System.out.print(reel[i] + " | ");
                        } else {
                            System.out.print("   | ");
                        }
                    }
                    System.out.println("\n+-----+-----+-----+");
                }
                
                int winnings = calculateWinnings(spinResult, bet);

                // Display the winnings message only if they're greater than 0
                if (winnings > 0) {
                    System.out.println("Congratulations! You won " + winnings + " credits!");
                    credits += winnings;
                } else {
                    System.out.println("Better luck next time!");
                }
            }
            System.out.println("Current credits: " + credits);
        }

        System.out.println("\nCashing out with " + credits + " credits.");
    }


    private void showPayoutTable(int bet) {
        System.out.println("\nPayout Table (based on bet of " + bet + " credits):");
        System.out.println("Three of a Kind:");
        System.out.println(" - CherCherCher: " + (10 * bet) + " credits");
        System.out.println(" - BelBelBel: " + (5 * bet) + " credits");
        System.out.println(" - BarBarBar: " + (15 * bet) + " credits");
        System.out.println(" - LemLemLem, OraOraOra, or GraGraGra: " + (3 * bet) + " credits");
        System.out.println("Two Cherries (on a payline): " + (2 * bet) + " credits");
        System.out.println("Two Bells (on a payline): " + (1 * bet) + " credits");
    }

    private int calculateWinnings(String[] spinResult, int bet) {
        int winnings = 0; 

        if (spinResult[0].equals(spinResult[1]) && spinResult[1].equals(spinResult[2])) {
            switch (spinResult[1]) { 
                case "Cher":
                    winnings = 20 * bet; 
                    break;
                case "Bel":
                    winnings = 10 * bet;
                    break;
                case "Bar":
                    winnings = 30 * bet; 
                    break;
                case "Lem":
                case "Ora":
                case "Gra":
                    winnings = 6 * bet;  
                    break;
            }
        } 
        // Check for two cherries on the payline (middle row):
        else if (spinResult[1].equals("Cher") && (spinResult[0].equals("Cher") || spinResult[2].equals("Cher"))) {
            winnings = 4 * bet;  
        }

        return winnings; 
    }

    private int getValidBet() {
        int bet = scanner.nextInt();
        scanner.nextLine(); // Consume newline character
        while (bet <= 0 || bet > credits) {
            System.out.println("Invalid bet. Enter a value between 1 and " + credits + ":");
            bet = scanner.nextInt();
            scanner.nextLine(); // Consume newline character
        }
        return bet;
    }

}
