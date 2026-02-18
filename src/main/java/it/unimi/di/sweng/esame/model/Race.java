package it.unimi.di.sweng.esame.model;

import java.util.Set;

public record Race(
        String raceId,
        String discipline,
        Podium podium
) {
}
