package control;

import java.util.ArrayList;

public abstract class EntityManager {
	private FileIO fileIO;
	private Object[] objArray;
	private int counter;
	
	public EntityManager(Class<?> cls) {
		fileIO = new FileIO();
		objArray = fileIO.readObject(cls);
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
		fileIO.writeObject(arrayList.toArray(), cls);
	}
	public abstract void add();
}
