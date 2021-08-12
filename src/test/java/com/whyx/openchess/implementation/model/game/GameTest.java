package com.whyx.openchess.implementation.model.game;

import com.whyx.openchess.implementation.model.rules.Move;
import com.whyx.openchess.interfaces.model.board.IBoard;
import com.whyx.openchess.interfaces.model.game.IGame;
import com.whyx.openchess.interfaces.model.piece.IPiece;
import com.whyx.openchess.interfaces.model.piece.IPieceRuleBook;
import com.whyx.openchess.interfaces.model.rules.IMove;
import com.whyx.openchess.interfaces.model.rules.IRule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.stream.Stream;

import static com.whyx.openchess.implementation.model.game.Game.GameBuilder;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNullPointerException;
import static org.assertj.core.api.Assumptions.assumeThat;
import static org.mockito.BDDMockito.given;

/**
 * @author Sam Wykes.
 * Class used to test the {@link Game} class.
 */
@ExtendWith(MockitoExtension.class)
public class GameTest {

    /**
     * @author Sam Wykes.
     * Class used to test the preconditions of the {@link Game} class.
     */
    @Nested
    class Preconditions {

        @Nested
        class BuilderPreconditions {

            private GameBuilder builder;

            @BeforeEach
            void setup() {
                builder = Game.builder();
                assumeThat(builder).isNotNull();
            }

            @Test
            void boardNotNullTest() {
                assertThatNullPointerException()
                        .isThrownBy(() -> builder.withBoard(null))
                        .withMessage("board must not be null");
            }

            @Test
            void boardMustBePresentTest() {
                assertThatNullPointerException()
                        .isThrownBy(() -> builder.build())
                        .withMessage("board must not be null");
            }

        }

        @Nested
        class MethodPreconditions {

            private Game game;

            @Mock
            private IBoard board;

            @BeforeEach
            void setup() {
                this.game = Game.builder()
                        .withBoard(board)
                        .build();
            }

            @Test
            void isMoveLegalPieceNotNullTest(@Mock final Move move) {
                assertThatNullPointerException()
                        .isThrownBy(() -> game.isMoveLegal(null, move))
                        .withMessage("piece must not be null");
            }

            @Test
            void isMoveLegalMoveNotNullTest(@Mock final IPiece piece) {
                assertThatNullPointerException()
                        .isThrownBy(() -> game.isMoveLegal(piece, null))
                        .withMessage("move must not be null");
            }
        }

    }

    @Nested
    class Build {

        @Nested
        class AfterBuild {

            @Mock
            private IBoard board;

            private IGame game;

            @BeforeEach
            void setup() {
                game = Game.builder()
                        .withBoard(board)
                        .build();
            }

            @Test
            void gameNotNullTest() {
                assertThat(game).isNotNull();
            }

            @Test
            void boardNotNullTest() {
                assertThat(game.getBoard()).isNotNull();
            }

            @Test
            void getBoardTest() {
                assertThat(game.getBoard()).isSameAs(board);
            }
        }

    }

    @Nested
    class Functionality {

        @Test
        void isMoveLegalReturnsTrueWhenAllRulesSatisfied(
                @Mock final IRule rule,
                @Mock final IPieceRuleBook ruleBook,
                @Mock final IMove move,
                @Mock final IPiece piece,
                @Mock final IBoard board
        ) {
            Stream<IRule> ruleStream = Stream.of(rule);
            given(piece.getRuleBook()).willReturn(ruleBook);
            given(ruleBook.getRules()).willReturn(ruleStream);
            given(rule.moveConformsToRule(move, piece, board)).willReturn(true);

            IGame game = Game.builder()
                    .withBoard(board)
                    .build();

            assertThat(game.isMoveLegal(piece, move)).isTrue();
        }

    }

}
