package com.whyx.openchess.interfaces.model.board;

/**
 * @author Sam Wykes
 * Interface representing a cell on a chess board.
 */
public interface ICell {

    /**
     * Get the state of the cell.
     * @return {@link ICellState} object.
     */
    ICellState getState();

    /**
     * Get the x coordinate of the cell.
     * @return int representing x coordinate of the cell.
     */
    int getX();

    /**
     * Get the y coordinate of the cell.
     * @return int representing y coordinate of the cell.
     */
    int getY();

}
