package it.unimi.di.sweng.esame;

import it.unimi.di.sweng.esame.model.IState;
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
                Set.of("ITA"),
                Set.of("GER"),
                Set.of("FRA")
        );
        Race race = new Race("G001", "Biathlon", podium);

        sut.insertRace(race);
        assertThat(sut.getRaces()).containsExactly(race);
    }

    @Test
    void insertDuplicateRace() {
        IState sut = new State();
        Podium podium1 = new Podium(
                Set.of("ITA"),
                Set.of("GER"),
                Set.of("FRA")
        );
        Race race1 = new Race("G001", "Biathlon", podium1);
        sut.insertRace(race1);

        Podium podium2 = new Podium(
                Set.of("USA"),
                Set.of("CAN"),
                Set.of("SWE")
        );
        Race race2 = new Race("G001", "Sci alpino", podium2);

        assertThatThrownBy(() -> sut.insertRace(race2))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("gara già inserita");
    }
}
