
public class Test {
	
	public static void main(String[] args) {
		Tickets t=new Tickets();
		SellWindow  w1=new SellWindow("一号售票窗口",t);
		SellWindow  w2=new SellWindow("二号售票窗口",t);
		SellWindow  w3=new SellWindow("三号售票窗口",t);
		SellWindow  w4=new SellWindow("四号售票窗口",t);
		SellWindow  w5=new SellWindow("五号售票窗口",t);
		w1.start();
		w2.start();
		w3.start();
		w4.start();
		w5.start();
		
	}

}
