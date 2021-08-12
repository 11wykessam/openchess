package com.whyx.openchess.interfaces.model.rules;

import com.whyx.openchess.implementation.exceptions.CellNotFoundException;
import com.whyx.openchess.implementation.exceptions.PieceNotFoundException;
import com.whyx.openchess.interfaces.model.board.IBoard;
import com.whyx.openchess.interfaces.model.piece.IPiece;

/**
 * @author Sam Wykes.
 * Interface representing a rule in a board game.
 */
public interface IRule {

    /**
     * Checks whether a given move conforms to the rule.
     *
     * @param move  The {@link IMove} object being made.
     * @param piece The {@link IPiece} object being moved.
     * @param board The {@link IBoard} object the pieces are being moved on.
     * @return boolean.
     * @throws PieceNotFoundException thrown if the piece is not found on the start cell.
     * @throws CellNotFoundException  thrown if the start or end cell not found on the board.
     */
    boolean moveConformsToRule(IMove move, IPiece piece, IBoard board) throws PieceNotFoundException, CellNotFoundException;

}
