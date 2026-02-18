package it.unimi.di.sweng.esame;

import it.unimi.di.sweng.esame.model.Model;
import it.unimi.di.sweng.esame.model.Discipline;
import it.unimi.di.sweng.esame.model.NationCode;
import it.unimi.di.sweng.esame.model.Podium;
import it.unimi.di.sweng.esame.model.Race;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class TestModel {
    @Test
    public void NotifyObservers() {
        Observer<List<Race>> DOC = mock();
        Observable<List<Race>> SUT = new Model();

        SUT.addObserver(DOC);
        SUT.notifyObservers();
        verify(DOC).update(SUT, SUT.getState());
    }

    @Test
    public void addRaceNotifiesObservers() {
        Observer<List<Race>> DOC = mock();
        Model SUT = new Model();
        SUT.addObserver(DOC);

        Podium podium = new Podium(
                Set.of(new NationCode("ITA")),
                Set.of(new NationCode("GER")),
                Set.of(new NationCode("FRA"))
        );
        Race race = new Race("G001", Discipline.BIATHLON, podium);

        SUT.insertRace(race);
        verify(DOC).update(SUT, SUT.getState());
    }

    @Test
    public void getState() {
        Model SUT = new Model();
        assertThat(SUT.getState()).isEmpty();

        Podium podium = new Podium(
                Set.of(new NationCode("ITA")),
                Set.of(new NationCode("GER")),
                Set.of(new NationCode("FRA"))
        );
        Race race = new Race("G001", Discipline.BIATHLON, podium);

        SUT.insertRace(race);
        assertThat(SUT.getState()).containsExactly(race);
    }

}
