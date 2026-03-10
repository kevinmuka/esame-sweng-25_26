package it.unimi.di.sweng.esame;

import it.unimi.di.sweng.esame.model.Discipline;
import it.unimi.di.sweng.esame.model.NationCode;
import it.unimi.di.sweng.esame.model.Podium;
import it.unimi.di.sweng.esame.model.Race;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class TestRaceValidation {
    @Test
    void testRaceInvalidDiscipline() {
        Podium podium = new Podium(
                List.of(new NationCode("ITA")),
                List.of(new NationCode("GER")),
                List.of(new NationCode("FRA"))
        );

        assertThatThrownBy(() -> new Race("G001", Discipline.fromString("Calcio"), podium))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("disciplina non valida");
    }

    @Test
    void testRaceEmptyId() {
        Podium podium = new Podium(
                List.of(new NationCode("ITA")),
                List.of(new NationCode("GER")),
                List.of(new NationCode("FRA"))
        );

        assertThatThrownBy(() -> new Race(" ", Discipline.BIATHLON, podium))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("id gara non valido");
    }
}
