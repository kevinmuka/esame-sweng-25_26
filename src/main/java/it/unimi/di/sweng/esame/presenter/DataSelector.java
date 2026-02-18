package it.unimi.di.sweng.esame.presenter;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public interface DataSelector<T> {
    @NotNull List<T> select(@NotNull List<T> input);
}
