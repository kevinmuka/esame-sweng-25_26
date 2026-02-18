package it.unimi.di.sweng.esame.model;

import org.jetbrains.annotations.NotNull;

import java.util.Set;

public record Podium(
        @NotNull Set<NationCode> gold,
        @NotNull Set<NationCode> silver,
        @NotNull Set<NationCode> bronze
) {
    public Podium {
        gold = Set.copyOf(gold);
        silver = Set.copyOf(silver);
        bronze = Set.copyOf(bronze);
    }
}
