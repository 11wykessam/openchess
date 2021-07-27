package com.whyx.openchess.model.board;

import com.whyx.openchessinterface.model.board.ICell;
import com.whyx.openchessinterface.model.board.ICellState;
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
public class CellTest {

    // default values.
    private static final int DEFAULT_X = 1;
    private static final int DEFAULT_Y = 1;

    @Nested
    class Preconditions {

        private Cell.CellBuilder builder;

        @BeforeEach
        void setup() {
            builder = Cell.builder();
            assumeThat(builder).isNotNull();
        }

        @Test
        void cellStateMustNotBeNullTest() {
            assertThatNullPointerException()
                    .isThrownBy(() -> builder
                            .withCellState(null)
                    ).withMessage("cellState must not be null");
        }

        @Test
        void cellStateMustBePresentTest() {
            assertThatNullPointerException()
                    .isThrownBy(() -> builder
                            .withCellState(null)
                            .withXSupplier(() -> DEFAULT_X)
                            .withYSupplier(() -> DEFAULT_Y)
                            .build()
                    ).withMessage("cellState must not be null");
        }

        @Test
        void xSupplierMustNotBeNullTest() {
            assertThatNullPointerException()
                    .isThrownBy(() -> builder
                            .withXSupplier(null)
                    ).withMessage("xSupplier must not be null");
        }

        @Test
        void xSupplierMustBePresentTest(@Mock final ICellState cellState) {
            assertThatNullPointerException()
                    .isThrownBy(() -> builder
                            .withCellState(cellState)
                            .withXSupplier(null)
                            .withYSupplier(() -> DEFAULT_Y)
                            .build()).withMessage("xSupplier must not be null");
        }

        @Test
        void ySupplierMustNotBeNullTest() {
            assertThatNullPointerException()
                    .isThrownBy(() -> builder
                            .withYSupplier(null)
                    ).withMessage("ySupplier must not be null");
        }

        @Test
        void ySupplierMustBePresentTest(@Mock final ICellState cellState) {
            assertThatNullPointerException()
                    .isThrownBy(() -> builder
                            .withCellState(cellState)
                            .withXSupplier(() -> DEFAULT_X)
                            .withYSupplier(null)
                            .build()).withMessage("ySupplier must not be null");
        }


    }

    @Nested
    class Build {

        private ICell cell;

        @Mock
        private ICellState cellState;

        @BeforeEach
        void setup() {
            cell = Cell.builder()
                    .withCellState(cellState)
                    .withXSupplier(() -> DEFAULT_X)
                    .withYSupplier(() -> DEFAULT_Y)
                    .build();
            // assume that cell builds correctly.
            assumeThat(cell).isNotNull();
        }

        @Test
        void builderNotNullTest() {
            assertThat(Cell.builder()).isNotNull();
        }

        @Test
        void getCellStateNotNullTest() {
            assertThat(cell.getState()).isNotNull();
        }

        @Test
        void getCellStateTest() {
            assumeThat(cell.getState()).isNotNull();
            assertThat(cell.getState()).isSameAs(cellState);
        }

        @Test
        void getXTest() {
            assertThat(cell.getX()).isEqualTo(DEFAULT_X);
        }

        @Test
        void getYTest() {
            assertThat(cell.getY()).isEqualTo(DEFAULT_Y);
        }

    }

}
