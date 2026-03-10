package it.unimi.di.sweng.esame;

import it.unimi.di.sweng.esame.model.Discipline;
import it.unimi.di.sweng.esame.model.NationCode;
import it.unimi.di.sweng.esame.model.Podium;
import it.unimi.di.sweng.esame.model.Race;
import it.unimi.di.sweng.esame.presenter.*;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class TestStrategies {
    @Test
    void sportSelector_filtersByDisciplineAndExpandsPodiumIntoMedals() {
        DataSelector<Race, MedalEntry> sut = new SportSelector(Discipline.BIATHLON);

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

        List<MedalEntry> out = sut.apply(List.of(biathlon, snowboard));

        assertThat(out).containsExactlyInAnyOrder(
                new MedalEntry("G001", Discipline.BIATHLON, new NationCode("ITA"), MedalEntry.MedalType.ORO),
                new MedalEntry("G001", Discipline.BIATHLON, new NationCode("GER"), MedalEntry.MedalType.ARGENTO),
                new MedalEntry("G001", Discipline.BIATHLON, new NationCode("FRA"), MedalEntry.MedalType.BRONZO)
        );
    }

    @Test
    void statsSelector_aggregatesMedalsByNation() {
        DataSelector<Race, NationStats> sut = new StatsSelector();

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

        List<NationStats> out = sut.apply(List.of(r1, r2));

        assertThat(out).contains(
                new NationStats(new NationCode("ITA"), 2, 2, 0, 0),
                new NationStats(new NationCode("GER"), 2, 0, 1, 1),
                new NationStats(new NationCode("FRA"), 1, 0, 0, 1),
                new NationStats(new NationCode("CAN"), 1, 0, 1, 0)
        );
    }


    @Test
    void sportOrdering_sortsByNationThenMedalType() {
        List<MedalEntry> input = List.of(
                new MedalEntry("G003", Discipline.BIATHLON, new NationCode("ITA"), MedalEntry.MedalType.BRONZO),
                new MedalEntry("G002", Discipline.BIATHLON, new NationCode("ITA"), MedalEntry.MedalType.ARGENTO),
                new MedalEntry("G001", Discipline.BIATHLON, new NationCode("ITA"), MedalEntry.MedalType.ORO)
        );

        List<MedalEntry> out = input.stream().sorted(MedalEntry.BY_NATION_THEN_MEDAL).toList();

        assertThat(out).containsExactly(
                new MedalEntry("G001", Discipline.BIATHLON, new NationCode("ITA"), MedalEntry.MedalType.ORO),
                new MedalEntry("G002", Discipline.BIATHLON, new NationCode("ITA"), MedalEntry.MedalType.ARGENTO),
                new MedalEntry("G003", Discipline.BIATHLON, new NationCode("ITA"), MedalEntry.MedalType.BRONZO)
        );


    }

    @Test
    void sportOrdering_sortsByMedalTypeThenNation() {
        List<MedalEntry> input = List.of(
                new MedalEntry("G005", Discipline.BIATHLON, new NationCode("ITA"), MedalEntry.MedalType.ORO),
                new MedalEntry("G001", Discipline.BIATHLON, new NationCode("GER"), MedalEntry.MedalType.ARGENTO),
                new MedalEntry("G002", Discipline.BIATHLON, new NationCode("AUT"), MedalEntry.MedalType.BRONZO),
                new MedalEntry("G001", Discipline.BIATHLON, new NationCode("FRA"), MedalEntry.MedalType.BRONZO),
                new MedalEntry("G005", Discipline.BIATHLON, new NationCode("GER"), MedalEntry.MedalType.ORO)
        );

        List<MedalEntry> out = input.stream().sorted(MedalEntry.BY_MEDAL_THEN_NATION).toList();

        assertThat(out).containsExactly(
                new MedalEntry("G005", Discipline.BIATHLON, new NationCode("GER"), MedalEntry.MedalType.ORO),
                new MedalEntry("G005", Discipline.BIATHLON, new NationCode("ITA"), MedalEntry.MedalType.ORO),
                new MedalEntry("G001", Discipline.BIATHLON, new NationCode("GER"), MedalEntry.MedalType.ARGENTO),
                new MedalEntry("G002", Discipline.BIATHLON, new NationCode("AUT"), MedalEntry.MedalType.BRONZO),
                new MedalEntry("G001", Discipline.BIATHLON, new NationCode("FRA"), MedalEntry.MedalType.BRONZO)
        );
    }

    @Test
    void statsOrdering_sortsByGoldThenSilverThenBronzeThenTotalThenNation() {
        List<NationStats> input = List.of(
                new NationStats(new NationCode("FRA"), 2, 0, 1, 1),
                new NationStats(new NationCode("USA"), 1, 1, 0, 0),
                new NationStats(new NationCode("NOR"), 2, 1, 0, 1),
                new NationStats(new NationCode("GER"), 2, 1, 1, 0),
                new NationStats(new NationCode("ITA"), 3, 2, 1, 0)
        );

        List<NationStats> out = input.stream().sorted(NationStats.BY_MEDAL_TABLE).toList();

        assertThat(out).containsExactly(
                new NationStats(new NationCode("ITA"), 3, 2, 1, 0),
                new NationStats(new NationCode("GER"), 2, 1, 1, 0),
                new NationStats(new NationCode("NOR"), 2, 1, 0, 1),
                new NationStats(new NationCode("USA"), 1, 1, 0, 0),
                new NationStats(new NationCode("FRA"), 2, 0, 1, 1)
        );
    }


    @Test
    void sportRowFormatter_formatsSportRows() {
        List<MedalEntry> input = List.of(
                new MedalEntry("G002", Discipline.BIATHLON, new NationCode("AUT"), MedalEntry.MedalType.BRONZO),
                new MedalEntry("G005", Discipline.BIATHLON, new NationCode("GER"), MedalEntry.MedalType.ORO),
                new MedalEntry("G001", Discipline.BIATHLON, new NationCode("GER"), MedalEntry.MedalType.ARGENTO)
        );

        List<String> out = input.stream().map(MedalEntry::toString).toList();

        assertThat(out).containsExactly(
                "AUT BRONZO G002",
                "GER ORO G005",
                "GER ARGENTO G001"
        );
    }

    @Test
    void nationStatsRowFormatter_formatsStatsRows() {
        List<NationStats> input = List.of(
                new NationStats(new NationCode("ITA"), 3, 2, 1, 0),
                new NationStats(new NationCode("GER"), 2, 1, 1, 0),
                new NationStats(new NationCode("NOR"), 2, 1, 0, 1)
        );

        List<String> out = input.stream().map(NationStats::toString).toList();

        assertThat(out).containsExactly(
                "ITA (Tot: 3 Ori: 2 Argenti: 1 Bronzi: 0)",
                "GER (Tot: 2 Ori: 1 Argenti: 1 Bronzi: 0)",
                "NOR (Tot: 2 Ori: 1 Argenti: 0 Bronzi: 1)"
        );
    }




}
