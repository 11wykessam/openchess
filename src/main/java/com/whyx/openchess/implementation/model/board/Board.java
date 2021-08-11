package com.whyx.openchess.implementation.model.board;

import com.whyx.openchess.implementation.exceptions.CellOutOfBoundsException;
import com.whyx.openchess.implementation.exceptions.InvalidBoardDimensionException;
import com.whyx.openchess.interfaces.common.Builder;
import com.whyx.openchess.interfaces.model.board.IBoard;
import com.whyx.openchess.interfaces.model.board.ICell;
import com.whyx.openchess.interfaces.model.piece.IPiece;
import com.whyx.whyxcommons.collections.ImmutableList;
import com.whyx.whyxcommons.collections.ImmutableListCollector;

import java.util.function.Function;

import static java.util.Objects.requireNonNull;

/**
 * @author Sam Wykes.
 * Class that represents a board in a game of chess.
 */
public class Board implements IBoard {

    private final ImmutableList<ImmutableList<ICell>> cells;
    private final int width;
    private final int height;

    private Board(BoardBuilder boardBuilder) {
        this.cells = boardBuilder.cells;
        this.width = this.cells.size();
        this.height = this.cells.get(0).size();
    }

    /**
     * Get the cells in the board.
     *
     * @return 2D {@link ImmutableList} containing the cells in the board.
     */
    @Override
    public ImmutableList<ImmutableList<ICell>> getCells() {
        return this.cells;
    }

    /**
     * Get the width of the board.
     *
     * @return int.
     */
    @Override
    public int getWidth() {
        return this.width;
    }

    /**
     * Get the height of the board.
     *
     * @return int.
     */
    @Override
    public int getHeight() {
        return this.height;
    }

    /**
     * Checks whether the board contains a piece.
     *
     * @param piece {@link IPiece} being checked for.
     * @return boolean.
     */
    @Override
    public boolean containsPiece(IPiece piece) {
        requireNonNull(piece, "piece must not be null");
        return this.cells.stream()
                .anyMatch(
                        column -> column.stream().anyMatch(
                                cell -> cell.getPiece().isPresent() && cell.getPiece().get().equals(piece))
                );
    }

    /**
     * Place a piece on the board.
     *
     * @param x     x coordinate of cell the piece is being placed on.
     * @param y     y coordinate of cell the piece is being placed on.
     * @param piece {@link IPiece} being placed.
     * @return newly created {@link IBoard} object.
     */
    @Override
    public IBoard placePiece(final int x, final int y, final IPiece piece) {
        // do a check on the coordinates.
        if (x < 0 || x >= this.width || y < 0 || y >= this.height) throw new CellOutOfBoundsException();

        // create new immutable cell list with change made.
        ImmutableList<ImmutableList<ICell>> cells = this.cells.stream().map(
                immutableColumn -> immutableColumn.stream()
                        .map(cellToCellWithPieceConverter(x, y, piece))
                        .collect(new ImmutableListCollector<>())
        ).collect(new ImmutableListCollector<>());

        return Board.builder()
                .withCells(cells)
                .build();
    }

    private Function<ICell, ICell> cellToCellWithPieceConverter(final int x, final int y, final IPiece piece) {
        return cell -> {
            if (cell.getX() == x && cell.getY() == y) return Cell.builder()
                    .withX(() -> x)
                    .withY(() -> y)
                    .withPiece(piece)
                    .build();
            else return cell;
        };
    }

    /**
     * Generate an instance of a builder.
     *
     * @return {@link BoardBuilder} object.
     */
    public static BoardBuilder builder() {
        return new BoardBuilder();
    }

    /**
     * @author Sam Wykes.
     * Builder class responsible for creating {@link Board} objects.
     */
    public static class BoardBuilder implements Builder<IBoard> {

        private ImmutableList<ImmutableList<ICell>> cells;

        /**
         * Build the instance.
         *
         * @return {@link IBoard} object.
         */
        @Override
        public IBoard build() {
            requireNonNull(cells, "cells must not be null");
            return new Board(this);
        }

        /**
         * Provide the builder cells for the board.
         *
         * @param cells 2D {@link ImmutableList}.
         * @return {@link BoardBuilder} object.
         */
        public BoardBuilder withCells(ImmutableList<ImmutableList<ICell>> cells) {
            requireNonNull(cells, "cells must not be null");

            // ensure the cells array is "rectangular".
            if (cells.size() <= 0 || cells.get(0).size() <= 0) throw new InvalidBoardDimensionException();
            for (int i = 1; i < cells.size(); i++) {
                if (cells.get(i).size() != cells.get(i - 1).size()) throw new InvalidBoardDimensionException();
            }

            this.cells = cells;
            return this;
        }
    }
}
