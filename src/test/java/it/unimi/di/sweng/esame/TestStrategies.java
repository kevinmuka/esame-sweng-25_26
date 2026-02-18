package it.unimi.di.sweng.esame;

import it.unimi.di.sweng.esame.model.Discipline;
import it.unimi.di.sweng.esame.model.NationCode;
import it.unimi.di.sweng.esame.model.Podium;
import it.unimi.di.sweng.esame.model.Race;
import it.unimi.di.sweng.esame.presenter.DataSelector;
import it.unimi.di.sweng.esame.presenter.SportSelector;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class TestStrategies {
    @Test
    void sportSelector_filtersByDisciplineAndExpandsPodiumIntoMedals() {
        DataSelector<SportSelector.MedalEntry> sut = new SportSelector(Discipline.BIATHLON);

        Race biathlon = new Race(
                "G001",
                Discipline.BIATHLON,
                new Podium(
                        Set.of(new NationCode("ITA")),
                        Set.of(new NationCode("GER")),
                        Set.of(new NationCode("FRA"))
                )
        );

        Race snowboard = new Race(
                "G002",
                Discipline.SNOWBOARD,
                new Podium(
                        Set.of(new NationCode("USA")),
                        Set.of(new NationCode("CAN")),
                        Set.of(new NationCode("JPN"))
                )
        );

        List<SportSelector.MedalEntry> out = sut.select(List.of(biathlon, snowboard));

        assertThat(out).containsExactlyInAnyOrder(
                new SportSelector.MedalEntry("G001", Discipline.BIATHLON, new NationCode("ITA"), SportSelector.MedalType.ORO),
                new SportSelector.MedalEntry("G001", Discipline.BIATHLON, new NationCode("GER"), SportSelector.MedalType.ARGENTO),
                new SportSelector.MedalEntry("G001", Discipline.BIATHLON, new NationCode("FRA"), SportSelector.MedalType.BRONZO)
        );
    }


}
