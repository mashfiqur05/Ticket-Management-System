// package Frames;
// import Classes.*;

import java.lang.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Login extends JFrame implements MouseListener, ActionListener{
    JPanel panel;
    JLabel titleLabel, userLabel,passLabel, forgotLabel;
    JButton loginButton, exitButton, clickButton;
    JTextField userTF;
    JPasswordField passTF;
    JCheckBox showPassword;
    Font myFont1, myFont2, titleFont;
    Color myColor1, myColor2, colourChange;
    TicketSellerList seller;
    MovieFileReader movieFileReader;

    public Login(TicketSellerList seller,MovieFileReader movieFileReader) {
        super("Aiub Cineplex - Login");
        seller = new TicketSellerList();
        this.seller = seller; 
        this.movieFileReader = movieFileReader;
		this.setSize(800,600);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);

        /// color and font
        myColor1 = new Color(206, 185, 224);
        myColor2 = Color.decode("#a5d8ff");
        colourChange = Color.decode("#228be6");
        myFont1 = new Font("Arial", Font.BOLD, 16);
        titleFont = new Font("Arial", Font.BOLD, 30);
        myFont2 = new Font("Arial", Font.PLAIN, 14);

        panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(myColor1);
        
        /// title
        titleLabel = new JLabel("Aiub Cineplex", JLabel.CENTER);
        titleLabel.setFont(titleFont);
        titleLabel.setBounds(0, 100, 800, 30);
        panel.add(titleLabel);

        /// user Label
        userLabel = new JLabel("Username: ");  
        userLabel.setBounds(250, 200, 100, 25);
        userLabel.setForeground(Color.BLACK);
        userLabel.setFont(myFont1);
        panel.add(userLabel);

        userTF = new JTextField();
        userTF.setBounds(350, 200, 200, 25);
        userTF.setFont(myFont2);
        panel.add(userTF);
        
        /// Pass label
        passLabel = new JLabel("Password: ");
        passLabel.setBounds(250, 260, 100, 25);
        passLabel.setForeground(Color.BLACK);
        passLabel.setFont(myFont1);
        panel.add(passLabel);

        passTF = new JPasswordField();
        passTF.setBounds(350, 260, 200, 25);
        passTF.setFont(myFont2);
        panel.add(passTF);

        showPassword = new JCheckBox("Show Password");
        showPassword.setBounds(350, 290, 150, 25);
        showPassword.setOpaque(false);
        panel.add(showPassword);


        // Simplified "Show Password" functionality using an anonymous inner class
        showPassword.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (showPassword.isSelected()) {
                    passTF.setEchoChar((char) 0);  // Show password
                } else {
                    passTF.setEchoChar('*');  // Hide password
                }
            }
        });

        loginButton = new JButton("Login");
        loginButton.setBounds(350, 370, 90, 30);
        loginButton.setBackground(myColor2);
        loginButton.setForeground(Color.BLACK);
        loginButton.setFont(new Font("Arial", Font.BOLD, 14));
        loginButton.addMouseListener(this);
        loginButton.addActionListener(this);
        loginButton.setOpaque(true);
        panel.add(loginButton);

        // Modified "Sign up" button to work as "Exit"
        exitButton = new JButton("Exit");
        exitButton.setBounds(460, 370, 90, 30);
        exitButton.setBackground(Color.decode("#FF6666"));
        exitButton.setForeground(Color.BLACK);
        exitButton.setBorderPainted(false);
        exitButton.setFont(new Font("Arial", Font.BOLD, 14));
        exitButton.addMouseListener(this);
        exitButton.addActionListener(this);
        exitButton.setOpaque(true);
        panel.add(exitButton);

        // Simplified "Forgot Password" label
        forgotLabel = new JLabel("Forgot password?");
        forgotLabel.setBounds(350, 415, 110, 30);
        forgotLabel.setForeground(Color.BLACK); 
        forgotLabel.setBackground(myColor1);
        forgotLabel.setBorder(null);
        forgotLabel.setOpaque(true);
        panel.add(forgotLabel);

        /// Click button
        clickButton = new JButton("Click here.");
        clickButton.setBounds(460, 415, 90, 30);
        clickButton.setForeground(Color.RED);
        clickButton.setBackground(myColor1);
        clickButton.setFont (new Font("Arial", Font.BOLD, 14));
        clickButton.setBorder(null);
        clickButton.setOpaque(true);
        clickButton.addActionListener(this);
        panel.add (clickButton);
        
        

		this.add(panel);
		this.setVisible(true);
    }

    public void mouseClicked(MouseEvent me) {
    }

    public void mousePressed(MouseEvent me) {
    }

    public void mouseReleased(MouseEvent me) {
    }

    public void mouseEntered(MouseEvent me) {
        if (me.getSource() == loginButton) {
            loginButton.setBackground(colourChange);
        } else if (me.getSource() == exitButton) {
            exitButton.setBackground(Color.RED);
        } 
    }

    public void mouseExited(MouseEvent me) {
        if (me.getSource() == loginButton) {
            loginButton.setBackground(myColor2);
        } else if (me.getSource() == exitButton) {
            exitButton.setBackground(Color.decode("#FF6666"));
        }
    }

    public void actionPerformed(ActionEvent ae) {
        String command = ae.getActionCommand(); 
    
        if (ae.getSource() == exitButton) {
            System.exit(0);
        } else if (ae.getSource() == loginButton) {
            String name = userTF.getText();
            String pass = new String(passTF.getPassword());
    
            if (!name.isEmpty() && !pass.isEmpty()) {
    
                int index = seller.isUserExist(name);
                if (index == -1) {
                    JOptionPane.showMessageDialog(this, "User doesn't exist.");
                } else {
                    Agent curAgent = seller.passwordChecker(index, pass);
                    if (curAgent == null) {
                        JOptionPane.showMessageDialog(this, "Invalid credentials or incorrect user type.");
                    } else {
                        String userType = curAgent.getUserType();
                        if (userType.equals("admin")) {
                            Admin admin = new Admin(seller, movieFileReader);
                            admin.setVisible(true);
                        } else if (userType.equals("ticket seller")) {
                            Home home = new Home(seller, movieFileReader);
                            home.setVisible(true);
                        }
                        this.setVisible(false);
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this, "Username/Password required!");
            }
        } 
        else if (ae.getSource() == clickButton) {
            if (seller == null) {
                System.out.println("Seller is not initialized!");
                return; // or handle the error appropriately
            }
            
            ForgotPassword forgotpass = new ForgotPassword(seller, movieFileReader);
            forgotpass.setVisible(true);
            this.setVisible(false);
        }
    }
    
    

}
