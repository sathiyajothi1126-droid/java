import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class ParkingFullException extends Exception {
    ParkingFullException(String msg) {
        super(msg);
    }
}

class Vehicle {
    String number;
    long entryTime;

    Vehicle(String number) {
        this.number = number;
        this.entryTime = System.currentTimeMillis();
    }
}

public class SmartParkingLot {
    static final int CAPACITY = 2;
    static Map<String, Vehicle> parking = new HashMap<>();

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n1. Park Vehicle\n2. Remove Vehicle\n3. Exit");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    try {
                        System.out.print("Enter vehicle number: ");
                        parkVehicle(sc.nextLine());
                    } catch (ParkingFullException e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 2:
                    System.out.print("Enter vehicle number: ");
                    removeVehicle(sc.nextLine());
                    break;

                case 3:
                    System.exit(0);
            }
        }
    }

    static void parkVehicle(String number) throws ParkingFullException, IOException {
        if (parking.size() >= CAPACITY) {
            throw new ParkingFullException("Parking Lot is Full!");
        }

        Vehicle v = new Vehicle(number);
        parking.put(number, v);

        FileWriter fw = new FileWriter("parking_log.txt", true);
        fw.write("Vehicle Parked: " + number + "\n");
        fw.close();

        System.out.println("Vehicle parked successfully. Ticket generated.");
    }

    static void removeVehicle(String number) throws IOException {
        Vehicle v = parking.remove(number);

        if (v == null) {
            System.out.println("Vehicle not found!");
            return;
        }

        long duration = (System.currentTimeMillis() - v.entryTime) / 1000;
        double charge = duration * 2;

        FileWriter fw = new FileWriter("parking_log.txt", true);
        fw.write("Vehicle Removed: " + number + " Charge: " + charge + "\n");
        fw.close();

        System.out.println("Vehicle removed. Parking charge: ₹" + charge);
    }
}