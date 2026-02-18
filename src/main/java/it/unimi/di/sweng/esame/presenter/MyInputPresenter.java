package it.unimi.di.sweng.esame.presenter;

import it.unimi.di.sweng.esame.model.*;
import it.unimi.di.sweng.esame.view.InputView;
import org.jetbrains.annotations.NotNull;

import java.util.Set;

public class MyInputPresenter implements InputPresenter {
    private final IState model;
    private final InputView view;

    public MyInputPresenter(IState model, InputView view) {
        this.model = model;
        this.view = view;
    }

    @Override
    public void action1(@NotNull String text, @NotNull String text1) {
        // Parse first field
        String[] parts = text.split(",");
        String raceId = parts[0];
        Discipline discipline = Discipline.fromString(parts[1]);

        // Parse second field
        String[] medals = text1.split(";");
        Set<NationCode> gold = Set.of(new NationCode(medals[0]));
        Set<NationCode> silver = Set.of(new NationCode(medals[1]));
        Set<NationCode> bronze = Set.of(new NationCode(medals[2]));

        Podium podium = new Podium(gold, silver, bronze);
        Race race = new Race(raceId, discipline, podium);

        model.insertRace(race);
        view.showSuccess();
    }
}
