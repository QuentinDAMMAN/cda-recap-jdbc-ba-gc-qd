package app.dao;

import java.util.List;
import app.model.Piece;

public interface PieceDao {
	Piece createPiece(Piece piece);
	boolean deletePiece(int id);
	boolean updatePiece(String champ, String value, int id);
	Piece findPieceById(int id);
	List<Piece> listPieces();
}
