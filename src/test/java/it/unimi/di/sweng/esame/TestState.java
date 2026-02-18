package it.unimi.di.sweng.esame;

import it.unimi.di.sweng.esame.model.IState;
import it.unimi.di.sweng.esame.model.Discipline;
import it.unimi.di.sweng.esame.model.NationCode;
import it.unimi.di.sweng.esame.model.Podium;
import it.unimi.di.sweng.esame.model.Race;
import it.unimi.di.sweng.esame.model.State;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class TestState {

    @Test
    void insertRace() {
        IState sut = new State();
        Podium podium = new Podium(
                Set.of(new NationCode("ITA")),
                Set.of(new NationCode("GER")),
                Set.of(new NationCode("FRA"))
        );
        Race race = new Race("G001", Discipline.BIATHLON, podium);

        sut.insertRace(race);
        assertThat(sut.getRaces()).containsExactly(race);
    }

    @Test
    void insertDuplicateRace() {
        IState sut = new State();
        Podium podium1 = new Podium(
                Set.of(new NationCode("ITA")),
                Set.of(new NationCode("GER")),
                Set.of(new NationCode("FRA"))
        );
        Race race1 = new Race("G001", Discipline.BIATHLON, podium1);
        sut.insertRace(race1);

        Podium podium2 = new Podium(
                Set.of(new NationCode("USA")),
                Set.of(new NationCode("CAN")),
                Set.of(new NationCode("SWE"))
        );
        Race race2 = new Race("G001", Discipline.SCI_ALPINO, podium2);

        assertThatThrownBy(() -> sut.insertRace(race2))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("gara già inserita");
    }
}
