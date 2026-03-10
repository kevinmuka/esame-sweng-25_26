package it.unimi.di.sweng.esame.presenter;

import it.unimi.di.sweng.esame.model.Discipline;
import it.unimi.di.sweng.esame.model.NationCode;
import org.jetbrains.annotations.NotNull;

import java.util.Comparator;

public record MedalEntry(
        @NotNull String raceId,
        @NotNull Discipline discipline,
        @NotNull NationCode nation,
        @NotNull MedalEntry.MedalType medalType
) {
    public static final Comparator<MedalEntry> BY_NATION_THEN_MEDAL =
            Comparator
                    .comparing((MedalEntry e) -> e.nation().nationCode())
                    .thenComparingInt(e -> e.medalType().rank())
                    .thenComparing(MedalEntry::raceId);

    public static final Comparator<MedalEntry> BY_MEDAL_THEN_NATION =
            Comparator
                    .comparingInt((MedalEntry e) -> e.medalType().rank())
                    .thenComparing(e -> e.nation().nationCode())
                    .thenComparing(MedalEntry::raceId);

    @Override
    public @NotNull String toString() {
        return "%s %s %s".formatted(nation.nationCode(), medalType, raceId);
    }

    public enum MedalType {
        ORO,
        ARGENTO,
        BRONZO;

        int rank() {
            return ordinal();
        }
    }
}
