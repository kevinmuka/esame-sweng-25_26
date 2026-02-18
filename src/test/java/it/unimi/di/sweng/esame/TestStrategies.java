package it.unimi.di.sweng.esame;

import it.unimi.di.sweng.esame.model.Discipline;
import it.unimi.di.sweng.esame.model.NationCode;
import it.unimi.di.sweng.esame.model.Podium;
import it.unimi.di.sweng.esame.model.Race;
import it.unimi.di.sweng.esame.presenter.DataSelector;
import it.unimi.di.sweng.esame.presenter.SportSelector;
import it.unimi.di.sweng.esame.presenter.StatsSelector;
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

    @Test
    void statsSelector_aggregatesMedalsByNation() {
        DataSelector<StatsSelector.NationStats> sut = new StatsSelector();

        Race r1 = new Race(
                "G001",
                Discipline.BIATHLON,
                new Podium(
                        Set.of(new NationCode("ITA")),
                        Set.of(new NationCode("GER")),
                        Set.of(new NationCode("FRA"))
                )
        );

        Race r2 = new Race(
                "G002",
                Discipline.SNOWBOARD,
                new Podium(
                        Set.of(new NationCode("ITA")),
                        Set.of(new NationCode("CAN")),
                        Set.of(new NationCode("GER"))
                )
        );

        List<StatsSelector.NationStats> out = sut.select(List.of(r1, r2));

        assertThat(out).contains(
                new StatsSelector.NationStats(new NationCode("ITA"), 2, 2, 0, 0),
                new StatsSelector.NationStats(new NationCode("GER"), 2, 0, 1, 1),
                new StatsSelector.NationStats(new NationCode("FRA"), 1, 0, 0, 1),
                new StatsSelector.NationStats(new NationCode("CAN"), 1, 0, 1, 0)
        );
    }



}
