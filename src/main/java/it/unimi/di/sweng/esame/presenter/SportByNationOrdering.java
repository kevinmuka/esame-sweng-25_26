package it.unimi.di.sweng.esame.presenter;

import org.jetbrains.annotations.NotNull;

import java.util.Comparator;
import java.util.List;

public class SportByNationOrdering implements Ordering<MedalEntry> {
    private static final Comparator<MedalEntry> COMPARATOR =
            Comparator
                    .comparing((MedalEntry e) -> e.nation().nationCode())
                    .thenComparingInt(e -> switch (e.medalType()) {
                        case ORO -> 0;
                        case ARGENTO -> 1;
                        case BRONZO -> 2;
                    })
                    .thenComparing(MedalEntry::raceId);

    @Override
    public @NotNull List<MedalEntry> sort(@NotNull List<MedalEntry> input) {
        return input.stream().sorted(COMPARATOR).toList();
    }
}
