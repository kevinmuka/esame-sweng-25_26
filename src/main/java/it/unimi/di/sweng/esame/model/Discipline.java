package it.unimi.di.sweng.esame.model;

import org.jetbrains.annotations.NotNull;

import java.util.Locale;

public enum Discipline {
    BIATHLON,
    BOB,
    COMBINATA_NORDICA,
    CURLING,
    HOCKEY_SU_GHIACCIO,
    PATTINAGGIO_DI_FIGURA,
    PATTINAGGIO_DI_VELOCITA,
    SALTO_CON_GLI_SCI,
    SCI_ACROBATICO,
    SCI_ALPINISMO,
    SCI_ALPINO,
    SCI_DI_FONDO,
    SHORT_TRACK,
    SKELETON,
    SLITTINO,
    SNOWBOARD;

    public static @NotNull Discipline fromString(@NotNull String label) {
        try {
            return Discipline.valueOf(label.toUpperCase().replace(" ", "_"));
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("disciplina non valida");
        }
    }
}
