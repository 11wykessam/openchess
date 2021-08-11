package com.whyx.openchess.implementation.model.board;

import com.whyx.openchess.implementation.exceptions.InvalidBoardDimensionException;
import com.whyx.openchess.interfaces.model.board.IBoard;
import com.whyx.openchess.interfaces.model.board.IBoardFactory;
import com.whyx.openchess.interfaces.model.board.ICell;
import com.whyx.whyxcommons.collections.ImmutableList;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * @author Sam Wykes.
 * Class used to test the {@link BoardFactory} class.
 */
public class BoardFactoryTest {

    private static final int WIDTH = 8;
    private static final int HEIGHT = 8;

    /**
     * Create an instance of a factory to test.
     *
     * @return {@link IBoardFactory} object.
     */
    protected IBoardFactory createInstance() {
        return new BoardFactory();
    }

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
            IBoard board = createInstance().emptyBoard(WIDTH, HEIGHT);
            assertThat(board.getCells().size()).isEqualTo(WIDTH);
        }

        @Test
        void heightCorrectEmptyBoardTest() {
            IBoard board = createInstance().emptyBoard(WIDTH, HEIGHT);
            for (int i = 0; i < WIDTH; i++) {
                ImmutableList<ICell> column = board.getCells().get(i);
                assertThat(column.size()).isEqualTo(HEIGHT);
            }
        }

        @Test
        void noPiecesEmptyBoardTest() {
            IBoard board = createInstance().emptyBoard(WIDTH, HEIGHT);
            for (int i = 0; i < WIDTH; i++) {
                for (int j = 0; j < HEIGHT; j++) {
                    assertThat(board.getCells().get(i).get(j).getPiece().isEmpty()).isTrue();
                }
            }
        }

    }
}
