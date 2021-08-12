package com.whyx.openchess.interfaces.model.rules;

import com.whyx.openchess.interfaces.model.board.IBoard;
import com.whyx.openchess.interfaces.model.piece.IPiece;

/**
 * @author Sam Wykes.
 * Interface representing a rule in a board game.
 */
public interface IRule {

    /**
     * Checks whether a given move conforms to the rule.
     * Assumes that the piece is on the start cell and the cells are both on the board.
     *
     * @param move  The {@link IMove} object being made.
     * @param piece The {@link IPiece} object being moved.
     * @param board The {@link IBoard} object the pieces are being moved on.
     * @return boolean.
     */
    boolean moveConformsToRule(IMove move, IPiece piece, IBoard board);

}
