package com.whyx.openchess.interfaces.model.piece;

import com.whyx.openchess.interfaces.rules.IRule;

import java.util.Set;

public interface IPieceRuleBook {

    IPiece getPiece();

    Set<IRule> getRules();

}
