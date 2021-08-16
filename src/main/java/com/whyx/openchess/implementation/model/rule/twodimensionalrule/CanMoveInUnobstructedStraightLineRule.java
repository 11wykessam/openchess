package com.whyx.openchess.implementation.model.rule.twodimensionalrule;

import com.whyx.openchess.implementation.model.board.location.TwoDimensionalLocation;
import com.whyx.openchess.interfaces.model.board.IBoard;
import com.whyx.openchess.interfaces.model.board.ICell;
import com.whyx.openchess.interfaces.model.piece.IPiece;
import com.whyx.openchess.interfaces.model.rules.IMove;

import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author Sam Wykes.
 * Class represents a rule where pieces can move in straight lines and that those lines do not contain any obstructions.
 * Obstructions consist of missing cells or pieces.
 */
public class CanMoveInUnobstructedStraightLineRule extends CanMoveInStraightLineRule {
    @Override
    public boolean moveConformsToRule(
            final IMove<TwoDimensionalLocation> move,
            final IPiece<TwoDimensionalLocation> piece,
            final IBoard<TwoDimensionalLocation> board) {
        if (!(super.moveConformsToRule(move, piece, board))) return false;

        // check whether the destination is in the same column (or row).
        final boolean sameColumn = move.getStart().getLocation().getX() == move.getDestination().getLocation().getX();

        // decide based on the boolean which coordinate is remaining constant or changing in the row, or column.
        final Function<ICell<TwoDimensionalLocation>, Integer> getConstantCoordinate = sameColumn ?
                cell -> cell.getLocation().getX() :
                cell -> cell.getLocation().getY();
        final Function<ICell<TwoDimensionalLocation>, Integer> getChangingCoordinate = sameColumn ?
                cell -> cell.getLocation().getY() :
                cell -> cell.getLocation().getX();

        // get a set of all the cells between the start and destination.
        final Set<ICell<TwoDimensionalLocation>> intermediateCells = getPiecesBetweenStartAndDestination(
                move, board, getConstantCoordinate, getChangingCoordinate
        );

        // calculate the number of cells expected between the two.
        final int distanceBetweenStartAndDestination =
                Math.abs(getChangingCoordinate.apply(move.getStart()) - getChangingCoordinate.apply(move.getDestination())) - 1;

        return intermediateCells.size() == distanceBetweenStartAndDestination &&
                intermediateCells.stream().noneMatch(cell -> cell.getPiece().isPresent());
    }

    /**
     * Get the set of pieces belonging in the cells between the start and the destination coordinate.
     *
     * @param move                  The move being made.
     * @param board                 The board the move is being made on.
     * @param getConstantCoordinate Function that gets the constant coordinate, this is x for a column, and y for a row.
     * @param getChangingCoordinate Function that gets the changing coordinate.
     * @return
     */
    private Set<ICell<TwoDimensionalLocation>> getPiecesBetweenStartAndDestination(
            final IMove<TwoDimensionalLocation> move,
            final IBoard<TwoDimensionalLocation> board,
            final Function<ICell<TwoDimensionalLocation>, Integer> getConstantCoordinate,
            final Function<ICell<TwoDimensionalLocation>, Integer> getChangingCoordinate
    ) {
        // calculate the bounds the coordinates must br within.
        final int smallerCoordinate = Math.min(
                getChangingCoordinate.apply(move.getStart()),
                getChangingCoordinate.apply(move.getDestination())
        );
        final int largerCoordinate = Math.max(
                getChangingCoordinate.apply(move.getStart()),
                getChangingCoordinate.apply(move.getDestination())
        );

        // filter the board for the appropriate cells.
        return board.getCells()
                .filter(cell -> getConstantCoordinate.apply(cell).equals(getConstantCoordinate.apply(move.getStart())))
                .filter(cell -> getChangingCoordinate.apply(cell) > smallerCoordinate)
                .filter(cell -> getChangingCoordinate.apply(cell) < largerCoordinate)
                .collect(Collectors.toSet());
    }
}
