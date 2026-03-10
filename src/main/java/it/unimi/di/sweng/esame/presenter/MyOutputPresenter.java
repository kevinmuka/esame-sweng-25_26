package it.unimi.di.sweng.esame.presenter;

import it.unimi.di.sweng.esame.Observable;
import it.unimi.di.sweng.esame.Observer;
import it.unimi.di.sweng.esame.model.Race;
import it.unimi.di.sweng.esame.view.OutputView;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.function.Function;

public class MyOutputPresenter<T> implements Observer<List<Race>> {
    private final OutputView view;
    private final DataSelector<T> selector;
    private final Ordering<T> ordering;
    private final Function<T, String> formatter;

    public MyOutputPresenter(
            @NotNull OutputView view,
            @NotNull DataSelector<T> selector,
            @NotNull Ordering<T> ordering,
            @NotNull Function<T, String> formatter
    ) {
        this.view = view;
        this.selector = selector;
        this.ordering = ordering;
        this.formatter = formatter;
    }

    @Override
    public void update(@NotNull Observable<List<Race>> subject, @NotNull List<Race> state) {
        List<T> selected = selector.select(state);
        List<T> ordered = ordering.sort(selected);
        List<String> rows = ordered.stream().map(formatter).toList();
        for (int i = 0; i < view.size(); i++) {
            view.set(i, i < rows.size() ? rows.get(i) : "");
        }
    }
}
