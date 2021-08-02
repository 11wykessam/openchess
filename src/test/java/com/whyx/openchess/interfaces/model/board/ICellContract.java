package com.whyx.openchess.interfaces.model.board;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * @author Sam Wykes.
 * Contract for how the {@link ICell} interface should behave.
 */
@ExtendWith(MockitoExtension.class)
public abstract class ICellContract {

    /**
     * Check input parameters are correctly checked for preconditons.
     */
    @Nested
    class Preconditions {

    }

    /**
     * Check class is built properly.
     */
    @Nested
    class Build {

    }

}
