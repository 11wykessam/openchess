package com.whyx.openchess.implementation.model.board;

import com.whyx.openchess.implementation.model.board.Cell.CellBuilder;
import com.whyx.openchess.interfaces.model.board.ICell;
import com.whyx.openchess.interfaces.model.piece.IPiece;
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
 * Class used to test the {@link Cell} class.
 */
@ExtendWith(MockitoExtension.class)
class CellTest {

    /**
     * @author Sam Wykes.
     * Tests the preconditions of the {@link Cell} class.
     */
    @Nested
    class Preconditions {

        @Nested
        class BuildPreconditions {

            private CellBuilder builder;

            @BeforeEach
            void setup() {
                builder = Cell.builder();
                assumeThat(builder).isNotNull();
            }

            @Test
            void pieceNotNullTest() {
                assertThatNullPointerException()
                        .isThrownBy(() -> builder.withPiece(null));
            }

        }

    }

    /**
     * @author Sam Wykes.
     * Tests that the class is built correctly.
     */
    @Nested
    class Build {

        @Test
        void cellNotNullTest() {
            ICell cell = Cell.builder().build();
            assertThat(cell).isNotNull();
        }

        @Test
        void pieceOptionalNotNullWhenProvidedTest(@Mock final IPiece piece) {
            ICell cell = Cell.builder()
                    .withPiece(piece)
                    .build();
            assertThat(cell.getPiece()).isNotNull();
        }

        @Test
        void pieceOptionalPresentWhenProvidedTest(@Mock final IPiece piece) {
            ICell cell = Cell.builder()
                    .withPiece(piece)
                    .build();
            assertThat(cell.getPiece().isPresent()).isTrue();
        }

        @Test
        void getPieceWhenProvidedTest(@Mock final IPiece piece) {
            ICell cell = Cell.builder()
                    .withPiece(piece)
                    .build();
            assertThat(cell.getPiece().get()).isSameAs(piece);
        }

        @Test
        void pieceOptionalNotNullWhenNotProvidedTest() {
            ICell cell = Cell.builder().build();
            assertThat(cell.getPiece()).isNotNull();
        }

        @Test
        void pieceOptionalEmptyWhenNotProvidedTest() {
            ICell cell = Cell.builder().build();
            assertThat(cell.getPiece().isEmpty()).isTrue();
        }

    }

}