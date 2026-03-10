package it.unimi.di.sweng.esame.presenter;

import it.unimi.di.sweng.esame.model.Discipline;
import it.unimi.di.sweng.esame.model.NationCode;
import org.jetbrains.annotations.NotNull;

public record MedalEntry(
        @NotNull String raceId,
        @NotNull Discipline discipline,
        @NotNull NationCode nation,
        @NotNull SportSelector.MedalType medalType
) {
    @Override
    public @NotNull String toString() {
        return "%s %s %s".formatted(nation.nationCode(), medalType, raceId);
    }
}
