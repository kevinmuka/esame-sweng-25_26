package it.unimi.di.sweng.esame.model;

import it.unimi.di.sweng.esame.Observable;
import it.unimi.di.sweng.esame.Observer;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class Model extends State implements Observable<List<Race>> {
    @Override
    public void addObserver(@NotNull Observer<List<Race>> observer) {

    }

    @Override
    public void notifyObservers() {

    }

    @Override
    public @NotNull List<Race> getState() {
        return List.of();
    }
}
