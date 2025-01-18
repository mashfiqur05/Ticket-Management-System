// package Frames;
// import Classes.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MovieTicketBooking extends JFrame implements ActionListener, MouseListener 
{
    JPanel panel;
    JLabel dateLabel, timeLabel, quantityLabel, seatsLabel, availableLabel, selectedLabel, notAvailableLabel, nameLabel, phoneLabel;
    JComboBox<String> dayComboBox, monthComboBox, yearComboBox, timeComboBox;
    JSpinner quantitySpinner;
    JTextField nameTF, phoneTF;
    JButton backButton, confirmButton;
    JCheckBox[] seatCheckBoxes;
    Font myFont1, myFont2;
    Color myColor1, myColor2, availableColor, selectedColor, notAvailableColor;
    int selectedSeatCount = 0;
    int[] selectedSeatsArray = new int[10]; 
    int selectedSeatsIndex = 0;
    TicketDetailsList ticketDetailsList;
    TicketSellerList seller;
    MovieFileReader movieFileReader;
    String imagePath, movieName, userType;

    int[][] unavailableSeats = 
    {
        {1, 7}, /// 11:00
        {2, 5}, /// 15:00 
        {3, 6}  /// 18:00
    };

    public MovieTicketBooking(TicketSellerList seller, String imagePath, String movieName, TicketDetailsList ticketDetailsList,MovieFileReader movieFileReader, String userType) 
    {
        super("Movie Ticket Booking");
        this.setSize(800, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.seller = seller;
        this.imagePath = imagePath;
        this.movieName = movieName;
        this.userType = userType;
        this.ticketDetailsList = ticketDetailsList;
        this.movieFileReader = movieFileReader;
        this.movieFileReader = new MovieFileReader();
        System.out.println(imagePath);

        myColor1 = new Color(206, 185, 224);
        myColor2 = Color.decode("#a5d8ff");
        availableColor = Color.GREEN;
        selectedColor = Color.RED;
        notAvailableColor = Color.decode("#e9ecef");
        myFont1 = new Font("Arial", Font.BOLD, 16);
        myFont2 = new Font("Arial", Font.PLAIN, 14);

        panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(myColor1);

        System.out.print("Movie ticket booking : ");
        System.out.println(seller);

        // Label
        nameLabel = new JLabel("Name: ");
        nameLabel.setBackground(myColor1);
        nameLabel.setBounds(50, 100, 55, 30);
        nameLabel.setForeground(Color.BLACK);
        nameLabel.setOpaque(true);
        nameLabel.setFont(myFont1);
        panel.add(nameLabel);

        phoneLabel = new JLabel("Phone: ");
        phoneLabel.setBackground(myColor1);
        phoneLabel.setBounds(50, 130, 60, 30);
        phoneLabel.setForeground(Color.BLACK);
        phoneLabel.setOpaque(true);
        phoneLabel.setFont(myFont1);
        panel.add(phoneLabel);

        quantityLabel = new JLabel("Quantity:");
        quantityLabel.setBounds(570, 100, 150, 30);
        quantityLabel.setFont(myFont1);
        panel.add(quantityLabel);

        seatsLabel = new JLabel("Select Seats");
        seatsLabel.setBounds(350, 200, 150, 30);
        seatsLabel.setFont(myFont1);
        panel.add(seatsLabel);

        availableLabel = new JLabel("Available");
        availableLabel.setBounds(350, 240, 100, 30);
        availableLabel.setOpaque(true);
        availableLabel.setBackground(availableColor);
        availableLabel.setFont(myFont2);
        panel.add(availableLabel);

        selectedLabel = new JLabel("Selected");
        selectedLabel.setBounds(460, 240, 100, 30);
        selectedLabel.setOpaque(true);
        selectedLabel.setBackground(selectedColor);
        selectedLabel.setFont(myFont2);
        panel.add(selectedLabel);

        notAvailableLabel = new JLabel("Not Available");
        notAvailableLabel.setBounds(570, 240, 120, 30);
        notAvailableLabel.setOpaque(true);
        notAvailableLabel.setBackground(notAvailableColor);
        notAvailableLabel.setFont(myFont2);
        panel.add(notAvailableLabel);

        dateLabel = new JLabel("Select Date");
        dateLabel.setBounds(50, 240, 150, 30);
        dateLabel.setFont(myFont1);
        panel.add(dateLabel);

        timeLabel = new JLabel("Select Time");
        timeLabel.setBounds(50, 360, 150, 30);
        timeLabel.setFont(myFont1);
        panel.add(timeLabel);

        nameTF = new JTextField();
        nameTF.setBounds(110, 100, 200, 30);
        nameTF.setFont(myFont2);
        panel.add(nameTF);

        phoneTF = new JTextField();
        phoneTF.setBounds(110, 130, 200, 30);
        phoneTF.setFont(myFont2);
        panel.add(phoneTF);

        // ComboBox for Day
        String[] days = new String[31];
        for (int i = 0; i < 31; i++) 
        {
            days[i] = String.valueOf(i + 1);
        }
        dayComboBox = new JComboBox<>(days);
        dayComboBox.setBounds(50, 280, 70, 30);
        panel.add(dayComboBox);

        // ComboBox for Month
        String[] months = {"JAN", "FEB", "MAR", "APR", "MAY", "JUN", "JUL", "AUG", "SEP", "OCT", "NOV", "DEC"};
        monthComboBox = new JComboBox<>(months);
        monthComboBox.setBounds(120, 280, 80, 30);
        panel.add(monthComboBox);

        // ComboBox for Year
        String[] years = {"2024","2025"};
        yearComboBox = new JComboBox<>(years);
        yearComboBox.setBounds(200, 280, 90, 30);
        panel.add(yearComboBox);

        String[] times = {"11:00", "15:00", "18:00"};
        timeComboBox = new JComboBox<>(times);
        timeComboBox.setBounds(50, 400, 180, 30);
        timeComboBox.addActionListener(this);
        panel.add(timeComboBox);

        quantitySpinner = new JSpinner(new SpinnerNumberModel(1, 1, 10, 1));
        quantitySpinner.setBounds(570, 130, 100, 30);
        panel.add(quantitySpinner);

        seatCheckBoxes = new JCheckBox[10];
        initializeSeats();

        // Button

        backButton = new JButton("Back");
        backButton.setBounds(30, 30, 120, 30);
        backButton.setBackground(myColor2);
        backButton.setOpaque(true);
        backButton.setBorderPainted(false);
        backButton.setFont(new Font("Arial", Font.BOLD, 14));
        backButton.addActionListener(this);
        backButton.addMouseListener(this);
        panel.add(backButton);

        confirmButton = new JButton("Confirm");
        confirmButton.setBounds(585, 480, 150, 50);
        confirmButton.setBackground(myColor2);
        confirmButton.setOpaque(true);
        confirmButton.setBorderPainted(false);
        confirmButton.setFont(new Font("Arial", Font.BOLD, 14));
        confirmButton.addActionListener(this);
        confirmButton.addMouseListener(this);
        panel.add(confirmButton);

        this.add(panel);
        this.setVisible(true);
    }

    public void initializeSeats() 
    {
        int x = 350, y = 280;
        for (int i = 0; i < 10; i++) 
        {
            seatCheckBoxes[i] = new JCheckBox(String.valueOf(i + 1));
            seatCheckBoxes[i].setBounds(x, y, 60, 60);
            seatCheckBoxes[i].setEnabled(true);
            seatCheckBoxes[i].setSelected(false);
            seatCheckBoxes[i].setBackground(availableColor);
            seatCheckBoxes[i].setOpaque(true);
            seatCheckBoxes[i].addActionListener(this);
            panel.add(seatCheckBoxes[i]);
            x += 80;
            if ((i + 1) % 5 == 0) 
            {
                x = 350;
                y += 80;
            }
        }
        updateSeatAvailability();
    }

    public void updateSeatAvailability() 
    {
        int selectedTimeIndex = timeComboBox.getSelectedIndex();
        int[] unavailableSeatsForTime = unavailableSeats[selectedTimeIndex];

        for (JCheckBox seatCheckBox : seatCheckBoxes) 
        {
            int seatNumber = Integer.parseInt(seatCheckBox.getText());
            if (contains(unavailableSeatsForTime, seatNumber)) 
            {
                seatCheckBox.setBackground(notAvailableColor);
                seatCheckBox.setEnabled(false);
            } 
            else 
            {
                seatCheckBox.setBackground(availableColor);
                seatCheckBox.setEnabled(true);
            }
        }
        selectedSeatCount = 0;
        selectedSeatsIndex = 0;
    }

    public void actionPerformed(ActionEvent ae) 
    {
        String command = ae.getActionCommand();
        String name = nameTF.getText().trim();
        String phone = phoneTF.getText().trim();

        if (ae.getSource() == backButton) 
        {
            if (seller != null) {
                Home home = new Home(seller, movieFileReader, userType);
                home.setVisible(true);
            } else {
                // Admin logic or default handling
                JOptionPane.showMessageDialog(this, "Returning to admin dashboard.");
                // Navigate to admin home or do something else
                Admin admin = new Admin(seller, movieFileReader, userType); // Assuming you have an AdminHome class
                admin.setVisible(true);
                this.setVisible(false);
            }
            // this.dispose();    
        } 
        else if (confirmButton.getText().equals(command)) 
        {
            if (name.isEmpty() || phone.isEmpty()) 
            {
                JOptionPane.showMessageDialog(this, "Please fill in both Name and Phone.");
                return;
            }
            int selectedQuantity = (Integer) quantitySpinner.getValue(); 

            if (selectedSeatCount < selectedQuantity) 
            { 
                JOptionPane.showMessageDialog(this, "Please select exactly " + selectedQuantity + " seats.");
                return;
            }

            int day = Integer.parseInt((String) dayComboBox.getSelectedItem());
            String month = (String) monthComboBox.getSelectedItem();
            int year = Integer.parseInt((String) yearComboBox.getSelectedItem());
            String selectedDate = day + " " + month + ", " + year;

            String selectedTime = (String) timeComboBox.getSelectedItem();
            
            String seats = "";
            for (int i = 0; i < selectedSeatsIndex; i++) 
            {
                seats += selectedSeatsArray[i]; 
                if (i < selectedSeatsIndex - 1) 
                {
                    seats += ", "; 
                }
            }

            MovieReceipt r = new MovieReceipt(this, name, phone, selectedDate, selectedTime, seats, selectedQuantity, seller, imagePath, movieName, ticketDetailsList, movieFileReader, userType);
            r.setVisible(true);
            // this.dispose();
            this.setVisible(false);
            System.out.println( seller);
        } 
        else if (ae.getSource() == timeComboBox) 
        {
            updateSeatAvailability(); 
        } 
        else 
        {
            for (JCheckBox seatCheckBox : seatCheckBoxes) 
            {
                if (ae.getSource() == seatCheckBox) 
                {
                    SeatSelection(seatCheckBox);
                    break;
                }
            }
        }
    }

    public void SeatSelection(JCheckBox seatCheckBox) 
    {
        int quantityLimit = (Integer) quantitySpinner.getValue(); 
        if (seatCheckBox.isSelected()) 
        {
            if (selectedSeatCount < quantityLimit) 
            {
                selectedSeatCount++;
                seatCheckBox.setBackground(selectedColor);
                selectedSeatsArray[selectedSeatsIndex++] = Integer.parseInt(seatCheckBox.getText());
            } 
            else 
            {
                seatCheckBox.setSelected(false);
                JOptionPane.showMessageDialog(this, "You can only select " + quantityLimit + " seats.");
            }
        } 
        else 
        {
            selectedSeatCount--;
            seatCheckBox.setBackground(availableColor);
            int seatNumber = Integer.parseInt(seatCheckBox.getText());
            for (int i = 0; i < selectedSeatsIndex; i++) {
                if (selectedSeatsArray[i] == seatNumber) 
                {
                    for (int j = i; j < selectedSeatsIndex - 1; j++) 
                    {
                        selectedSeatsArray[j] = selectedSeatsArray[j + 1];
                    }
                    selectedSeatsIndex--;
                    break;
                }
            }
        }
    }

    private boolean contains(int[] array, int value) 
    {
        for (int i : array) 
        {
            if (i == value) 
            {
                return true;
            }
        }
        return false;
    }

    public void mouseEntered(MouseEvent me) 
    {
        if (me.getSource() == backButton) 
        {
            backButton.setBackground(Color.decode("#228be6"));
        } 
        else if(me.getSource() == confirmButton) 
        {
            confirmButton.setBackground(Color.decode("#228be6"));
        }
    }

    public void mouseExited(MouseEvent e) 
    {
        if (e.getSource() == backButton) 
        {
            backButton.setBackground(myColor2);
        } 
        else if (e.getSource() == confirmButton) 
        {
            confirmButton.setBackground(myColor2);
        }
    }

    public void mousePressed(MouseEvent me) {}
    public void mouseReleased(MouseEvent me) {}
    public void mouseClicked(MouseEvent me) {}
}
