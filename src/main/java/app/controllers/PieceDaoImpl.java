package app.controllers;

import app.dao.PieceDao;
import app.model.Piece;

import java.util.List;

public class PieceDoaImpl implements PieceDao {
    @Override
    public Piece createPiece(Piece piece) {
        return null;
    }

    @Override
    public boolean deletePiece(int id) {
        return false;
    }

    @Override
    public boolean updatePiece(Piece Piece) {
        return false;
    }

    @Override
    public Piece findPieceById(int id) {
        return null;
    }

    @Override
    public List<Piece> listPieces() {
        return null;
    }
}
