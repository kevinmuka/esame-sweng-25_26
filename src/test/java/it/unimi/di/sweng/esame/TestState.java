package it.unimi.di.sweng.esame;

import it.unimi.di.sweng.esame.model.IState;
import it.unimi.di.sweng.esame.model.Podium;
import it.unimi.di.sweng.esame.model.Race;
import it.unimi.di.sweng.esame.model.State;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

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
    }}