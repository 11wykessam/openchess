package com.whyx.openchess.implementation.model.board;

import com.whyx.openchess.interfaces.model.board.IBoard;
import com.whyx.openchess.interfaces.model.board.IBoardState;
import com.whyx.openchess.interfaces.model.board.ICell;
import com.whyx.openchess.interfaces.model.board.ICellState;
import com.whyx.whyxcommons.collections.ImmutableList;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Sam Wykes.
 * Class responsible for producing chess {@link IBoard} objects.
 */
public class BoardFactory {

    /**
     * Create an empty board of given dimensions.
     *
     * @param width  The width of the board being created.
     * @param height The height of the board being created.
     * @return {@link IBoard} object.
     */
    public IBoard getEmptyBoard(int width, int height) {

        // create list of columns.
        List<ImmutableList<ICell>> columns = new ArrayList<>();
        for (int i = 0; i < width; i++) {
            // create list of cells for column.
            List<ICell> column = new ArrayList<>();
            for (int j = 0; j < height; j++) {
                // create empty cell state.
                ICellState cellState = CellState.builder().build();

                final int x = i;
                final int y = j;

                ICell cell = Cell.builder()
                        .withCellState(cellState)
                        .withXSupplier(() -> x)
                        .withYSupplier(() -> y)
                        .build();
                column.add(cell);
            }
            columns.add(ImmutableList.ofList(column));
        }

        IBoardState boardState = BoardState.builder()
                .withCells(ImmutableList.ofList(columns))
                .build();

        return Board.builder()
                .withBoardState(boardState)
                .build();

    }

}
