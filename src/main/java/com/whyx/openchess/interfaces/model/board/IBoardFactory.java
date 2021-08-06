package com.whyx.openchess.interfaces.model.board;

/**
 * @author Sam Wykes.
 * Factory class responsible for building {@link IBoard} objects.
 */
public interface IBoardFactory {

    /**
     * Create an empty {@link IBoard} object of a given width and height.
     *
     * @param width  Width of board being created.
     * @param height Height of board being created.
     * @return {@link IBoard} object.
     */
    IBoard emptyBoard(int width, int height);

}
