package it.unimi.di.sweng.esame.presenter;

import it.unimi.di.sweng.esame.model.NationCode;
import org.jetbrains.annotations.NotNull;

public record NationStats(
        @NotNull NationCode nation,
        int total,
        int gold,
        int silver,
        int bronze
) {
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
