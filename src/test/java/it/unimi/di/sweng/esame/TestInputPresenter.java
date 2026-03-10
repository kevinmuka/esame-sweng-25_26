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
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static org.mockito.Mockito.*;

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
                        List.of(new NationCode("ITA")),
                        List.of(new NationCode("GER")),
                        List.of(new NationCode("FRA"))
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
                        List.of(new NationCode("ITA")),
                        List.of(new NationCode("SWE")),
                        List.of(new NationCode("GER"), new NationCode("NOR"))
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
                        List.of(new NationCode("ITA")),
                        List.of(new NationCode("SWE"), new NationCode("GER")),
                        List.of()
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

    @Test
    void invalidPodiumShowsErrorAndDoesNotInsertRace() {
        IState model = mock();
        InputView view = mock();
        InputPresenter sut = new MyInputPresenter(model, view);

        sut.action1("G004,Biathlon", "ITA;;"); // 1 oro, 0 argento, 0 bronzo -> non valido

        verify(view).showError("configurazione podio non valida");
        verifyNoMoreInteractions(model);
    }

    @ParameterizedTest
    @ValueSource(strings = {"G001", ",Biathlon", "G001,", ","})
    void invalidRaceFieldFormatShowsError(String raceField) {
        IState model = mock();
        InputView view = mock();
        InputPresenter sut = new MyInputPresenter(model, view);

        sut.action1(raceField, "ITA;GER;FRA");

        verify(view).showError("formato gara non valido");
    }

    @ParameterizedTest
    @ValueSource(strings = {"ITA;GER", "ITA;GER;FRA;SWE", "ITA,GER,FRA", "ITA;GER,;FRA", ""})
    void invalidMedalsFieldFormatShowsError(String medalsField) {
        IState model = mock();
        InputView view = mock();
        InputPresenter sut = new MyInputPresenter(model, view);

        sut.action1("G005,Biathlon", medalsField);

        verify(view).showError("formato medaglie non valido");
    }



}
