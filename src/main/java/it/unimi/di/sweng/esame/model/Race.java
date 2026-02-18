package it.unimi.di.sweng.esame.model;

import org.jetbrains.annotations.NotNull;

public record Race(
        @NotNull String raceId,
        @NotNull Discipline discipline,
        @NotNull Podium podium
) {
    public Race {
        if (raceId.isBlank()) {
            throw new IllegalArgumentException("id gara non valido");
        }
    }
}
