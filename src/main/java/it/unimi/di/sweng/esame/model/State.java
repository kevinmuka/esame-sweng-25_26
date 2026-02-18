package it.unimi.di.sweng.esame.model;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class State implements IState {
    private final Map<String, Race> races = new HashMap<>();

    @Override
    public void insertRace(@NotNull Race race) {
        races.put(race.raceId(), race);
    }

    @Override
    public @NotNull List<Race> getRaces() {
        return races.values().stream().toList();
    }
}
