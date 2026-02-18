package it.unimi.di.sweng.esame.presenter;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class SportRowFormatter implements RowFormatter<SportSelector.MedalEntry> {
    @Override
    public @NotNull List<String> format(@NotNull List<SportSelector.MedalEntry> input) {
        return input.stream()
                .map(e -> "%s %s %s".formatted(e.nation().value(), e.medalType(), e.raceId()))
                .toList();
    }
}
