package it.unimi.di.sweng.esame.presenter;

import it.unimi.di.sweng.esame.model.NationCode;
import it.unimi.di.sweng.esame.model.Race;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StatsSelector implements DataSelector<Race, NationStats> {
    @Override
    public @NotNull List<NationStats> select(@NotNull List<Race> input) {
        Map<NationCode, MutableStats> byNation = new HashMap<>();

        for (Race race : input) {
            for (NationCode nation : race.podium().gold()) {
                byNation.computeIfAbsent(nation, ignored -> new MutableStats()).gold++;
            }
            for (NationCode nation : race.podium().silver()) {
                byNation.computeIfAbsent(nation, ignored -> new MutableStats()).silver++;
            }
            for (NationCode nation : race.podium().bronze()) {
                byNation.computeIfAbsent(nation, ignored -> new MutableStats()).bronze++;
            }
        }

        List<NationStats> out = new ArrayList<>();
        for (Map.Entry<NationCode, MutableStats> entry : byNation.entrySet()) {
            MutableStats stats = entry.getValue();
            out.add(new NationStats(
                    entry.getKey(),
                    stats.gold + stats.silver + stats.bronze,
                    stats.gold,
                    stats.silver,
                    stats.bronze
            ));
        }
        return out;
    }

    private static final class MutableStats {
        private int gold;
        private int silver;
        private int bronze;
    }
}
