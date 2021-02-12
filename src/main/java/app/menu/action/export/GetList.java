package app.menu.action.export;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class GetList {
	
	protected final int id;
	protected final String description;
	protected final String nom;
	protected static final Logger logger = LoggerFactory.getLogger(GetList.class.getSimpleName());
	protected GetList(int pId, String pDescription,String pNom) {
		this.id = pId;
		this.description = pDescription;
		this.nom = pNom;
	}
	
	public abstract List<String[]> executer();
	
	public int getId() {
		return this.id;
	}
	
	public String getDescription() {
		return this.description;
	}
}
