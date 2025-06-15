package com.elevator.system;

import com.elevator.context.Elevator;
import com.elevator.enums.Direction;
import com.elevator.scheduler.NearestScheduler;
import com.elevator.scheduler.Scheduler;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class ElevatorSystem {
    // Method to get all elevators
    @Getter
    private final List<Elevator> elevators;
    private final Scheduler scheduler;

    public ElevatorSystem(int count) {
        this.elevators = new ArrayList<>();
        for(int i=0; i<count; i++) {
           elevators.add(new Elevator(i));
        }
        this.scheduler = new NearestScheduler();
    }

    // Method to request an elevator to a specific floor in a specific direction
    public void requestElevator(int floor, Direction direction) {
        Elevator assignedElevator = scheduler.assignElevator(elevators, floor, direction);
        if (assignedElevator != null) {
            System.out.println("Elevator " + assignedElevator.getId() + " assigned to floor " + floor + " in direction " + direction);
            assignedElevator.addDestination(floor);
        } else {
            System.out.println("No available elevator to handle the request.");
        }
    }

    // Method to simulate the elevator system step
    public void step() {
        for (Elevator elevator : elevators) {
            elevator.step();
        }
    }

    // Method to get the status of all elevators
    public void getElevatorStatus() {
        for (Elevator elevator : elevators) {
            System.out.println("Elevator ID: " + elevator.getId() +
                    ", Current Floor: " + elevator.getCurrentFloor() +
                    ", Current Direction: " + elevator.getCurrentDirection() +
                    ", Destinations: " + elevator.getDestinations());
        }
    }

}
