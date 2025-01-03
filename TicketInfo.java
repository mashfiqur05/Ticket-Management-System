// package Classes;
// import Frames.*;

public class TicketInfo {
    private String movieName;
    private String moviePath;
    private String date;
    private String time;
    private String seatNo;

    public TicketInfo(String movieName, String moviePath, String date, String time, String seatNo) {
        this.movieName = movieName;
        this.moviePath = moviePath;
        this.date = date;
        this.time = time;
        this.seatNo = seatNo;
    }

    // Getter for movieName
    public String getMovieName() {
        return movieName;
    }

    // Setter for movieName
    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    // Getter for moviePath
    public String getMoviePath() {
        return moviePath;
    }

    // Setter for moviePath
    public void setMoviePath(String moviePath) {
        this.moviePath = moviePath;
    }

    // Getter for date
    public String getDate() {
        return date;
    }

    // Setter for date
    public void setDate(String date) {
        this.date = date;
    }

    // Getter for time
    public String getTime() {
        return time;
    }

    // Setter for time
    public void setTime(String time) {
        this.time = time;
    }

    // Getter for seatNo
    public String getSeatNo() {
        return seatNo;
    }

    // Setter for seatNo
    public void setSeatNo(String seatNo) {
        this.seatNo = seatNo;
    }
}
