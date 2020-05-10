package control;

import java.util.ArrayList;

import interfaces.*;

/**
 * 
 * @author muhazerin
 *
 */

public abstract class EntityManager implements AddObject, ModifyObject, PrintObject{
	private DataAccess dataAccess;
	private Object[] objArray;
	private int counter;
	
	public EntityManager(Class<?> cls, DataAccess dataAccess) {
		this.dataAccess = dataAccess;
		objArray = this.dataAccess.readObject(cls);
		counter = 1;
	}
	public int getCounter() {
		return counter;
	}
	public void setCounter(int counter) {
		this.counter = counter;
	}
	public Object[] getList() {
		return objArray;
	}
	public void writeToFile(ArrayList<?> arrayList, Class<?> cls) {
		dataAccess.writeObject(arrayList.toArray(), cls);
	}
}
