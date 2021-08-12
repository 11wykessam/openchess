package com.whyx.openchess.interfaces.model.piece;

import com.whyx.openchess.interfaces.model.rules.IRule;

import java.util.stream.Stream;

/**
 * @author Sam Wykes.
 * Interface representing the rules that a piece must obey in a board game.
 */
public interface IPieceRuleBook {

    /**
     * Get the rules associated with the book.
     *
     * @return {@link Stream} containing {@link IRule} objects.
     */
    Stream<IRule> getRules();

}
