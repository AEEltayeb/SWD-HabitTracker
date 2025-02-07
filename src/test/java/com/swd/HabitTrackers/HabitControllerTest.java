package com.swd.HabitTrackers;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

class HabitControllerTest {
    private HabitController controller;
    private List<Habit> habitList;

    @BeforeEach
    void setUp() {
        habitList = new ArrayList<>(List.of(
                new Habit(0, "Exercise"),
                new Habit(1, "Eat Healthy"),
                new Habit(2, "Sleep Well")));
        controller = new HabitController();
        controller.db = habitList;
    }

    @Test
    void testGetHabits() {
        assertEquals(3, controller.getHabits().size());
    }

    @Test
    void testGetHabitByIdValid() {
        assertEquals("Exercise", controller.getHabits(0).getName());
    }

    @Test
    void testGetHabitByIdInvalid() {
        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            controller.getHabits(5);
        });
        assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());
    }

    @Test
    void testDeleteHabitValid() {
        controller.deleteHabit(1);
        assertEquals(2, habitList.size());
    }

    @Test
    void testDeleteHabitInvalid() {
        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            controller.deleteHabit(5);
        });
        assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());
    }

    @Test
    void testPostHabit() {
        Habit habit = new Habit(3, "Reading");
        controller.postHabit(habit);
        assertEquals(4, habitList.size());
    }

    @Test
    void testCompleteHabitInvalid() {
        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            controller.completeHabit(5);
        });
        assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());
    }

}
