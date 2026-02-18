package it.unimi.di.sweng.esame;

import it.unimi.di.sweng.esame.model.NationCode;
import it.unimi.di.sweng.esame.model.Podium;
import it.unimi.di.sweng.esame.model.Race;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class RaceValidation {
    @Test
    void testRaceInvalidDiscipline() {
        Podium podium = new Podium(
                Set.of(new NationCode("ITA")),
                Set.of(new NationCode("GER")),
                Set.of(new NationCode("FRA"))
        );

        assertThatThrownBy(() -> new Race("G001", "Calcio", podium))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("disciplina non valida");
    }
}
