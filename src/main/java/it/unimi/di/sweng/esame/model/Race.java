package it.unimi.di.sweng.esame.model;

import org.jetbrains.annotations.NotNull;

import java.util.Set;

public record Race(
        @NotNull String raceId,
        @NotNull String discipline,
        @NotNull Podium podium
) {
    private static final Set<String> ALLOWED_DISCIPLINES = Set.of(
            "Biathlon",
            "Bob",
            "Combinata nordica",
            "Curling",
            "Hockey su ghiaccio",
            "Pattinaggio di figura",
            "Pattinaggio di velocità",
            "Salto con gli sci",
            "Sci acrobatico",
            "Sci alpinismo",
            "Sci alpino",
            "Sci di fondo",
            "Short track",
            "Skeleton",
            "Slittino",
            "Snowboard"
    );

    public Race {
        if (!ALLOWED_DISCIPLINES.contains(discipline)) {
            throw new IllegalArgumentException("disciplina non valida");
        }
    }
}
