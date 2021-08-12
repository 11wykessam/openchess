package com.whyx.openchess.implementation.model.board;

import com.whyx.openchess.implementation.exceptions.CellNotFoundException;
import com.whyx.openchess.interfaces.model.board.IBoard;
import com.whyx.openchess.interfaces.model.board.ICell;
import com.whyx.openchess.interfaces.model.piece.IPiece;

import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Objects.requireNonNull;

/**
 * @author Sam Wykes.
 * Class represents a board in a board game.
 */
public class Board implements IBoard {

    private final Set<ICell> cells;

    /**
     * @param boardBuilder the builder responsible for constructing the class.
     */
    private Board(final BoardBuilder boardBuilder) {
        this.cells = boardBuilder.cells;
    }

    /**
     * Get the cells on the board.
     *
     * @return {@link Stream} containing {@link ICell} objects.
     */
    @Override
    public Stream<ICell> getCells() {
        return cells.stream();
    }

    /**
     * Place a given piece on a cell on the board.
     *
     * @param targetCell The {@link ICell} the piece is being placed on.
     * @param piece      The {@link IPiece} being placed.
     * @return {@link IBoard} object created as a result of the piece being placed.
     * @throws CellNotFoundException Thrown if the target cell is not on the board.
     */
    @Override
    public IBoard placePieceOnCell(final ICell targetCell, final IPiece piece) throws CellNotFoundException {
        requireNonNull(targetCell, "targetCell must not be null");
        requireNonNull(piece, "piece must not be null");

        // change the cell that needs the piece placed on it.
        final Set<ICell> alteredCells = this.cells.stream()
                .map(changeCellPiece(targetCell, piece)).collect(Collectors.toSet());

        // check if anything has been changed.
        if (alteredCells.equals(this.cells)) throw new CellNotFoundException();

        // construct the new board.
        return Board.builder()
                .withCells(alteredCells)
                .build();
    }

    /**
     * Returns function that either maps a cell to itself or places a piece on the cell if it matches a given target.
     *
     * @param targetCell The {@link ICell} that is having a piece placed on it.
     * @param piece      The {@link IPiece} object being placed on the cell.
     * @return {@link Function} mapping {@link ICell} objects to {@link ICell} objects.
     */
    private Function<ICell, ICell> changeCellPiece(final ICell targetCell, final IPiece piece) {
        return cell -> cell.equals(targetCell) ?
                Cell.builder()
                        // if the cell is the target construct a new cell with the piece on it.
                        .withLocation(cell.getLocation())
                        .withPiece(piece)
                        .build() :
                // otherwise, map it to itself.
                cell;
    }

    /**
     * Create instance of the builder.
     *
     * @return {@link BoardBuilder} object.
     */
    public static BoardBuilder builder() {
        return new BoardBuilder();
    }

    /**
     * @author Sam Wykes.
     * Class used to create instances of {@link Board}.
     */
    public static class BoardBuilder {

        public Set<ICell> cells;

        public BoardBuilder withCells(final Set<ICell> cells) {
            requireNonNull(cells, "cells must not be null");
            this.cells = cells;
            return this;
        }

        public IBoard build() {
            requireNonNull(cells, "cells must not be null");
            return new Board(this);
        }
    }
}
