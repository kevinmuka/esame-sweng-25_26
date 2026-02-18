package it.unimi.di.sweng.esame.presenter;

import it.unimi.di.sweng.esame.model.Discipline;
import it.unimi.di.sweng.esame.model.NationCode;
import it.unimi.di.sweng.esame.model.Race;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class SportSelector implements DataSelector<SportSelector.MedalEntry> {
    private final Discipline targetDiscipline;

    public SportSelector(@NotNull Discipline targetDiscipline) {
        this.targetDiscipline = targetDiscipline;
    }

    @Override
    public @NotNull List<MedalEntry> select(@NotNull List<Race> input) {
        return List.of();
    }

    public enum MedalType {
        ORO,
        ARGENTO,
        BRONZO
    }

    public record MedalEntry(
            @NotNull String raceId,
            @NotNull Discipline discipline,
            @NotNull NationCode nation,
            @NotNull MedalType medalType
    ) {
    }
}
