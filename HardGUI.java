import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import java.util.Stack;

public class HardGUI {
    private JFrame frame;
    private JPanel panel;
    private JLabel[] pileLabels;
    private JLabel[] starLabels;
    private JButton[] removeButtons;
    private Stack<Integer>[] piles;
    private int lastMovePlayer = 1; // Initialize to player 1
    private JLabel player1Indicator; // Square for player 1
    private JLabel player2Indicator; // Square for player 2

    private ImageIcon starIcon;

    public HardGUI() {
        SwingUtilities.invokeLater(() -> {
            createAndShowGUI();
            initializeGame();
        });
    }

    private void createAndShowGUI() {
        frame = new JFrame("Hard Mode Nim");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(1000, 707));
        frame.setResizable(false);

        starIcon = new ImageIcon("C:/Users/evban/OneDrive/Pictures/Nim/StarNIM.png");

        JLabel backgroundLabel = new JLabel();
        ImageIcon backgroundImage = new ImageIcon("C:/Users/evban/OneDrive/Pictures/Nim/HardMode.png");
        Image image = backgroundImage.getImage().getScaledInstance(1000, 707, Image.SCALE_SMOOTH);
        backgroundImage = new ImageIcon(image);
        backgroundLabel.setIcon(backgroundImage);

        panel = new JPanel();
        panel.setOpaque(false);
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.CENTER;

        JPanel pileButtonPanel = new JPanel();
        pileButtonPanel.setOpaque(false);
        pileButtonPanel.setLayout(new GridBagLayout());
        GridBagConstraints pileButtonPanelConstraints = new GridBagConstraints();
        // Adjusting GridBagConstraints for pileButtonPanel
        pileButtonPanelConstraints.gridx = 0;
        pileButtonPanelConstraints.gridy = 0;
        pileButtonPanelConstraints.anchor = GridBagConstraints.CENTER;
        pileButtonPanelConstraints.weighty = 0; // Setting weighty to 0 to remove vertical gaps

