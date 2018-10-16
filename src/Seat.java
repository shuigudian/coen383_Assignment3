
public class Seat {

    private int seatNum;
    private Customer seatTaken;

    public Seat(int num) {
        seatNum = num;
        seatTaken = null;
    }

    public int getSeatNum() {
        return seatNum;
    }

    public Customer getCustomer() {
        return seatTaken;
    }

    public void assignSeat(Customer c) {
        seatTaken = c;
    }

    public boolean isSeatEmpty() {
        return seatTaken == null;
    }
}
