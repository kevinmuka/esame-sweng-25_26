package it.unimi.di.sweng.esame.presenter;

import it.unimi.di.sweng.esame.model.Discipline;
import it.unimi.di.sweng.esame.model.NationCode;
import it.unimi.di.sweng.esame.model.Race;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class SportSelector implements DataSelector<Race, MedalEntry> {
    private final Discipline targetDiscipline;

    public SportSelector(@NotNull Discipline targetDiscipline) {
        this.targetDiscipline = targetDiscipline;
    }

    @Override
    public @NotNull List<MedalEntry> apply(@NotNull List<Race> input) {
        List<MedalEntry> out = new ArrayList<>();
        for (Race race : input) {
            if (race.discipline() != targetDiscipline) {
                continue;
            }
            addEntries(out, race, race.podium().gold(), MedalEntry.MedalType.ORO);
            addEntries(out, race, race.podium().silver(), MedalEntry.MedalType.ARGENTO);
            addEntries(out, race, race.podium().bronze(), MedalEntry.MedalType.BRONZO);
        }
        return out;
    }

    private static void addEntries(
            @NotNull List<MedalEntry> out,
            @NotNull Race race,
            @NotNull List<NationCode> nations,
            @NotNull MedalEntry.MedalType medalType
    ) {
        for (NationCode nation : nations) {
            out.add(new MedalEntry(race.raceId(), race.discipline(), nation, medalType));
        }
    }
}
