package it.unimi.di.sweng.esame.presenter;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class NationStatsOrdering implements Ordering<StatsSelector.NationStats> {
    @Override
    public @NotNull List<StatsSelector.NationStats> sort(@NotNull List<StatsSelector.NationStats> input) {
        return List.copyOf(input);
    }
}
