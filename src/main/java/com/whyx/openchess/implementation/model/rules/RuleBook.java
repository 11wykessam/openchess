package com.whyx.openchess.implementation.model.rules;

import com.whyx.openchess.interfaces.model.board.ILocation;
import com.whyx.openchess.interfaces.model.rules.IRule;
import com.whyx.openchess.interfaces.model.rules.IRuleBook;

import java.util.Set;
import java.util.stream.Stream;

import static java.util.Objects.requireNonNull;

/**
 * @param <T> The type of location the rules in the rule book can deal with.
 * @author Sam Wykes.
 * Class representing a collection of rules that a given piece in a board game must adhere to.
 */
public class RuleBook<T extends ILocation> implements IRuleBook<T> {

    private final Set<IRule<T>> rules;

    /**
     * @param ruleBookBuilder object responsible for building object.
     */
    public RuleBook(final RuleBookBuilder<T> ruleBookBuilder) {
        this.rules = ruleBookBuilder.rules;
    }

    /**
     * Get the rules associated with this rule book.
     *
     * @return {@link Stream} containing {@link IRule} objects.
     */
    @Override
    public Stream<IRule<T>> getRules() {
        return this.rules.stream();
    }

    /**
     * Create instance of builder.
     *
     * @return {@link RuleBookBuilder} object.
     */
    public static <U extends ILocation> RuleBookBuilder<U> builder() {
        return new RuleBookBuilder<>();
    }

    /**
     * @param <U> The type of location the rule book being built will deal with.
     * @author Sam Wykes.
     * Class responsible for constructing {@link RuleBook} instances.
     */
    public static class RuleBookBuilder<U extends ILocation> {

        public Set<IRule<U>> rules;

        public RuleBookBuilder<U> withRules(final Set<IRule<U>> rules) {
            requireNonNull(rules, "rules must not be null");
            this.rules = rules;
            return this;
        }

        public RuleBook<U> build() {
            requireNonNull(rules, "rules must not be null");
            return new RuleBook<>(this);
        }
    }
}
