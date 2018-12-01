public class SellWindow extends Thread {
	private Tickets  t;
	String name;

	public SellWindow(String name,Tickets t) {
		super();
		this.t = t;
		this.name=name;
	}
	

	@Override
	public void run() {
		while(true) {
			if(t.getTicketNum()<100) {
				int x=t.sellTicket();
				System.out.println(name+":"+x);
			}
			else
				break;
		}
	}
	

}
