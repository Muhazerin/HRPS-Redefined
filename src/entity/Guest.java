package entity;

import java.io.Serializable;

/**
 * 
 * @author Asyraaf
 * @author https://github.com/masyraaf
 *
 */

public class Guest extends Entity implements Serializable{
	private static final long serialVersionUID = 1L;
	private String nric;
	private String name;
	private String gender;
	private String nationality;
	
	public Guest(int id, String nric, String name, String gender, String nationality) {
		super(id);
		this.nric = nric;
		this.name = name;
		this.gender = gender;
		this.nationality = nationality;
	}
	public String getNRIC() {
		return nric;
	}
	public void setNRIC(String nric) {
		this.nric = nric;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getNationality() {
		return nationality;
	}
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
}
