package it.unimi.di.sweng.esame.model;

import it.unimi.di.sweng.esame.Observable;
import it.unimi.di.sweng.esame.Observer;
import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Model extends State implements Observable<List<Race>> {
    private final List<Observer<List<Race>>> obs = new ArrayList<>();

    @Override
    public void addObserver(@NotNull Observer<List<Race>> observer) {
        obs.add(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer<List<Race>> o : obs) o.update(this, getState());
    }

    @Override
    public @NotNull List<Race> getState() {
        return List.of();
    }
}
