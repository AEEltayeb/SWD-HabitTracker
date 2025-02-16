package com.swd.HabitTrackers.jmh;

import org.openjdk.jmh.annotations.*;
import org.springframework.web.server.ResponseStatusException;
import com.swd.HabitTrackers.Habit;
import com.swd.HabitTrackers.HabitController;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.ThreadLocalRandom;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@State(Scope.Thread)
@Warmup(iterations = 5, time = 5, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 5, time = 5, timeUnit = TimeUnit.SECONDS)
@Fork(2) // Run the benchmark in different JVM instances
@Threads(4) // Simulate multiple users
public class HabitControllerBenchmark {

    private HabitController habitController;

    @Setup(Level.Trial)
    public void setUp() {
        habitController = new HabitController();
        habitController.postHabit(new Habit(0, "Exercise"));
        habitController.postHabit(new Habit(1, "Eat Healthy"));
        habitController.postHabit(new Habit(2, "Sleep Well"));
    }

    @Benchmark
    public void benchmarkGetHabits() {
        habitController.getHabits();
    }

    @Benchmark
    public void benchmarkGetHabitById() {
        habitController.getHabits(0);
    }

    @Benchmark
    public void benchmarkAddHabit() {
        if (habitController.getHabits().size() > 100) { // Preventing excessive memory usage
            habitController.deleteHabit(0);
        }
        int randomId = ThreadLocalRandom.current().nextInt(1000); // More efficient and thread-safe
        habitController.postHabit(new Habit(randomId, "New Habit"));
    }

    @Benchmark
    public void benchmarkDeleteHabit() {
        try {
            habitController.deleteHabit(1);
        } catch (ResponseStatusException ignored) {
        }
    }

    @Benchmark
    public void benchmarkCompleteHabit() {
        try {
            habitController.completeHabit(0);
        } catch (ResponseStatusException ignored) {
        }
    }
}
