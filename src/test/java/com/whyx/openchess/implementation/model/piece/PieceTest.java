package com.whyx.openchess.implementation.model.piece;

import com.whyx.openchess.implementation.model.piece.Piece.PieceBuilder;
import com.whyx.openchess.interfaces.model.board.IBoard;
import com.whyx.openchess.interfaces.model.board.ICell;
import com.whyx.openchess.interfaces.model.piece.IPiece;
import com.whyx.openchess.interfaces.model.piece.PieceColour;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNullPointerException;
import static org.assertj.core.api.Assumptions.assumeThat;
import static org.mockito.Mockito.when;

/**
 * @author Sam Wykes.
 * Class used to test the abstract class {@link Piece}
 */
@ExtendWith(MockitoExtension.class)
public abstract class PieceTest {

    /**
     * Generate a {@link PieceBuilder} object to produce {@link Piece} objects to test.
     *
     * @return {@link PieceBuilder} object.
     */
    protected abstract PieceBuilder createBuilder();

    /**
     * Generate a {@link IPiece} object to test.
     *
     * @return {@link IPiece} object.
     */
    protected abstract IPiece createPiece();

    /**
     * Check input parameters are correctly checked for preconditions.
     */
    @Nested
    class Preconditions {

        @Nested
        class BuilderPreconditions {

            private PieceBuilder builder;

            @BeforeEach
            void setup() {
                builder = createBuilder();
                assumeThat(builder).isNotNull();
            }

            @Test
            void colourNotNullTest() {
                assertThatNullPointerException()
                        .isThrownBy(() -> builder.withColour(null))
                        .withMessage("colour must not be null");
            }

            @Test
            void colourMustBePresentTest() {
                assertThatNullPointerException()
                        .isThrownBy(() -> builder.build())
                        .withMessage("colour must not be null");
            }

        }

        @Nested
        class MethodPreconditions {

            private IPiece piece;

            @BeforeEach
            void setup() {
                piece = createPiece();
                assumeThat(piece).isNotNull();
            }

            @Test
            void isMoveLegalBoardNotNullTest(@Mock final ICell start, @Mock final ICell destination) {
                assertThatNullPointerException()
                        .isThrownBy(() -> piece.isMoveLegal(null, start, destination))
                        .withMessage("board must not be null");
            }

            @Test
            void isMoveLegalStartNotNullTest(@Mock final IBoard board, @Mock final ICell destination) {
                assertThatNullPointerException()
                        .isThrownBy(() -> piece.isMoveLegal(board, null, destination))
                        .withMessage("start must not be null");
            }

            @Test
            void isMoveLegalDestinationNotNullTest(@Mock final IBoard board, @Mock final ICell start) {
                assertThatNullPointerException()
                        .isThrownBy(() -> piece.isMoveLegal(board, start, null))
                        .withMessage("destination must not be null");
            }

        }

    }

    /**
     * Check class is built properly.
     */
    @Nested
    class Build {

        @Mock
        private PieceColour colour;

        private IPiece piece;

        @BeforeEach
        void setup() {
            piece = createBuilder()
                    .withColour(colour)
                    .build();
        }

        @Test
        void colourNotNullTest() {
            assertThat(piece.getColour()).isNotNull();
        }

        @Test
        void getColourTest() {
            assertThat(piece.getColour()).isSameAs(colour);
        }

        @Nested
        class IsMoveLegal {

            @Test
            void pieceMustBeOnStartCell(
                    @Mock final IPiece wrongPiece,
                    @Mock final IBoard board,
                    @Mock final ICell start,
                    @Mock final ICell destination
            ) {
                Optional<IPiece> wrongPieceOptional = Optional.ofNullable(wrongPiece);
                when(start.getPiece()).thenReturn(wrongPieceOptional);

                assertThat(piece.isMoveLegal(board, start, destination)).isFalse();
            }

            @Test
            void startCellMustBeOnBoard(
                    @Mock final IBoard board,
                    @Mock final ICell start,
                    @Mock final ICell destination
            ) {

            }

        }

    }
}
