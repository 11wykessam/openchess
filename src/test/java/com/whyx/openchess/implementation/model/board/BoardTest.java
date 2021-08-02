package com.whyx.openchess.implementation.model.board;

import com.whyx.openchess.interfaces.model.board.IBoard;
import com.whyx.openchess.interfaces.model.board.IBoardContract;
import com.whyx.openchess.interfaces.model.board.ICell;
import com.whyx.whyxcommons.collections.ImmutableList;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNullPointerException;

/**
 * @author Sam Wykes.
 * Class used to test the {@link Board} class.
 */
@ExtendWith(MockitoExtension.class)
public class BoardTest extends IBoardContract {

    // mock attributes.
    @Mock
    private ImmutableList<ImmutableList<ICell>> cells;

    /**
     * Check input parameters are correctly checked for preconditions.
     */
    @Nested
    class Preconditions {

        @Test
        void cellsNotNullTest() {
            assertThatNullPointerException()
                    .isThrownBy(() -> Board.builder()
                            .withCells(null))
                    .withMessage("cells must not be null");
        }

        @Test
        void cellsMustBePresentTest() {
            assertThatNullPointerException()
                    .isThrownBy(() -> Board.builder()
                            .build())
                    .withMessage("cells must not be null");
        }

    }

    /**
     * Check class is built properly.
     */
    @Nested
    class Build {

        @Test
        void builderNotNullTest() {
            assertThat(Board.builder()).isNotNull();
        }

        @Test
        void boardNotNullTest() {
            IBoard board = Board.builder()
                    .withCells(cells)
                    .build();
            assertThat(board).isNotNull();
        }

        @Test
        void getBoardTest() {
            IBoard board = Board.builder()
                    .withCells(cells)
                    .build();
            assertThat(board).isSameAs(board);
        }

    }

}
