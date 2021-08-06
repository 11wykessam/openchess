package com.whyx.openchess.implementation.model.board;

import com.whyx.openchess.implementation.exceptions.CellOutOfBoundsException;
import com.whyx.openchess.interfaces.model.board.IBoard;
import com.whyx.openchess.interfaces.model.board.IBoardContract;
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

import java.util.List;

import static org.assertj.core.api.Assertions.*;

/**
 * @author Sam Wykes.
 * Class used to test the {@link Board} class.
 */
@ExtendWith(MockitoExtension.class)
public class BoardTest extends IBoardContract {

    private static final int DEFAULT_WIDTH = 8;
    private static final int DEFAULT_HEIGHT = 8;
    private static final int X = 1;
    private static final int Y = 1;

    @Mock
    private ICell cell;

    @Override
    protected IBoardFactory createBoardFactory() {
        return new BoardFactory();
    }

    private ImmutableList<ImmutableList<ICell>> createEmptyCellsList() {
        ImmutableList<ICell> column = ImmutableList.ofList(List.of(cell));
        return ImmutableList.ofList(List.of(column));
    }

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

        @Test
        void placePieceNotNullTest() {
            IBoard board = createBoardFactory().emptyBoard(DEFAULT_WIDTH, DEFAULT_HEIGHT);
            assertThatNullPointerException()
                    .isThrownBy(() -> board.placePiece(X, Y, null))
                    .withMessage("piece must not be null");
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
                    .withCells(createEmptyCellsList())
                    .build();
            assertThat(board).isNotNull();
        }

        @Test
        void getBoardTest() {
            IBoard board = Board.builder()
                    .withCells(createEmptyCellsList())
                    .build();
            assertThat(board).isSameAs(board);
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
            void cellsNotNullTest() {
                IBoard board = Board.builder()
                        .withCells(createEmptyCellsList())
                        .build();
                assertThat(board.getCells()).isNotNull();
            }

            @Test
            void getCellsTest() {
                ImmutableList<ImmutableList<ICell>> cells = createEmptyCellsList();
                IBoard board = Board.builder()
                        .withCells(cells)
                        .build();
                assertThat(board.getCells()).isSameAs(cells);
            }

            @Test
            void getWidthTest() {
                IBoard board = createBoardFactory().emptyBoard(DEFAULT_WIDTH, DEFAULT_HEIGHT);
                assertThat(board.getWidth()).isEqualTo(DEFAULT_WIDTH);
            }

            @Test
            void getHeightTest() {
                IBoard board = createBoardFactory().emptyBoard(DEFAULT_WIDTH, DEFAULT_HEIGHT);
                assertThat(board.getHeight()).isEqualTo(DEFAULT_HEIGHT);
            }

            @Test
            void pieceCorrectlyPlacedTest(@Mock final IPiece piece) {
                IBoard initialBoard = factory.emptyBoard(DEFAULT_WIDTH, DEFAULT_HEIGHT);
                IBoard updatedBoard = initialBoard.placePiece(X, Y, piece);

                assertThat(updatedBoard.getCells().get(X).get(Y).getPiece().get()).isSameAs(piece);
            }

            @Test
            void cannotPlacePieceLeftOfBoardTest(@Mock final IPiece piece) {
                IBoard initialBoard = factory.emptyBoard(DEFAULT_WIDTH, DEFAULT_HEIGHT);
                assertThatThrownBy(() -> initialBoard.placePiece(-1, 0, piece))
                        .isExactlyInstanceOf(CellOutOfBoundsException.class)
                        .hasMessage("Cell out of bounds");
            }

            @Test
            void cannotPlacePieceRightOfBoardTest(@Mock final IPiece piece) {
                IBoard initialBoard = factory.emptyBoard(DEFAULT_WIDTH, DEFAULT_HEIGHT);
                assertThatThrownBy(() -> initialBoard.placePiece(DEFAULT_WIDTH, 0, piece))
                        .isExactlyInstanceOf(CellOutOfBoundsException.class)
                        .hasMessage("Cell out of bounds");
            }

            @Test
            void cannotPlacePieceAboveBoardTest(@Mock final IPiece piece) {
                IBoard initialBoard = factory.emptyBoard(DEFAULT_WIDTH, DEFAULT_HEIGHT);
                assertThatThrownBy(() -> initialBoard.placePiece(0, -1, piece))
                        .isExactlyInstanceOf(CellOutOfBoundsException.class)
                        .hasMessage("Cell out of bounds");
            }

            @Test
            void cannotPlacePieceBelowBoardTest(@Mock final IPiece piece) {
                IBoard initialBoard = factory.emptyBoard(DEFAULT_WIDTH, DEFAULT_HEIGHT);
                assertThatThrownBy(() -> initialBoard.placePiece(0, DEFAULT_HEIGHT, piece))
                        .isExactlyInstanceOf(CellOutOfBoundsException.class)
                        .hasMessage("Cell out of bounds");
            }

        }

    }

}
