package com.whyx.openchess.model.interfaces.board.pieces;

import com.whyx.openchess.model.interfaces.board.pieces.IPiece;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assumptions.assumeThat;

/**
 * @author Sam Wykes.
 * Interface for testing the {@link IPiece} interface.
 */
@ExtendWith(MockitoExtension.class)
public abstract class IPieceContract<T extends IPiece> {

    /**
     * Checks all functions deal correctly with invalid input.
     */
    @Nested
    class Preconditions {

        // piece being tested.
        private IPiece piece;

        /**
         * Set up the piece to test and assume it is non-null.
         */
        @BeforeEach
        void setup() {
            piece = create();
            assumeThat(piece).isNotNull();
        }

        /**
         * Test that the program deals with the currentBoard parameter being null.
         */
        @Test
        void isLegalMoveCurrentBoardMustNotBeNullTest() {
            final int X = 0;
            final int Y = 0;

            assertThatNullPointerException()
                    .isThrownBy(() -> piece.isLegalMove(X, Y, null))
                    .withMessage("currentBoard must not be null");
        }

        /**
         * Check the x coordinate is not null.
         */
        @Test
        void getXMustNotBeNullTest() {
            assertThat(piece.getX()).isNotNull();
        }

        /**
         * Check the y coordinate is not null.
         */
        @Test
        void getYMustNotBeNullTest() {
            assertThat(piece.getY()).isNotNull();
        }

        /**
         * Checks the team attribute is not null.
         */
        @Test
        void getTeamMustNotBeNullTest() {
            assertThat(piece.getTeam()).isNotNull();
        }
    }

    /**
     * Checks all functions provide valid output.
     */
    @Nested
    class PostConditions {

        /**
         * Check the create method returns non-null.
         */
        @Test
        void createMustNotBeNull() {
            assertThat(create()).isNotNull();
        }

    }

    /**
     * Checks all functions provide logically sound output.
     */
    @Nested
    class Build {

        private IPiece piece;

        /**
         * Set up the piece to test and assume it is non-null.
         */
        @BeforeEach
        void setup() {
            piece = create();
            assumeThat(piece).isNotNull();
        }

        // Test that a piece hasn't moved when it is first created.
        @Test
        void hasMovedFalseTest() {
            assertThat(piece.hasMoved()).isFalse();
        }
    }

    /**
     * Create a piece to test.
     * @return {@link T} object.
     */
    protected abstract T create();


}
