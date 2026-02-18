package it.unimi.di.sweng.esame.presenter;

import it.unimi.di.sweng.esame.Observable;
import it.unimi.di.sweng.esame.Observer;
import it.unimi.di.sweng.esame.model.Race;
import it.unimi.di.sweng.esame.view.OutputView;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class MyOutputPresenter implements Observer<List<Race>> {
    public MyOutputPresenter(@NotNull OutputView view, @NotNull DataSelector<Race> selector, @NotNull Ordering<Race> ordering, @NotNull RowFormatter<Race> formatter) {
    }

    @Override
    public void update(@NotNull Observable<List<Race>> subject, @NotNull List<Race> state) {

    }
}
