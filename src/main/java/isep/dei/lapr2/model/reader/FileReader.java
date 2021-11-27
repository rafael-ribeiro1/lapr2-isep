package isep.dei.lapr2.model.reader;

import java.io.File;

/**
 * Represents the file readers
 */
public interface FileReader {
    /**
     * Reads a file
     * @param file the file to read
     */
    public void readFile(File file);
}
