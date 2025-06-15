package com.elevator.scheduler;

import com.elevator.context.Elevator;
import com.elevator.enums.Direction;

import java.util.List;

/**
 * Scheduler interface for assigning elevators based on the requested floor and direction.
 * Implementations of this interface will define the logic for selecting the most appropriate elevator.
 */
public interface Scheduler {
    Elevator assignElevator(List<Elevator> elevators, int floor, Direction direction);
}