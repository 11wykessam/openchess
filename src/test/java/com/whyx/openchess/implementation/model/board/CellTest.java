package com.whyx.openchess.implementation.model.board;

import com.whyx.openchess.implementation.exceptions.CellOccupiedException;
import com.whyx.openchess.implementation.exceptions.PieceNotFoundException;
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

import static org.assertj.core.api.Assertions.*;
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

            private CellBuilder<ILocation> builder;

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

        @Nested
        class MethodPreconditions {

            @Mock
            private ILocation location;

            private ICell<ILocation> cell;

            @BeforeEach
            void setup() {
                cell = Cell.builder()
                        .withLocation(location)
                        .build();
            }

            @Test
            void placePiecePieceNotNullTest() {
                assertThatNullPointerException()
                        .isThrownBy(() -> cell.placePiece(null))
                        .withMessage("piece must not be null");
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
            final ICell<ILocation> cell = Cell.builder()
                    .withLocation(location)
                    .build();
            assertThat(cell).isNotNull();
        }

        @Test
        void pieceOptionalNotNullWhenProvidedTest(@Mock final IPiece<ILocation> piece, @Mock final ILocation location) {
            final ICell<ILocation> cell = Cell.builder()
                    .withPiece(piece)
                    .withLocation(location)
                    .build();
            assertThat(cell.getPiece()).isNotNull();
        }

        @Test
        void pieceOptionalPresentWhenProvidedTest(@Mock final IPiece<ILocation> piece, @Mock final ILocation location) {
            final ICell<ILocation> cell = Cell.builder()
                    .withPiece(piece)
                    .withLocation(location)
                    .build();
            assertThat(cell.getPiece().isPresent()).isTrue();
        }

        @SuppressWarnings("OptionalGetWithoutIsPresent")
        @Test
        void getPieceWhenProvidedTest(@Mock final IPiece<ILocation> piece, @Mock final ILocation location) {
            final ICell<ILocation> cell = Cell.builder()
                    .withPiece(piece)
                    .withLocation(location)
                    .build();
            assertThat(cell.getPiece().get()).isSameAs(piece);
        }

        @Test
        void pieceOptionalNotNullWhenNotProvidedTest(@Mock final ILocation location) {
            final ICell<ILocation> cell = Cell.builder()
                    .withLocation(location)
                    .build();
            assertThat(cell.getPiece()).isNotNull();
        }

        @Test
        void pieceOptionalEmptyWhenNotProvidedTest(@Mock final ILocation location) {
            final ICell<ILocation> cell = Cell.builder()
                    .withLocation(location)
                    .build();
            assertThat(cell.getPiece().isEmpty()).isTrue();
        }

        @Test
        void locationNotNullTest(@Mock final ILocation location) {
            final ICell<ILocation> cell = Cell.builder()
                    .withLocation(location)
                    .build();
            assertThat(cell.getLocation()).isNotNull();
        }

        @Test
        void getLocationTest(@Mock final ILocation location) {
            final ICell<ILocation> cell = Cell.builder()
                    .withLocation(location)
                    .build();
            assertThat(cell.getLocation()).isSameAs(location);
        }

        @Test
        void placePieceNotNullTest(
                @Mock final ILocation location,
                @Mock final IPiece<ILocation> piece
        ) {
            final ICell<ILocation> cell = Cell.builder()
                    .withLocation(location)
                    .build();
            final ICell<ILocation> altered = cell.placePiece(piece);

            assertThat(altered).isNotNull();
        }

        @Test
        void placePieceAlteredTest(
                @Mock final ILocation location,
                @Mock final IPiece<ILocation> piece
        ) {
            final ICell<ILocation> cell = Cell.builder()
                    .withLocation(location)
                    .build();
            final ICell<ILocation> altered = cell.placePiece(piece);

            assertThat(altered).isNotEqualTo(cell);
        }

        @Test
        void placePieceCellPieceNotNullTest(
                @Mock final ILocation location,
                @Mock final IPiece<ILocation> piece
        ) {
            final ICell<ILocation> cell = Cell.builder()
                    .withLocation(location)
                    .build();
            final ICell<ILocation> altered = cell.placePiece(piece);

            assertThat(altered.getPiece()).isNotNull();
        }

        @Test
        void placePieceCellPieceNotEmptyTest(
                @Mock final ILocation location,
                @Mock final IPiece<ILocation> piece
        ) {
            final ICell<ILocation> cell = Cell.builder()
                    .withLocation(location)
                    .build();
            final ICell<ILocation> altered = cell.placePiece(piece);

            assertThat(altered.getPiece()).isNotEmpty();
        }

        @Test
        void placePieceCellPieceExpectedTest(
                @Mock final ILocation location,
                @Mock final IPiece<ILocation> piece
        ) {
            final ICell<ILocation> cell = Cell.builder()
                    .withLocation(location)
                    .build();
            final ICell<ILocation> altered = cell.placePiece(piece);

            assertThat(altered.getPiece().get()).isSameAs(piece);
        }

        @Test
        void placePieceFailsIfCellOccupiedTest(
                @Mock final ILocation location,
                @Mock final IPiece<ILocation> movingPiece,
                @Mock final IPiece<ILocation> occupyingPiece
        ) {
            final ICell<ILocation> cell = Cell.builder()
                    .withLocation(location)
                    .withPiece(occupyingPiece)
                    .build();

            assertThatExceptionOfType(CellOccupiedException.class)
                    .isThrownBy(() -> cell.placePiece(movingPiece))
                    .withMessage("cell occupied");
        }

        @Test
        void removePieceNotNullTest(
                @Mock final ILocation location,
                @Mock final IPiece<ILocation> piece
        ) {
            final ICell<ILocation> cell = Cell.builder()
                    .withLocation(location)
                    .withPiece(piece)
                    .build();

            assertThat(cell.removePiece()).isNotNull();
        }

        @Test
        void removePieceCellAlteredTest(
                @Mock final ILocation location,
                @Mock final IPiece<ILocation> piece
        ) {
            final ICell<ILocation> cell = Cell.builder()
                    .withLocation(location)
                    .withPiece(piece)
                    .build();
            final ICell<ILocation> altered = cell.removePiece();

            assertThat(altered).isNotEqualTo(cell);
        }

        @Test
        void removePieceCellPieceOptionalNotNullTest(
                @Mock final ILocation location,
                @Mock final IPiece<ILocation> piece
        ) {
            final ICell<ILocation> cell = Cell.builder()
                    .withLocation(location)
                    .withPiece(piece)
                    .build();
            final ICell<ILocation> altered = cell.removePiece();

            assertThat(altered.getPiece()).isNotNull();
        }

        @Test
        void removePieceCellEmptyTest(
                @Mock final ILocation location,
                @Mock final IPiece<ILocation> piece
        ) {
            final ICell<ILocation> cell = Cell.builder()
                    .withLocation(location)
                    .withPiece(piece)
                    .build();
            final ICell<ILocation> altered = cell.removePiece();

            assertThat(altered.getPiece().isEmpty()).isTrue();
        }

        @Test
        void removePieceFailsIfNoPiecePresent(
                @Mock final ILocation location
        ) {
            final ICell<ILocation> cell = Cell.builder()
                    .withLocation(location)
                    .build();
            assertThatExceptionOfType(PieceNotFoundException.class)
                    .isThrownBy(cell::removePiece)
                    .withMessage("piece not found");
        }

    }

}