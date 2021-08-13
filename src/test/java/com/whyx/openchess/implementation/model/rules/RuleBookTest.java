package com.whyx.openchess.implementation.model.rules;

import com.whyx.openchess.implementation.model.rules.RuleBook.RuleBookBuilder;
import com.whyx.openchess.interfaces.model.board.ILocation;
import com.whyx.openchess.interfaces.model.rules.IRule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Set;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNullPointerException;
import static org.assertj.core.api.Assumptions.assumeThat;

/**
 * @author Sam Wykes.
 * Class used to test the {@link RuleBook} class.
 */
@ExtendWith(MockitoExtension.class)
class RuleBookTest {

    /**
     * @author Sam Wykes.
     * Used to test the preconditions of the {@link RuleBook} class.
     */
    @Nested
    class Preconditions {

        private RuleBookBuilder<ILocation> builder;

        @BeforeEach
        void setup() {
            builder = RuleBook.builder();
            assumeThat(builder).isNotNull();
        }

        @Test
        void rulesNotNullTest() {
            assertThatNullPointerException()
                    .isThrownBy(() -> builder.withRules(null))
                    .withMessage("rules must not be null");
        }

        @Test
        void rulesMustBePresentTest() {
            assertThatNullPointerException()
                    .isThrownBy(() -> builder.build())
                    .withMessage("rules must not be null");
        }

    }

    /**
     * @author Sam Wykes.
     * Checks that the {@link RuleBook} class is being built correctly.
     */
    @Nested
    class Build {

        @Mock
        private IRule<ILocation> ruleOne;
        @Mock
        private IRule<ILocation> ruleTwo;

        private RuleBook<ILocation> ruleBook;

        @BeforeEach
        void setup() {
            ruleBook = RuleBook.builder()
                    .withRules(Set.of(ruleOne, ruleTwo))
                    .build();
        }

        @Test
        void rulesNotNullTest() {
            assertThat(ruleBook.getRules()).isNotNull();
        }

        @Test
        void getRulesTest() {
            assertThat(ruleBook.getRules().collect(Collectors.toSet())).isEqualTo(Set.of(ruleOne, ruleTwo));
        }

    }

}