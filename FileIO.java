import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/*
 * Flat file database reader and writer.
 * @author Loh Zhi Shen
 * @version 1.0
 * @since 2022-03-06
 * @param <T> a class that is to be written into a .txt file
 */

public class FileReader<T> implements DataBaseInterface<T> {
    private String filePath;
  
/*
* Constructor of FileReader<T>
* @param filePath path to the location where the objects of 
*                 class T will be read and written to
*/
    public FileReader (String filePath) {
        this.filePath = filePath;
    }
  
/*
* Function to read objects of class T from a .txt file<br>
* If the provided file path doesn't lead to an existing file, 
* a new file will be created
* @return Returns an ArrayList<T> object containing objects of 
*         class T that are read from the provided file path
*/
  @SuppressWarnings("unchecked")
    public ArrayList<T> load() {
        ArrayList<T> output = new ArrayList<T>();
        try {
            File database = new File(filePath);
            if (database.isFile()) {
            FileInputStream fileStream = new FileInputStream(filePath);
            BufferedInputStream bufferedStream = new BufferedInputStream(fileStream);
            ObjectInputStream reader = new ObjectInputStream(bufferedStream);
                while (true) {
                 data = (T) reader.readObject();
                output.add(data);
                }
            } 
            else {
                database.createNewFile();
            }
        } 
        catch (EOFException e) {
            return output;
        } 
        catch (ClassNotFoundException e) {
            System.out.println("[ERROR] Unable to read saved data due to class mismatch.");
            e.printStackTrace();
        } 
        catch (IOException e) {
            System.out.println("[ERROR] Unable to read saved data.");
            e.printStackTrace();
        }
    return output;
  }
  
/*
* Function to write objects of class T to a .txt file
* @param items ArrayList containing objects of class T that will
*              be written to the .txt file
*/
    public void save(ArrayList<T> items) {
        try {
            FileOutputStream fileStream = new FileOutputStream(filePath);
            BufferedOutputStream bufferedStream = new BufferedOutputStream(fileStream);
            ObjectOutputStream writer = new ObjectOutputStream(bufferedStream);
            for (T item: items) {
                writer.writeObject(item);
            }
            writer.close();
        }
        catch (FileNotFoundException e) {
            System.out.println("[ERROR] Unable to save data as file location not found.");
            e.printStackTrace();
        } 
        catch (IOException e) {
            System.out.println("[ERROR] Unable to save data.");
            e.printStackTrace();
        }    
    }
}


