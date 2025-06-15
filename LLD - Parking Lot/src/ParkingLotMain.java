import com.parkinglot.enums.VehicleType;
import com.parkinglot.model.ParkingFloor;
import com.parkinglot.model.ParkingSlot;
import com.parkinglot.model.Ticket;
import com.parkinglot.model.Vehicle;
import com.parkinglot.service.ParkingLot;
import com.parkinglot.strategy.VehicleBasedFeeStrategy;

import java.time.LocalDateTime;
import java.util.List;

public class ParkingLotMain {
    public static void main(String[] args) throws InterruptedException {
        ParkingLot parkingLot = ParkingLot.getInstance();

        createParkingLot(parkingLot);

        Vehicle car = new Vehicle("CAR123", VehicleType.CAR) {};
        Vehicle bike = new Vehicle("BIKE456", VehicleType.BIKE) {};
        Vehicle truck = new Vehicle("TRUCK789", VehicleType.TRUCK) {};

        // Park vehicles
        Ticket car123Ticket = parkingLot.parkVehicle(car);
        Thread.sleep(2000);
        Ticket bike456Ticket = parkingLot.parkVehicle(bike);
        Thread.sleep(2000);
        Ticket truck789Ticket = parkingLot.parkVehicle(truck);
        Thread.sleep(2000);
        // Unpark vehicles
        try {
            double carFee = parkingLot.unparkVehicle(car123Ticket);
            System.out.println("Car unparked. Fee: " + carFee);
            Thread.sleep(2000);
            double bikeFee = parkingLot.unparkVehicle(bike456Ticket);
            System.out.println("Bike unparked. Fee: " + bikeFee);
            Thread.sleep(2000);
            double truckFee = parkingLot.unparkVehicle(truck789Ticket);
            System.out.println("Truck unparked. Fee: " + truckFee);
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        }

    }

    private static void createParkingLot(ParkingLot parkingLot) {
        ParkingFloor floor1 = new ParkingFloor("F1");
        ParkingFloor floor2 = new ParkingFloor("F2");

        // Create parking slots for Floor 1
        List<ParkingSlot> parkingSlotsFloor1 = List.of(
                new ParkingSlot("101", VehicleType.CAR),
                new ParkingSlot("102", VehicleType.CAR),
                new ParkingSlot("103", VehicleType.BIKE)
        );
        // Add slots to Floor 1
        for (ParkingSlot parkingSlot : parkingSlotsFloor1) {
            floor1.addSlot(parkingSlot);
        }

        // Create parking slots for Floor 2
        List<ParkingSlot> parkingSlotsFloor2 = List.of(
                new ParkingSlot("201", VehicleType.BIKE),
                new ParkingSlot("202", VehicleType.TRUCK)
        );
        // Add slots to Floor 2
        for (ParkingSlot parkingSlot : parkingSlotsFloor2) {
            floor2.addSlot(parkingSlot);
        }

        // Add floors to the parking lot
        parkingLot.addFloor(floor1);
        parkingLot.addFloor(floor2);

        //Check if current time is after 8pm
        if (isAfter8PM()) {
            parkingLot.setPricingStrategy(new VehicleBasedFeeStrategy());
        }

    }

    private static boolean isAfter8PM() {
        LocalDateTime now = LocalDateTime.now();
        return now.getHour() >= 20; // 20:00 is 8 PM
    }
}