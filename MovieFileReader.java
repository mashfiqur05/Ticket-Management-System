// package Classes;
// import Frames.*;
import java.io.*;
import java.util.Scanner;

public class MovieFileReader {
    Movie movieList[] = new Movie[100];
    public int movieCount = 0;

    public MovieFileReader() {
        try {
            File file = new File("File/movies.txt");
            Scanner sc = new Scanner(file);
            while (sc.hasNext()) {
                String imagePath = sc.nextLine();  // Image path
                String movieName = sc.nextLine();  // Movie name
                if (sc.hasNextLine()) {
                    sc.nextLine();  // Empty line (separating movies)
                }
                Movie movie = new Movie(imagePath, movieName);
                movieList[movieCount] = movie;
                movieCount++;
            }
            sc.close();
        } catch (Exception ex) {
            System.out.println("File not found or error reading the file.");
        }
    }

    // Method to retrieve all movies from the movieList array
    public Movie[] getMovieList() {
        return movieList;
    }

    // Add a new movie and save it to the file
    public void addNewMovie(Movie movie) {
        try {
            movieList[movieCount] = movie;  // Add movie to the array
            movieCount++;  // Increment movie count

            String movieDetails = movie.getImagePath() + "\n" + movie.getMovieName() + "\n\n";

            // Save movie to the file
            FileWriter fileWrite = new FileWriter("File/movies.txt", true);  // Append hobe
            fileWrite.write(movieDetails);
            fileWrite.close();
        } catch (Exception e) {
            System.out.println("Error while writing to file: " + e.getMessage());
        }
    }

    
}
