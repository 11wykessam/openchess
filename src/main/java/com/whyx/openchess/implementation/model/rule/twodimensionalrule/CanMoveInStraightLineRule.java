package com.whyx.openchess.implementation.model.rule.twodimensionalrule;

import com.whyx.openchess.implementation.model.board.location.TwoDimensionalLocation;
import com.whyx.openchess.interfaces.model.board.IBoard;
import com.whyx.openchess.interfaces.model.piece.IPiece;
import com.whyx.openchess.interfaces.model.rules.IMove;
import com.whyx.openchess.interfaces.model.rules.IRule;

/**
 * @author Sam Wykes.
 * Class that represents a rule where a piece can move in straight lines in a 2D plane.
 */
public class CanMoveInStraightLineRule implements IRule<TwoDimensionalLocation> {
    @Override
    public boolean moveConformsToRule(
            final IMove<TwoDimensionalLocation> move,
            final IPiece<TwoDimensionalLocation> piece,
            final IBoard<TwoDimensionalLocation> board) {
        return move.getStart().getLocation().getX() == move.getDestination().getLocation().getX() ||
                move.getStart().getLocation().getY() == move.getDestination().getLocation().getY();
    }
}
