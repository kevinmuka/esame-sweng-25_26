package it.unimi.di.sweng.esame.presenter;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class NationStatsRowFormatter implements RowFormatter<StatsSelector.NationStats> {
    @Override
    public @NotNull List<String> format(@NotNull List<StatsSelector.NationStats> input) {
        return input.stream()
                .map(s -> "%s (Tot: %d Ori: %d Argenti: %d Bronzi: %d)".formatted(
                        s.nation().value(),
                        s.total(),
                        s.gold(),
                        s.silver(),
                        s.bronze()
                ))
                .toList();
    }
}
