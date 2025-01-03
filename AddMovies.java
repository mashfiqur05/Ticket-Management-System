// package Frames;

// import Classes.*;
// import Frames.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileWriter;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class AddMovies extends JFrame implements MouseListener, ActionListener {
    JPanel panel;
    JLabel welcomeLabel, imageLabel, movieNameLabel, pathLabel;
    JTextField movieNameTF, pathTF;
    JButton dashboardButton, addMoviesButton, ticketSellersButton, importButton, confirmButton, adduserButton, buyTicketsButton;
    Font myFont1, myFont2;
    Color myColor1, myColor2, colourChange, labelColor;
    TicketSellerList seller;
    MovieFileReader movieFileReader;
    BufferedImage movieImage;

    public AddMovies(TicketSellerList seller, MovieFileReader movieFileReader) {
        super("Aiub Cineplex");
        this.setSize(800, 650);
        this.movieFileReader = movieFileReader;
        this.movieFileReader = new MovieFileReader();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        /// color and font
        myColor1 = new Color(206, 185, 224);
        myColor2 = Color.decode("#a5d8ff");
        colourChange = Color.decode("#228be6");
        labelColor = Color.decode("#9775fa");
        myFont1 = new Font("Arial", Font.BOLD, 16);
        myFont2 = new Font("Arial", Font.PLAIN, 14);
        

        /// panel
        panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(myColor1);

        /// Welcome label
        welcomeLabel = new JLabel("Welcome Admin", SwingConstants.CENTER);
        welcomeLabel.setFont(myFont1);
        welcomeLabel.setBackground(myColor2);
        welcomeLabel.setForeground(Color.BLACK);
        welcomeLabel.setBounds(0, 0, 210, 100);
        panel.add(welcomeLabel);

        /// Dashboard button
        dashboardButton = new JButton("Dashboard");
        dashboardButton.setBounds(0, 100, 200, 100);
        dashboardButton.setBackground(myColor2);
        dashboardButton.setOpaque(true);
        dashboardButton.addMouseListener(this);
        dashboardButton.addActionListener(this);
        dashboardButton.setFont(myFont1);
        panel.add(dashboardButton);

        /// Buy tickets button
        buyTicketsButton = new JButton("Buy Tickets");
        buyTicketsButton.setBounds(0, 200, 200, 100);
        buyTicketsButton.setBackground(myColor2);
        buyTicketsButton.setOpaque(true);
        buyTicketsButton.addMouseListener(this);
        buyTicketsButton.addActionListener(this);
        buyTicketsButton.setFont(myFont1);
        panel.add(buyTicketsButton);

        /// Register seller button
        adduserButton = new JButton("Register a seller");
        adduserButton.setBounds(0, 300, 200, 100);
        adduserButton.setBackground(myColor2);
        adduserButton.setOpaque(true);
        adduserButton.addMouseListener(this);
        adduserButton.addActionListener(this);
        adduserButton.setFont(myFont1);
        panel.add(adduserButton); 

        /// Add movies button
        addMoviesButton = new JButton("Add movies");
        addMoviesButton.setBounds(0, 400, 200, 100);
        addMoviesButton.setBackground(colourChange);
        addMoviesButton.setOpaque(true);
        addMoviesButton.addMouseListener(this);
        addMoviesButton.addActionListener(this);
        addMoviesButton.setFont(myFont1);
        panel.add(addMoviesButton);

        /// Ticket sellers button
        ticketSellersButton = new JButton("Ticket sellers");
        ticketSellersButton.setBounds(0, 500, 200, 100);
        ticketSellersButton.setBackground(myColor2);
        ticketSellersButton.setOpaque(true);
        ticketSellersButton.addMouseListener(this);
        ticketSellersButton.addActionListener(this);
        ticketSellersButton.setFont(myFont1);
        panel.add(ticketSellersButton);

        /// Movie image label (+add movie button functionality)
        imageLabel = new JLabel("+add movie", SwingConstants.CENTER);
        imageLabel.setFont(myFont1);
        imageLabel.setBounds(400, 80, 140, 180);
        imageLabel.setBackground(colourChange);
        imageLabel.setForeground(Color.BLACK);
        imageLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        imageLabel.addMouseListener(this); // MouseListener added to detect click
        panel.add(imageLabel);

        /// Movie name label
        movieNameLabel = new JLabel("Movie Name: ", SwingConstants.CENTER);
        movieNameLabel.setBounds(250, 325, 110, 30);
        movieNameLabel.setBackground(myColor1);
        movieNameLabel.setForeground(Color.BLACK);
        movieNameLabel.setFont(myFont1);
        movieNameLabel.setOpaque(true);
        panel.add(movieNameLabel);

        movieNameTF = new JTextField();
        movieNameTF.setBounds(365, 325, 200, 30);
        movieNameTF.setFont(myFont2);
        panel.add(movieNameTF);

        /// Movie Path
        pathLabel = new JLabel("Movie Path: ", SwingConstants.CENTER);
        pathLabel.setBounds(250, 360, 110, 30);
        pathLabel.setBackground(myColor1);
        pathLabel.setForeground(Color.BLACK);
        pathLabel.setFont(myFont1);
        pathLabel.setOpaque(true);
        panel.add(pathLabel);

        pathTF = new JTextField();
        pathTF.setBounds(365, 360, 200, 30);
        pathTF.setFont(myFont2);
        panel.add(pathTF);

        /// Confirm button
        confirmButton = new JButton("Confirm");
        confirmButton.setBounds(400, 400, 100, 25);
        confirmButton.setBackground(myColor2);
        confirmButton.setOpaque(true);
        confirmButton.addMouseListener(this);
        confirmButton.addActionListener(this);
        confirmButton.setFont(myFont1);
        panel.add(confirmButton);

        this.add(panel);
        this.setVisible(true);
    }

    /// Action Listener
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == dashboardButton) {
            Admin admin = new Admin(seller, movieFileReader);
            admin.setVisible(true);
            this.setVisible(false);
        } else if (ae.getSource() == buyTicketsButton) {
            Home home = new Home(seller, movieFileReader);
            home.setVisible(true);
            this.setVisible(false);
        } else if (ae.getSource() == adduserButton) {
            Register register = new Register(seller, movieFileReader);
            register.setVisible(true);
            this.setVisible(false);
        } else if (ae.getSource() == ticketSellersButton) {
            TicketSellerGui ticketSellerGui = new TicketSellerGui(seller, movieFileReader);
            ticketSellerGui.setVisible(true);
            this.setVisible(false);
        } else if (ae.getSource() == confirmButton) {
    String movieName = movieNameTF.getText();  // Movie name from text field
    String moviePath = pathTF.getText();
    if (movieName.isEmpty() || movieImage == null) {
        JOptionPane.showMessageDialog(null, "Please add both movie name and image.", "Error", JOptionPane.ERROR_MESSAGE);
    } else {
        
        moviePath = moviePath.substring(1, moviePath.length() - 1);
        // System.out.println(movieName);
        // System.out.println(moviePath);
        Movie movie = new Movie(moviePath, movieName);
        movieFileReader.addNewMovie(movie);
        int response = JOptionPane.showConfirmDialog(null,
                "Successfully added the movie! Do you want to go back to Dashboard?",
                "Movie Added", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);

        if (response == JOptionPane.OK_OPTION) {
            // Redirect to dashboard after OK is clicked
            Admin admin = new Admin(seller, movieFileReader);
            admin.setVisible(true);
            this.setVisible(false);
        }
    }
}
    }

    /// Mouse Listener
    public void mouseClicked(MouseEvent me) {
        // When +add movie label is clicked
        if (me.getSource() == imageLabel) {
            JFileChooser fileChooser = new JFileChooser();
            int returnValue = fileChooser.showOpenDialog(null);
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                try {
                    movieImage = ImageIO.read(selectedFile);
                    ImageIcon icon = new ImageIcon(movieImage.getScaledInstance(140, 180, Image.SCALE_SMOOTH));
                    imageLabel.setIcon(icon);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void mousePressed(MouseEvent me) {}
    public void mouseReleased(MouseEvent me) {}
    public void mouseEntered(MouseEvent me) {
        if (me.getSource() == dashboardButton) {
            dashboardButton.setBackground(colourChange);
        } else if (me.getSource() == buyTicketsButton) {
            buyTicketsButton.setBackground(colourChange);
        } else if (me.getSource() == adduserButton) {
            adduserButton.setBackground(colourChange);
        } else if (me.getSource() == ticketSellersButton) {
            ticketSellersButton.setBackground(colourChange);
        } else if (me.getSource() == confirmButton) {
            confirmButton.setBackground(colourChange);
        }
    }

    public void mouseExited(MouseEvent me) {
        if (me.getSource() == dashboardButton) {
            dashboardButton.setBackground(myColor2);
        } else if (me.getSource() == buyTicketsButton) {
            buyTicketsButton.setBackground(myColor2);
        } else if (me.getSource() == adduserButton) {
            adduserButton.setBackground(myColor2);
        } else if (me.getSource() == ticketSellersButton) {
            ticketSellersButton.setBackground(myColor2);
        } else if (me.getSource() == confirmButton) {
            confirmButton.setBackground(myColor2);
        }
    }
}
