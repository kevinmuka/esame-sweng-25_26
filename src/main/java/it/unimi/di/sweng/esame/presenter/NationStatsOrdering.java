package it.unimi.di.sweng.esame.presenter;

import org.jetbrains.annotations.NotNull;

import java.util.Comparator;
import java.util.List;

public class NationStatsOrdering implements Ordering<NationStats> {
    private static final Comparator<NationStats> COMPARATOR =
            Comparator
                    .comparingInt(NationStats::gold).reversed()
                    .thenComparing(Comparator.comparingInt(NationStats::silver).reversed())
                    .thenComparing(Comparator.comparingInt(NationStats::bronze).reversed())
                    .thenComparing(Comparator.comparingInt(NationStats::total).reversed())
                    .thenComparing(s -> s.nation().nationCode());

    @Override
    public @NotNull List<NationStats> sort(@NotNull List<NationStats> input) {
        return input.stream().sorted(COMPARATOR).toList();
    }
}
