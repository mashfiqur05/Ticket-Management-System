// package Frames;
// import Classes.*;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TicketSellerGui extends JFrame implements MouseListener, ActionListener {
    JPanel panel;
    JLabel welcomeLabel, listLabel, nameLabel, typeLabel;
    JButton dashboardButton, addMoviesButton, ticketSellersButton, buyTicketsButton, adduserButton;
    Font myFont1, myFont2, titleFont;
    Color myColor1, myColor2, colourChange;
    TicketSellerList seller;
    MovieFileReader movieFileReader;

    public TicketSellerGui(TicketSellerList seller, MovieFileReader movieFileReader) {
        super("Aiub Cineplex");
        this.setSize(800, 645);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        
        // Initialize seller and movieFileReader
        this.seller = seller; // Make sure seller is initialized
        this.seller = new TicketSellerList();
        this.movieFileReader = movieFileReader;

        // Color and font
        myColor1 = new Color(206, 185, 224);
        myColor2 = Color.decode("#a5d8ff");
        colourChange = Color.decode("#228be6");
        titleFont = new Font("Arial", Font.BOLD, 30);
        myFont1 = new Font("Arial", Font.BOLD, 16);
        myFont2 = new Font("Arial", Font.PLAIN, 14);

        // Panel
        panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(myColor1);

        // Welcome
        welcomeLabel = new JLabel("Welcome Admin", SwingConstants.CENTER);
        welcomeLabel.setFont(myFont1);
        welcomeLabel.setBackground(myColor1);
        welcomeLabel.setForeground(Color.BLACK);
        welcomeLabel.setBounds(0, 0, 210, 100);
        panel.add(welcomeLabel);

        // Create buttons
        dashboardButton = new JButton("Dashboard");
        dashboardButton.setBounds(0, 100, 200, 100);
        dashboardButton.setBackground(myColor2);
        dashboardButton.setOpaque(true);
        dashboardButton.addMouseListener(this);
        dashboardButton.addActionListener(this);
        dashboardButton.setFont(myFont1);
        panel.add(dashboardButton);

        buyTicketsButton = new JButton("Buy Tickets");
        buyTicketsButton.setBounds(0, 200, 200, 100);
        buyTicketsButton.setBackground(myColor2);
        buyTicketsButton.setOpaque(true);
        buyTicketsButton.addMouseListener(this);
        buyTicketsButton.addActionListener(this);
        buyTicketsButton.setFont(myFont1);
        panel.add(buyTicketsButton);

        adduserButton = new JButton("Register a seller");
        adduserButton.setBounds(0, 300, 200, 100);
        adduserButton.setBackground(myColor2);
        adduserButton.setOpaque(true);
        adduserButton.addMouseListener(this);
        adduserButton.addActionListener(this);
        adduserButton.setFont(myFont1);
        panel.add(adduserButton);

        addMoviesButton = new JButton("Add movies");
        addMoviesButton.setBounds(0, 400, 200, 100);
        addMoviesButton.setBackground(myColor2);
        addMoviesButton.setOpaque(true);
        addMoviesButton.addMouseListener(this);
        addMoviesButton.addActionListener(this);
        addMoviesButton.setFont(myFont1);
        panel.add(addMoviesButton);

        ticketSellersButton = new JButton("Ticket sellers");
        ticketSellersButton.setBounds(0, 500, 200, 100);
        ticketSellersButton.setBackground(colourChange);
        ticketSellersButton.setOpaque(true);
        ticketSellersButton.addMouseListener(this);
        ticketSellersButton.addActionListener(this);
        ticketSellersButton.setFont(myFont1);
        panel.add(ticketSellersButton);

        // Ticket Seller List Title
        listLabel = new JLabel("Sellers List", SwingConstants.CENTER);
        listLabel.setBounds(350, 30, 200, 40);
        listLabel.setFont(titleFont);
        listLabel.setForeground(Color.BLACK);
        panel.add(listLabel);


        /// Name Label
        nameLabel = new JLabel("Name", SwingConstants.CENTER);
        nameLabel.setBounds(300, 100, 150, 30);
        nameLabel.setFont(myFont1);
        nameLabel.setBackground(myColor2);
        nameLabel.setForeground(Color.BLACK);
        nameLabel.setOpaque(true);
        panel.add (nameLabel);

        /// Type Label
        typeLabel = new JLabel("User type", SwingConstants.CENTER);
        typeLabel.setBounds(450, 100, 200, 30);
        typeLabel.setFont(myFont1);
        typeLabel.setBackground(myColor2);
        typeLabel.setForeground(Color.BLACK);
        typeLabel.setOpaque(true);
        panel.add (typeLabel);

        // Add sellers in GUI
        addSellersInGui();

        this.add(panel);
        this.setVisible(true);
    }

    /// Gui te add kortesi seller er details
    private void addSellersInGui() {
        if (seller == null) {
            System.out.println("Seller list is null!");
            return; // Prevents NullPointerException if seller is not initialized
        }
    
        Agent[] agents = seller.getSellerList();
        int yPosition = 140; // Start position for the first seller label
        int labelHeight = 30; // Height of each label
        int labelWidth = 200; // Width of each label
    
        for (int i = 0; i < seller.userCount; i++) {
            Agent agent = agents[i];
            if (agent != null) { 
                String sellerInfo = agent.getName();
                String userTypeINfo = agent.getUserType();
                JLabel sellerLabel = new JLabel(sellerInfo, SwingConstants.CENTER);
                JLabel userTypeLabel = new JLabel(userTypeINfo, SwingConstants.CENTER);
                sellerLabel.setFont(myFont2);
                sellerLabel.setBackground(myColor2);
                sellerLabel.setForeground(Color.BLACK);
                sellerLabel.setOpaque(true);
                sellerLabel.setBounds(300, yPosition, labelWidth, labelHeight); // Dynamically set label position
                panel.add(sellerLabel);

                userTypeLabel.setFont(myFont2);
                userTypeLabel.setBackground(myColor2);
                userTypeLabel.setForeground(Color.BLACK);
                userTypeLabel.setOpaque(true);
                userTypeLabel.setBounds(450, yPosition, labelWidth, labelHeight);
                panel.add(userTypeLabel);
                yPosition += 40; // Adjust y-position for next label (spacing between labels)
            }
        }
    }
    
    public void mouseClicked(MouseEvent me) {}

    public void mousePressed(MouseEvent me) {}

    public void mouseReleased(MouseEvent me) {}

    public void mouseEntered(MouseEvent me) {
        if (me.getSource() == dashboardButton) {
            dashboardButton.setBackground(colourChange);
        } else if (me.getSource() == buyTicketsButton) {
            buyTicketsButton.setBackground(colourChange);
        } else if (me.getSource() == adduserButton) {
            adduserButton.setBackground(colourChange);
        } 
    }

    public void mouseExited(MouseEvent me) {
        if (me.getSource() == dashboardButton) {
            dashboardButton.setBackground(myColor2);
        } else if (me.getSource() == buyTicketsButton) {
            buyTicketsButton.setBackground(myColor2);
        } else if (me.getSource() == adduserButton) {
            adduserButton.setBackground(myColor2);
        } 
    }

    public void actionPerformed(ActionEvent ae) {
        String command = ae.getActionCommand();
        if (buyTicketsButton.getText().equals(command)) {
            Home home = new Home(seller, movieFileReader);
            home.setVisible(true);
            this.setVisible(false);
        } else if (addMoviesButton.getText().equals(command)) {
            AddMovies addMovies = new AddMovies(seller, movieFileReader);
            addMovies.setVisible(true);
            this.setVisible(false);
        } else if (adduserButton.getText().equals(command)) {
            Register register = new Register(seller, movieFileReader);
            register.setVisible(true);
            this.setVisible(false);
        } else if (dashboardButton.getText().equals(command)) {
            Admin admin = new Admin(seller, movieFileReader);
            admin.setVisible(true);
            this.setVisible(false);
        }
    }
}
