// package Interfaces;
// import Classes.*;
// import Frames.*;

public interface ITicketSellerList {
    
    public abstract void addNewAgent (Agent agent);
    public Agent[] getSellerList();
    public int isUserExist(String name);
}
