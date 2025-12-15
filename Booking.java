public class Booking {

    int BookingId,customerId,pickuptime,droptime,amount;

    char from, to;

    Booking(int BookingId,int  customerId,char from,char to,int pickuptime,int droptime,int amount){
        this.BookingId = BookingId;
        this.customerId=customerId;
        this.from=from;
        this.to=to;
        this.pickuptime=pickuptime;
        this.droptime=droptime;
        this.amount=amount;
    }
}
