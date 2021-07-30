package com.whyx.openchess.implementation.model.piece;

import com.whyx.openchess.interfaces.common.PieceColour;
import com.whyx.openchess.interfaces.model.piece.IPiece;
import com.whyx.openchess.interfaces.model.piece.IPieceState;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNullPointerException;
import static org.assertj.core.api.Assumptions.assumeThat;

/**
 * @author Sam Wykes.
 */
@ExtendWith(MockitoExtension.class)
public class PieceTest {

    private static final PieceColour DEFAULT_COLOUR = PieceColour.BLACK;

    @Nested
    class Preconditions {

        private Piece.PieceBuilder builder;

        @BeforeEach
        void setup() {
            builder = Piece.builder();
            assumeThat(builder).isNotNull();
        }

        @Test
        void stateMustNotBeNullTest() {
            assertThatNullPointerException()
                    .isThrownBy(() -> builder
                            .withState(null))
                    .withMessage("state must not be null");
        }

        @Test
        void stateMustBePresentTest() {
            assertThatNullPointerException()
                    .isThrownBy(() -> builder
                            .withColour(DEFAULT_COLOUR)
                            .build())
                    .withMessage("state must not be null");
        }

        @Test
        void colourMustNotBeNullTest() {
            assertThatNullPointerException()
                    .isThrownBy(() -> builder
                            .withColour(null))
                    .withMessage("colour must not be null");
        }

        @Test
        void colourMustBePresentTest(@Mock final IPieceState pieceState) {
            assertThatNullPointerException()
                    .isThrownBy(() -> builder
                            .withState(pieceState)
                            .build())
                    .withMessage("colour must not be null");
        }

    }

    @Nested
    class Build {

        private IPiece piece;

        @Mock
        private IPieceState pieceState;

        @BeforeEach
        void setup() {
            piece = Piece.builder()
                    .withState(pieceState)
                    .withColour(DEFAULT_COLOUR)
                    .build();
        }

        @Test
        void builderNotNullTest() {
            assertThat(Piece.builder()).isNotNull();
        }

        @Test
        void pieceNotNullTest() {
            assertThat(piece).isNotNull();
        }

        @Test
        void pieceStateNotNullTest() {
            assertThat(piece.getState()).isNotNull();
        }

        @Test
        void getPieceStateTest() {
            assertThat(piece.getState()).isSameAs(pieceState);
        }

        @Test
        void pieceColourNotNullTest() {
            assertThat(piece.getColour()).isNotNull();
        }

        @Test
        void getPieceColourTest() {
            assertThat(piece.getColour()).isEqualTo(DEFAULT_COLOUR);
        }

    }

}