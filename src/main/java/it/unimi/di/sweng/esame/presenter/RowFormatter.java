package it.unimi.di.sweng.esame.presenter;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public interface RowFormatter<T> {
    @NotNull List<String> format(@NotNull List<T> input);
}
