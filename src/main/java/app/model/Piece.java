package app.model;

import java.util.Date;

public class Piece {
	private int id_piece;
	private Date date_extraction;
	private int id_vehicule;
	private String id_reference;

	public Piece(int id_piece, Date date_extraction,String id_reference, int id_vehicule ) {
		this.id_piece = id_piece;
		this.date_extraction = date_extraction;
		this.id_vehicule = id_vehicule;
		this.id_reference = id_reference;
	}

	public Piece(Date date_extraction,String id_reference,int id_vehicule) {
		this.date_extraction = date_extraction;
		this.id_vehicule = id_vehicule;
		this.id_reference = id_reference;
	}

	public int getId_piece() {
		return id_piece;
	}

	public void setId_piece(int id_piece) {
		this.id_piece = id_piece;
	}

	public Date getDate_extraction() {
		return date_extraction;
	}

	public void setDate_extraction(Date date_extraction) {
		this.date_extraction = date_extraction;
	}

	public int getId_vehicule() {
		return id_vehicule;
	}

	public void setId_vehicule(int id_vehicule) {
		this.id_vehicule = id_vehicule;
	}

	public String getId_reference() {
		return id_reference;
	}

	public void setId_reference(String id_reference) {
		this.id_reference = id_reference;
	}

	@Override
	public String toString() {
		return "Piece [id_piece=" + id_piece + ", date_extraction=" + date_extraction + ", id_vehicule=" + id_vehicule
				+ ", id_reference=" + id_reference + "]";
	}
}
