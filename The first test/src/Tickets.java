
public class Tickets {
	
	private int ticketNum=0;
    
	
	public int getTicketNum() {
		return ticketNum;
	}
	public  synchronized  int sellTicket() {
			ticketNum++;
			return ticketNum;
	}

}
