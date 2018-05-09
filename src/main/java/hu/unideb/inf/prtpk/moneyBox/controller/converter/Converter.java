package hu.unideb.inf.prtpk.moneyBox.controller.converter;

public interface Converter<T, P> {
    T from(P p);

    P to(T t);
}