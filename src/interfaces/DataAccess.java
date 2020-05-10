package interfaces;

/**
 * 
 * @author muhazerin
 *
 */
public interface DataAccess {
	public Object[] readObject(Class<?> cls);
	
	public void writeObject (Object[] objs, Class<?> cls);
}
