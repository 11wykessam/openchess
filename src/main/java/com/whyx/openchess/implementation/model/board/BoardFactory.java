package com.whyx.openchess.implementation.model.board;

import com.whyx.openchess.implementation.exceptions.InvalidBoardDimensionException;
import com.whyx.openchess.interfaces.model.board.IBoard;
import com.whyx.openchess.interfaces.model.board.IBoardFactory;
import com.whyx.openchess.interfaces.model.board.ICell;
import com.whyx.whyxcommons.collections.ImmutableList;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Sam Wykes.
 * Factory responsible for creating {@link Board} objects.
 */
public class BoardFactory implements IBoardFactory {

    /**
     * Create empty chess board of given dimensions.
     *
     * @param width  Width of board being created.
     * @param height Height of board being created.
     * @return {@link IBoard} object.
     */
    @Override
    public IBoard emptyBoard(int width, int height) {

        // check board dimensions are valid.
        if (width <= 0 || height <= 0) throw new InvalidBoardDimensionException();

        ImmutableList<ImmutableList<ICell>> cells = createEmpty2DCellList(width, height);
        return Board.builder()
                .withCells(cells)
                .build();
    }

    /**
     * Create an empty 2D immutable list containing empty cells.
     *
     * @param width  Width of the 2D list.
     * @param height Heigh of the 2D list.
     * @return 2D {@link ImmutableList} object.
     */
    private ImmutableList<ImmutableList<ICell>> createEmpty2DCellList(int width, int height) {
        List<ImmutableList<ICell>> mutable2DList = new ArrayList<>();
        for (int x = 0; x < width; x++) {
            ImmutableList<ICell> column = createEmptyColumn(height, x);
            mutable2DList.add(column);
        }
        return ImmutableList.ofList(mutable2DList);
    }

    /**
     * Create empty column of cell objects.
     *
     * @param height The height of the column.
     * @param x      The x coordinate of the column.
     * @return {@link ImmutableList} representing the column.
     */
    private ImmutableList<ICell> createEmptyColumn(int height, int x) {
        List<ICell> mutableColumn = new ArrayList<>();
        for (int y = 0; y < height; y++) {
            ICell cell = createEmptyCell(x, y);
            mutableColumn.add(cell);
        }
        return ImmutableList.ofList(mutableColumn);
    }

    /**
     * Create an empty cell.
     *
     * @param x The x coordinate of the cell.
     * @param y The y coordinate of the cell.
     * @return {@link ICell} object.
     */
    private ICell createEmptyCell(final int x, final int y) {
        return Cell.builder()
                .withX(() -> x)
                .withY(() -> y)
                .build();
    }
}
