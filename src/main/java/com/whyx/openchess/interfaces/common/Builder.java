package com.whyx.openchess.interfaces.common;

/**
 * @param <T> The type of object being built.
 * @author Sam Wykes.
 * Interface representing the builder design pattern.
 */
public interface Builder<T> {

    /**
     * Build the object.
     *
     * @return {@link T} object.
     */
    T build();

}
