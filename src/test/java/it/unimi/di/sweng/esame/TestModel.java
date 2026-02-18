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
import static org.assertj.core.api.Assertions.assertThatThrownBy;
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

    @Test
    public void getStateDoesNotExposeMutableCollection() {
        Model SUT = new Model();
        Race race = new Race(
                "G001",
                Discipline.BIATHLON,
                new Podium(
                        Set.of(new NationCode("ITA")),
                        Set.of(new NationCode("GER")),
                        Set.of(new NationCode("FRA"))
                )
        );
        SUT.insertRace(race);

        List<Race> snapshot = SUT.getState();
        assertThatThrownBy(() -> snapshot.add(race))
                .isInstanceOf(UnsupportedOperationException.class);
    }

    @Test
    public void getStateReturnsSnapshotNotLiveView() {
        Model SUT = new Model();
        Race race1 = new Race(
                "G001",
                Discipline.BIATHLON,
                new Podium(
                        Set.of(new NationCode("ITA")),
                        Set.of(new NationCode("GER")),
                        Set.of(new NationCode("FRA"))
                )
        );
        Race race2 = new Race(
                "G002",
                Discipline.BOB,
                new Podium(
                        Set.of(new NationCode("USA")),
                        Set.of(new NationCode("CAN")),
                        Set.of(new NationCode("SWE"))
                )
        );

        SUT.insertRace(race1);
        List<Race> snapshot = SUT.getState();
        SUT.insertRace(race2);

        assertThat(snapshot).containsExactly(race1);
        assertThat(SUT.getState()).containsExactlyInAnyOrder(race1, race2);
    }

}
