// package Frames;


// import Classes.*;
// import Frames.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class DeletUser extends JFrame implements ActionListener, MouseListener {

    JPanel panel;
    JLabel userLabel, passLabel, confirmLabel;
    JButton confirmButton, backButton;
    JTextField userTF;
    JPasswordField passTF;
    JCheckBox showPassword;
    Font myFont1, myFont2;
    Color myColor1, myColor2;
    TicketSellerList seller;
    MovieFileReader movieFileReader;

    public DeletUser(TicketSellerList seller, MovieFileReader movieFileReader) {
        super("Delet a User");
        this.seller = seller;
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

        // Confirm Button
        confirmButton = new JButton("Confirm");
        confirmButton.setBounds(290, 370, 100, 30);
        confirmButton.setBackground(myColor2);
        confirmButton.setOpaque(true);
        confirmButton.setBorderPainted(false);
        confirmButton.setFont(new Font("Arial", Font.BOLD, 14));
        confirmButton.addActionListener(this);
        confirmButton.addMouseListener(this);
        panel.add(confirmButton);

        // Username Label
        userLabel = new JLabel("Username: ");
        userLabel.setBackground(myColor1);
        userLabel.setBounds(180, 150, 120, 30);
        userLabel.setForeground(Color.BLACK);
        userLabel.setOpaque(true);
        userLabel.setFont(myFont1);
        panel.add(userLabel);

        // Password Label
        passLabel = new JLabel("Password: ");
        passLabel.setBackground(myColor1);
        passLabel.setBounds(180, 200, 140, 30);
        passLabel.setForeground(Color.BLACK);
        passLabel.setOpaque(true);
        passLabel.setFont(myFont1);
        panel.add(passLabel);

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
            Admin admin = new Admin(seller, movieFileReader);
            admin.setVisible(true);
            this.setVisible(false);
        } else if (e.getSource() == confirmButton) {
            String username = userTF.getText();
            String password = new String(passTF.getPassword());

            if (!username.isEmpty() && !password.isEmpty()) {
                    int index = seller.isUserExist(username);
                    if (index == -1) {
                        JOptionPane.showMessageDialog(this, "User doesn't exist.");
                    } else {
                        Agent agent = seller.getUser (index);
                        if (agent != null) {
                            seller.deleteUser(agent); // Safely delete user
                            JOptionPane.showMessageDialog(this, "Successfully you've deleted a user.");
                            Login login = new Login(seller, movieFileReader); // Go back to login
                            login.setVisible(true);
                            this.setVisible(false);
                        } else {
                            JOptionPane.showMessageDialog(this, "Error: User data is invalid.");
                        }
                    }
                
            } else {
                JOptionPane.showMessageDialog(this, "All fields are required!");
            }
        } else if (e.getSource() == showPassword) {
            if (showPassword.isSelected()) {
                passTF.setEchoChar((char) 0); // Show password
            } else {
                passTF.setEchoChar('*'); // Hide password
            }
        }
    }

    public void mouseEntered(MouseEvent me) {
        if (me.getSource() == backButton) {
            backButton.setBackground(Color.decode("#228be6"));
        } else if (me.getSource() == confirmButton) {
            confirmButton.setBackground(Color.decode("#228be6"));
        }
    }

    public void mouseExited(MouseEvent e) {
        if (e.getSource() == backButton) {
            backButton.setBackground(myColor2);
        } else if (e.getSource() == confirmButton) {
            confirmButton.setBackground(myColor2);
        }
    }

    public void mouseClicked(MouseEvent e) {
    }

    public void mousePressed(MouseEvent e) {
    }

    public void mouseReleased(MouseEvent e) {
    }

}
