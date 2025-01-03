// package Frames;
// import Classes.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Register extends JFrame implements ActionListener, MouseListener 
{
    JPanel panel;
    JLabel nameLabel, ageLabel, mobileLabel, emailLabel, passwordLabel, confirmPasswordLabel, userTypeLabel;
    JTextField nameField, ageField, mobileField, emailField;
    JPasswordField passwordField, confirmPasswordField;
    JButton backButton, registerButton;
    JCheckBox showPassword;
    JComboBox userTypeComboBox;
    Font myFont1, myFont2;
    Color myColor1, myColor2;
    TicketSellerList seller;
    MovieFileReader movieFileReader;
    

    public Register(TicketSellerList seller,MovieFileReader movieFileReader) {
        super("Register");
        
        this.setSize(800, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.seller = seller;
        this.movieFileReader = movieFileReader;

        myColor1 = new Color(206, 185, 224);
        myColor2 = Color.decode("#a5d8ff");
        myFont1 = new Font("Arial", Font.BOLD, 16);
        myFont2 = new Font("Arial", Font.PLAIN, 14);

        panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(myColor1);

        backButton = new JButton("Back");
        backButton.setBounds(30, 30, 120, 30);
        backButton.setBackground(myColor2);
        backButton.setFont(new Font("Arial", Font.BOLD, 14));
        backButton.setOpaque(true);
        backButton.setBorderPainted(false);
        backButton.addActionListener(this);
        backButton.addMouseListener(this);
        panel.add(backButton);

        // Name field
        nameLabel = new JLabel("Name");
        nameLabel.setBounds(150, 80, 100, 30);
        nameLabel.setFont(myFont1);
        panel.add(nameLabel);

        nameField = new JTextField();
        nameField.setBounds(300, 80, 350, 30);
        nameField.setFont(myFont2);
        panel.add(nameField);

        // Age field
        ageLabel = new JLabel("Age");
        ageLabel.setBounds(150, 130, 100, 30);
        ageLabel.setFont(myFont1);
        panel.add(ageLabel);

        ageField = new JTextField();
        ageField.setBounds(300, 130, 100, 30);
        ageField.setFont(myFont2);
        panel.add(ageField);

        // user type field
        userTypeLabel = new JLabel("Register as");
        userTypeLabel.setBounds(410, 130, 120, 30);
        userTypeLabel.setFont(myFont1);
        panel.add(userTypeLabel);

        String names[] = { "Admin", "Ticket Seller" };
        userTypeComboBox = new JComboBox<>(names);
        userTypeComboBox.setBounds(520, 130, 130, 30);
        panel.add(userTypeComboBox);

        // Mobile number field
        mobileLabel = new JLabel("Phone");
        mobileLabel.setBounds(150, 180, 100, 30);
        mobileLabel.setFont(myFont1);
        panel.add(mobileLabel);

        mobileField = new JTextField();
        mobileField.setBounds(300, 180, 350, 30);
        mobileField.setFont(myFont2);
        panel.add(mobileField);

        // Email field
        emailLabel = new JLabel("Email");
        emailLabel.setBounds(150, 230, 100, 30);
        emailLabel.setFont(myFont1);
        panel.add(emailLabel);

        emailField = new JTextField();
        emailField.setBounds(300, 230, 350, 30);
        emailField.setFont(myFont2);
        panel.add(emailField);

        // Password field
        passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(150, 280, 100, 30);
        passwordLabel.setFont(myFont1);
        panel.add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(300, 280, 350, 30);
        passwordField.setFont(myFont2);
        panel.add(passwordField);

        // Confirm password field
        confirmPasswordLabel = new JLabel("Confirm Password");
        confirmPasswordLabel.setBounds(150, 330, 150, 30);
        confirmPasswordLabel.setFont(myFont1);
        panel.add(confirmPasswordLabel);

        confirmPasswordField = new JPasswordField();
        confirmPasswordField.setBounds(300, 330, 350, 30);
        confirmPasswordField.setFont(myFont2);
        panel.add(confirmPasswordField);

        // Show password checkbox
        showPassword = new JCheckBox("Show Password");
        showPassword.setBounds(300, 370, 150, 30);
        showPassword.setOpaque(false);
        showPassword.addActionListener(e -> {
            if (showPassword.isSelected()) {
                passwordField.setEchoChar((char) 0); // Show password
                confirmPasswordField.setEchoChar((char) 0); // Show confirm password
            } else {
                passwordField.setEchoChar('*'); // Hide password
                confirmPasswordField.setEchoChar('*'); // Hide confirm password
            }
        });
        panel.add(showPassword);

        // Register button
        registerButton = new JButton("Register");
        registerButton.setBounds(500, 455, 150, 50);
        registerButton.setBackground(myColor2);
        registerButton.setOpaque(true);
        registerButton.setBorderPainted(false);
        registerButton.setFont(new Font("Arial", Font.BOLD, 14));
        registerButton.addActionListener(this);
        registerButton.addMouseListener(this);
        panel.add(registerButton);

        this.add(panel);
        this.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == backButton) {
            // Close the register screen and go back to login
            Admin admin = new Admin(seller, movieFileReader);
            admin.setVisible(true);
            this.setVisible(false);
        } else if (e.getSource() == registerButton) {
            // Handle user registration
            String name = nameField.getText();
            String age = ageField.getText();
            String mobile = mobileField.getText();
            String email = emailField.getText();
            String password = new String(passwordField.getPassword());
            String confirmPassword = new String(confirmPasswordField.getPassword());
            String userType = (String) userTypeComboBox.getSelectedItem(); 
            userType = userType.toLowerCase();

            if (!name.isEmpty() && !age.isEmpty() && !mobile.isEmpty() && !email.isEmpty()){
                if (password.equals(confirmPassword)){
                    Agent agent = new Agent(name, age, mobile, email, password, userType);
                    this.seller = new TicketSellerList();
                    seller.addNewAgent(agent);
                }else{
                    JOptionPane.showMessageDialog(this, "Password doesn't match");
                }

                JOptionPane.showMessageDialog(this, "Congratulation! You've register a new user");
                Admin admin = new Admin(seller, movieFileReader);
                admin.setVisible(true);
                this.setVisible(false);
            }else{
                JOptionPane.showMessageDialog(this, "Some field is required");
            }
        }  
    }      

    public void mouseEntered(MouseEvent e) {
        if (e.getSource() == backButton || e.getSource() == registerButton) {
            ((JButton) e.getSource()).setBackground(Color.decode("#228be6"));
        }
    }

    public void mouseExited(MouseEvent e) {
        if (e.getSource() == backButton) {
            backButton.setBackground(myColor2);
        } else if (e.getSource() == registerButton) {
            registerButton.setBackground(myColor2);
        }
    }

    public void mouseClicked(MouseEvent e) {}
    public void mousePressed(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}


}
