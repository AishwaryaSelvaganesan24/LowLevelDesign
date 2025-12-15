import java.util.*;
public class Taxi {

    int id;
    char currentpoint = 'A';

    int totalEarnings=0;
    List<Booking>bookings= new ArrayList<>();

    Taxi(int id){
        this.id=id;
    }


    public  boolean isAvailable(int pickuptime){
        if(bookings.isEmpty())return true;
        Booking lastBooking = bookings.get(bookings.size()-1);
        return lastBooking.droptime<=pickuptime;
    }

    public  int calculateEarnings(char from,char to){
        int distance = Math.abs(to-from)*15;
        return 100+Math.max(0,(distance-5)*10);

    }
    public void addbookings(Booking booking){
        bookings.add(booking);
        totalEarnings+=booking.amount;
        currentpoint=booking.to;

    }

}
