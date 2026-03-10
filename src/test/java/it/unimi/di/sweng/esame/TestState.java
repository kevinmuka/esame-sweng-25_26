package it.unimi.di.sweng.esame;

import it.unimi.di.sweng.esame.model.IState;
import it.unimi.di.sweng.esame.model.Discipline;
import it.unimi.di.sweng.esame.model.NationCode;
import it.unimi.di.sweng.esame.model.Podium;
import it.unimi.di.sweng.esame.model.Race;
import it.unimi.di.sweng.esame.model.State;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class TestState {

    @Test
    void insertRace() {
        IState sut = new State();
        Podium podium = new Podium(
                List.of(new NationCode("ITA")),
                List.of(new NationCode("GER")),
                List.of(new NationCode("FRA"))
        );
        Race race = new Race("G001", Discipline.BIATHLON, podium);

        sut.insertRace(race);
        assertThat(sut.getRaces()).containsExactly(race);
    }

    @Test
    void insertDuplicateRace() {
        IState sut = new State();
        Podium podium1 = new Podium(
                List.of(new NationCode("ITA")),
                List.of(new NationCode("GER")),
                List.of(new NationCode("FRA"))
        );
        Race race1 = new Race("G001", Discipline.BIATHLON, podium1);
        sut.insertRace(race1);

        Podium podium2 = new Podium(
                List.of(new NationCode("USA")),
                List.of(new NationCode("CAN")),
                List.of(new NationCode("SWE"))
        );
        Race race2 = new Race("G001", Discipline.SCI_ALPINO, podium2);

        assertThatThrownBy(() -> sut.insertRace(race2))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("gara già inserita");
    }

    @Test
    void getRacesDoesNotExposeMutableCollection() {
        IState sut = new State();
        Race race = new Race(
                "G001",
                Discipline.BIATHLON,
                new Podium(
                        List.of(new NationCode("ITA")),
                        List.of(new NationCode("GER")),
                        List.of(new NationCode("FRA"))
                )
        );
        sut.insertRace(race);

        List<Race> snapshot = sut.getRaces();
        assertThatThrownBy(() -> snapshot.add(race))
                .isInstanceOf(UnsupportedOperationException.class);
    }

    @Test
    void getRacesReturnsSnapshotNotLiveView() {
        IState sut = new State();
        Race race1 = new Race(
                "G001",
                Discipline.BIATHLON,
                new Podium(
                        List.of(new NationCode("ITA")),
                        List.of(new NationCode("GER")),
                        List.of(new NationCode("FRA"))
                )
        );
        Race race2 = new Race(
                "G002",
                Discipline.BOB,
                new Podium(
                        List.of(new NationCode("USA")),
                        List.of(new NationCode("CAN")),
                        List.of(new NationCode("SWE"))
                )
        );

        sut.insertRace(race1);
        List<Race> snapshot = sut.getRaces();
        sut.insertRace(race2);

        assertThat(snapshot).containsExactly(race1);
        assertThat(sut.getRaces()).containsExactlyInAnyOrder(race1, race2);
    }
}
