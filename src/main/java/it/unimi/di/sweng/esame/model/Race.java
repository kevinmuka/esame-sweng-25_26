package it.unimi.di.sweng.esame.model;

import org.jetbrains.annotations.NotNull;

public record Race(
        @NotNull String raceId,
        @NotNull String discipline,
        @NotNull Podium podium
) {
}
