package app.menu.action;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class Action {
	
	protected final int id;
	protected final String description;
	protected static final Logger logger = LoggerFactory.getLogger(Action.class.getSimpleName());
	protected Action(int pId, String pDescription) {
		this.id = pId;
		this.description = pDescription;
	}
	
	public abstract boolean executer();
	
	public int getId() {
		return this.id;
	}
	
	public String getDescription() {
		return this.description;
	}
}
