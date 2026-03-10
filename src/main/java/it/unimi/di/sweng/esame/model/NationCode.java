package it.unimi.di.sweng.esame.model;

import org.jetbrains.annotations.NotNull;

public record NationCode(@NotNull String nationCode) {
    public NationCode {
        if (!nationCode.matches("[A-Z]{3}")) {
            throw new IllegalArgumentException("sigla nazione non valida");
        }
    }
}
