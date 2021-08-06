package com.whyx.openchess.interfaces.model.board;

import com.whyx.openchess.implementation.exceptions.InvalidBoardDimensionException;
import com.whyx.whyxcommons.collections.ImmutableList;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * @author Sam Wykes.
 * Contract for how the {@link IBoardFactory} interface should behave.
 */
@ExtendWith(MockitoExtension.class)
public abstract class IBoardFactoryContract {

    private static final int DEFAULT_WIDTH = 8;
    private static final int DEFAULT_HEIGHT = 8;

    /**
     * Create an instance of {@link IBoardFactory} to test.
     *
     * @return {@link IBoardFactory} object.
     */
    protected abstract IBoardFactory createInstance();

    /**
     * @author Sam Wykes.
     * Check that the preconditions of {@link IBoardFactory} class' methods are satisfied.
     */
    @Nested
    class Preconditions {

        @Test
        void widthNotNegativeEmptyBoardTest() {
            assertThatThrownBy(() -> createInstance().emptyBoard(-1, 1))
                    .isExactlyInstanceOf(InvalidBoardDimensionException.class)
                    .hasMessage("Board dimensions invalid");
        }

        @Test
        void heightNotNegativeEmptyBoardTest() {
            assertThatThrownBy(() -> createInstance().emptyBoard(1, -1))
                    .isExactlyInstanceOf(InvalidBoardDimensionException.class)
                    .hasMessage("Board dimensions invalid");
        }

        @Test
        void widthNotZeroEmptyBoardTest() {
            assertThatThrownBy(() -> createInstance().emptyBoard(0, 1))
                    .isExactlyInstanceOf(InvalidBoardDimensionException.class)
                    .hasMessage("Board dimensions invalid");
        }

        @Test
        void heightNotZeroEmptyBoardTest() {
            assertThatThrownBy(() -> createInstance().emptyBoard(1, 0))
                    .isExactlyInstanceOf(InvalidBoardDimensionException.class)
                    .hasMessage("Board dimensions invalid");
        }

    }

    /**
     * Check the class behaves as expected.
     */
    @Nested
    class Build {

        @Test
        void widthCorrectEmptyBoardTest() {
            IBoard board = createInstance().emptyBoard(DEFAULT_WIDTH, DEFAULT_HEIGHT);
            assertThat(board.getCells().size()).isEqualTo(DEFAULT_WIDTH);
        }

        @Test
        void heightCorrectEmptyBoardTest() {
            IBoard board = createInstance().emptyBoard(DEFAULT_WIDTH, DEFAULT_HEIGHT);
            for (int i = 0; i < DEFAULT_WIDTH; i++) {
                ImmutableList<ICell> column = board.getCells().get(i);
                assertThat(column.size()).isEqualTo(DEFAULT_HEIGHT);
            }
        }

        @Test
        void noPiecesEmptyBoardTest() {
            IBoard board = createInstance().emptyBoard(DEFAULT_WIDTH, DEFAULT_HEIGHT);
            for (int i = 0; i < DEFAULT_WIDTH; i++) {
                for (int j = 0; j < DEFAULT_HEIGHT; j++) {
                    assertThat(board.getCells().get(i).get(j).getPiece().isEmpty()).isTrue();
                }
            }
        }

    }

}
