package com.whyx.openchess.implementation.model.rule.moverule.twodimensionalrule;

import com.whyx.openchess.implementation.model.board.location.TwoDimensionalLocation;
import com.whyx.openchess.interfaces.model.board.IBoard;
import com.whyx.openchess.interfaces.model.board.ICell;
import com.whyx.openchess.interfaces.model.piece.IPiece;

import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.Objects.requireNonNull;

/**
 * @author Sam Wykes.
 * Class represents a rule where pieces can move in straight lines and that those lines do not contain any obstructions.
 * Obstructions consist of missing cells or pieces.
 */
public class CanMoveInUnobstructedStraightLineRule extends CanMoveInStraightLineRule {
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

        if (!(super.moveConformsToRule(start, destination, piece, board))) return false;

        // check whether the destination is in the same column (or row).
        final boolean sameColumn = start.getLocation().getX() == destination.getLocation().getX();

        // decide based on the boolean which coordinate is remaining constant or changing in the row, or column.
        final Function<ICell<TwoDimensionalLocation>, Integer> getConstantCoordinate = sameColumn ?
                cell -> cell.getLocation().getX() :
                cell -> cell.getLocation().getY();
        final Function<ICell<TwoDimensionalLocation>, Integer> getChangingCoordinate = sameColumn ?
                cell -> cell.getLocation().getY() :
                cell -> cell.getLocation().getX();

        // get a set of all the cells between the start and destination.
        final Set<ICell<TwoDimensionalLocation>> intermediateCells = getPiecesBetweenStartAndDestination(
                start, destination, board, getConstantCoordinate, getChangingCoordinate
        );

        // calculate the number of cells expected between the two.
        final int distanceBetweenStartAndDestination =
                Math.abs(getChangingCoordinate.apply(start) - getChangingCoordinate.apply(destination)) - 1;

        return intermediateCells.size() == distanceBetweenStartAndDestination &&
                intermediateCells.stream().noneMatch(cell -> cell.getPiece().isPresent());
    }

    /**
     * Get the set of pieces belonging in the cells between the start and the destination coordinate.
     *
     * @param start                 The cell the move is being made from.
     * @param destination           The destination the move is being made to.
     * @param board                 The board the move is being made on.
     * @param getConstantCoordinate Function that gets the constant coordinate, this is x for a column, and y for a row.
     * @param getChangingCoordinate Function that gets the changing coordinate.
     * @return
     */
    private Set<ICell<TwoDimensionalLocation>> getPiecesBetweenStartAndDestination(
            final ICell<TwoDimensionalLocation> start,
            final ICell<TwoDimensionalLocation> destination,
            final IBoard<TwoDimensionalLocation> board,
            final Function<ICell<TwoDimensionalLocation>, Integer> getConstantCoordinate,
            final Function<ICell<TwoDimensionalLocation>, Integer> getChangingCoordinate
    ) {
        // calculate the bounds the coordinates must br within.
        final int smallerCoordinate = Math.min(
                getChangingCoordinate.apply(start),
                getChangingCoordinate.apply(destination)
        );
        final int largerCoordinate = Math.max(
                getChangingCoordinate.apply(start),
                getChangingCoordinate.apply(destination)
        );

        // filter the board for the appropriate cells.
        return board.getCells()
                .filter(cell -> getConstantCoordinate.apply(cell).equals(getConstantCoordinate.apply(start)))
                .filter(cell -> getChangingCoordinate.apply(cell) > smallerCoordinate)
                .filter(cell -> getChangingCoordinate.apply(cell) < largerCoordinate)
                .collect(Collectors.toSet());
    }
}
