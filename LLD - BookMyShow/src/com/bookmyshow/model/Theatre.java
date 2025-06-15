package com.bookmyshow.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class Theatre {
    private final String theatreId;
    private final String theatreName;
    private final String city;
    private final List<Screen> screens;
}
