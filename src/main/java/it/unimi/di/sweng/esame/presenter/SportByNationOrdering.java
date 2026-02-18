package it.unimi.di.sweng.esame.presenter;

import org.jetbrains.annotations.NotNull;

import java.util.Comparator;
import java.util.List;

public class SportByNationOrdering implements Ordering<SportSelector.MedalEntry> {
    private static final Comparator<SportSelector.MedalEntry> COMPARATOR =
            Comparator
                    .comparing((SportSelector.MedalEntry e) -> e.nation().value())
                    .thenComparingInt(e -> switch (e.medalType()) {
                        case ORO -> 0;
                        case ARGENTO -> 1;
                        case BRONZO -> 2;
                    })
                    .thenComparing(SportSelector.MedalEntry::raceId);

    @Override
    public @NotNull List<SportSelector.MedalEntry> sort(@NotNull List<SportSelector.MedalEntry> input) {
        return input.stream().sorted(COMPARATOR).toList();
    }
}
