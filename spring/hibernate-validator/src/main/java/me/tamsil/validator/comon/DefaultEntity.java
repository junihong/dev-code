package me.tamsil.validator.comon;

public interface DefaultEntity<T, V> {
    T convert();
    void update(V v);
}
