import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public abstract class Seller implements Runnable {
    protected long pastTime;
    protected int elapseTime;
    protected long currentTime;
    protected Seat[][] seating;

    private Object lock;
    Queue<Customer> customers;
    protected static Random r = new Random();
    protected String sellerID;
    protected int serviceTime;
    protected int ticketNum = 1;
    protected int time = 0;

    public Seller(Seat[][] seating, int serviceTime, String sellerID, Object lock, long current_time) {
        customers = new LinkedList<>();
        this.serviceTime = serviceTime;
        this.seating = seating;
        this.sellerID = sellerID;
        this.lock = lock;
        this.pastTime = current_time;
    }

    protected void calTime(Customer customer) {
        time = (int) (currentTime + serviceTime);
        customer.setTime(time);
    }

    protected void printMsg(Customer customer, Seat seat) {
        int hour = customer.getTime() / 60;
        int min = customer.getTime() % 60;

        String time = "";
        if (min < 10) time = hour + ":0" + min;
        else time = hour + ":" + min;
        if (seat == null) System.out.println(time + "  " + sellerID + " - The concert is sold out.");
        else System.out.println(time + "  " + sellerID + " - Success! Your seat is " + seat.getSeatNumber());

        printSeating(this.seating, 10, 10);
    }

    protected void assignSeat(Customer customer, Seat seat, int i, int j) {
        if (ticketNum < 10)
            customer.setTicket(sellerID + "0" + ticketNum);
        else
            customer.setTicket(sellerID + ticketNum);

        calTime(customer);
        ticketNum++;
        seat.assignSeat(customer);
        seating[i][j] = seat;
    }

    protected void update() {
        currentTime = System.currentTimeMillis() - this.pastTime;
        if (currentTime < 1000)
            currentTime = 0;
        else
            currentTime /= 1000;
    }

    public void addCustomer(Customer c) {
        customers.add(c);
    }

    public abstract void sell();

    @Override
    public void run() {
        sell();
    }

    public static void printSeating(Seat[][] seating, int maxRows, int maxCols) {
        for (int row = 0; row < maxRows; row++) {
            for (int col = 0; col < maxCols; col++) {
                if (seating[row][col].isSeatEmpty())
                    System.out.printf("%s ", "-");
                else
                    System.out.printf("%s ", seating[row][col].getCustomer().getTicket());
            }
            System.out.println();
        }
    }


    public void sortQueue() {
        Customer[] array = customers.toArray(new Customer[customers.size()]);
        customers.clear();
        Arrays.sort(array);
        for (Customer c : array)
            customers.add(c);
    }
}
