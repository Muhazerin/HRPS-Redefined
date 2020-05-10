package control;

import interfaces.DataAccess;

/**
 * This class will contain all the data source that is needed for this program.
 * @author muhazerin
 *
 */
public class DataSource implements DataAccess{
	private FileIO fileIO;
	
	public DataSource() {
		fileIO = new FileIO();
	}

	@Override
	public Object[] readObject(Class<?> cls) {
		return fileIO.readObject(cls);
	}

	@Override
	public void writeObject(Object[] objs, Class<?> cls) {
		fileIO.writeObject(objs, cls);
	}
}
