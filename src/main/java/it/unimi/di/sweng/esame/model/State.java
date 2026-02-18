package it.unimi.di.sweng.esame.model;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class State implements IState {
    @Override
    public void insertRace(@NotNull Race race) {
    }

    @Override
    public @NotNull List<Race> getRaces() {
        return List.of();
    }
}
