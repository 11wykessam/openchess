package com.whyx.openchess.implementation.model.rule.moverule.twodimensionalrule;

import com.whyx.openchess.implementation.model.board.location.TwoDimensionalLocation;
import com.whyx.openchess.interfaces.model.board.IBoard;
import com.whyx.openchess.interfaces.model.board.ICell;
import com.whyx.openchess.interfaces.model.piece.IPiece;
import com.whyx.openchess.interfaces.model.rules.IMoveRule;

import static java.util.Objects.requireNonNull;

/**
 * @author Sam Wykes.
 * Class representing a rule where pieces in a two-dimensional cell plane can move to adjacent tiles.
 */
public class CanMoveToAdjacentTwoDimensionalLocationRule implements IMoveRule<TwoDimensionalLocation> {

    @Override
    public boolean moveConformsToRule(
            final ICell<TwoDimensionalLocation> start,
            final ICell<TwoDimensionalLocation> destination,
            final IPiece<TwoDimensionalLocation> piece,
            final IBoard<TwoDimensionalLocation> board
    ) {
        requireNonNull(start, "start must not be null");
        requireNonNull(destination, "destination must not be null");
        requireNonNull(piece, "piece must not be null");
        requireNonNull(board, "board must not be null");

        // check the absolute x and y distances are less than or equal to one.
        final TwoDimensionalLocation startLocation = start.getLocation();
        final TwoDimensionalLocation destinationLocation = destination.getLocation();

        return Math.abs(startLocation.getX() - destinationLocation.getX()) <= 1 &&
                Math.abs(startLocation.getY() - destinationLocation.getY()) <= 1;
    }

}
