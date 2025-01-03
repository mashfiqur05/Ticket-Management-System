// package Frames;
// import Classes.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MovieReceipt extends JFrame implements ActionListener, MouseListener {
    JPanel panel;
    JLabel nmLabel, nmbLabel, dateLabel, tmLabel, stLabel, qtLabel, totalLabel, mvLabel,movieNameLabel;
    JButton backButton, purchaseBtn;
    Font myFont1, myFont2;
    Color myColor1, myColor2;
    TicketDetailsList ticketDetailsList;
    TicketSellerList seller;
    MovieFileReader movieFileReader;
    String imagepath, movieName, date, time, seatNo;
    JFrame previousFrame;

    public MovieReceipt(JFrame previousFrame, String name, String phone, String date, String time, String seatNo, int quantity, TicketSellerList seller, String imagepath, String movieName, TicketDetailsList ticketDetailsList, MovieFileReader movieFileReader) {
        super("Receipt");
        this.setSize(800, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.movieName = movieName;
        this.ticketDetailsList = ticketDetailsList;
        this.movieFileReader = movieFileReader;
        this.date = date;
        this.time = time;
        this.seatNo = seatNo;
        this.previousFrame = previousFrame; 

        myColor1 = new Color(206, 185, 224);
        myColor2 = Color.decode("#a5d8ff");
        myFont1 = new Font("Arial", Font.BOLD, 20);
        myFont2 = new Font("Arial", Font.PLAIN, 16);

        panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(myColor1);

        backButton = new JButton("Back");
        backButton.setBounds(30, 30, 120, 30);
        backButton.setBackground(myColor2);
        backButton.setOpaque(true);
        backButton.setBorderPainted(false);
        backButton.addActionListener(this);
        backButton.addMouseListener(this);
        panel.add(backButton);

        mvLabel = new JLabel();
        mvLabel.setBounds(30, 100, 190, 230);
        mvLabel.setBackground(myColor2);

        // Load and set the movie image
        ImageIcon movieImage = new ImageIcon(imagepath);
        Image img = movieImage.getImage(); // transform it
        Image newimg = img.getScaledInstance(190, 230, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
        movieImage = new ImageIcon(newimg); // transform it back
        mvLabel.setIcon(movieImage);
        mvLabel.setHorizontalTextPosition(SwingConstants.CENTER); // Center text horizontally
        mvLabel.setVerticalTextPosition(SwingConstants.BOTTOM); // Center text vertically

        panel.add(mvLabel);

        String[] labels = {
            "Name: ", "Phone: ", "Show Date: ", "Time: ", "Quantity: ",
            "Seat: ", "Total Amount: "
        };

        for (int i = 0; i < labels.length; i++) {
            JLabel label = new JLabel(labels[i]);
            label.setBounds(400, 85 + i * 30, 250, 50);
            label.setFont(myFont1);
            panel.add(label);
        }

        
        movieNameLabel = new JLabel(movieName);
        movieNameLabel.setBounds(55, 360, 140, 25);
        movieNameLabel.setOpaque(true);
        movieNameLabel.setFont(myFont1);
        movieNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        movieNameLabel.setVerticalAlignment(SwingConstants.CENTER);
        panel.add(movieNameLabel);
        
        nmLabel = new JLabel(name);
        nmLabel.setBounds(560, 95, 150, 25);
        nmLabel.setOpaque(true);
        nmLabel.setBackground(Color.WHITE);
        nmLabel.setFont(myFont2);
        panel.add(nmLabel);

        nmbLabel = new JLabel(phone);
        nmbLabel.setBounds(560, 125, 150, 25);
        nmbLabel.setOpaque(true);
        nmbLabel.setBackground(Color.WHITE);
        nmbLabel.setFont(myFont2);
        panel.add(nmbLabel);

        dateLabel = new JLabel(date);
        dateLabel.setBounds(560, 155, 150, 25);
        dateLabel.setOpaque(true);
        dateLabel.setBackground(Color.WHITE);
        dateLabel.setFont(myFont2);
        panel.add(dateLabel);

        tmLabel = new JLabel(time);
        tmLabel.setBounds(560, 185, 150, 25);
        tmLabel.setOpaque(true);
        tmLabel.setBackground(Color.WHITE);
        tmLabel.setFont(myFont2);
        panel.add(tmLabel);

        qtLabel = new JLabel(String.valueOf(quantity));
        qtLabel.setBounds(560, 215, 150, 25);
        qtLabel.setOpaque(true);
        qtLabel.setBackground(Color.WHITE);
        qtLabel.setFont(myFont2);
        panel.add(qtLabel);

        stLabel = new JLabel(seatNo);
        stLabel.setBounds(560, 245, 150, 25);
        stLabel.setOpaque(true);
        stLabel.setBackground(Color.WHITE);
        stLabel.setFont(myFont2);
        panel.add(stLabel);

        int totalAmount = quantity * 500; 
        totalLabel = new JLabel(totalAmount + " BDT");
        totalLabel.setBounds(560, 280, 150, 25);
        totalLabel.setOpaque(true);
        totalLabel.setBackground(Color.WHITE);
        totalLabel.setFont(myFont2);
        panel.add(totalLabel);

        purchaseBtn = new JButton("Purchase Ticket");
        purchaseBtn.setBounds(310, 440, 150, 30);
        purchaseBtn.setBackground(myColor2);
        purchaseBtn.setOpaque(true);
        purchaseBtn.setBorderPainted(false);
        purchaseBtn.addActionListener(this); 
        purchaseBtn.addMouseListener(this);
        panel.add(purchaseBtn);

        this.add(panel);
        this.setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        String command = ae.getActionCommand();
        if (backButton.getText().equals(command)) {
            // MovieTicketBooking m = new MovieTicketBooking(seller, imagepath, movieName, ticketDetailsList, movieFileReader);
            
            previousFrame.setVisible(true); 
            dispose();
        }
        if (ae.getSource() == purchaseBtn) {
            if (ae.getSource() == purchaseBtn) {
                // Collect ticket details
                TicketInfo ticketInfo = new TicketInfo(movieName, this.imagepath, date, time, seatNo);
            
                // Save ticket info to the file
                ticketDetailsList.addNewTicket(ticketInfo); // Adding the ticket to the list
            
                // Save the updated ticket details list to the file
                ticketDetailsList.saveToFile();
            
                JOptionPane.showMessageDialog(this, "\t \tPayment Successful \n \tThank You!");
                System.exit(0);
            }
            

            JOptionPane.showMessageDialog(this, "\t \tPayment Successful \n \tThank You!");
            System.exit(0);
        }
    }

    public void mouseClicked(MouseEvent me) {}
    public void mouseEntered(MouseEvent me) {
        if (me.getSource() == backButton) {
            backButton.setBackground(Color.decode("#228be6"));
        } else if (me.getSource() == purchaseBtn) {
            purchaseBtn.setBackground(Color.decode("#228be6"));
        }    
    }
    
    public void mouseExited(MouseEvent me) {
        if (me.getSource() == purchaseBtn) {
            purchaseBtn.setBackground(myColor2);
        } else if (me.getSource() == backButton) {
            backButton.setBackground(myColor2);
        }
    }
    
    public void mousePressed(MouseEvent me) {}
    public void mouseReleased(MouseEvent me) {}
}
