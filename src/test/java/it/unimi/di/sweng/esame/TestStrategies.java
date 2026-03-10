package it.unimi.di.sweng.esame;

import it.unimi.di.sweng.esame.model.Discipline;
import it.unimi.di.sweng.esame.model.NationCode;
import it.unimi.di.sweng.esame.model.Podium;
import it.unimi.di.sweng.esame.model.Race;
import it.unimi.di.sweng.esame.presenter.DataSelector;
import it.unimi.di.sweng.esame.presenter.NationStatsOrdering;
import it.unimi.di.sweng.esame.presenter.NationStatsRowFormatter;
import it.unimi.di.sweng.esame.presenter.Ordering;
import it.unimi.di.sweng.esame.presenter.RowFormatter;
import it.unimi.di.sweng.esame.presenter.SportSelector;
import it.unimi.di.sweng.esame.presenter.SportByNationOrdering;
import it.unimi.di.sweng.esame.presenter.SportRowFormatter;
import it.unimi.di.sweng.esame.presenter.StatsSelector;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class TestStrategies {
    @Test
    void sportSelector_filtersByDisciplineAndExpandsPodiumIntoMedals() {
        DataSelector<SportSelector.MedalEntry> sut = new SportSelector(Discipline.BIATHLON);

        Race biathlon = new Race(
                "G001",
                Discipline.BIATHLON,
                new Podium(
                        List.of(new NationCode("ITA")),
                        List.of(new NationCode("GER")),
                        List.of(new NationCode("FRA"))
                )
        );

        Race snowboard = new Race(
                "G002",
                Discipline.SNOWBOARD,
                new Podium(
                        List.of(new NationCode("USA")),
                        List.of(new NationCode("CAN")),
                        List.of(new NationCode("JPN"))
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
                        List.of(new NationCode("ITA")),
                        List.of(new NationCode("GER")),
                        List.of(new NationCode("FRA"))
                )
        );

        Race r2 = new Race(
                "G002",
                Discipline.SNOWBOARD,
                new Podium(
                        List.of(new NationCode("ITA")),
                        List.of(new NationCode("CAN")),
                        List.of(new NationCode("GER"))
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


    @Test
    void sportOrdering_sortsByNationThenMedalType() {
        Ordering<SportSelector.MedalEntry> sut = new SportByNationOrdering();

        List<SportSelector.MedalEntry> input = List.of(
                new SportSelector.MedalEntry("G005", Discipline.BIATHLON, new NationCode("ITA"), SportSelector.MedalType.ORO),
                new SportSelector.MedalEntry("G001", Discipline.BIATHLON, new NationCode("GER"), SportSelector.MedalType.ARGENTO),
                new SportSelector.MedalEntry("G002", Discipline.BIATHLON, new NationCode("AUT"), SportSelector.MedalType.BRONZO),
                new SportSelector.MedalEntry("G001", Discipline.BIATHLON, new NationCode("FRA"), SportSelector.MedalType.BRONZO),
                new SportSelector.MedalEntry("G005", Discipline.BIATHLON, new NationCode("GER"), SportSelector.MedalType.ORO)
        );

        List<SportSelector.MedalEntry> out = sut.sort(input);

        assertThat(out).containsExactly(
                new SportSelector.MedalEntry("G002", Discipline.BIATHLON, new NationCode("AUT"), SportSelector.MedalType.BRONZO),
                new SportSelector.MedalEntry("G001", Discipline.BIATHLON, new NationCode("FRA"), SportSelector.MedalType.BRONZO),
                new SportSelector.MedalEntry("G005", Discipline.BIATHLON, new NationCode("GER"), SportSelector.MedalType.ORO),
                new SportSelector.MedalEntry("G001", Discipline.BIATHLON, new NationCode("GER"), SportSelector.MedalType.ARGENTO),
                new SportSelector.MedalEntry("G005", Discipline.BIATHLON, new NationCode("ITA"), SportSelector.MedalType.ORO)
        );


    }

    @Test
    void statsOrdering_sortsByGoldThenSilverThenBronzeThenTotalThenNation() {
        Ordering<StatsSelector.NationStats> sut = new NationStatsOrdering();

        List<StatsSelector.NationStats> input = List.of(
                new StatsSelector.NationStats(new NationCode("FRA"), 2, 0, 1, 1),
                new StatsSelector.NationStats(new NationCode("USA"), 1, 1, 0, 0),
                new StatsSelector.NationStats(new NationCode("NOR"), 2, 1, 0, 1),
                new StatsSelector.NationStats(new NationCode("GER"), 2, 1, 1, 0),
                new StatsSelector.NationStats(new NationCode("ITA"), 3, 2, 1, 0)
        );

        List<StatsSelector.NationStats> out = sut.sort(input);

        assertThat(out).containsExactly(
                new StatsSelector.NationStats(new NationCode("ITA"), 3, 2, 1, 0),
                new StatsSelector.NationStats(new NationCode("GER"), 2, 1, 1, 0),
                new StatsSelector.NationStats(new NationCode("NOR"), 2, 1, 0, 1),
                new StatsSelector.NationStats(new NationCode("USA"), 1, 1, 0, 0),
                new StatsSelector.NationStats(new NationCode("FRA"), 2, 0, 1, 1)
        );
    }


    @Test
    void sportRowFormatter_formatsSportRows() {
        RowFormatter<SportSelector.MedalEntry> sut = new SportRowFormatter();

        List<SportSelector.MedalEntry> input = List.of(
                new SportSelector.MedalEntry("G002", Discipline.BIATHLON, new NationCode("AUT"), SportSelector.MedalType.BRONZO),
                new SportSelector.MedalEntry("G005", Discipline.BIATHLON, new NationCode("GER"), SportSelector.MedalType.ORO),
                new SportSelector.MedalEntry("G001", Discipline.BIATHLON, new NationCode("GER"), SportSelector.MedalType.ARGENTO)
        );

        List<String> out = sut.format(input);

        assertThat(out).containsExactly(
                "AUT BRONZO G002",
                "GER ORO G005",
                "GER ARGENTO G001"
        );
    }

    @Test
    void nationStatsRowFormatter_formatsStatsRows() {
        RowFormatter<StatsSelector.NationStats> sut = new NationStatsRowFormatter();

        List<StatsSelector.NationStats> input = List.of(
                new StatsSelector.NationStats(new NationCode("ITA"), 3, 2, 1, 0),
                new StatsSelector.NationStats(new NationCode("GER"), 2, 1, 1, 0),
                new StatsSelector.NationStats(new NationCode("NOR"), 2, 1, 0, 1)
        );

        List<String> out = sut.format(input);

        assertThat(out).containsExactly(
                "ITA (Tot: 3 Ori: 2 Argenti: 1 Bronzi: 0)",
                "GER (Tot: 2 Ori: 1 Argenti: 1 Bronzi: 0)",
                "NOR (Tot: 2 Ori: 1 Argenti: 0 Bronzi: 1)"
        );
    }




}
