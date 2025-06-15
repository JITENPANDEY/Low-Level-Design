package com.elevator.state;

import com.elevator.context.Elevator;

public class IdleState implements ElevatorState{
    @Override
    public void openDoor() {
        System.out.println("Opening door...");
    }

    @Override
    public void closeDoor() {
        System.out.println("Closing door...");
    }

    @Override
    public void move(Elevator elevator) {
        System.out.println("Elevator is idle, cannot move.");
    }

    @Override
    public void stop(Elevator elevator) {
        System.out.println("Elevator is already stopped and idle.");
    }
}
