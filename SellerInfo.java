// package Classes;
// import Frames.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SellerInfo extends JFrame implements MouseListener, ActionListener {
    JPanel panel, sidebar, content;
    JLabel welcomeLabel;
    JButton dashboardButton, addMoviesButton, ticketSellersButton;
    Font myFont1, myFont2;
    Color myColor1, myColor2, colourChange;

    public SellerInfo() {
        super("Aiub Cineplex");
        this.setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

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
        welcomeLabel.setBounds(0, 0, 210, 125);
        panel.add(welcomeLabel);

        /// Dashboard
        dashboardButton = new JButton("Dashboard");
        dashboardButton.setBounds(0, 125, 200, 125);
        dashboardButton.setBackground(myColor2);
        dashboardButton.setOpaque(true);
        dashboardButton.addMouseListener(this);
        dashboardButton.addActionListener(this);
        dashboardButton.setFont(myFont1);
        panel.add(dashboardButton);

        /// add movies
        addMoviesButton = new JButton("Add movies");
        addMoviesButton.setBounds(0, 250, 200, 125);
        addMoviesButton.setBackground(myColor2);
        addMoviesButton.setOpaque(true);
        addMoviesButton.addMouseListener(this);
        addMoviesButton.addActionListener(this);
        addMoviesButton.setFont(myFont1);
        panel.add(addMoviesButton);

        // /// ticket seller
        ticketSellersButton = new JButton("Ticket sellers");
        ticketSellersButton.setBounds(0, 375, 200, 125);
        ticketSellersButton.setBackground(colourChange);
        ticketSellersButton.setOpaque(true);
        ticketSellersButton.setFont(myFont1);
        panel.add(ticketSellersButton);

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
        if (me.getSource() == addMoviesButton) {
            addMoviesButton.setBackground(colourChange);
        } else if (me.getSource() == dashboardButton) {
            dashboardButton.setBackground(colourChange);
        }
    }

    public void mouseExited(MouseEvent me) {
        if (me.getSource() == addMoviesButton) {
            addMoviesButton.setBackground(myColor2);
        } else if (me.getSource() == dashboardButton) {
            dashboardButton.setBackground(myColor2);
        }
    }

    public void actionPerformed(ActionEvent ae) {
    }

}
