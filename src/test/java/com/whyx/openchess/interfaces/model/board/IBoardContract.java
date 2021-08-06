package com.whyx.openchess.interfaces.model.board;

import com.whyx.openchess.implementation.exceptions.CellOutOfBoundsException;
import com.whyx.openchess.interfaces.model.piece.IPiece;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThatNullPointerException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * @author Sam Wykes.
 * Contract for how the {@link IBoard} interface should behave.
 */
@ExtendWith(MockitoExtension.class)
public abstract class IBoardContract {

    private static final int X = 1;
    private static final int Y = 1;

    protected abstract IBoardFactory createBoardFactory();

    /**
     * Check input parameters are correctly checked for preconditions.
     */
    @Nested
    class Preconditions {

        private static final int BOARD_WIDTH = 8;
        private static final int BOARD_HEIGHT = 8;

        // board being tested.
        private IBoard board;

        @BeforeEach
        void setup() {
            board = createBoardFactory().emptyBoard(BOARD_WIDTH, BOARD_HEIGHT);
        }

        @Test
        void pieceNotNullPlacePieceTest() {
            assertThatNullPointerException()
                    .isThrownBy(() -> board.placePiece(X, Y, null))
                    .withMessage("piece must not be null");
        }

        @Test
        void negativeCoordinatesOutOfBoundsTest(@Mock final IPiece piece) {
            assertThatThrownBy(() -> board.placePiece(-X, -Y, piece))
                    .isExactlyInstanceOf(CellOutOfBoundsException.class)
                    .hasMessage("Cell out of bounds");
        }

    }

    /**
     * Check class is built properly.
     */
    @Nested
    class Build {

    }

}
