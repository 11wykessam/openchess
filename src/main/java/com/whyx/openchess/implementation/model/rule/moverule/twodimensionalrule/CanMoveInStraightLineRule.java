package com.whyx.openchess.implementation.model.rule.moverule.twodimensionalrule;

import com.whyx.openchess.implementation.model.board.location.TwoDimensionalLocation;
import com.whyx.openchess.interfaces.model.board.IBoard;
import com.whyx.openchess.interfaces.model.board.ICell;
import com.whyx.openchess.interfaces.model.piece.IPiece;
import com.whyx.openchess.interfaces.model.rules.IMoveRule;

/**
 * @author Sam Wykes.
 * Class that represents a rule where a piece can move in straight lines in a 2D plane.
 */
public class CanMoveInStraightLineRule implements IMoveRule<TwoDimensionalLocation> {
    @Override
    public boolean moveConformsToRule(
            final ICell<TwoDimensionalLocation> start,
            final ICell<TwoDimensionalLocation> destination,
            final IPiece<TwoDimensionalLocation> piece,
            final IBoard<TwoDimensionalLocation> board) {
        return start.getLocation().getX() == destination.getLocation().getX() ||
                start.getLocation().getY() == destination.getLocation().getY();
    }
}
