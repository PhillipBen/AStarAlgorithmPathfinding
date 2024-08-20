import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

class SaveLoadManager {
	/*
	Used to save and load the Grid class.
	 */

	public Grid load(String filename) throws IOException {
		//Source: https://www.geeksforgeeks.org/serializable-interface-in-java/
		try {
			FileInputStream fis = new FileInputStream(filename); 
			ObjectInputStream ois = new ObjectInputStream(fis);
			Grid grid = (Grid)ois.readObject();
			fis.close();
			ois.close();
			System.out.println("Test loaded successfully!");
			return grid;
		}catch(Exception e) {
			System.out.println("Failed to load file");
			e.getMessage();
			return null;
		}
	}
	

	/**
	 * Save the test set using the given filename.
	 * @param testSet The test set to be stored.
	 * @param filename  The filename to be used.
	 * @return true if save is successful
	 */
	public boolean save(Grid grid, String filename) {
		//Source: https://www.geeksforgeeks.org/serializable-interface-in-java/
		try {
			FileOutputStream fos = new FileOutputStream(filename);
			ObjectOutputStream oos = new ObjectOutputStream(fos); 
			oos.writeObject(grid); 
			oos.close();
			fos.close();
			return true;
		}catch(Exception e) {
			e.getMessage();
			return false;
		}
	}
}