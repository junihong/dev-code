package me.tamsil.springboothibernatevalidation;

public interface DefaultEntity<T, V> {
    T convert();
    void update(V v);
}
