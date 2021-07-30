package com.whyx.openchess.interfaces.model.board;

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
 * Contract for how the {@link ICell} interface should behave.
 */
@ExtendWith(MockitoExtension.class)
public abstract class ICellContract {

    /**
     * Create an instance of {@link ICellBuilder} to create {@link ICell} objects.
     *
     * @return {@link ICellBuilder} object.
     */
    public abstract ICellBuilder createBuilder();

    @Test
    void builderNotNullTest() {
        assertThat(createBuilder()).isNotNull();
    }

    /**
     * Check input parameters are correctly checked for preconditons.
     */
    @Nested
    class Preconditions {

        @Test
        void xSupplierNotNullTest() {
            assertThatNullPointerException()
                    .isThrownBy(() -> createBuilder()
                            .withX(null))
                    .withMessage("xSupplier must not be null");
        }

        @Test
        void ySupplierNotNullTest() {
            assertThatNullPointerException()
                    .isThrownBy(() -> createBuilder()
                            .withY(null))
                    .withMessage("ySupplier must not be null");
        }

        @Test
        void pieceNotNullTest() {
            assertThatNullPointerException()
                    .isThrownBy(() -> createBuilder()
                            .withPiece(null))
                    .withMessage("piece must not be null");
        }

    }

    /**
     * Check class is built properly.
     */
    @Nested
    class Build {

        private static final int X = 1;
        private static final int Y = 1;

        @Mock
        private IPiece piece;

        private ICell createCellWithPiece() {
            return createBuilder()
                    .withX(() -> X)
                    .withY(() -> Y)
                    .withPiece(piece)
                    .build();
        }

        private ICell createCellWithoutPiece() {
            return createBuilder()
                    .withX(() -> X)
                    .withY(() -> Y)
                    .build();
        }

        @Test
        void getXTest() {
            ICell cell = createCellWithoutPiece();
            assertThat(cell.getX()).isEqualTo(X);
        }

        @Test
        void getYTest() {
            ICell cell = createCellWithoutPiece();
            assertThat(cell.getY()).isEqualTo(Y);
        }

        @Test
        void getPieceOptionalNotNullWhenProvidedTest() {
            ICell cell = createCellWithPiece();
            assertThat(cell.getPiece()).isNotNull();
        }

        @Test
        void getPieceIsPresentWhenProvidedTest() {
            ICell cell = createCellWithPiece();
            assertThat(cell.getPiece().isPresent()).isTrue();
        }

        @Test
        void getPieceNotNullWhenProvidedTest() {
            ICell cell = createCellWithPiece();
            assertThat(cell.getPiece().get()).isNotNull();
        }

        @Test
        void getPieceWhenProvidedTest() {
            ICell cell = createCellWithPiece();
            assertThat(cell.getPiece().get()).isSameAs(piece);
        }

        @Test
        void getPieceOptionalNotNullWhenNotProvidedTest() {
            ICell cell = createCellWithoutPiece();
            assertThat(cell.getPiece()).isNotNull();
        }

        @Test
        void getPieceIsEmptyWhenNotProvidedTest() {
            ICell cell = createCellWithoutPiece();
            assertThat(cell.getPiece().isEmpty()).isTrue();
        }
    }

}
