package app.dao;

import java.util.List;
import app.model.Piece;

public interface DAOPiece {
	Piece createPiece(Piece piece);
	boolean deletePiece(int id);
	boolean updatePiece(Piece Piece);
	Piece findPieceById(int id);
	List<Piece> listPieces();
}
