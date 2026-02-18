package it.unimi.di.sweng.esame.model;

import org.jetbrains.annotations.NotNull;

public record NationCode(@NotNull String value) {
    public NationCode {
        if (!value.matches("[A-Z]{3}")) {
            throw new IllegalArgumentException("sigla nazione non valida");
        }
    }
}
