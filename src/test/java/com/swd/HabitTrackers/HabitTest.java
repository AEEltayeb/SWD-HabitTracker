package com.swd.HabitTrackers;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class HabitTest {

    @Test
    void testHabitInitialization() {
        Habit habit = new Habit(1, "Exercise");
        assertEquals(1, habit.getId());
        assertEquals("Exercise", habit.getName());
        assertEquals(0, habit.getStreak());
    }

    @Test
    void testIncrementStreak() {
        Habit habit = new Habit(1, "Exercise");
        habit.incrementStreak();
        assertEquals(1, habit.getStreak());
    }

    @Test
    void testSettersAndGetters() {
        Habit habit = new Habit(1, "Exercise");
        habit.setId(2);
        habit.setName("Reading");
        assertEquals(2, habit.getId());
        assertEquals("Reading", habit.getName());
    }
}