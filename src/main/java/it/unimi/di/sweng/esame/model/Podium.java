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

        int goldCount = gold.size();
        int silverCount = silver.size();
        int bronzeCount = bronze.size();

        boolean validConfiguration =
                (goldCount == 1 && silverCount == 1 && bronzeCount >= 1) ||
                (goldCount == 1 && silverCount >= 2 && bronzeCount == 0) ||
                (goldCount == 2 && silverCount == 0 && bronzeCount >= 1) ||
                (goldCount >= 3 && silverCount == 0 && bronzeCount == 0);

        if (!validConfiguration) {
            throw new IllegalArgumentException("configurazione podio non valida");
        }
    }
}
