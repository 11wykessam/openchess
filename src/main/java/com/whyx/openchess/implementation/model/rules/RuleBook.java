package com.whyx.openchess.implementation.model.rules;

import com.whyx.openchess.interfaces.model.rules.IRule;
import com.whyx.openchess.interfaces.model.rules.IRuleBook;

import java.util.Set;
import java.util.stream.Stream;

import static java.util.Objects.requireNonNull;

/**
 * @author Sam Wykes.
 * Class representing a collection of rules that a given piece in a board game must adhere to.
 */
public class RuleBook implements IRuleBook {

    private final Set<IRule> rules;

    /**
     * @param ruleBookBuilder object responsible for building object.
     */
    public RuleBook(final RuleBookBuilder ruleBookBuilder) {
        this.rules = ruleBookBuilder.rules;
    }

    /**
     * Get the rules associated with this rule book.
     *
     * @return {@link Stream} containing {@link IRule} objects.
     */
    @Override
    public Stream<IRule> getRules() {
        return this.rules.stream();
    }

    /**
     * Create instance of builder.
     *
     * @return {@link RuleBookBuilder} object.
     */
    public static RuleBookBuilder builder() {
        return new RuleBookBuilder();
    }

    /**
     * @author Sam Wykes.
     * Class responsible for constructing {@link RuleBook} instances.
     */
    public static class RuleBookBuilder {

        public Set<IRule> rules;

        public RuleBookBuilder withRules(final Set<IRule> rules) {
            requireNonNull(rules, "rules must not be null");
            this.rules = rules;
            return this;
        }

        public RuleBook build() {
            requireNonNull(rules, "rules must not be null");
            return new RuleBook(this);
        }
    }
}
