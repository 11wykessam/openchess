package com.whyx.openchess.implementation.model.board;

import com.whyx.openchess.implementation.exceptions.CellNotFoundException;
import com.whyx.openchess.interfaces.model.board.IBoard;
import com.whyx.openchess.interfaces.model.board.ICell;
import com.whyx.openchess.interfaces.model.board.ILocation;
import com.whyx.openchess.interfaces.model.piece.IPiece;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Set;
import java.util.stream.Collectors;

import static com.whyx.openchess.implementation.model.board.Board.BoardBuilder;
import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assumptions.assumeThat;

/**
 * @author Sam Wykes.
 * Class used to test the {@link Board} class.
 */
@ExtendWith(MockitoExtension.class)
public class BoardTest {

    /**
     * @author Sam Wykes.
     * Class used to test preconditions for the {@link Board} class.
     */
    @Nested
    class Preconditions {

        @Nested
        class BuildPreconditions {

            private BoardBuilder<ILocation> builder;

            @BeforeEach
            void setup() {
                builder = Board.builder();
                assumeThat(builder).isNotNull();
            }

            @Test
            void cellsNotNullTest() {
                assertThatNullPointerException()
                        .isThrownBy(() -> builder.withCells(null))
                        .withMessage("cells must not be null");
            }

            @Test
            void cellsMustBePresentTest() {
                assertThatNullPointerException()
                        .isThrownBy(() -> builder.build())
                        .withMessage("cells must not be null");
            }

        }

        @Nested
        class MethodPreconditions {

            @Mock
            private ICell<ILocation> cell;
            @Mock
            private IPiece<ILocation> piece;

            private IBoard<ILocation> board;

            @BeforeEach
            void setup() {
                board = Board.builder()
                        .withCells(Set.of(cell))
                        .build();
            }

            @Test
            void placePieceCellNotNullTest() {
                assertThatNullPointerException()
                        .isThrownBy(() -> board.placePieceOnCell(null, piece))
                        .withMessage("targetCell must not be null");
            }

            @Test
            void placePiecePieceNotNullTest() {
                assertThatNullPointerException()
                        .isThrownBy(() -> board.placePieceOnCell(cell, null))
                        .withMessage("piece must not be null");
            }

            @Test
            void placePieceCellMustBeOnBoardTest(@Mock final ICell<ILocation> wrongCell, @Mock final IPiece<ILocation> piece) {
                assertThatThrownBy(
                        () -> board.placePieceOnCell(wrongCell, piece)
                )
                        .isExactlyInstanceOf(CellNotFoundException.class)
                        .hasMessage("cell not found");
            }

            @Test
            void containsCellCellNotNullTest() {
                assertThatNullPointerException()
                        .isThrownBy(() -> board.containsCell(null))
                        .withMessage("cell must not be null");
            }

        }

    }

    /**
     * @author Sam Wykes.
     * Class used to check the {@link Board} class is built properly.
     */
    @Nested
    class Build {

        @Mock
        private ICell<ILocation> cellOne;
        @Mock
        private ICell<ILocation> cellTwo;

        private IBoard<ILocation> board;

        @BeforeEach
        void setup() {
            board = Board.builder()
                    .withCells(Set.of(cellOne, cellTwo))
                    .build();
        }

        @Test
        void cellsNotNullTest() {
            assertThat(board.getCells()).isNotNull();
        }

        @Test
        void getCellsTest() {
            final Set<ICell<ILocation>> cells = board.getCells().collect(Collectors.toSet());
            assertThat(cells).isEqualTo(Set.of(cellOne, cellTwo));
        }

    }

    /**
     * @author Sam Wykes.
     * Class used to test {@link Board} class' functionality.
     */
    @Nested
    class Functionality {

        @Test
        void placePieceChangesBoardTest(
                @Mock final IPiece<ILocation> piece,
                @Mock final ILocation locationOne,
                @Mock final ILocation locationTwo
        ) {
            final ICell<ILocation> cellOne = Cell.builder()
                    .withLocation(locationOne)
                    .build();
            final ICell<ILocation> cellTwo = Cell.builder()
                    .withLocation(locationTwo)
                    .build();
            final IBoard<ILocation> board = Board.builder()
                    .withCells(Set.of(cellOne, cellTwo))
                    .build();

            final IBoard<ILocation> altered = board.placePieceOnCell(cellOne, piece);

            assertThat(altered).isNotEqualTo(board);
        }

        @Test
        void placePieceBoardNotNullTest(
                @Mock final IPiece<ILocation> piece,
                @Mock final ILocation locationOne,
                @Mock final ILocation locationTwo
        ) {
            final ICell<ILocation> cellOne = Cell.builder()
                    .withLocation(locationOne)
                    .build();
            final ICell<ILocation> cellTwo = Cell.builder()
                    .withLocation(locationTwo)
                    .build();
            final IBoard<ILocation> board = Board.builder()
                    .withCells(Set.of(cellOne, cellTwo))
                    .build();

            final IBoard<ILocation> altered = board.placePieceOnCell(cellOne, piece);

            assertThat(altered).isNotNull();
        }

        @Test
        void containsCellReturnsTrueTest(@Mock final ICell<ILocation> cell) {
            final IBoard<ILocation> board = Board.builder()
                    .withCells(Set.of(cell))
                    .build();
            assertThat(board.containsCell(cell)).isTrue();
        }

        @Test
        void containsCellReturnsFalseNoCellsTest(@Mock final ICell<ILocation> cell) {
            final IBoard<ILocation> board = Board.builder()
                    .withCells(Set.of())
                    .build();
            assertThat(board.containsCell(cell)).isFalse();
        }

        @Test
        void containsCellReturnsFalseWithCellsTest(@Mock final ICell<ILocation> cell, @Mock final ICell<ILocation> wrongCell) {
            final IBoard<ILocation> board = Board.builder()
                    .withCells(Set.of(cell))
                    .build();
            assertThat(board.containsCell(wrongCell)).isFalse();
        }

    }

}
