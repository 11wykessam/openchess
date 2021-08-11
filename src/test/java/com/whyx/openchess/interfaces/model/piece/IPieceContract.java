package com.whyx.openchess.interfaces.model.piece;

import com.whyx.openchess.interfaces.model.board.IBoard;
import com.whyx.openchess.interfaces.model.board.ICell;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThatNullPointerException;
import static org.assertj.core.api.Assumptions.assumeThat;

/**
 * @author Sam Wykes.
 * Contract for how the {@link IPiece} interface should behave.
 */
@ExtendWith(MockitoExtension.class)
public abstract class IPieceContract {

    /**
     * Create a piece object to test.
     *
     * @return {@link IPiece} object.
     */
    public abstract IPiece createPiece();

    /**
     * Check input parameters are correctly checked for preconditions.
     */
    @Nested
    class Preconditions {

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

    /**
     * Check class is built properly.
     */
    @Nested
    class Build {

    }

}
