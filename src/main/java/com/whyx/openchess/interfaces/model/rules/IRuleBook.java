package com.whyx.openchess.interfaces.model.rules;

import com.whyx.openchess.interfaces.model.board.ILocation;

import java.util.stream.Stream;

/**
 * @param <T> The type of location the rules in the book can deal with.
 * @author Sam Wykes.
 * Interface representing the rules that a piece must obey in a board game.
 */
public interface IRuleBook<T extends ILocation> {

    /**
     * Get the rules associated with the book.
     *
     * @return {@link Stream} containing {@link IMoveRule} objects.
     */
    Stream<IMoveRule<T>> getRules();

}
