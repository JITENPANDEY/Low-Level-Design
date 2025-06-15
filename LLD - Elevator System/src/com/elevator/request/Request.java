package com.elevator.request;

import com.elevator.enums.Direction;
import com.elevator.enums.RequestType;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Request {
    private final int floor;
    private final Direction direction;
    private final RequestType requestType;
}
