package com.whyx.openchess.implementation.model.rule.moverule;

import com.whyx.openchess.interfaces.model.board.IBoard;
import com.whyx.openchess.interfaces.model.board.ICell;
import com.whyx.openchess.interfaces.model.board.ILocation;
import com.whyx.openchess.interfaces.model.piece.IPiece;
import com.whyx.openchess.interfaces.model.rules.IMoveRule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThatNullPointerException;

/**
 * @author Sam Wykes.
 * Class used to test the preconditons for each of the classes extending {@link IMoveRule}.
 */
@ExtendWith(MockitoExtension.class)
public abstract class MoveRuleTest<T extends ILocation> {

    /**
     * Create instance of rule to test for preconditions.
     *
     * @return {@link IMoveRule} object.
     */
    protected abstract IMoveRule<T> createRule();

    private IMoveRule<T> rule;

    @BeforeEach
    void setup() {
        rule = createRule();
    }

    @Test
    void startNotNullTest(
            @Mock final ICell<T> destination,
            @Mock final IPiece<T> piece,
            @Mock final IBoard<T> board
    ) {
        assertThatNullPointerException()
                .isThrownBy(() -> rule.moveConformsToRule(null, destination, piece, board))
                .withMessage("start must not be null");
    }

    @Test
    void destinationNotNullTest(
            @Mock final ICell<T> start,
            @Mock final IPiece<T> piece,
            @Mock final IBoard<T> board
    ) {
        assertThatNullPointerException()
                .isThrownBy(() -> rule.moveConformsToRule(start, null, piece, board))
                .withMessage("destination must not be null");
    }

    @Test
    void pieceNotNullTest(
            @Mock final ICell<T> start,
            @Mock final ICell<T> destination,
            @Mock final IBoard<T> board
    ) {
        assertThatNullPointerException()
                .isThrownBy(() -> rule.moveConformsToRule(start, destination, null, board))
                .withMessage("piece must not be null");
    }

    @Test
    void boardNotNullTest(
            @Mock final ICell<T> start,
            @Mock final ICell<T> destination,
            @Mock final IPiece<T> piece
    ) {
        assertThatNullPointerException()
                .isThrownBy(() -> rule.moveConformsToRule(start, destination, piece, null))
                .withMessage("board must not be null");
    }

}
