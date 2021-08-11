package com.whyx.openchess.implementation.model.board;

import com.whyx.openchess.implementation.exceptions.CellOutOfBoundsException;
import com.whyx.openchess.interfaces.model.board.IBoard;
import com.whyx.openchess.interfaces.model.board.IBoardFactory;
import com.whyx.openchess.interfaces.model.board.ICell;
import com.whyx.openchess.interfaces.model.piece.IPiece;
import com.whyx.whyxcommons.collections.ImmutableList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

/**
 * @author Sam Wykes.
 * Class used to test the {@link Board} class.
 */
@ExtendWith(MockitoExtension.class)
public class BoardTest {

    private static final int WIDTH = 8;
    private static final int HEIGHT = 8;
    private static final int X = 1;
    private static final int Y = 1;

    @Mock
    private ICell cell;

    /**
     * Create board factory responsible for producing {@link IBoard} objects.
     *
     * @return {@link IBoardFactory} object.
     */
    protected IBoardFactory createBoardFactory() {
        return new BoardFactory();
    }

    /**
     * Create empty cells 2D list.
     *
     * @param width  width of the 2D list.
     * @param height height of the 2D list.
     * @return {@link ImmutableList}.
     */
    private ImmutableList<ImmutableList<ICell>> createEmptyCellsList(int width, int height) {
        List<ImmutableList<ICell>> mutableCells = new ArrayList<>();
        for (int i = 0; i < width; i++) {
            final int x = i;

            // create the column.
            List<ICell> mutableColumn = new ArrayList<>();
            for (int j = 0; j < height; j++) {
                final int y = j;

                // create the cell.
                mutableColumn.add(
                        Cell.builder()
                                .withX(() -> x)
                                .withY(() -> y)
                                .build()
                );
            }
            mutableCells.add(ImmutableList.ofList(mutableColumn));

        }
        return ImmutableList.ofList(mutableCells);
    }

    /**
     * Check input parameters are correctly checked for preconditions.
     */
    @Nested
    class Preconditions {

        @Nested
        class BuilderPreconditions {

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

            @Test
            void placePieceNotNullTest() {
                IBoard board = createBoardFactory().emptyBoard(WIDTH, HEIGHT);
                assertThatNullPointerException()
                        .isThrownBy(() -> board.placePiece(X, Y, null))
                        .withMessage("piece must not be null");
            }

        }

        @Nested
        class MethodPreconditions {

            private static final int BOARD_WIDTH = 8;
            private static final int BOARD_HEIGHT = 8;

            // board being tested.
            private IBoard emptyBoard;

            @BeforeEach
            void setup() {
                emptyBoard = createBoardFactory().emptyBoard(BOARD_WIDTH, BOARD_HEIGHT);
            }

            @Test
            void containsPiecePieceNotNullTest() {
                assertThatNullPointerException()
                        .isThrownBy(() -> emptyBoard.containsPiece(null))
                        .withMessage("piece must not be null");
            }

            @Test
            void placePiecePieceNotNullTest() {
                assertThatNullPointerException()
                        .isThrownBy(() -> emptyBoard.placePiece(X, Y, null))
                        .withMessage("piece must not be null");
            }

            @Test
            void negativeCoordinatesOutOfBoundsTest(@Mock final IPiece piece) {
                assertThatThrownBy(() -> emptyBoard.placePiece(-X, -Y, piece))
                        .isExactlyInstanceOf(CellOutOfBoundsException.class)
                        .hasMessage("Cell out of bounds");
            }

        }

    }

    /**
     * Check class is built properly.
     */
    @Nested
    class Build {

        private final ImmutableList<ImmutableList<ICell>> cells = createEmptyCellsList(WIDTH, HEIGHT);

        private IBoard board;

        @BeforeEach
        void setup() {
            board = Board.builder()
                    .withCells(cells)
                    .build();
        }

