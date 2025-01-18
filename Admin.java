// package Frames;

// import Classes.*;
// import Frames.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Admin extends JFrame implements MouseListener, ActionListener {
    JPanel panel, sidebar, content;
    JLabel welcomeLabel, AvailableMovieLabel, moviesListLabel, ticketSoldLabel, totalSold, profitNameLabel, totalProfit;
    JButton dashboardButton, addMoviesButton, ticketSellersButton, buyTicketsButton, adduserButton,deleteUserButton;
    Font myFont1, myFont2;
    Color myColor1, myColor2, colourChange;
    TicketSellerList seller;
    MovieFileReader movieFileReader;
    TicketDetailsList ticketDetailsList;
    String userType;

    public Admin(TicketSellerList seller, MovieFileReader movieFileReader, String userType) {
        super("Aiub Cineplex");
        this.setSize(800, 645);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.movieFileReader = movieFileReader;
        this.ticketDetailsList = new TicketDetailsList();
        this.seller = seller;
        this.userType = userType;

        System.out.print("Admin: ");
        System.out.println(seller);

        /// color and font
        myColor1 = new Color(206, 185, 224);
        myColor2 = Color.decode("#a5d8ff");
        colourChange = Color.decode("#228be6");
        myFont1 = new Font("Arial", Font.BOLD, 16);
        myFont2 = new Font("Arial", Font.PLAIN, 14);

        /// panel
        panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(myColor1);

        /// welcome
        welcomeLabel = new JLabel("Welcome Admin", SwingConstants.CENTER);
        welcomeLabel.setFont(myFont1);
        welcomeLabel.setBackground(myColor1);
        welcomeLabel.setForeground(Color.BLACK);
        welcomeLabel.setBounds(0, 0, 210, 100);
        panel.add(welcomeLabel);

        /// Dashboard
        dashboardButton = new JButton("Dashboard");
        dashboardButton.setBounds(0, 100, 200, 100);
        dashboardButton.setBackground(colourChange);
        dashboardButton.setOpaque(true);
        dashboardButton.setFont(myFont1);
        panel.add(dashboardButton);

        /// Buy ticket 
        buyTicketsButton = new JButton("Buy Tickets");
        buyTicketsButton.setBounds(0, 200, 200, 100);
        buyTicketsButton.setBackground(myColor2);
        buyTicketsButton.setOpaque(true);
        buyTicketsButton.addMouseListener(this);
        buyTicketsButton.addActionListener(this);
        buyTicketsButton.setFont(myFont1);
        panel.add(buyTicketsButton);

        /// Add user
        adduserButton = new JButton("Register a seller");
        adduserButton.setBounds(0, 300, 200, 100);
        adduserButton.setBackground(myColor2);
        adduserButton.setOpaque(true);
        adduserButton.addMouseListener(this);
        adduserButton.addActionListener(this);
        adduserButton.setFont(myFont1);
        panel.add(adduserButton); 

        /// add movies
        addMoviesButton = new JButton("Add movies");
        addMoviesButton.setBounds(0, 400, 200, 100);
        addMoviesButton.setBackground(myColor2);
        addMoviesButton.setOpaque(true);
        addMoviesButton.addMouseListener(this);
        addMoviesButton.addActionListener(this);
        addMoviesButton.setFont(myFont1);
        panel.add(addMoviesButton);

        /// ticket sellers
        ticketSellersButton = new JButton("Ticket sellers");
        ticketSellersButton.setBounds(0, 500, 200, 100);
        ticketSellersButton.setBackground(myColor2);
        ticketSellersButton.setOpaque(true);
        ticketSellersButton.addMouseListener(this);
        ticketSellersButton.addActionListener(this);
        ticketSellersButton.setFont(myFont1);
        panel.add(ticketSellersButton);

        /// Available Movies
        AvailableMovieLabel = new JLabel("Available Movies", SwingConstants.CENTER);
        AvailableMovieLabel.setFont(myFont1);
        AvailableMovieLabel.setBackground(myColor2);
        AvailableMovieLabel.setForeground(Color.BLACK);
        AvailableMovieLabel.setOpaque(true);
        AvailableMovieLabel.setBounds(250, 100, 200, 30);
        panel.add(AvailableMovieLabel);

        /// Movies List 
        addMovieLabels();

        /// Total ticket sold
        ticketSoldLabel = new JLabel("Total Ticket Sold", SwingConstants.CENTER);
        ticketSoldLabel.setFont(myFont1);
        ticketSoldLabel.setBackground(myColor2);
        ticketSoldLabel.setForeground(Color.BLACK);
        ticketSoldLabel.setOpaque(true);
        ticketSoldLabel.setBounds(500, 100, 200, 30);
        panel.add(ticketSoldLabel);
        /// count label
        int soldCount = ticketDetailsList.getTotalTicketSold();
        // System.out.println(soldCount);
        totalSold = new JLabel(String.valueOf(soldCount), SwingConstants.CENTER);
        totalSold.setFont(myFont1);
        totalSold.setBackground(myColor2);
        totalSold.setForeground(Color.BLACK);
        totalSold.setOpaque(true);
        totalSold.setBounds(500, 135, 200, 30);
        panel.add(totalSold);


        /// Profit label
        profitNameLabel = new JLabel("Total Profit", SwingConstants.CENTER);
        profitNameLabel.setFont(myFont1);
        profitNameLabel.setBackground(myColor2);
        profitNameLabel.setForeground(Color.BLACK);
        profitNameLabel.setOpaque(true);
        profitNameLabel.setBounds(500, 200, 200, 30);
        panel.add(profitNameLabel);

        /// profit label
        int profit = soldCount * 500;
        // System.out.println(soldCount);
        totalSold = new JLabel(String.valueOf(profit) + ".00 Tk.", SwingConstants.CENTER);
        totalSold.setFont(myFont1);
        totalSold.setBackground(myColor2);
        totalSold.setForeground(Color.BLACK);
        totalSold.setOpaque(true);
        totalSold.setBounds(500, 235, 200, 30);
        panel.add(totalSold);

        /// delet User
        deleteUserButton = new JButton("Delete a User");
        deleteUserButton.setBounds(500, 300, 200, 30);
        deleteUserButton.setBackground(Color.decode("#FF6666"));
        deleteUserButton.setOpaque(true);
        deleteUserButton.addMouseListener(this);
        deleteUserButton.addActionListener(this);
        deleteUserButton.setFont(myFont1);
        panel.add(deleteUserButton);
        
        this.add(panel);
        this.setVisible(true);
    }

    /**
     * Adds movie names from the movieList array to the panel as labels.
     */
    private void addMovieLabels() {
        Movie[] movies = movieFileReader.getMovieList();
        int yPosition = 140; // Start position for the first movie label
        int labelHeight = 30; // Height of each label
        int labelWidth = 200; // Width of each label

        for (int i = 0; i < movieFileReader.movieCount; i++) {
            Movie movie = movies[i];
            if (movie != null) {  // Check if the movie exists in the array
                JLabel movieLabel = new JLabel(movie.getMovieName(), SwingConstants.CENTER);
                movieLabel.setFont(myFont2);
                movieLabel.setBackground(myColor2);
                movieLabel.setForeground(Color.BLACK);
                movieLabel.setOpaque(true);
                movieLabel.setBounds(250, yPosition, labelWidth, labelHeight); // Dynamically set label position
                panel.add(movieLabel);
                yPosition += 40; // Adjust y-position for next label (spacing between labels)
            }
        }
    }

    public void mouseClicked(MouseEvent me) {}

    public void mousePressed(MouseEvent me) {}

    public void mouseReleased(MouseEvent me) {}

    public void mouseEntered(MouseEvent me) {
        if (me.getSource() == addMoviesButton) {
            addMoviesButton.setBackground(colourChange);
        } else if (me.getSource() == ticketSellersButton) {
            ticketSellersButton.setBackground(colourChange);
        } else if (me.getSource() == buyTicketsButton) {
            buyTicketsButton.setBackground(colourChange);
        } else if (me.getSource() == adduserButton) {
            adduserButton.setBackground(colourChange);
        }
    }

    public void mouseExited(MouseEvent me) {
        if (me.getSource() == addMoviesButton) {
            addMoviesButton.setBackground(myColor2);
        } else if (me.getSource() == ticketSellersButton) {
            ticketSellersButton.setBackground(myColor2);
        } else if (me.getSource() == buyTicketsButton) {
            buyTicketsButton.setBackground(myColor2);
        } else if (me.getSource() == adduserButton) {
            adduserButton.setBackground(myColor2);
        }
    }

    public void actionPerformed(ActionEvent ae) {
        String command = ae.getActionCommand();
        if (buyTicketsButton.getText().equals(command)) {
            System.out.print("Admin action performed : ");
            System.out.println(seller);
            HomeForAdmin home = new HomeForAdmin(seller, movieFileReader, userType);
            home.setVisible(true);
            this.setVisible(false);
        } else if (addMoviesButton.getText().equals(command)) {
            AddMovies addMovies = new AddMovies(seller, movieFileReader, userType);
            addMovies.setVisible(true);
            this.setVisible(false);
        } else if (adduserButton.getText().equals(command)) {
            Register register = new Register(seller, movieFileReader, userType);
            register.setVisible(true);
            this.setVisible(false);
        } else if (ticketSellersButton.getText().equals(command)){
            TicketSellerGui ticketSellerGui = new TicketSellerGui(seller, movieFileReader, userType);
            ticketSellerGui.setVisible(true);
            this.setVisible(false);
        } else if (deleteUserButton.getText().equals(command)){
            DeletUser deletUser = new DeletUser(seller, movieFileReader, userType);
            deletUser.setVisible(true);
            this.setVisible(false);
            System.out.println("delet button clicked");
        }
    }
}
