import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class SelectCharacterScreen {
    SelectCharacterScreen(){
        JFrame f = new JFrame();
        JButton A=new JButton();
        JButton B=new JButton();


        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon imageIcon = new ImageIcon("C:\\Users\\evban\\OneDrive\\Pictures\\Nim\\Frame 1.png");
                g.drawImage(imageIcon.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };

        panel.setPreferredSize(new Dimension(1000, 707));
        panel.setLayout(null);

        B.setBounds(545,457,156,55);
        f.add(B);
        B.setOpaque(false);
        B.setContentAreaFilled(false);
        B.setBorderPainted(false);
        B.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new SelectCharacterScreen();
                f.dispose();
            }
        });

        A.setBounds(300,457,156,55);
        f.add(A);
        A.setOpaque(false);
        A.setContentAreaFilled(false);
        A.setBorderPainted(false);
        A.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new SelectCharacterScreen();
                f.dispose();
            }
        });

        f.getContentPane().add(panel);

        f.pack();
        f.setLocationRelativeTo(null);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
        f.setResizable(false);

    }

}
