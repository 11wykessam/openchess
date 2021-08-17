package com.whyx.openchess.implementation.model.rule.gamerule;

import com.whyx.openchess.interfaces.model.board.ILocation;
import com.whyx.openchess.interfaces.model.game.IGame;
import com.whyx.openchess.interfaces.model.rules.IGameRule;

public class CheckRule<T extends ILocation> implements IGameRule<T> {

    @Override
    public boolean ruleSatisfied(final IGame<T> game) {
        return false;
    }

}
