package it.unimi.di.sweng.esame.presenter;

import org.jetbrains.annotations.NotNull;

import java.util.List;

@FunctionalInterface
public interface DataSelector<I, O> {
    @NotNull List<O> select(@NotNull List<I> input);
}
