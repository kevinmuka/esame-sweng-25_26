package it.unimi.di.sweng.esame.presenter;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class SportByNationOrdering implements Ordering<SportSelector.MedalEntry> {
    @Override
    public @NotNull List<SportSelector.MedalEntry> sort(@NotNull List<SportSelector.MedalEntry> input) {
        return List.copyOf(input);
    }
}
