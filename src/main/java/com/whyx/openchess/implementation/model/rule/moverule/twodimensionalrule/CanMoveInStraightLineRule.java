package com.whyx.openchess.implementation.model.rule.moverule.twodimensionalrule;

import com.whyx.openchess.implementation.model.board.location.TwoDimensionalLocation;
import com.whyx.openchess.interfaces.model.board.IBoard;
import com.whyx.openchess.interfaces.model.board.ICell;
import com.whyx.openchess.interfaces.model.piece.IPiece;
import com.whyx.openchess.interfaces.model.rules.IMoveRule;

import static java.util.Objects.requireNonNull;

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
        requireNonNull(start, "start must not be null");
        requireNonNull(destination, "destination must not be null");
        requireNonNull(piece, "piece must not be null");
        requireNonNull(board, "board must not be null");

        return start.getLocation().getX() == destination.getLocation().getX() ||
                start.getLocation().getY() == destination.getLocation().getY();
    }
}
