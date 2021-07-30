package com.whyx.openchess.implementation.model.piece;

import com.whyx.openchess.interfaces.model.board.ICell;
import com.whyx.openchess.interfaces.model.piece.IPieceState;

import java.util.Optional;


public class PieceState implements IPieceState {

    @Override
    public Optional<ICell> getCell() {
        return Optional.empty();
    }

}
