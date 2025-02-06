package com.swd.HabitTrackers;

import jakarta.validation.constraints.NotEmpty;

public class Habit {

    private int id;
    @NotEmpty
    private String name;
    private int streak; // Count how many times habit has been done

    public Habit(int id, String name) {
        this.id = id;
        this.name = name;
        this.streak = 0; // Start with 0 streak
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStreak() {
        return streak;
    }

    public void incrementStreak() {
        this.streak++;
    } // Increase streak when completed
}
