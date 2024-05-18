import java.util.Random;
import java.util.Scanner;

public class Casino {
    private int credits;
    private final Random random = new Random();
    private final Scanner scanner = new Scanner(System.in);
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
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    playSlots();
                    break;
                case 2:
                    System.out.println("Thanks for playing! You leave with " + credits + " credits.");
                    return;
                default:
                    System.out.println("Invalid choice.");
            }

            if (credits == 0) {
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
                    spinResult[i] = symbols[1][random.nextInt(symbols[1].length)];
                }

                // Randomize first and third rows separately
                String[] firstRow = new String[3];
                String[] thirdRow = new String[3];
                for (int i = 0; i < 3; i++) {
                    firstRow[i] = symbols[0][random.nextInt(symbols[0].length)];
                    thirdRow[i] = symbols[2][random.nextInt(symbols[2].length)];
                }

                // Print the slot machine layout
                System.out.println("+-----+-----+-----+");
                for (int i = 0; i < 3; i++) {
                    System.out.print("| " + firstRow[i] + " ");
                }
                System.out.println("|");
                System.out.println("+-----+-----+-----+");

                for (int i = 0; i < 3; i++) {
                    System.out.print("| " + spinResult[i] + " ");
                }
                System.out.println("|");
                System.out.println("+-----+-----+-----+");

                for (int i = 0; i < 3; i++) {
                    System.out.print("| " + thirdRow[i] + " ");
                }
                System.out.println("|");
                System.out.println("+-----+-----+-----+");

                int winnings = calculateWinnings(firstRow, spinResult, thirdRow, bet);

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
    }

    private int calculateWinnings(String[] firstRow, String[] middleRow, String[] thirdRow, int bet) {
        int maxWinnings = 0;

        maxWinnings = Math.max(maxWinnings, calculateRowWinnings(firstRow, bet));
        maxWinnings = Math.max(maxWinnings, calculateRowWinnings(middleRow, bet));
        maxWinnings = Math.max(maxWinnings, calculateRowWinnings(thirdRow, bet));

        return maxWinnings;
    }

    private int calculateRowWinnings(String[] row, int bet) {
        int winnings = 0;

        if (row[0].equals(row[1]) && row[1].equals(row[2])) {
            switch (row[1]) {
                case "Cher":
                    winnings = 10 * bet;
                    break;
                case "Bel":
                    winnings = 5 * bet;
                    break;
                case "Bar":
                    winnings = 15 * bet;
                    break;
                case "Lem":
                case "Ora":
                case "Gra":
                    winnings = 3 * bet;
                    break;
            }
        } else if (row[1].equals("Cher") && (row[0].equals("Cher") || row[2].equals("Cher"))) {
            winnings = 2 * bet;
        }

        return winnings;
    }
}
