package it.unimi.di.sweng.esame;

import it.unimi.di.sweng.esame.model.Model;
import it.unimi.di.sweng.esame.model.Race;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;

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

}
