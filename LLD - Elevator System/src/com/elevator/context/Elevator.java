package com.elevator.context;

import com.elevator.enums.Direction;
import com.elevator.state.ElevatorState;
import com.elevator.state.IdleState;
import com.elevator.state.MovingDownState;
import com.elevator.state.MovingUpState;
import lombok.Data;

import java.util.TreeSet;

@Data
public class Elevator {
    private final int id;
    private int currentFloor;
    private Direction currentDirection;
    private ElevatorState currentSate;

    private final TreeSet<Integer> destinations = new TreeSet<>();

    public Elevator(int id) {
        this.id = id;
        this.currentFloor = 0;
        this.currentDirection = Direction.IDLE;
        this.currentSate = new IdleState();
    }

    public void addDestination(int floor) {
        destinations.add(floor);
        updateDirection();
    }

    public void step() {
        if(destinations.isEmpty()) {
            updateDirection();
        } else {
            int targetFloor = currentDirection == Direction.UP ? destinations.first() : destinations.last();
            if(currentFloor == targetFloor) {
                currentSate.stop(this);
                currentSate.openDoor();
                destinations.remove(targetFloor);
                currentSate.closeDoor();
                updateDirection();
            } else {
                currentSate.move(this);
            }
        }
    }

    private void updateDirection() {
        if (destinations.isEmpty()) {
            currentDirection = Direction.IDLE;
            currentSate = new IdleState();
        } else {
            int lowestRequestedFloor = destinations.first();
            int highestRequestedFloor = destinations.last();
            if (lowestRequestedFloor > currentFloor) { // if all the request floors are above the current floor
                currentDirection = Direction.UP;
                currentSate = new MovingUpState();
            } else if (highestRequestedFloor < currentFloor) { // if all the request floors are below the current floor
                currentDirection = Direction.DOWN;
                currentSate = new MovingDownState();
            }
        }
    }
}
