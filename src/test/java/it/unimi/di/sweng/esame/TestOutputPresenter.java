package it.unimi.di.sweng.esame;

import it.unimi.di.sweng.esame.model.Race;
import it.unimi.di.sweng.esame.presenter.DataSelector;
import it.unimi.di.sweng.esame.presenter.MyOutputPresenter;
import it.unimi.di.sweng.esame.view.OutputView;
import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.List;
import java.util.function.Function;

import static org.mockito.Mockito.*;

public class TestOutputPresenter {
    @Test
    void testUpdateOutputPresenter() {
        Observable<List<Race>> subject = mock();
        List<Race> state = List.of();

        OutputView view = mock();
        when(view.size()).thenReturn(5);

        DataSelector<Race, String> selector = mock();
        when(selector.select(state)).thenReturn(List.of("b", "a"));
        Comparator<String> comparator = mock();
        when(comparator.compare("a", "b")).thenReturn(-1);
        when(comparator.compare("b", "a")).thenReturn(1);
        Function<String, String> formatter = mock();
        when(formatter.apply("a")).thenReturn("G001");
        when(formatter.apply("b")).thenReturn("G002");

        MyOutputPresenter<String> sut = new MyOutputPresenter<>(
                view,
                selector,
                comparator,
                formatter
        );

        sut.update(subject, state);

        verify(view).set(0, "G001");
        verify(view).set(1, "G002");
        verify(view).set(2, "");
        verify(view).set(3, "");
        verify(view).set(4, "");
    }


}
