import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class Screen extends JFrame {
    private JPanel mainPanel, gamePanel;
    private JMenuBar menuBar;

    public Screen() {
        setTitle("Casino Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);

        // Main Panel Layout
        mainPanel = new JPanel(new BorderLayout());

        // Game Area
        gamePanel = new JPanel();
        gamePanel.setBackground(new Color(50, 120, 50));
        // Add your game components (slots, cards, etc.) to gamePanel

        // Menu Bar
        menuBar = new JMenuBar();

        JMenu homeMenu = new JMenu("Home");
        JMenu gamesMenu = new JMenu("Games");
        JMenu profileMenu = new JMenu("Profile");

        JMenuItem coinFlip = new JMenuItem("Coin Flip");
        JMenuItem solitaire = new JMenuItem("Solitaire");
        JMenuItem anotherGame = new JMenuItem("another game");

        // Add menu items to each menu (e.g., JMenuItem)
        // ...

        menuBar.add(homeMenu);
        menuBar.add(gamesMenu);
        menuBar.add(profileMenu);

        gamesMenu.add(coinFlip);
        gamesMenu.add(solitaire);
        gamesMenu.add(anotherGame);

        
        setJMenuBar(menuBar); // Set the menu bar for the JFrame

        mainPanel.add(gamePanel, BorderLayout.CENTER);
        add(mainPanel);

        setVisible(true);
    }

   

    public static void run() {
        SwingUtilities.invokeLater(() -> new Screen());
    }
}