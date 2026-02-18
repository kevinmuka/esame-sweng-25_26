package it.unimi.di.sweng.esame.model;

import org.jetbrains.annotations.NotNull;

public enum Discipline {
    BIATHLON("Biathlon"),
    BOB("Bob"),
    COMBINATA_NORDICA("Combinata nordica"),
    CURLING("Curling"),
    HOCKEY_SU_GHIACCIO("Hockey su ghiaccio"),
    PATTINAGGIO_DI_FIGURA("Pattinaggio di figura"),
    PATTINAGGIO_DI_VELOCITA("Pattinaggio di velocità"),
    SALTO_CON_GLI_SCI("Salto con gli sci"),
    SCI_ACROBATICO("Sci acrobatico"),
    SCI_ALPINISMO("Sci alpinismo"),
    SCI_ALPINO("Sci alpino"),
    SCI_DI_FONDO("Sci di fondo"),
    SHORT_TRACK("Short track"),
    SKELETON("Skeleton"),
    SLITTINO("Slittino"),
    SNOWBOARD("Snowboard");

    private final String text;

    Discipline(@NotNull String text) {
        this.text = text;
    }

    public static @NotNull Discipline fromString(@NotNull String label) {
        for (Discipline discipline : values()) {
            if (discipline.toString().equals(label)) {
                return discipline;
            }
        }
        throw new IllegalArgumentException("disciplina non valida");
    }
}
