package com.whyx.openchess.interfaces.model.piece;

import com.whyx.openchess.interfaces.model.board.ILocation;
import com.whyx.openchess.interfaces.model.rules.IRuleBook;

/**
 * @param <T> The type of location being used in the board game.
 * @author Sam Wykes.
 * Interface representing a piece in a board game.
 */
public interface IPiece<T extends ILocation> {

    /**
     * Get the rule book associated with the piece.
     *
     * @return {@link IRuleBook} object.
     */
    IRuleBook<T> getRuleBook();

    /**
     * Get the team associated with the given piece.
     *
     * @return {@link IPieceTeam} object.
     */
    IPieceTeam getTeam();

}
