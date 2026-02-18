package it.unimi.di.sweng.esame.presenter;

import it.unimi.di.sweng.esame.model.*;
import it.unimi.di.sweng.esame.view.InputView;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class MyInputPresenter implements InputPresenter {
    private final IState model;
    private final InputView view;

    public MyInputPresenter(IState model, InputView view) {
        this.model = model;
        this.view = view;
    }

    @Override
    public void action1(@NotNull String text, @NotNull String text1) {
        try {
            // Parse first field
            String[] parts = text.split(",");
            String raceId = parts[0];
            Discipline discipline = Discipline.fromString(parts[1]);

            // Parse second field
            String[] medals = text1.split(";");
            Set<NationCode> gold = parseGroup(medals[0]);
            Set<NationCode> silver = parseGroup(medals[1]);
            Set<NationCode> bronze = medals.length > 2 ? parseGroup(medals[2]) : Set.of();

            Podium podium = new Podium(gold, silver, bronze);
            Race race = new Race(raceId, discipline, podium);

            model.insertRace(race);
            view.showSuccess();
        } catch (IllegalArgumentException e) {
            view.showError(e.getMessage());
        }
    }

    private static @NotNull Set<NationCode> parseGroup(@NotNull String group) {
        return Arrays.stream(group.split(","))
                .map(String::trim)
                .map(NationCode::new)
                .collect(Collectors.toSet());
    }
}
