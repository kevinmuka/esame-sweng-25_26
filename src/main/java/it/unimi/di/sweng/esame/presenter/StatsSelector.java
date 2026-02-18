package it.unimi.di.sweng.esame.presenter;

import it.unimi.di.sweng.esame.model.NationCode;
import it.unimi.di.sweng.esame.model.Race;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class StatsSelector implements DataSelector<StatsSelector.NationStats> {
    @Override
    public @NotNull List<NationStats> select(@NotNull List<Race> input) {
        return List.of();
    }

    public record NationStats(
            @NotNull NationCode nation,
            int total,
            int gold,
            int silver,
            int bronze
    ) {
    }
}
