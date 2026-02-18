package it.unimi.di.sweng.esame.presenter;

import org.jetbrains.annotations.NotNull;

import java.util.Comparator;
import java.util.List;

public class SportByMedalOrdering implements Ordering<SportSelector.MedalEntry> {
    private static final Comparator<SportSelector.MedalEntry> COMPARATOR =
            Comparator
                    .comparingInt((SportSelector.MedalEntry e) -> switch (e.medalType()) {
                        case ORO -> 0;
                        case ARGENTO -> 1;
                        case BRONZO -> 2;
                    })
                    .thenComparing(e -> e.nation().value())
                    .thenComparing(SportSelector.MedalEntry::raceId);

    @Override
    public @NotNull List<SportSelector.MedalEntry> sort(@NotNull List<SportSelector.MedalEntry> input) {
        return input.stream().sorted(COMPARATOR).toList();
    }
}
