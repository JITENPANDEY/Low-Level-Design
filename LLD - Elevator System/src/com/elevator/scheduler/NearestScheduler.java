package com.elevator.scheduler;

import com.elevator.context.Elevator;
import com.elevator.enums.Direction;

import java.util.List;

public class NearestScheduler implements Scheduler{

    @Override
    public Elevator assignElevator(List<Elevator> elevators, int floor, Direction direction) {
        Elevator nearestElevator = null;
        int minDistance = Integer.MAX_VALUE;

        for (Elevator elevator : elevators) {
            if (elevator.getCurrentDirection() == Direction.IDLE || elevator.getCurrentDirection() == direction) {
                int distance = Math.abs(elevator.getCurrentFloor() - floor);
                if (distance < minDistance) {
                    minDistance = distance;
                    nearestElevator = elevator;
                }
            }
        }

        return nearestElevator;
    }
}
