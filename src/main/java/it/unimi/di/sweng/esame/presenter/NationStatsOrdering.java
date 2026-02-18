package it.unimi.di.sweng.esame.presenter;

import org.jetbrains.annotations.NotNull;

import java.util.Comparator;
import java.util.List;

public class NationStatsOrdering implements Ordering<StatsSelector.NationStats> {
    private static final Comparator<StatsSelector.NationStats> COMPARATOR =
            Comparator
                    .comparingInt(StatsSelector.NationStats::gold).reversed()
                    .thenComparing(Comparator.comparingInt(StatsSelector.NationStats::silver).reversed())
                    .thenComparing(Comparator.comparingInt(StatsSelector.NationStats::bronze).reversed())
                    .thenComparing(Comparator.comparingInt(StatsSelector.NationStats::total).reversed())
                    .thenComparing(s -> s.nation().value());

    @Override
    public @NotNull List<StatsSelector.NationStats> sort(@NotNull List<StatsSelector.NationStats> input) {
        return input.stream().sorted(COMPARATOR).toList();
    }
}
