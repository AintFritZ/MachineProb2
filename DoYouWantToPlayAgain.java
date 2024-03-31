import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DoYouWantToPlayAgain extends JFrame {

    public DoYouWantToPlayAgain() {
        setTitle("Play Again?");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false); // Disable resizing
        setSize(1000, 707); // Set fixed size

        // Load the background image
        ImageIcon backgroundImage = new ImageIcon("C:\\Users\\evban\\OneDrive\\Pictures\\Nim\\PlayAgain.png");

        // Create buttons
        JButton yesButton = new JButton("");
        yesButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new SelectGameMode();
                System.out.println("Yes clicked");
                dispose();
            }
        });

        JButton noButton = new JButton("");
        noButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ThankYou thankYouFrame = new ThankYou();
                thankYouFrame.setVisible(true);
                dispose(); // Dispose the current frame
            }
        });


        // Set the bounds of the buttons
        yesButton.setBounds(242, 394, 176, 69); // Adjust position and size as needed
        noButton.setBounds(566, 394, 176, 69); // Adjust position and size as needed

        yesButton.setOpaque(false);
        yesButton.setContentAreaFilled(false);
        yesButton.setBorderPainted(false);

        noButton.setOpaque(false);
        noButton.setContentAreaFilled(false);
        noButton.setBorderPainted(false);


        // Create a JLabel to hold the background image
        JLabel backgroundLabel = new JLabel(backgroundImage);
        backgroundLabel.setLayout(null); // Enable absolute positioning
        backgroundLabel.setBounds(0, 0, backgroundImage.getIconWidth(), backgroundImage.getIconHeight());

        // Add buttons to the label
        backgroundLabel.add(yesButton);
        backgroundLabel.add(noButton);

        // Get the dimensions of the screen
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        // Calculate the coordinates to center the frame
        int x = (screenSize.width - getWidth()) / 2;
        int y = (screenSize.height - getHeight()) / 2;

        // Set the frame location
        setLocation(x, y);

        // Add the background label to the frame
        getContentPane().add(backgroundLabel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new DoYouWantToPlayAgain().setVisible(true);
            }
        });
    }
}
