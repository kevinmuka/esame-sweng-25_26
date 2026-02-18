package it.unimi.di.sweng.esame;

import it.unimi.di.sweng.esame.model.Discipline;
import it.unimi.di.sweng.esame.model.IState;
import it.unimi.di.sweng.esame.model.NationCode;
import it.unimi.di.sweng.esame.model.Podium;
import it.unimi.di.sweng.esame.model.Race;
import it.unimi.di.sweng.esame.presenter.InputPresenter;
import it.unimi.di.sweng.esame.presenter.MyInputPresenter;
import it.unimi.di.sweng.esame.view.InputView;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

public class TestInputPresenter {
    @Test
    void insertsRaceAndShowsSuccess() {
        IState model = mock();
        InputView view = mock();
        InputPresenter sut = new MyInputPresenter(model, view);

        sut.action1("G001,Biathlon", "ITA;GER;FRA");

        Race race = new Race(
                "G001",
                Discipline.BIATHLON,
                new Podium(
                        Set.of(new NationCode("ITA")),
                        Set.of(new NationCode("GER")),
                        Set.of(new NationCode("FRA"))
                )
        );

        verify(model).insertRace(race);
        verify(view).showSuccess();
    }

    @Test
    void insertRaceWithTiesAndShowsSuccess() {
        IState model = mock();
        InputView view = mock();
        InputPresenter sut = new MyInputPresenter(model, view);

        sut.action1("G002,Biathlon", "ITA;SWE;GER,NOR");

        Race race = new Race(
                "G002",
                Discipline.BIATHLON,
                new Podium(
                        Set.of(new NationCode("ITA")),
                        Set.of(new NationCode("SWE")),
                        Set.of(new NationCode("GER"), new NationCode("NOR"))
                )
        );

        verify(model).insertRace(race);
        verify(view).showSuccess();
    }

    @Test
    void insertRaceWithNoBronzeAndShowsSuccess() {
        IState model = mock();
        InputView view = mock();
        InputPresenter sut = new MyInputPresenter(model, view);

        sut.action1("G002,Biathlon", "ITA;SWE,GER;");

        Race race = new Race(
                "G002",
                Discipline.BIATHLON,
                new Podium(
                        Set.of(new NationCode("ITA")),
                        Set.of(new NationCode("SWE"), new NationCode("GER")),
                        Set.of()
                )
        );

        verify(model).insertRace(race);
        verify(view).showSuccess();
    }

    @Test
    void invalidDisciplineShowsErrorAndDoesNotInsertRace() {
        IState model = mock();
        InputView view = mock();
        InputPresenter sut = new MyInputPresenter(model, view);

        sut.action1("G003,Calcio", "ITA;GER;FRA");

        verify(view).showError("disciplina non valida");
        verifyNoMoreInteractions(model);
    }

}
