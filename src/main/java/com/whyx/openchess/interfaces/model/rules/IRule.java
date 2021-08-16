package com.whyx.openchess.interfaces.model.rules;

import com.whyx.openchess.interfaces.model.board.IBoard;
import com.whyx.openchess.interfaces.model.board.ILocation;
import com.whyx.openchess.interfaces.model.piece.IPiece;

/**
 * @param <T> The type of location the rule can deal with.
 * @author Sam Wykes.
 * Interface representing a rule in a board game.
 */
public interface IRule<T extends ILocation> {

    /**
     * Checks whether a given move conforms to the rule.
     * Assumes that the piece is on the start cell and the cells are both on the board.
     *
     * @param move  The {@link IMove} object being made.
     * @param piece The {@link IPiece} object being moved.
     * @param board The {@link IBoard} object the pieces are being moved on.
     * @return boolean.
     */
    boolean moveConformsToRule(IMove<T> move, IPiece<T> piece, IBoard<T> board);

}
