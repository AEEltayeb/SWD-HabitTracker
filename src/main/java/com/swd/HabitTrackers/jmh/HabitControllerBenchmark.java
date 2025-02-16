package com.swd.HabitTrackers.jmh;

import org.openjdk.jmh.annotations.*;
import org.springframework.web.server.ResponseStatusException;
import com.swd.HabitTrackers.Habit;
import com.swd.HabitTrackers.HabitController;

import java.util.concurrent.TimeUnit;

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
        habitController.postHabit(new Habit((int) (Math.random() * 1000), "New Habit"));
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
