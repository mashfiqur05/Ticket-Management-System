import java.lang.*;
// import Frames.*;
// import Classes.*;
// import Interfaces.*;


public class Start
{
	public static void main (String []args)
	{
		TicketSellerList seller = new TicketSellerList();
		MovieFileReader movieFileReader = new MovieFileReader();
		Login login = new Login(seller, movieFileReader);
		login.setVisible(true);
	}
}