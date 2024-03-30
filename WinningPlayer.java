import javax.swing.*;
import java.awt.*;

public class WinningPlayer extends JFrame {
    private ImageIcon winnerIcon;

    public WinningPlayer(int winningPlayer) {
        super("Winner");

        // Load the appropriate image based on the winning player
        if (winningPlayer == 1) {
            winnerIcon = new ImageIcon("C:/Users/evban/OneDrive/Pictures/Nim/Winner Player 1.png");
        } else {
            winnerIcon = new ImageIcon("C:/Users/evban/OneDrive/Pictures/Nim/Winner Player 2.png");
        }

        // Resize the image to fit the frame
        Image scaledImage = winnerIcon.getImage().getScaledInstance(1000, 707, Image.SCALE_SMOOTH);
        winnerIcon = new ImageIcon(scaledImage);

        JLabel backgroundLabel = new JLabel(winnerIcon);
        backgroundLabel.setLayout(new BorderLayout());
        setContentPane(backgroundLabel);

        setSize(1000, 707); // Set the size to match the main frame
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }
}
