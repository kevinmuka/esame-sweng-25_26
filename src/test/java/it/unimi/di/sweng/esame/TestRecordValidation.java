package it.unimi.di.sweng.esame;

import it.unimi.di.sweng.esame.model.Podium;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class TestRecordValidation {
    @Test
    void testPodiumRefEscaping() {
        Set<String> mutableGold = new HashSet<>(Set.of("ITA"));
        Podium podium = new Podium(
                mutableGold,
                Set.of("GER"),
                Set.of("FRA")
        );

        mutableGold.add("USA");
        assertThat(podium.gold()).containsExactly("ITA");

        assertThatThrownBy(() -> podium.gold().add("SWE"))
                .isInstanceOf(UnsupportedOperationException.class);
    }
}
