package com.whyx.openchess.implementation.model.board;

import com.whyx.openchess.implementation.model.board.Cell.CellBuilder;
import com.whyx.openchess.interfaces.model.board.ICell;
import com.whyx.openchess.interfaces.model.board.ILocation;
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
                        .isThrownBy(() -> builder.withPiece(null))
                        .withMessage("piece must not be null");
            }

            @Test
            void locationNotNullTest() {
                assertThatNullPointerException()
                        .isThrownBy(() -> builder.withLocation(null))
                        .withMessage("location must not be null");
            }

            @Test
            void locationMustBePresentTest() {
                assertThatNullPointerException()
                        .isThrownBy(() -> builder.build())
                        .withMessage("location must not be null");
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
        void cellNotNullTest(@Mock final ILocation location) {
            ICell cell = Cell.builder()
                    .withLocation(location)
                    .build();
            assertThat(cell).isNotNull();
        }

        @Test
        void pieceOptionalNotNullWhenProvidedTest(@Mock final IPiece piece, @Mock final ILocation location) {
            ICell cell = Cell.builder()
                    .withPiece(piece)
                    .withLocation(location)
                    .build();
            assertThat(cell.getPiece()).isNotNull();
        }

        @Test
        void pieceOptionalPresentWhenProvidedTest(@Mock final IPiece piece, @Mock final ILocation location) {
            ICell cell = Cell.builder()
                    .withPiece(piece)
                    .withLocation(location)
                    .build();
            assertThat(cell.getPiece().isPresent()).isTrue();
        }

        @Test
        void getPieceWhenProvidedTest(@Mock final IPiece piece, @Mock final ILocation location) {
            ICell cell = Cell.builder()
                    .withPiece(piece)
                    .withLocation(location)
                    .build();
            assertThat(cell.getPiece().get()).isSameAs(piece);
        }

        @Test
        void pieceOptionalNotNullWhenNotProvidedTest(@Mock final ILocation location) {
            ICell cell = Cell.builder()
                    .withLocation(location)
                    .build();
            assertThat(cell.getPiece()).isNotNull();
        }

        @Test
        void pieceOptionalEmptyWhenNotProvidedTest(@Mock final ILocation location) {
            ICell cell = Cell.builder()
                    .withLocation(location)
                    .build();
            assertThat(cell.getPiece().isEmpty()).isTrue();
        }

        @Test
        void locationNotNullTest(@Mock final ILocation location) {
            ICell cell = Cell.builder()
                    .withLocation(location)
                    .build();
            assertThat(cell.getLocation()).isNotNull();
        }

        @Test
        void getLocationTest(@Mock final ILocation location) {
            ICell cell = Cell.builder()
                    .withLocation(location)
                    .build();
            assertThat(cell.getLocation()).isSameAs(location);
        }


    }

}