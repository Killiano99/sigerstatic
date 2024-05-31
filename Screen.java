import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Screen extends JFrame {
    // Panels
    private JPanel mainPanel;
    private JPanel cardsPanel;
    private JMenuBar menuBar;

    // Game tracking and layout
    private String currentGame = "Home";
    private CardLayout cardLayout;

    // Coin Flip Game Components (static for easy access)
    private static JLabel resultLabel;
    private static JButton playButton;


    public Screen() {
        setTitle("Casino Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        // Initialize Main Panel and Card Layout
        mainPanel = new JPanel(new BorderLayout());
        cardLayout = new CardLayout();
        cardsPanel = new JPanel(cardLayout);

        // Game Panels
        JPanel homePanel = new JPanel(); // Placeholder
        JPanel coinFlipPanel = createCoinFlipPanel();
        JPanel solitairePanel = new JPanel(); // Placeholder
        JPanel slotsPanel = new JPanel();     // Placeholder
        JPanel profilePanel = new JPanel();    // Placeholder


        
        

        // Add Panels to CardLayout
        cardsPanel.add(homePanel, "Home");
        cardsPanel.add(coinFlipPanel, "Coin Flip");
        cardsPanel.add(solitairePanel, "Solitaire");
        cardsPanel.add(slotsPanel, "Slots");
        cardsPanel.add(profilePanel, "Profile");

          // Menu Bar
        menuBar = createMenuBar();
        setJMenuBar(menuBar);

        // Assemble Layout
        mainPanel.add(cardsPanel, BorderLayout.CENTER);
        add(mainPanel);
        
        switchToGame("Home");
        setVisible(true);
    }

    // --- Helper Methods ---

    // Create Coin Flip Panel
    private JPanel createCoinFlipPanel() {
        JPanel coinFlipPanel = new JPanel();
        coinFlipPanel.setBackground(new Color(50, 120, 50)); // Dark green background
    
        resultLabel = new JLabel(new ImageIcon("coin.png"));
        resultLabel.setHorizontalAlignment(JLabel.CENTER);
        coinFlipPanel.add(resultLabel, BorderLayout.CENTER); // Add label to the CENTER

        playButton = new JButton("Play Coin Flip"); 
        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playCoinFlip();
            }
        });
        
        // Style the Play Button (Add this block within your coinFlipPanel setup)
        playButton.setPreferredSize(new Dimension(240, 60)); 
        playButton.setFont(new Font("Arial", Font.BOLD, 24));
        playButton.setBackground(Color.WHITE); 
        playButton.setForeground(Color.BLACK); 
        playButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20)); 
        playButton.setFocusPainted(false); 
        // CSS Styling (Inline HTML for the button)
        playButton.setText("<html><div style='" +
                "position: fixed; " +
                "top: 50%; " +
                "left: 50%; " +
                "transform: translate(-50%, -50%); " +
                "z-index: 9999; " +
                "'>" + 
                "Play Coin Flip" +
                "</div></html>"); 
        coinFlipPanel.add(playButton);
        return coinFlipPanel;
    }

    // Create Menu Bar
    private JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        JMenu homeMenu = new JMenu("Home");
        JMenu casinoMenu = new JMenu("Casino");
        JMenu profileMenu = new JMenu("Profile");

        JMenuItem coinFlip = new JMenuItem("Coin Flip");
        JMenuItem solitaire = new JMenuItem("Solitaire");
        JMenuItem slots = new JMenuItem("Slots");
        JMenuItem home = new JMenuItem("Home");
        JMenuItem profile = new JMenuItem("Profile");

        menuBar.add(homeMenu);
        menuBar.add(casinoMenu);
        menuBar.add(profileMenu);

        casinoMenu.add(coinFlip);
        casinoMenu.add(solitaire);
        casinoMenu.add(slots);
        homeMenu.add(home);
        profileMenu.add(profile);

        // Action Listeners
        coinFlip.addActionListener(e -> switchToGame("Coin Flip"));
        solitaire.addActionListener(e -> switchToGame("Solitaire"));
        slots.addActionListener(e -> switchToGame("Slots"));
        home.addActionListener(e -> switchToGame("Home"));
        profile.addActionListener(e -> switchToGame("Profile"));
        
        return menuBar;
    }
    
    // Switch Game Panel
    private void switchToGame(String gameName) {
        if (!currentGame.equals(gameName)) {
            cardLayout.show(cardsPanel, gameName);
            currentGame = gameName;
        }
    }

    // Launch Application
    public static void run() {
        SwingUtilities.invokeLater(() -> new Screen());
    }

    // Coin Flip Game Logic
    private static void playCoinFlip() {
        // Input for bet amount
        String betAmountString = JOptionPane.showInputDialog("Enter your bet amount:");
        double betAmount;
        try {
            betAmount = Double.parseDouble(betAmountString);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid bet amount. Please enter a number.");
            return;
        }

        // Choice for heads or tails
        Object[] options = {"Heads", "Tails"};
        int choice = JOptionPane.showOptionDialog(null, "Choose heads or tails:", "Coin Flip",
                JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,
                null, options, options[0]);
        String side = (choice == 0) ? "Heads" : "Tails";

        // Make the bet using your CoinFlip class
        String result = CoinFlip.makeBet(betAmount, side);

        // Display the result using the image (replace with your actual image paths)
        if (Double.parseDouble(result) > betAmount) {
            JOptionPane.showMessageDialog(null, "You won $" + result + "!");
        } else {
            JOptionPane.showMessageDialog(null, "You lost $" + result + "!");
        }
    }

    // Method to display result of coin flip
    public static void displayCoinFlip(String coinSide) {
        if(coinSide.equals("Heads")) {
            resultLabel.setIcon(new ImageIcon("heads.png"));
        }
        else {
            resultLabel.setIcon(new ImageIcon("tails.png"));
        }
    }
}