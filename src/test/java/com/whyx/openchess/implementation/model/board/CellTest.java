package com.whyx.openchess.implementation.model.board;

import com.whyx.openchess.interfaces.model.board.ICellBuilder;
import com.whyx.openchess.interfaces.model.board.ICellContract;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * @author Sam Wykes.
 * Class used to test the {@link Cell} class.
 */
@ExtendWith(MockitoExtension.class)
public class CellTest extends ICellContract {

    @Override
    public ICellBuilder createBuilder() {
        return Cell.builder();
    }

}
