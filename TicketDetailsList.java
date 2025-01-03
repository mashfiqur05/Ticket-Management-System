// package Classes;
// import Frames.*;
import java.io.*;
import java.util.Scanner;

public class TicketDetailsList {
    TicketInfo[] ticketList = new TicketInfo[100]; // Array of TicketInfo with a size of 100
    public int ticketCount = 0;  // Keeps track of the number of tickets

    // Constructor that reads from the file and populates the ticket array
    public TicketDetailsList() {
        try {
            File file = new File("File/TicketDetails.txt");
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine() && ticketCount < ticketList.length) {
                String movieName = sc.nextLine();  // Movie name
                String imagePath = sc.nextLine();  // Image path
                String date = sc.nextLine();       // Show date
                String time = sc.nextLine();       // Time
                String seatNo = sc.nextLine();      // seatNo
                sc.nextLine(); // Skip extra newline

                // Create TicketInfo object and add to the array
                ticketList[ticketCount] = new TicketInfo(movieName, imagePath, date, time, seatNo);
                ticketCount++;
            }
            sc.close();
        } catch (FileNotFoundException ex) {
            System.out.println("File not found.");
        }
    }

    /// return koto ta ticket sold hoice.
    public int getTotalTicketSold(){
        return ticketCount;
    }

    // Method to add new ticket information
    public void addNewTicket(TicketInfo ticket) {
        if (ticketCount < ticketList.length) {
            ticketList[ticketCount] = ticket;
            ticketCount++;
            saveToFile();  // Save the updated ticket array to the file
        } else {
            System.out.println("Ticket list is full.");
        }
    }

    // Method to save the ticket details to the file
    public void saveToFile() {
        try (FileWriter fileWriter = new FileWriter("File/TicketDetails.txt", false)) {
            for (int i = 0; i < ticketCount; i++) {
                TicketInfo ticket = ticketList[i];
                fileWriter.write(ticket.getMovieName() + "\n");
                fileWriter.write(ticket.getMoviePath() + "\n");
                fileWriter.write(ticket.getDate() + "\n");
                fileWriter.write(ticket.getTime() + "\n");
                fileWriter.write(ticket.getSeatNo() + "\n\n"); // Extra newline for separation
            }
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file: " + e.getMessage());
        }
    }
}
