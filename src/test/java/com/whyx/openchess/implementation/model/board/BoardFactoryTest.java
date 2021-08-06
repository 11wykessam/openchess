package com.whyx.openchess.implementation.model.board;

import com.whyx.openchess.interfaces.model.board.IBoardFactory;
import com.whyx.openchess.interfaces.model.board.IBoardFactoryContract;

/**
 * @author Sam Wykes.
 * Class used to test the {@link BoardFactory} class.
 */
public class BoardFactoryTest extends IBoardFactoryContract {
    @Override
    protected IBoardFactory createInstance() {
        return new BoardFactory();
    }
}
