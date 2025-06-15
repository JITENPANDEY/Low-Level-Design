package com.elevator.state;

import com.elevator.context.Elevator;
import com.elevator.enums.Direction;

public class MovingUpState implements ElevatorState{
    @Override
    public void openDoor() {
        System.out.println("Cannot open door while moving up.");
    }

    @Override
    public void closeDoor() {
        System.out.println("Closing door while moving up.");
    }

    @Override
    public void move(Elevator elevator) {
        int nextFloor = elevator.getCurrentFloor() + 1;
        System.out.println("Elevator no. " + elevator.getId() + " Moving up from floor " + elevator.getCurrentFloor() + " to floor " + nextFloor);
        elevator.setCurrentFloor(nextFloor);
        try {
            Thread.sleep(1000); // Simulating 1 second per floor
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @Override
    public void stop(Elevator elevator) {
        System.out.println("Elevator no. " + elevator.getId() + " Stopping at floor " + elevator.getCurrentFloor());
        elevator.setCurrentDirection(Direction.IDLE); // Set direction to null or IDLE
        elevator.setCurrentSate(new IdleState()); // Change state to IdleState
        System.out.println("Elevator no. " + elevator.getId() + " Elevator stopped and is now idle.");
    }
}
