package com.swd.HabitTrackers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import jakarta.validation.Valid;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController // rest controller lets us know that this is a rest controller
public class HabitController {

    // Mutable list (array list) to allow adding/removing habits
    private List<Habit> db = new ArrayList<>(List.of(
            new Habit(0, "Exercise"),
            new Habit(1, "Eat Healthy"),
            new Habit(2, "Sleep Well")));

    @GetMapping("/") // get mapping lets us know that this is a get request
    public String hello() {
        return "Hello world";
    }

    @GetMapping("/habit")
    public List<Habit> getHabits() {
        return db;
    }

    @GetMapping("/habit/{id}")
    public Habit getHabits(@PathVariable int id) {
        Habit habit = db.get(id);
        if (id < 0 || id >= db.size()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Habit not found");
        }
        return habit;
    }

    @DeleteMapping("/habit/{id}")
    public void deleteHabit(@PathVariable int id) {
        Habit habit = db.remove(id);
        if (habit == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Habit not found");
        }

    }

    @PostMapping("/habit")
    public Habit postHabit(@RequestBody @Valid Habit habit) {
        habit.setId(db.size());// index starts at 0, so the id is the size of the list. (if size is 3 the last
                               // element has id 2)
        db.add(habit.getId(), habit);
        return habit;
    }

    // ✅ Mark habit as completed and increase streak
    @PostMapping("/habit/{id}/complete")
    public void completeHabit(@PathVariable int id) {
        if (id < 0 || id >= db.size()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Habit not found");
        }
        db.get(id).incrementStreak();
    }

}

/*
 * console:
 * 
 * 
 * delete:
 * (async function deleteHabit(id) {
 * await fetch("http://localhost:8080/habit/" + id, {
 * method: "DELETE"
 * })
 * })("2")
 * 
 * 
 * 
 * add:
 * (async function addHabit() {
 * await fetch("http://localhost:8080/habit", {
 * method: "POST",
 * headers: {
 * "Content-Type": "application/json"
 * },
 * body: JSON.stringify({
 * "name": "Work on "
 * })
 * })
 * })()
 * 
 * 
 */