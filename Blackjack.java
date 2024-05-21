import java.util.*;

public class Blackjack extends Game{
    static List<String> deck = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);
    static int temp, pAce, oAce;

    public static void runBlackjack() {
        pAce = 0;
        oAce = 0;
        System.out.println("\nWelcome to Blackjack!\n");
        
        createDeck();
        Collections.shuffle(deck);

        while (true) {
            int playerTotal = dealHand(false, "You");
            int dealerTotal = dealHand(true, "Dealer"); 

            // Player's turn
            while (playerTotal < 21) {
                System.out.println("\nYour total: " + playerTotal + ". Hit (h) or Stand (s)?");
                String choice = scanner.nextLine();
                if (choice.equalsIgnoreCase("h")) {
                    temp = dealCard("You");
                    if(temp == 11) {
                        if(playerTotal < 11) {
                            playerTotal += temp;
                            pAce++;
                            System.out.println(pAce);
                        }
                        else {
                            playerTotal += 1;
                        }
                    }
                    else {
                        playerTotal += temp;
                    }
                    if(playerTotal > 21 && pAce > 0) {
                        playerTotal -= 10;
                        pAce--;
                    }
                } else if (choice.equalsIgnoreCase("s")) {
                    break;
                } else {
                    System.out.println("Invalid input. Please enter 'h' or 's'.");
                }
            }

            // Dealer's turn
            System.out.println("Dealer's face-up card: " + dealerTotal);
            while (dealerTotal < 17) {
                temp = dealCard("Dealer");
                if(temp == 11) {
                    if(dealerTotal < 11) {
                        dealerTotal += temp;
                        oAce++;
                    }
                    else {
                        dealerTotal += 1;
                    }
                }
                else {
                    dealerTotal += temp;
                }
                if(dealerTotal > 21 && oAce > 0) {
                    dealerTotal -= 10;
                    oAce--;
                }
                System.out.println("Dealer's total: " + dealerTotal);
            }

            // Determine the winner
            System.out.println("\nYour final total: " + playerTotal);
            System.out.println("Dealer's final total: " + dealerTotal + "\n");
            determineWinner(playerTotal, dealerTotal);

            // Play again?
            System.out.println("Play again? (y/n)");
            String playAgain = scanner.nextLine();
            if (!playAgain.equalsIgnoreCase("y")) {
                break;
            }
        }
    }

    static void createDeck() {
        String[] suits = {"Hearts", "Diamonds", "Clubs", "Spades"};
        String[] ranks = {"Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King"};
        for (String suit : suits) {
            for (String rank : ranks) {
                deck.add(rank + " of " + suit);
            }
        }
    }

    static int dealCard(String s) {
        String card = deck.remove(0);
        System.out.println(s + " drew: " + card);
        if(deck.size() == 0) {
            createDeck();
            Collections.shuffle(deck);
            System.out.println("\nReshuffling...\n");
        }
        return getCardValue(card);
    }

    static int dealHand(boolean hideFirstCard, String s) {
        int total = dealCard(s);
        if (!hideFirstCard) {
            total += dealCard(s);
            System.out.println();
        } else {
            deck.remove(0); // Remove the hidden card
        }
        return total;
    }

    static int getCardValue(String card) {
        String rank = card.split(" ")[0];
        switch (rank) {
            case "Ace":
                return 11; // Initially assume 11, adjust later if needed
            case "Jack":
            case "Queen":
            case "King":
                return 10;
            default:
                return Integer.parseInt(rank);
        }
    }
    
    // ... calculateTotal method from previous response ...

    static void determineWinner(int playerTotal, int dealerTotal) {
        if (playerTotal > 21) {
            System.out.println("You bust! Dealer wins.\n");
        } else if (dealerTotal > 21) {
            System.out.println("Dealer busts! You win.\n");
        } else if (playerTotal > dealerTotal) {
            System.out.println("You win!\n");
        } else if (dealerTotal > playerTotal) {
            System.out.println("Dealer wins\n");
        } else {
            System.out.println("It's a tie\n");
        }
    }
}

