package it.unimi.di.sweng.esame.presenter;

import it.unimi.di.sweng.esame.model.*;
import it.unimi.di.sweng.esame.view.InputView;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;

public class MyInputPresenter implements InputPresenter {
    private final IState model;
    private final InputView view;

    public MyInputPresenter(@NotNull IState model, @NotNull InputView view) {
        this.model = model;
        this.view = view;
    }

    @Override
    public void action1(@NotNull String text, @NotNull String text1) {
        try {
            RaceData raceData = parseRaceField(text);
            Podium podium = parsePodiumField(text1);
            model.insertRace(new Race(raceData.raceId(), raceData.discipline(), podium));
            view.showSuccess();
        } catch (IllegalArgumentException e) {
            view.showError(e.getMessage());
        }
    }

    private static @NotNull RaceData parseRaceField(@NotNull String raceField) {
        String[] parts = raceField.split(",", -1);
        if (parts.length != 2 || parts[0].isBlank() || parts[1].isBlank()) {
            throw new IllegalArgumentException("formato gara non valido");
        }
        return new RaceData(parts[0], Discipline.fromString(parts[1]));
    }

    private static @NotNull Podium parsePodiumField(@NotNull String podiumField) {
        String[] medals = podiumField.split(";", -1);
        if (medals.length != 3) {
            throw new IllegalArgumentException("formato medaglie non valido");
        }
        return new Podium(
                parseGroup(medals[0]),
                parseGroup(medals[1]),
                parseGroup(medals[2])
        );
    }

    private static @NotNull List<NationCode> parseGroup(@NotNull String group) {
        if (group.isBlank()) {
            return List.of();
        }
        String[] codes = group.split(",", -1);
        if (Arrays.stream(codes).map(String::trim).anyMatch(String::isEmpty)) {
            throw new IllegalArgumentException("formato medaglie non valido");
        }
        return Arrays.stream(codes)
                .map(String::trim)
                .map(NationCode::new)
                .toList();
    }

    private record RaceData(@NotNull String raceId, @NotNull Discipline discipline) {
    }
}
