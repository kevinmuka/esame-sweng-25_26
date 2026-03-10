package it.unimi.di.sweng.esame.presenter;

import org.jetbrains.annotations.NotNull;

import java.util.Comparator;
import java.util.List;

public class SportByMedalOrdering implements Ordering<MedalEntry> {
    private static final Comparator<MedalEntry> COMPARATOR =
            Comparator
                    .comparingInt((MedalEntry e) -> switch (e.medalType()) {
                        case ORO -> 0;
                        case ARGENTO -> 1;
                        case BRONZO -> 2;
                    })
                    .thenComparing(e -> e.nation().nationCode())
                    .thenComparing(MedalEntry::raceId);

    @Override
    public @NotNull List<MedalEntry> sort(@NotNull List<MedalEntry> input) {
        return input.stream().sorted(COMPARATOR).toList();
    }
}
