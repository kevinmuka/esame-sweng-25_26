package it.unimi.di.sweng.esame;

import it.unimi.di.sweng.esame.model.NationCode;
import it.unimi.di.sweng.esame.model.Podium;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class TestPodiumValidation {

    @ParameterizedTest
    @ValueSource(strings = {"ita", "Ita", "Italia", "I"})
    void testNationCodeInvalidFormat(String invalidCode) {
        assertThatThrownBy(() -> new NationCode(invalidCode))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("sigla nazione non valida");
    }

    @Test
    void testPodiumRefEscaping() {
        List<NationCode> mutableGold = new ArrayList<>(List.of(new NationCode("ITA")));
        Podium podium = new Podium(
                mutableGold,
                List.of(new NationCode("GER")),
                List.of(new NationCode("FRA"))
        );

        mutableGold.add(new NationCode("USA"));
        assertThat(podium.gold()).containsExactly(new NationCode("ITA"));

        assertThatThrownBy(() -> podium.gold().add(new NationCode("SWE")))
                .isInstanceOf(UnsupportedOperationException.class);
    }

    @ParameterizedTest
    @CsvSource({
            "1,1,0",
            "1,0,1",
            "1,2,1",
            "2,1,1",
            "2,0,0",
            "3,1,0",
            "0,1,1",
            "0,0,1"
    })
    void testPodiumInvalidFormats(int goldCount, int silverCount, int bronzeCount) {
        List<NationCode> gold = new ArrayList<>();
        List<NationCode> silver = new ArrayList<>();
        List<NationCode> bronze = new ArrayList<>();

        String[] validCodes = {"ITA", "GER", "FRA", "SWE", "NOR", "USA", "CAN"};

        for (int i = 0; i < goldCount; i++) gold.add(new NationCode(validCodes[i]));
        for (int i = 0; i < silverCount; i++) silver.add(new NationCode(validCodes[i + 2]));
        for (int i = 0; i < bronzeCount; i++) bronze.add(new NationCode(validCodes[i + 4]));

        assertThatThrownBy(() -> new Podium(gold, silver, bronze))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("configurazione podio non valida");
    }

    @ParameterizedTest
    @CsvSource({
            "1,1,1",
            "1,1,2",
            "1,2,0",
            "1,3,0",
            "2,0,1",
            "2,0,2",
            "3,0,0",
            "4,0,0"
    })
    void testPodiumValidFormats(int goldCount, int silverCount, int bronzeCount) {
        List<NationCode> gold = new ArrayList<>();
        List<NationCode> silver = new ArrayList<>();
        List<NationCode> bronze = new ArrayList<>();

        String[] validCodes = {"ITA", "GER", "FRA", "SWE", "NOR", "USA", "CAN"};

        for (int i = 0; i < goldCount; i++) gold.add(new NationCode(validCodes[i]));
        for (int i = 0; i < silverCount; i++) silver.add(new NationCode(validCodes[i + 2]));
        for (int i = 0; i < bronzeCount; i++) bronze.add(new NationCode(validCodes[i + 4]));

        assertThatCode(() -> new Podium(gold, silver, bronze))
                .doesNotThrowAnyException();
    }


}
