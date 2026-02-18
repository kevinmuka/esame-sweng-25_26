package it.unimi.di.sweng.esame.model;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class State implements IState {
    private final Map<String, Race> races = new HashMap<>();

    @Override
    public void insertRace(@NotNull Race race) {
        if (races.containsKey(race.raceId())) {
            throw new IllegalArgumentException("gara già inserita");
        }
        races.put(race.raceId(), race);
    }

    @Override
    public @NotNull List<Race> getRaces() {
        return List.copyOf(races.values());
    }
}
