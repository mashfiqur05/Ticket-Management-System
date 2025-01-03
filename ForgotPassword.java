// package Frames;

// import Classes.*;
// import Frames.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ForgotPassword extends JFrame implements ActionListener, MouseListener {
    JPanel panel;
    JLabel userLabel, passLabel, confirmLabel;
    JButton resetButton, backButton;
    JTextField userTF;
    JPasswordField passTF, confirmTF;
    JCheckBox showPassword;
    Font myFont1, myFont2;
    Color myColor1, myColor2;
    TicketSellerList seller;
    MovieFileReader movieFileReader;

    public ForgotPassword(TicketSellerList seller, MovieFileReader movieFileReader) {
        super("Forgot Password");
        this.seller = new TicketSellerList();
        this.movieFileReader = movieFileReader;
        this.setSize(800, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        myColor1 = new Color(206, 185, 224);
        myColor2 = Color.decode("#a5d8ff");
        myFont1 = new Font("Arial", Font.BOLD, 16);
        myFont2 = new Font("Arial", Font.PLAIN, 14);

        panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(myColor1);

        // Back Button
        backButton = new JButton("Back");
        backButton.setBounds(30, 30, 120, 30);
        backButton.setBackground(myColor2);
        backButton.setOpaque(true);
        backButton.setBorderPainted(false);
        backButton.setFont(new Font("Arial", Font.BOLD, 14));
        backButton.addActionListener(this); 
        backButton.addMouseListener(this);
        panel.add(backButton);

        // Reset Password Button
        resetButton = new JButton("Reset Password");
        resetButton.setBounds(290, 370, 150, 30);
        resetButton.setBackground(myColor2);
        resetButton.setOpaque(true);
        resetButton.setBorderPainted(false);
        resetButton.setFont(new Font("Arial", Font.BOLD, 14));
        resetButton.addActionListener(this);
        resetButton.addMouseListener(this);
        panel.add(resetButton);

        // Username Label
        userLabel = new JLabel("Username: ");
        userLabel.setBackground(myColor1);
        userLabel.setBounds(180, 150, 120, 30);
        userLabel.setForeground(Color.BLACK);
        userLabel.setOpaque(true);
        userLabel.setFont(myFont1);
        panel.add(userLabel);

        // Password Label
        passLabel = new JLabel("New Password: ");
        passLabel.setBackground(myColor1);
        passLabel.setBounds(180, 200, 140, 30);
        passLabel.setForeground(Color.BLACK);
        passLabel.setOpaque(true);
        passLabel.setFont(myFont1);
        panel.add(passLabel);

        // Confirm Password Label
        confirmLabel = new JLabel("Confirm Password: ");
        confirmLabel.setBackground(myColor1);
        confirmLabel.setBounds(180, 250, 150, 30);
        confirmLabel.setForeground(Color.BLACK);
        confirmLabel.setOpaque(true);
        confirmLabel.setFont(myFont1);
        panel.add(confirmLabel);

        // Username TextField
        userTF = new JTextField();
        userTF.setBounds(340, 150, 200, 30);
        userTF.setFont(myFont2);
        panel.add(userTF);

        // Password Field
        passTF = new JPasswordField();
        passTF.setBounds(340, 200, 200, 30);
        passTF.setFont(myFont2);
        panel.add(passTF);

        // Confirm Password Field
        confirmTF = new JPasswordField();
        confirmTF.setBounds(340, 250, 200, 30);
        confirmTF.setFont(myFont2);
        panel.add(confirmTF);

        // Show Password
        showPassword = new JCheckBox("Show Password");
        showPassword.setBounds(340, 290, 150, 30);
        showPassword.setOpaque(false);
        showPassword.addActionListener(this); 
        panel.add(showPassword);

        this.add(panel);
        this.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == backButton) {
            Login log = new Login(seller, movieFileReader);
            log.setVisible(true);
            this.setVisible(false);
        } 
        else if (e.getSource() == resetButton) {
            String username = userTF.getText();
            String newPassword = new String(passTF.getPassword());
            String confirmPassword = new String(confirmTF.getPassword());

            if (!username.isEmpty() && !newPassword.isEmpty() && !confirmPassword.isEmpty()) {
                if (newPassword.equals(confirmPassword)) {
                    int index = seller.isUserExist(username);
                    if (index == -1) {
                        JOptionPane.showMessageDialog(this, "User doesn't exist.");
                    } else {
                        seller.resetPassword(username, newPassword);
                        JOptionPane.showMessageDialog(this, "Password reset successfully!");
                        Login log = new Login(seller, movieFileReader); // Go back to login
                        this.setVisible(false);
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Passwords do not match!");
                }
            } else {
                JOptionPane.showMessageDialog(this, "All fields are required!");
            }
        }
        else if (e.getSource() == showPassword) {
            if (showPassword.isSelected()) {
                passTF.setEchoChar((char) 0); // Show password
                confirmTF.setEchoChar((char) 0); // Show confirm password
            } else {
                passTF.setEchoChar('*'); // Hide password
                confirmTF.setEchoChar('*'); // Hide confirm password
            }
        }
    }

    public void mouseEntered(MouseEvent me) {
        if (me.getSource() == backButton) {
            backButton.setBackground(Color.decode("#228be6"));
        } else if (me.getSource() == resetButton) {
            resetButton.setBackground(Color.decode("#228be6"));
        }
    }

    public void mouseExited(MouseEvent e) {
        if (e.getSource() == backButton) {
            backButton.setBackground(myColor2);
        } else if (e.getSource() == resetButton) {
            resetButton.setBackground(myColor2);
        }
    }

    public void mouseClicked(MouseEvent e) {}
    public void mousePressed(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}
}
