package it.unimi.di.sweng.esame.presenter;

import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.function.Function;

@FunctionalInterface
public interface DataSelector<I, O> extends Function<List<I>, List<O>> {
    @Override
    @NotNull List<O> apply(@NotNull List<I> input);
}

/*
 DataSelector è un wrapper semantico di Function: non aggiunge potenza espressiva, ma rende esplicito che quella funzione
 ha la responsabilità di selezionare e proiettare i dati da mostrare nella view. In questo modo il codice resta più
 leggibile e coerente con le altre strategie del presenter. I generici inoltre restano più puliti.
 */