package com.whyx.openchess.interfaces.model.rules;

import com.whyx.openchess.implementation.model.game.Game;
import com.whyx.openchess.interfaces.model.board.ILocation;
import com.whyx.openchess.interfaces.model.game.IGame;

/**
 * @param <T> The type of location in the game.
 * @author Sam Wykes.
 * Represents a rule that a {@link IGame} is subject to.
 */
public interface IGameRule<T extends ILocation> {

    /**
     * Check whether the rule is satisfied.
     *
     * @param game The {@link Game} following the rule.
     * @return boolean.
     */
    boolean ruleSatisfied(final IGame<T> game);

}
