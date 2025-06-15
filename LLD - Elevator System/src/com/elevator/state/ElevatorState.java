package com.elevator.state;

import com.elevator.context.Elevator;

public interface ElevatorState {
    void openDoor();

    void closeDoor();

    void move(Elevator elevator);

    void stop(Elevator elevator);
}
