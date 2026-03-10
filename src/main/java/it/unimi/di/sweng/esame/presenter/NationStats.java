package it.unimi.di.sweng.esame.presenter;

import it.unimi.di.sweng.esame.model.NationCode;
import org.jetbrains.annotations.NotNull;

import java.util.Comparator;

public record NationStats(
        @NotNull NationCode nation,
        int total,
        int gold,
        int silver,
        int bronze
) {
    public static final Comparator<NationStats> BY_MEDAL_TABLE =
            Comparator
                    .comparingInt(NationStats::gold).reversed()
                    .thenComparing(Comparator.comparingInt(NationStats::silver).reversed())
                    .thenComparing(Comparator.comparingInt(NationStats::bronze).reversed())
                    .thenComparing(Comparator.comparingInt(NationStats::total).reversed())
                    .thenComparing(s -> s.nation().nationCode());

    @Override
    public @NotNull String toString() {
        return "%s (Tot: %d Ori: %d Argenti: %d Bronzi: %d)".formatted(
                nation.nationCode(),
                total,
                gold,
                silver,
                bronze
        );
    }
}
