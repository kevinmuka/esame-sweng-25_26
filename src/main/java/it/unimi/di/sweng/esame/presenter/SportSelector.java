package it.unimi.di.sweng.esame.presenter;

import it.unimi.di.sweng.esame.model.Discipline;
import it.unimi.di.sweng.esame.model.NationCode;
import it.unimi.di.sweng.esame.model.Race;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class SportSelector implements DataSelector<MedalEntry> {
    private final Discipline targetDiscipline;

    public SportSelector(@NotNull Discipline targetDiscipline) {
        this.targetDiscipline = targetDiscipline;
    }

    @Override
    public @NotNull List<MedalEntry> select(@NotNull List<Race> input) {
        List<MedalEntry> out = new ArrayList<>();
        for (Race race : input) {
            if (race.discipline() != targetDiscipline) {
                continue;
            }
            for (NationCode nation : race.podium().gold()) {
                out.add(new MedalEntry(race.raceId(), race.discipline(), nation, MedalType.ORO));
            }
            for (NationCode nation : race.podium().silver()) {
                out.add(new MedalEntry(race.raceId(), race.discipline(), nation, MedalType.ARGENTO));
            }
            for (NationCode nation : race.podium().bronze()) {
                out.add(new MedalEntry(race.raceId(), race.discipline(), nation, MedalType.BRONZO));
            }
        }
        return out;
    }

    public enum MedalType {
        ORO,
        ARGENTO,
        BRONZO
    }

}
