package com.whyx.openchess.implementation.model.rule.twodimensionalrule;

import com.whyx.openchess.implementation.model.board.location.TwoDimensionalLocation;
import com.whyx.openchess.interfaces.model.board.IBoard;
import com.whyx.openchess.interfaces.model.piece.IPiece;
import com.whyx.openchess.interfaces.model.rules.IMove;
import com.whyx.openchess.interfaces.model.rules.IRule;

/**
 * @author Sam Wykes.
 * Class representing a rule where pieces in a two-dimensional cell plane can move to adjacent tiles.
 */
public class CanMoveToAdjacentTwoDimensionalLocationRule implements IRule<TwoDimensionalLocation> {

    @Override
    public boolean moveConformsToRule(
            final IMove<TwoDimensionalLocation> move,
            final IPiece<TwoDimensionalLocation> piece,
            final IBoard<TwoDimensionalLocation> board
    ) {
        // check the absolute x and y distances are less than or equal to one.
        final TwoDimensionalLocation startLocation = move.getStart().getLocation();
        final TwoDimensionalLocation destinationLocation = move.getDestination().getLocation();

        return Math.abs(startLocation.getX() - destinationLocation.getX()) <= 1 &&
                Math.abs(startLocation.getY() - destinationLocation.getY()) <= 1;
    }

}
