package it.unimi.di.sweng.esame;

import it.unimi.di.sweng.esame.Observer;
import org.jetbrains.annotations.NotNull;

public interface Observable<T> {
    void addObserver(@NotNull Observer<T> observer);
    void notifyObservers();
    @NotNull T getState();
}
