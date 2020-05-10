package entity;

import java.io.Serializable;

/**
 * 
 * @author muhazerin
 *
 */

public abstract class Entity implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	
	public Entity(int id) {
		this.id = id;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
}