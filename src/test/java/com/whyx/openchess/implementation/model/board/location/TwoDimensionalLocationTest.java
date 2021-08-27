package com.whyx.openchess.implementation.model.board.location;

import com.whyx.openchess.implementation.model.board.location.TwoDimensionalLocation.TwoDimensionalLocationBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.function.Supplier;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNullPointerException;
import static org.assertj.core.api.Assumptions.assumeThat;

/**
 * @author Sam Wykes.
 * Class responsible for testing the {@link TwoDimensionalLocation} test.
 */
@ExtendWith(MockitoExtension.class)
class TwoDimensionalLocationTest {

    @Nested
    class Preconditions {

        private TwoDimensionalLocationBuilder builder;

        @BeforeEach
        void setup() {
            builder = TwoDimensionalLocation.builder();
            assumeThat(builder).isNotNull();
        }

        @Test
        void xSupplierNotNullTest() {
            assertThatNullPointerException()
                    .isThrownBy(() -> builder.withXSupplier(null))
                    .withMessage("xSupplier must not be null");
        }

        @Test
        void xSupplierMustBePresentTest(@Mock final Supplier<Integer> ySupplier) {
            assertThatNullPointerException()
                    .isThrownBy(() -> builder
                            .withYSupplier(ySupplier)
                            .build())
                    .withMessage("xSupplier must not be null");
        }

        @Test
        void ySupplierNotNullTest() {
            assertThatNullPointerException()
                    .isThrownBy(() -> builder.withYSupplier(null))
                    .withMessage("ySupplier must not be null");
        }

        @Test
        void ySupplierMustBePresentTest(@Mock final Supplier<Integer> xSupplier) {
            assertThatNullPointerException()
                    .isThrownBy(() -> builder
                            .withXSupplier(xSupplier)
                            .build())
                    .withMessage("ySupplier must not be null");
        }

    }

    @Nested
    class Build {

        private static final int X = 13;
        private static final int Y = 14;

        private TwoDimensionalLocation location;

        @BeforeEach
        void setup() {
            location = TwoDimensionalLocation.builder()
                    .withXSupplier(() -> X)
                    .withYSupplier(() -> Y)
                    .build();
        }

        @Test
        void getXTest() {
            assertThat(location.getX()).isEqualTo(X);
        }

        @Test
        void getYTest() {
            assertThat(location.getY()).isEqualTo(Y);
        }

    }

}
