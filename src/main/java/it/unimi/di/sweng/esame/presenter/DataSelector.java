package it.unimi.di.sweng.esame.presenter;

import it.unimi.di.sweng.esame.model.Race;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public interface DataSelector<T> {
    @NotNull List<T> select(@NotNull List<Race> input);
}
