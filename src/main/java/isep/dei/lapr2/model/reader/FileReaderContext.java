package isep.dei.lapr2.model.reader;

import java.io.File;

/**
 * Class that defines the right file reader
 */
public class FileReaderContext {
    /**
     * The file reader
     */
    private FileReader strategy;

    /**
     * Sets the file reader to the specified
     * @param strategy the file reader to set
     */
    public void setReaderStrategy(FileReader strategy) {
        this.strategy = strategy;
    }

    /**
     * Reads the file
     * @param file the file to read
     */
    public void readFile(File file) {
        strategy.readFile(file);
    }
}