        @Test
        void builderNotNullTest() {
            assertThat(Board.builder()).isNotNull();
        }

        @Test
        void boardNotNullTest() {
            assertThat(board).isNotNull();
        }

        @Test
        void getBoardTest() {
            assertThat(board).isSameAs(board);
        }

        @Test
        void cellsNotNullTest() {
            assertThat(board.getCells()).isNotNull();
        }

        @Test
        void getCellsTest() {
            assertThat(board.getCells()).isSameAs(cells);
        }

        @Test
        void getWidthTest() {
            assertThat(board.getWidth()).isEqualTo(WIDTH);
        }

        @Test
        void getHeightTests() {
            assertThat(board.getHeight()).isEqualTo(HEIGHT);
        }

    }

    /**
     * Checks functions work as expected.
     */
    @Nested
    class Functionality {

        /**
         * @author Sam Wykes.
         * Tests associated with the containsPiece function.
         */
        @Nested
        class ContainsPieceTests {

            // board being tested.
            private IBoard emptyBoard;

            @BeforeEach
            void setup() {
                emptyBoard = createBoardFactory().emptyBoard(WIDTH, HEIGHT);
            }

            @Test
            void containsPieceReturnsFalseTest(@Mock final IPiece piece) {
                assertThat(emptyBoard.containsPiece(piece)).isFalse();
            }

            @Test
            void containsPieceReturnsTrueTest(@Mock final IPiece piece) {
                IBoard nonEmptyBoard = emptyBoard.placePiece(X, Y, piece);
                assertThat(nonEmptyBoard.containsPiece(piece)).isTrue();
            }
        }

        /**
         * @author Sam Wykes.
         * Tests associated with the placePiece function.
         */
        @Nested
        class PlacePieceTests {

            private IBoardFactory factory;

            @BeforeEach
            void setup() {
                factory = createBoardFactory();
            }

            @Test
            void pieceCorrectlyPlacedTest(@Mock final IPiece piece) {
                IBoard initialBoard = factory.emptyBoard(WIDTH, HEIGHT);
                IBoard updatedBoard = initialBoard.placePiece(X, Y, piece);

                assertThat(updatedBoard.getCells().get(X).get(Y).getPiece().get()).isSameAs(piece);
            }

            @Test
            void cannotPlacePieceLeftOfBoardTest(@Mock final IPiece piece) {
                IBoard initialBoard = factory.emptyBoard(WIDTH, HEIGHT);
                assertThatThrownBy(() -> initialBoard.placePiece(-1, 0, piece))
                        .isExactlyInstanceOf(CellOutOfBoundsException.class)
                        .hasMessage("Cell out of bounds");
            }

            @Test
            void cannotPlacePieceRightOfBoardTest(@Mock final IPiece piece) {
                IBoard initialBoard = factory.emptyBoard(WIDTH, HEIGHT);
                assertThatThrownBy(() -> initialBoard.placePiece(WIDTH, 0, piece))
                        .isExactlyInstanceOf(CellOutOfBoundsException.class)
                        .hasMessage("Cell out of bounds");
            }

            @Test
            void cannotPlacePieceAboveBoardTest(@Mock final IPiece piece) {
                IBoard initialBoard = factory.emptyBoard(WIDTH, HEIGHT);
                assertThatThrownBy(() -> initialBoard.placePiece(0, -1, piece))
                        .isExactlyInstanceOf(CellOutOfBoundsException.class)
                        .hasMessage("Cell out of bounds");
            }

            @Test
            void cannotPlacePieceBelowBoardTest(@Mock final IPiece piece) {
                IBoard initialBoard = factory.emptyBoard(WIDTH, HEIGHT);
                assertThatThrownBy(() -> initialBoard.placePiece(0, HEIGHT, piece))
                        .isExactlyInstanceOf(CellOutOfBoundsException.class)
                        .hasMessage("Cell out of bounds");
            }

        }

    }

}
