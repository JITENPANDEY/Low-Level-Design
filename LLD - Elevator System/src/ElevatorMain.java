import com.elevator.context.Elevator;
import com.elevator.enums.Direction;
import com.elevator.system.ElevatorSystem;

public class ElevatorMain {
    public static void main(String[] args) {
        // Create an elevator system with 3 elevators
        ElevatorSystem elevatorSystem = new ElevatorSystem(3);
        elevatorSystem.requestElevator(2, Direction.UP);
        elevatorSystem.requestElevator(5, Direction.DOWN);
        elevatorSystem.requestElevator(3, Direction.UP);
        elevatorSystem.requestElevator(1, Direction.DOWN);
        for (int i = 0; i < 10; i++) {
            elevatorSystem.step();
            try { Thread.sleep(1000); } catch (InterruptedException ignored) {}
        }
    }
}