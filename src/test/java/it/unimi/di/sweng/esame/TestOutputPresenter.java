package it.unimi.di.sweng.esame;

import it.unimi.di.sweng.esame.model.Discipline;
import it.unimi.di.sweng.esame.model.NationCode;
import it.unimi.di.sweng.esame.model.Podium;
import it.unimi.di.sweng.esame.model.Race;
import it.unimi.di.sweng.esame.presenter.DataSelector;
import it.unimi.di.sweng.esame.presenter.MyOutputPresenter;
import it.unimi.di.sweng.esame.presenter.Ordering;
import it.unimi.di.sweng.esame.view.OutputView;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.function.Function;

import static org.mockito.Mockito.*;

public class TestOutputPresenter {
    @Test
    void testUpdateOutputPresenter() {
        Observable<List<Race>> subject = mock();
        List<Race> state = List.of();

        Race race = new Race(
                "G001",
                Discipline.BIATHLON,
                new Podium(
                        List.of(new NationCode("ITA")),
                        List.of(new NationCode("GER")),
                        List.of(new NationCode("FRA"))
                )
        );
        List<Race> selected = List.of(race);
        List<Race> ordered = List.of(race);

        OutputView view = mock();
        when(view.size()).thenReturn(5);

        DataSelector<Race> selector = mock();
        when(selector.select(state)).thenReturn(selected);

        Ordering<Race> ordering = mock();
        when(ordering.sort(selected)).thenReturn(ordered);

        Function<Race, String> formatter = mock();
        when(formatter.apply(race)).thenReturn("r1");

        MyOutputPresenter<Race> sut = new MyOutputPresenter<>(view, selector, ordering, formatter);
        sut.update(subject, state);

        verify(selector).select(state);
        verify(ordering).sort(selected);
        verify(formatter).apply(race);
        verify(view).set(0, "r1");
        verify(view).set(1, "");
        verify(view).set(2, "");
        verify(view).set(3, "");
        verify(view).set(4, "");
    }


}
