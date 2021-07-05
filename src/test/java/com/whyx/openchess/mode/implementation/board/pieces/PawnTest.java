package com.whyx.openchess.mode.implementation.board.pieces;

import com.whyx.openchess.model.implementation.board.pieces.Pawn;
import com.whyx.openchess.model.implementation.board.pieces.Team;
import com.whyx.openchess.model.interfaces.board.pieces.IPawn;
import com.whyx.openchess.model.interfaces.board.pieces.IPawnContract;
import com.whyx.openchess.model.interfaces.board.pieces.IPiece;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNullPointerException;

/**
 * @author Sam Wykes.
 * Class used to test the {@link Pawn} object.
 */
public class PawnTest extends IPawnContract {

    // CONSTANTS.
    private static final int X = 0;
    private static final int Y = 0;
    private static final Team TEAM = Team.WHITE;

    @Override
    protected IPawn create() {
        return Pawn.builder()
                .withX(X)
                .withY(Y)
                .withTeam(TEAM)
                .build();
    }

    /**
     * @author Sam Wykes.
     * Tests that input to functions must be valid.
     */
    @Nested
    class Preconditions {

        /**
         * Tests that x must be present for {@link Pawn} to build.
         */
        @Test
        void xMustBePresentTest() {
            assertThatNullPointerException()
                    .isThrownBy(() -> Pawn.builder()
                    .withY(Y)
                    .withTeam(TEAM)
                    .build())
                    .withMessage("x must not be null");
        }

        /**
         * Tests that y must be present for {@link Pawn} to build.
         */
        @Test
        void yMustBePresentTest() {
            assertThatNullPointerException()
                    .isThrownBy(() -> Pawn.builder()
                    .withX(X)
                    .withTeam(TEAM)
                    .build())
                    .withMessage("y must not be null");
        }

        /**
         * Tests that team must not be null.
         */
        @Test
        void teamMustNotBeNullTest() {
            assertThatNullPointerException()
                    .isThrownBy(() -> Pawn.builder()
                    .withTeam(null))
                    .withMessage("team must not be null");
        }

        @Test
        void teamMustBePresentTest() {
            assertThatNullPointerException()
                    .isThrownBy(() -> Pawn.builder()
                    .withX(X)
                    .withY(Y)
                    .build())
                    .withMessage("team must not be null");
        }

    }
}
