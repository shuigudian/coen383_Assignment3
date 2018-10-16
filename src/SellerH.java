

public class SellerH extends Seller {

	private Object lock;
	public SellerH(Seat[][] s, String sellerID, Object lk) {
		super(s, r.nextInt(2) + 1, sellerID, lk, System.currentTimeMillis());
		lock = lk;
	}

	public void sell() {
		while (!customers.isEmpty()) {

			Customer customer = null;
			if (customers.isEmpty()) return;
			update();
			
			if(currentTime <= 59)
				customer = customers.peek();
			else
				return;
			Seat seat = null;

			
			synchronized(lock) {	
				
				update();
				if(currentTime  >= (customer.getArrivalTime())){
				find_seat:
					for (int i = 0; i < seating.length; i++) {
						for (int j = 0; j < seating[0].length; j++) {
							if (seating[i][j].isSeatEmpty()) {

								// Seat number = (Row x 10) + (Col + 1)
								int seatNum = (i*10)+j+1;
								seat = new Seat(seatNum);
								super.assignSeat(customer, seat, i, j);
								printMsg(customer, seat);
								customers.remove();
								
								break find_seat;
							}
						}
					}
				}
			}
			if(seat != null){
				try {
					Thread.sleep(serviceTime * 1000);
					update();
				} catch (InterruptedException e) {

					e.printStackTrace();
				}
			}
		
			
		}
	}

}
