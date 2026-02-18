package it.unimi.di.sweng.esame.presenter;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public interface Ordering<T> {
    @NotNull List<T> sort(@NotNull List<T> input);
}
