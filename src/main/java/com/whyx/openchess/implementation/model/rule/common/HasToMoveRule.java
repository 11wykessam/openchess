package com.whyx.openchess.implementation.model.rule.common;

import com.whyx.openchess.interfaces.model.board.IBoard;
import com.whyx.openchess.interfaces.model.board.ILocation;
import com.whyx.openchess.interfaces.model.piece.IPiece;
import com.whyx.openchess.interfaces.model.rules.IMove;
import com.whyx.openchess.interfaces.model.rules.IRule;

/**
 * @param <T>
 * @author Sam Wykes.
 * Class representing a rule wherein a piece has to move to a different cell.
 */
public class HasToMoveRule<T extends ILocation> implements IRule<T> {
    @Override
    public boolean moveConformsToRule(final IMove<T> move, final IPiece<T> piece, final IBoard<T> board) {
        return !move.getStart().equals(move.getDestination());
    }
}
