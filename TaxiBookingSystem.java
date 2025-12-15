import java.util.*;

public class TaxiBookingSystem {

      static List<Taxi> taxis = new ArrayList<>();
      static Scanner sc = new Scanner(System.in);
      static int customerCounter=1;

      public static void main(String[]args){
          System.out.println("enter number of taxis:");
          int n = sc.nextInt();
          initialize(n);
          while(true) {
              System.out.println("\n 1.booktaxi \n 2.displaytaxidetails \n 3.exit");
              System.out.println("Enter a choice");
              int choice = sc.nextInt();
              switch (choice) {
                  case 1:
                      booktaxi();
                      break;
                  case 2:
                      displaytaxidetails();
                      break;
                  case 3:
                      System.out.println("exitting..");
                      return;
                  default:
                      System.out.println("invalid");

              }

          }
      }

      public static void initialize(int n){
          for(int i=1;i<=n;i++){
              taxis.add(new Taxi(i));
          }
      }

      public static void booktaxi(){
          int customerId=customerCounter++;
          System.out.println("Enter pickup point (A-F):");
          char pickup = sc.next().toUpperCase().charAt(0);
          System.out.println("Enter drop point (A-F):");
          char drop = sc.next().toUpperCase().charAt(0);
          System.out.println("Enter pickup time:");
          int pickuptime = sc.nextInt();

          Taxi selectedTaxi = null;
          int minDistance = Integer.MAX_VALUE;

          for(Taxi taxi:taxis){
              if(taxi.isAvailable(pickuptime)){
                  int distance = Math.abs(taxi.currentpoint-pickup);
                  if(distance<minDistance ||distance==minDistance && taxi.totalEarnings<selectedTaxi.totalEarnings){
                      selectedTaxi = taxi;
                      minDistance=distance;
                  }

              }
          }

          if(selectedTaxi==null){
              System.out.println("Booking Rejected");
          }

          int droptime = pickuptime+Math.abs(drop-pickup);
          int amount = selectedTaxi.calculateEarnings(pickup,drop);

          int BookingId=selectedTaxi.bookings.size()+1;

          Booking booking = new Booking(BookingId,customerId,pickup,drop,pickuptime,droptime,amount);
          selectedTaxi.addbookings(booking);

          System.out.println("Taxi- "+ selectedTaxi.id+" is alloted");


      }
      public static void displaytaxidetails(){
          for(Taxi taxi:taxis){
              System.out.println("Taxi-" +taxi.id + " TotalEarnings: Rs."+taxi.totalEarnings);
              System.out.printf("%-10s %-10s %-5s %-5s %-12s %-9s %-6s%n","BookingId","CustomerId","From","to","Pickuptime","droptime","Amount");
              for(Booking booking:taxi.bookings){
                  System.out.printf("%-10s %-10s %-5s %-5s %-12s %-9s %-6s%n",booking.BookingId,booking.customerId,booking.from, booking.to,
                          booking.pickuptime, booking.droptime, booking.amount);

              }
          }
      }


}
