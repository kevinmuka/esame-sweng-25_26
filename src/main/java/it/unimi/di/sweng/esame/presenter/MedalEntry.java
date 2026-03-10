package it.unimi.di.sweng.esame.presenter;

import it.unimi.di.sweng.esame.model.Discipline;
import it.unimi.di.sweng.esame.model.NationCode;
import org.jetbrains.annotations.NotNull;

import java.util.Comparator;

public record MedalEntry(
        @NotNull String raceId,
        @NotNull Discipline discipline,
        @NotNull NationCode nation,
        @NotNull SportSelector.MedalType medalType
) {
    public static final Comparator<MedalEntry> BY_NATION_THEN_MEDAL =
            Comparator
                    .comparing((MedalEntry e) -> e.nation().nationCode())
                    .thenComparingInt(e -> switch (e.medalType()) {
                        case ORO -> 0;
                        case ARGENTO -> 1;
                        case BRONZO -> 2;
                    })
                    .thenComparing(MedalEntry::raceId);

    public static final Comparator<MedalEntry> BY_MEDAL_THEN_NATION =
            Comparator
                    .comparingInt((MedalEntry e) -> switch (e.medalType()) {
                        case ORO -> 0;
                        case ARGENTO -> 1;
                        case BRONZO -> 2;
                    })
                    .thenComparing(e -> e.nation().nationCode())
                    .thenComparing(MedalEntry::raceId);

    @Override
    public @NotNull String toString() {
        return "%s %s %s".formatted(nation.nationCode(), medalType, raceId);
    }
}
