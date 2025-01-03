// package Classes;
// import Interfaces.*;
// import Frames.*;

import java.io.*;
import java.util.Scanner;

public class TicketSellerList {
    Agent agentList[] = new Agent[100];
    public static int userCount = 0;
    
    public TicketSellerList (){

        try{
            File file = new File ("File/TicketSellerList.txt");
            Scanner sc = new Scanner(file);
            while (sc.hasNext()){
                String line1 = sc.nextLine();  //name
                String line2 = sc.nextLine();  //age
                String line3 = sc.nextLine();  //mobile
                String line4 = sc.nextLine();  //email
                String line5 = sc.nextLine();  //pass
                String line6 = sc.nextLine();  /// User type
                String line7 = sc.nextLine();  //extra newline for space between two objects
                
                Agent agent = new Agent(line1, line2, line3, line4, line5, line6);
                agentList[userCount] = agent;
                userCount++;
            }
            sc.close();
        }catch(Exception ex){
			//System.out.println(ex);
			System.out.println("File not found.");
			return;
        }
    }

    public Agent[] getSellerList() {
        return agentList;
    }

    /// Add new user
    public void addNewAgent (Agent agent){
        try{
            agentList[userCount] = agent;
            userCount++;
        }catch(Exception e){
            System.out.println("Array is out of bound");
        }

        String details = agent.getName() + "\n" + agent.getAge() + "\n" + agent.getMobile() + "\n" +  agent.getEmail() + "\n" + agent.getPassword() +  "\n" + agent.getUserType() + "\n" + "\n";

        try{
            FileWriter fileWrite = new FileWriter("File\\TicketSellerList.txt", true);
            fileWrite.write(details);
            fileWrite.close();
        }catch (Exception e){
            System.out.println(e);
        }
    }


    /// Checking username and pass
    public int isUserExist(String name){
        int index = -1;
        for (int i = 0; i < userCount; i++){
            if (agentList[i] != null && agentList[i].getName().equals(name)){
                index = i;
                break;
            }
        }
        return index;
    }

    public Agent passwordChecker (int index, String pass){
        Agent agent = null;
        System.out.println(agentList[index].getName());
        System.out.println(agentList[index].getPassword());
        System.out.println(agentList[index].getUserType());
        System.out.println(index);
        System.out.println(pass);
        if (agentList[index].getPassword().equals(pass))
        {
            agent = agentList[index];
        }

        return agent;
    }

    
    /// Reset password
    public void resetPassword (String username, String newPass){
        int index = isUserExist(username);
        if (index != -1) {
            agentList[index].setPassword(newPass);
            try {
                File inputFile = new File("File/TicketSellerList.txt");
                File tempFile = new File("File/tempFile.txt");
    
                // Use Scanner to read the original file
                Scanner sc = new Scanner(inputFile);
                FileWriter fileWrite = new FileWriter(tempFile, false);
    
                while (sc.hasNextLine()) {
                    String line1 = sc.nextLine();  // Name
                    String line2 = sc.nextLine();  // Age
                    String line3 = sc.nextLine();  // Mobile
                    String line4 = sc.nextLine();  // Email
                    String line5 = sc.nextLine();  // Password
                    String line6 = sc.nextLine();  // User Type
                    String line7 = sc.nextLine();  // Extra newline
    
                    if (line1.equals(username)) {
                        // If the username matches, write the new password
                        line5 = newPass;
                    }
    
                    // Write the data to the temp file (including the updated password if applicable)
                    fileWrite.write(line1 + "\n");
                    fileWrite.write(line2 + "\n");
                    fileWrite.write(line3 + "\n");
                    fileWrite.write(line4 + "\n");
                    fileWrite.write(line5 + "\n");
                    fileWrite.write(line6 + "\n");
                    fileWrite.write(line7 + "\n");
                }
    
                fileWrite.close();
                sc.close();
    
                // Replace the original file with the updated temp file
                if (!inputFile.delete()) {
                    System.out.println("Could not delete original file");
                }
                if (!tempFile.renameTo(inputFile)) {
                    System.out.println("Could not rename temp file");
                }
    
                System.out.println("Password reset successfully!");
    
            } catch (IOException e) {
                System.out.println("An error occurred while updating the file: " + e.getMessage());
            }
    
        } else {
            System.out.println("User does not exist!");
        }
    
    }

    /// get seller
    public Agent getUser(int index){
		return agentList[index];
	}

    /// Delet user 
    public void deleteUser(Agent agent) {
        // Deleting agent from the array
        for (int i = 0; i < userCount; i++) {
            if (agentList[i] == agent) {
                for (int j = i; j < userCount - 1; j++) {
                    agentList[j] = agentList[j + 1]; // Shift left
                }
                userCount--;
                agentList[userCount] = null; // Clear the last entry
                break;
            }
        }
    
        // Also deleting the agent from the file
        String agentToDelete = agent.getName() + "\n" + agent.getPassword() + "\n" +
                               agent.getAge() + "\n" + agent.getMobile() + "\n" +
                               agent.getEmail() + "\n" + agent.getUserType() + "\n";
    
        try {
            String filepath = "File/TicketSellerList.txt";
            File originalFile = new File(filepath);
    
            String newDetails = "";
            Scanner sc = new Scanner(originalFile);
    
            while (sc.hasNext()) {
                String readAgent = sc.nextLine() + "\n";   // Name
                       readAgent += sc.nextLine() + "\n";  // Age
                       readAgent += sc.nextLine() + "\n";  // Mobile
                       readAgent += sc.nextLine() + "\n";  // Email
                       readAgent += sc.nextLine() + "\n";  // Password
                       readAgent += sc.nextLine();         // User Type
    
                // If the read agent details match the agent to be deleted, skip this iteration
                if (readAgent.equals(agentToDelete)) {
                    continue; // Skip writing this agent to the new file
                }
    
                // Otherwise, add the agent's details to the new string
                newDetails += readAgent + "\n";
            }
            sc.close();
    
            // Write the updated details back to the file
            FileWriter fw = new FileWriter(filepath);
            fw.write(newDetails);
            fw.close();
    
            System.out.println("User deleted successfully from the file and array.");
    
        } catch (Exception ex) {
            System.out.println("An error occurred while deleting the user: " + ex.getMessage());
        }
    }
    
    
}
