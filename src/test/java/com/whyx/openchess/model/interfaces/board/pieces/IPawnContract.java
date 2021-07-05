package com.whyx.openchess.model.interfaces.board.pieces;

import com.whyx.openchess.model.interfaces.board.IBoard;
import com.whyx.openchess.model.interfaces.board.ICell;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assumptions.assumeThat;
import static org.mockito.BDDMockito.given;

/**
 * @author Sam Wykes.
 * Interface for testing the {@link IPawn} interface.
 */
@ExtendWith(MockitoExtension.class)
public abstract class IPawnContract extends IPieceContract<IPawn> {

    /**
     * @author Sam Wykes.
     * Check the output of {@link IPawn} objects' functions are logically sound.
     */
    @Nested
    class Build {

        /**
         * @author Sam Wykes.
         * Tests for the isLegalMoveFunction
         */
        @Nested
        class IsLegalMoveTests {

            private IPawn pawn;

            // mocks.
            @Mock
            private IBoard board;

            /**
             * @author Sam Wykes.
             * Tests for vertical movement.
             */
            @Nested
            class VerticalMovementTests {

                @Test
                void moveOneUnobstructedWhite(
                        @Mock final ICell targetCell
                        ) {



                }

            }

            @Nested
            class DiagonalMovementTests {}



        }

    }

}
