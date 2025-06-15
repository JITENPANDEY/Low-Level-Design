package com.spliwise.enums;

import lombok.Getter;

@Getter
public enum SplitType {
    EQUAL, // All users share the expense equally
    EXACT, // A specific amount is paid by a user
    PERCENTAGE // Users pay a percentage of the total expense
}
