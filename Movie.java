// package Classes;
// import Frames.*;

public class Movie {
    private String imagePath;
    private String movieName;

    public Movie(String imagePath, String movieName) {
        this.imagePath = imagePath;
        this.movieName = movieName;
    }

    public String getImagePath() {
        return imagePath;
    }

    public String getMovieName() {
        return movieName;
    }
}
