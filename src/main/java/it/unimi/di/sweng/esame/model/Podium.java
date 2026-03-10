package it.unimi.di.sweng.esame.model;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public record Podium(
        @NotNull List<NationCode> gold,
        @NotNull List<NationCode> silver,
        @NotNull List<NationCode> bronze
) {
    public Podium {
        gold = List.copyOf(gold);
        silver = List.copyOf(silver);
        bronze = List.copyOf(bronze);

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
