package me.tamsil.springbootjunittest;

public interface DefaultEntity<T, V> {

    T convert();
    void update(V v);
}
