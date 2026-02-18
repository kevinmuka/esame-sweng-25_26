package it.unimi.di.sweng.esame.model;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public interface IState {
    void insertRace(@NotNull Race race);

    @NotNull List<Race> getRaces();
}
