package it.unimi.di.sweng.esame.presenter;

import it.unimi.di.sweng.esame.Observable;
import it.unimi.di.sweng.esame.Observer;
import it.unimi.di.sweng.esame.model.Race;
import it.unimi.di.sweng.esame.view.OutputView;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class MyOutputPresenter implements Observer<List<Race>> {
    private final OutputView view;
    private final DataSelector<Race> selector;
    private final Ordering<Race> ordering;
    private final RowFormatter<Race> formatter;

    public MyOutputPresenter(@NotNull OutputView view, @NotNull DataSelector<Race> selector, @NotNull Ordering<Race> ordering, @NotNull RowFormatter<Race> formatter) {
        this.view = view;
        this.selector = selector;
        this.ordering = ordering;
        this.formatter = formatter;
    }

    @Override
    public void update(@NotNull Observable<List<Race>> subject, @NotNull List<Race> state) {
        List<Race> selected = selector.select(state);
        List<Race> ordered = ordering.sort(selected);
        List<String> rows = formatter.format(ordered);
        for (int i = 0; i < view.size(); i++) {
            view.set(i, rows.get(i));
        }
    }
}
