package it.unimi.di.sweng.esame;

import it.unimi.di.sweng.esame.model.NationCode;
import it.unimi.di.sweng.esame.model.Podium;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class TestRecordValidation {
    @Test
    void testPodiumRefEscaping() {
        Set<NationCode> mutableGold = new HashSet<>(Set.of(new NationCode("ITA")));
        Podium podium = new Podium(
                mutableGold,
                Set.of(new NationCode("GER")),
                Set.of(new NationCode("FRA"))
        );

        mutableGold.add(new NationCode("USA"));
        assertThat(podium.gold()).containsExactly(new NationCode("ITA"));

        assertThatThrownBy(() -> podium.gold().add(new NationCode("SWE")))
                .isInstanceOf(UnsupportedOperationException.class);
    }
}
