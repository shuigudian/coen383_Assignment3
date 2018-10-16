import java.util.Random;

public class Customer implements Comparable<Customer> {
    Random r = new Random();
    private int arrivalTime;
    private String ticket;
    private int time;
    private int seatNum;
    private int customerID;

    public Customer(int customerID) {
        arrivalTime = r.nextInt(60);
        this.customerID = customerID;
        seatNum = -1;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    public int getArrivalTime() {
        return this.arrivalTime;
    }

    public void setSeatNum(int seatNum) {
        this.seatNum = seatNum;
    }

    public int getSeatNum() {
        return this.seatNum;
    }

    public int customerID() {
        return this.customerID;
    }

    public boolean isSigned() {
        return seatNum != -1;
    }

    @Override
    public int compareTo(Customer customer) {
        return Integer.compare(this.arrivalTime, customer.arrivalTime);
    }
}
