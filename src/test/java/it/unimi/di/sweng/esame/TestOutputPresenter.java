package it.unimi.di.sweng.esame;

import it.unimi.di.sweng.esame.model.Race;
import it.unimi.di.sweng.esame.presenter.DataSelector;
import it.unimi.di.sweng.esame.presenter.MyOutputPresenter;
import it.unimi.di.sweng.esame.presenter.Ordering;
import it.unimi.di.sweng.esame.presenter.RowFormatter;
import it.unimi.di.sweng.esame.view.OutputView;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

public class TestOutputPresenter {
    @Test
    void testUpdateOutputPresenter() {
        Observable<List<Race>> subject = mock();
        List<Race> state = List.of();

        List<Race> selected = List.of();
        List<Race> ordered = List.of();
        List<String> rows = List.of("r1", "r2", "r3", "r4", "r5");

        OutputView view = mock();
        when(view.size()).thenReturn(5);

        DataSelector<Race> selector = mock();
        when(selector.select(state)).thenReturn(selected);

        Ordering<Race> ordering = mock();
        when(ordering.sort(selected)).thenReturn(ordered);

        RowFormatter<Race> formatter = mock();
        when(formatter.format(ordered)).thenReturn(rows);

        MyOutputPresenter sut = new MyOutputPresenter(view, selector, ordering, formatter);
        sut.update(subject, state);

        verify(selector).select(state);
        verify(ordering).sort(selected);
        verify(formatter).format(ordered);

        for (int i = 0; i < view.size(); i++) {
            verify(view).set(i, rows.get(i));
        }
    }


}
