package com.fssa.leavemanagement.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TestLeaveTypes {

    @Test
    void testEnumValues() {
        LeaveTypes[] leaveTypes = LeaveTypes.values();

        // Assert that there are exactly 3 enum values
        assertEquals(3, leaveTypes.length);

        // Assert that each enum value is correctly defined with its respective name
        assertEquals(LeaveTypes.SICK, LeaveTypes.valueOf("SICK"));
        assertEquals(LeaveTypes.CASUAL, LeaveTypes.valueOf("CASUAL"));
        assertEquals(LeaveTypes.EARNED, LeaveTypes.valueOf("EARNED"));
    }
}
