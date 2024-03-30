import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Stack;

public class EasyNimGUI {
    private JFrame frame;
    private JPanel panel;
    private JLabel[] pileLabels;
    private JLabel[] starLabels;
    private JButton[] removeButtons;
    private Stack<Integer>[] piles;
    private int lastMovePlayer = 2;

    private ImageIcon starIcon;

    public EasyNimGUI() {
        SwingUtilities.invokeLater(() -> {
            createAndShowGUI();
            initializeGame();
        });
    }

    private void createAndShowGUI() {
        frame = new JFrame("Easy Mode Nim");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(1000, 707));
        frame.setResizable(false);

        starIcon = new ImageIcon("C:/Users/evban/OneDrive/Pictures/Nim/StarNIM.png");

        JLabel backgroundLabel = new JLabel();
        ImageIcon backgroundImage = new ImageIcon("C:/Users/evban/OneDrive/Pictures/Nim/4.png");
        Image image = backgroundImage.getImage().getScaledInstance(1000, 707, Image.SCALE_SMOOTH);
        backgroundImage = new ImageIcon(image);
        backgroundLabel.setIcon(backgroundImage);

        panel = new JPanel();
        panel.setOpaque(false);
        panel.setLayout(new BorderLayout());

        JPanel pileButtonPanel = new JPanel(new GridLayout(5, 1));
        pileButtonPanel.setOpaque(false);
        pileButtonPanel.setBorder(BorderFactory.createEmptyBorder(50, 0, 0, 0));
        pileButtonPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        pileLabels = new JLabel[3];
        starLabels = new JLabel[3];
        removeButtons = new JButton[3];
        for (int i = 0; i < 3; i++) {
            int pileIndex = i + 1;
            pileLabels[i] = new JLabel("Pile " + pileIndex + ": ");
            pileLabels[i].setForeground(Color.WHITE);
            starLabels[i] = new JLabel();
            removeButtons[i] = new JButton("Remove");
            removeButtons[i].addActionListener(new RemoveButtonListener(pileIndex));
            JPanel pileButtonRowPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
            pileButtonRowPanel.setOpaque(false);
            pileButtonRowPanel.add(starLabels[i]);
            pileButtonRowPanel.add(removeButtons[i]);
            pileButtonPanel.add(pileButtonRowPanel);
        }

        JPanel pileLabelPanel = new JPanel(new GridLayout(1, 3));
        pileLabelPanel.setOpaque(false);
        for (int i = 0; i < 3; i++) {
            pileLabelPanel.add(pileLabels[i]);
        }
        pileButtonPanel.add(pileLabelPanel);

        panel.add(pileButtonPanel, BorderLayout.CENTER);

        backgroundLabel.setLayout(new GridBagLayout());
        backgroundLabel.add(panel);

        frame.getContentPane().add(backgroundLabel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void initializeGame() {
        piles = new Stack[4];
        piles[1] = new Stack<>();
        piles[2] = new Stack<>();
        piles[3] = new Stack<>();

        for (int i = 1; i <= 3; i++) {
            int pileSize = 3 + 2 * (i - 1);
            for (int j = 0; j < pileSize; j++) {
                piles[i].push(1);
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
            if (numToRemove > 0 && !piles[pileIndex].isEmpty()) {
                for (int i = 0; i < numToRemove && !piles[pileIndex].isEmpty(); i++) {
                    piles[pileIndex].pop();
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
        for (int i = 0; i < 3; i++) {
            int numObjects = piles[i + 1].size();
            starLabels[i].setIcon(null);
            if (numObjects > 0) {
                StringBuilder stars = new StringBuilder();
                for (int j = 0; j < numObjects; j++) {
                    stars.append("<img src='file:///C:/Users/evban/OneDrive/Pictures/Nim/StarNIM.png' width='20' height='20'/>");
                }
                starLabels[i].setText("<html>" + stars.toString() + "</html>");
            } else {
                starLabels[i].setText("");
            }
            pileLabels[i].setText("<html><font color='white'>Pile " + (i + 1) + ": " + numObjects + "</font></html>");
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
        for (int i = 0; i < 3; i++) {
            if (!piles[i + 1].isEmpty()) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        new EasyNimGUI();
    }
}
