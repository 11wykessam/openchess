package com.whyx.openchess.implementation.model.board;

import com.whyx.openchess.interfaces.model.board.ICell;
import com.whyx.openchess.interfaces.model.board.ICellContract;
import com.whyx.openchess.interfaces.model.piece.IPiece;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNullPointerException;

/**
 * @author Sam Wykes.
 * Class used to test the {@link Cell} class.
 */
@ExtendWith(MockitoExtension.class)
public class CellTest extends ICellContract {

    // final attributes.
    private static final int X = 1;
    private static final int Y = 1;

    // mock attributes.
    @Mock
    private IPiece piece;

    /**
     * Check input parameters are correctly checked for preconditions.
     */
    @Nested
    class Preconditions {

        @Test
        void xSupplierNotNullTest() {
            assertThatNullPointerException()
                    .isThrownBy(() -> Cell.builder()
                            .withX(null))
                    .withMessage("xSupplier must not be null");
        }

        @Test
        void xSupplierMustBePresentTest() {
            assertThatNullPointerException()
                    .isThrownBy(() -> Cell.builder()
                            .withPiece(piece)
                            .withY(() -> Y)
                            .build())
                    .withMessage("xSupplier must not be null");
        }

        @Test
        void ySupplierNotNullTest() {
            assertThatNullPointerException()
                    .isThrownBy(() -> Cell.builder()
                            .withY(null))
                    .withMessage("ySupplier must not be null");
        }

        @Test
        void ySupplierMustBePresentTest() {
            assertThatNullPointerException()
                    .isThrownBy(() -> Cell.builder()
                            .withPiece(piece)
                            .withX(() -> X)
                            .build())
                    .withMessage("ySupplier must not be null");
        }

        @Test
        void pieceNotNullTest() {
            assertThatNullPointerException()
                    .isThrownBy(() -> Cell.builder()
                            .withPiece(null))
                    .withMessage("piece must not be null");
        }
    }

    /**
     * Check class is built properly.
     */
    @Nested
    class Build {

        @Test
        void builderNotNullTest() {
            assertThat(Cell.builder()).isNotNull();
        }

        @Test
        void cellNotNullTest() {
            ICell cell = Cell.builder()
                    .withPiece(piece)
                    .withX(() -> X)
                    .withY(() -> Y)
                    .build();
            assertThat(cell).isNotNull();
        }

        @Test
        void pieceOptionalNotNullWhenGivenTest() {
            ICell cell = Cell.builder()
                    .withPiece(piece)
                    .withX(() -> X)
                    .withY(() -> Y)
                    .build();
            assertThat(cell.getPiece()).isNotNull();
        }

        @Test
        void pieceIsPresentWhenGivenTest() {
            ICell cell = Cell.builder()
                    .withPiece(piece)
                    .withX(() -> X)
                    .withY(() -> Y)
                    .build();
            assertThat(cell.getPiece().isPresent()).isTrue();
        }

        @Test
        void pieceValueNotNullWhenGivenTest() {
            ICell cell = Cell.builder()
                    .withPiece(piece)
                    .withX(() -> X)
                    .withY(() -> Y)
                    .build();
            assertThat(cell.getPiece().get()).isNotNull();
        }

        @Test
        void getPieceWhenGivenTest() {
            ICell cell = Cell.builder()
                    .withPiece(piece)
                    .withX(() -> X)
                    .withY(() -> Y)
                    .build();
            assertThat(cell.getPiece().get()).isSameAs(piece);
        }

        @Test
        void pieceOptionalNotNullWhenNotGivenTest() {
            ICell cell = Cell.builder()
                    .withX(() -> X)
                    .withY(() -> Y)
                    .build();
            assertThat(cell.getPiece()).isNotNull();
        }

        @Test
        void pieceOptionalIsEmptyWhenNotGivenTest() {
            ICell cell = Cell.builder()
                    .withX(() -> X)
                    .withY(() -> Y)
                    .build();
            assertThat(cell.getPiece().isEmpty()).isTrue();
        }

        @Test
        void getXTest() {
            ICell cell = Cell.builder()
                    .withX(() -> X)
                    .withY(() -> Y)
                    .build();
            assertThat(cell.getX()).isEqualTo(X);
        }

        @Test
        void getYTest() {
            ICell cell = Cell.builder()
                    .withX(() -> X)
                    .withY(() -> Y)
                    .build();
            assertThat(cell.getY()).isEqualTo(Y);
        }

    }

}
