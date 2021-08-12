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

            private BoardBuilder builder;

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
            private ICell cell;
            @Mock
            private IPiece piece;

            private IBoard board;

            @BeforeEach
            void setup() {
                board = Board.builder()
                        .withCells(Set.of(cell))
                        .build();
            }

            @Test
            void placePieceCellNotNullTest() {
                assertThatNullPointerException()
                        .isThrownBy(() -> board.placeOnCell(null, piece))
                        .withMessage("targetCell must not be null");
            }

            @Test
            void placePiecePieceNotNullTest() {
                assertThatNullPointerException()
                        .isThrownBy(() -> board.placeOnCell(cell, null))
                        .withMessage("piece must not be null");
            }

            @Test
            void placePieceCellMustBeOnBoard(@Mock final ICell wrongCell, @Mock final IPiece piece) {
                assertThatThrownBy(
                        () -> board.placeOnCell(wrongCell, piece)
                )
                        .isExactlyInstanceOf(CellNotFoundException.class)
                        .hasMessage("cell not found");
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
        private ICell cellOne;
        @Mock
        private ICell cellTwo;

        private IBoard board;

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
            final Set<ICell> cells = board.getCells().collect(Collectors.toSet());
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
                @Mock final IPiece piece,
                @Mock final ILocation locationOne,
                @Mock final ILocation locationTwo
        ) {
            final ICell cellOne = Cell.builder()
                    .withLocation(locationOne)
                    .build();
            final ICell cellTwo = Cell.builder()
                    .withLocation(locationTwo)
                    .build();
            final IBoard board = Board.builder()
                    .withCells(Set.of(cellOne, cellTwo))
                    .build();

            final IBoard altered = board.placeOnCell(cellOne, piece);

            assertThat(altered).isNotEqualTo(board);
        }

        @Test
        void placePieceBoardNotNullTest(
                @Mock final IPiece piece,
                @Mock final ILocation locationOne,
                @Mock final ILocation locationTwo
        ) {
            final ICell cellOne = Cell.builder()
                    .withLocation(locationOne)
                    .build();
            final ICell cellTwo = Cell.builder()
                    .withLocation(locationTwo)
                    .build();
            final IBoard board = Board.builder()
                    .withCells(Set.of(cellOne, cellTwo))
                    .build();

            final IBoard altered = board.placeOnCell(cellOne, piece);

            assertThat(altered).isNotNull();
        }

    }

}