        pileLabels = new JLabel[7]; // Change to 7 piles
        starLabels = new JLabel[7]; // Change to 7 piles
        removeButtons = new JButton[7]; // Change to 7 piles
        for (int i = 0; i < 7; i++) { // Change to 7 piles
            int pileIndex = i + 1;
            pileLabels[i] = new JLabel("Pile " + pileIndex + ": ");
            pileLabels[i].setForeground(Color.WHITE);
            starLabels[i] = new JLabel();
            removeButtons[i] = new JButton();
            try {
                BufferedImage img = ImageIO.read(new File("C:/Users/evban/OneDrive/Pictures/Nim/REMOVE.png"));
                Image scaledImage = img.getScaledInstance(50, 25, Image.SCALE_SMOOTH);
                removeButtons[i].setIcon(new ImageIcon(scaledImage));
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            removeButtons[i].setBorderPainted(false);
            removeButtons[i].setContentAreaFilled(false);
            removeButtons[i].addActionListener(new RemoveButtonListener(pileIndex));

            // Add pile label
            GridBagConstraints pileLabelConstraints = new GridBagConstraints();
            pileLabelConstraints.gridx = 0;
            pileLabelConstraints.gridy = i; // No space between rows
            pileLabelConstraints.anchor = GridBagConstraints.LINE_START;
            pileLabelConstraints.insets = new Insets(0, 10, 0, 165); // Adjust insets for vertical space
            pileButtonPanel.add(pileLabels[i], pileLabelConstraints);

            // Add star label
            GridBagConstraints starLabelConstraints = new GridBagConstraints();
            starLabelConstraints.gridx = 1;
            starLabelConstraints.gridy = i; // No space between rows
            starLabelConstraints.anchor = GridBagConstraints.CENTER;
            starLabelConstraints.insets = new Insets(0, 10, 0, 10); // Adjust insets for vertical space
            pileButtonPanel.add(starLabels[i], starLabelConstraints);

            // Add remove button
            GridBagConstraints removeButtonConstraints = new GridBagConstraints();
            removeButtonConstraints.gridx = 2;
            removeButtonConstraints.gridy = i; // No space between rows
            removeButtonConstraints.anchor = GridBagConstraints.LINE_START;
            removeButtonConstraints.insets = new Insets(0, 165, 0, 10); // Adjust insets for vertical space
            pileButtonPanel.add(removeButtons[i], removeButtonConstraints);
        }





        // Adding squares for player indicators
        player1Indicator = createIndicatorSquare("C:/Users/evban/OneDrive/Pictures/Nim/Player1.png");
        player2Indicator = createIndicatorSquare("C:/Users/evban/OneDrive/Pictures/Nim/Player2.png");

        JPanel indicatorPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        indicatorPanel.setOpaque(false);
        indicatorPanel.add(player1Indicator);
        indicatorPanel.add(player2Indicator);

        gbc.gridy++;
        panel.add(pileButtonPanel, gbc);
        gbc.gridy++;
        panel.add(indicatorPanel, gbc);

        backgroundLabel.setLayout(new GridBagLayout());
        backgroundLabel.add(panel);

        frame.getContentPane().add(backgroundLabel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private JLabel createIndicatorSquare(String imagePath) {
        JLabel indicator = new JLabel();
        ImageIcon icon = new ImageIcon(imagePath);
        if (icon.getIconWidth() <= 0 || icon.getIconHeight() <= 0) {
            // Handle case where image dimensions are zero or negative
            System.err.println("Error: Image dimensions are zero or negative.");
            return indicator;
        }
        int newWidth = (int) (icon.getIconWidth() * 0.7);
        int newHeight = (int) (icon.getIconHeight() * 0.7);
        Image scaledImage = icon.getImage().getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
        if (scaledImage == null) {
            // Handle case where scaled image is null
            System.err.println("Error: Scaled image is null.");
            return indicator;
        }
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        indicator.setPreferredSize(new Dimension(180, 180));
        indicator.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        indicator.setIcon(scaledIcon);
        indicator.setHorizontalAlignment(SwingConstants.CENTER);
        indicator.setVerticalAlignment(SwingConstants.CENTER);
        return indicator;
    }

    private void initializeGame() {
        piles = new Stack[7]; // Change to 7 piles
        int[] pileSizes = {1, 3, 5, 7, 9, 11, 13}; // Hard mode pile sizes
        for (int i = 0; i < 7; i++) { // Change to 7 piles
            piles[i] = new Stack<>();
            int numElements = pileSizes[i];
            while (numElements > 0) {
                piles[i].push(1); // Each element in the pile is represented as 1
                numElements--;
            }
        }

        updateGUI();
    }

    private class RemoveButtonListener implements ActionListener {
        private int pileIndex;

        public RemoveButtonListener(int pileIndex) {
            this.pileIndex = pileIndex;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            int numToRemove = chooseNumberToRemove();
            if (numToRemove > 0 && !piles[pileIndex - 1].isEmpty()) {
                for (int i = 0; i < numToRemove && !piles[pileIndex - 1].isEmpty(); i++) {
                    piles[pileIndex - 1].pop();
                }
                lastMovePlayer = 3 - lastMovePlayer;
                updateGUI();
            }
        }

        private int chooseNumberToRemove() {
            String[] options = {"1", "2"};
            return Integer.parseInt((String) JOptionPane.showInputDialog(frame,
                    "Choose the number of objects to remove from Pile " + pileIndex + ":",
                    "Remove",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    options,
                    options[0]));
        }
    }

    private int findWinningPlayer() {
        return lastMovePlayer;
    }

    private void updateGUI() {
        // Update player indicators
        if (lastMovePlayer == 1) {
            player1Indicator.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2)); // White outline for player 1
            player2Indicator.setBorder(null); // No outline for player 2
        } else {
            player1Indicator.setBorder(null); // No outline for player 1
            player2Indicator.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2)); // White outline for player 2
        }

        for (int i = 0; i < 7; i++) { // Change to 7 piles
            int numObjects = piles[i].size(); // Adjust index
            starLabels[i].setIcon(null); // Adjust index
            if (numObjects > 0) {
                StringBuilder stars = new StringBuilder();
                for (int j = 0; j < numObjects; j++) {
                    stars.append("<img src='file:///C:/Users/evban/OneDrive/Pictures/Nim/StarNIM.png' width='22' height='22'/>");
                    if (j < numObjects - 1) {
                        stars.append("&nbsp;");
                    }
                }

                starLabels[i].setText("<html>" + stars.toString() + "</html>");
            } else {
                starLabels[i].setText("");
            }
            pileLabels[i].setText("<html><font color='white'>Pile " + (i + 1) + ": " + numObjects + "</font></html>"); // Adjust index
        }

        if (isGameOver()) {
            int winningPlayer = findWinningPlayer();
            if (winningPlayer != -1) {
                new WinningPlayer(winningPlayer);
            }
            frame.dispose();
        }
    }

    private boolean isGameOver() {
        for (int i = 0; i < 7; i++) { // Change to 7 piles
            if (!piles[i].isEmpty()) { // Adjust index
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        new HardGUI();
    }
}
