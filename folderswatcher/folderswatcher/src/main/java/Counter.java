import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class Counter {

    private static final String COUNTER_FILE_NAME = "count.txt";

    private final Path pathToCountFile;

    private int allMovedFilesCounter = 0;
    private int movedToDevDirectoryCounter = 0;
    private int movedToTestDirectoryCounter = 0;


    public Counter(Path pathToHomeDirectory) throws IOException {
        pathToCountFile = Path.of(pathToHomeDirectory.toString(), COUNTER_FILE_NAME);
        writeCounterContentToFile();
    }

    public void fileAddedToDevDirectory() throws IOException {
        allMovedFilesCounter += 1;
        movedToDevDirectoryCounter += 1;
        writeCounterContentToFile();
    }

    public void fileAddedToTestDirectory() throws IOException {
        allMovedFilesCounter += 1;
        movedToTestDirectoryCounter += 1;
        writeCounterContentToFile();
    }


    private void writeCounterContentToFile() throws IOException {
        Files.writeString(pathToCountFile, toString(), StandardOpenOption.WRITE);
    }

    @Override
    public String toString() {
        return "allMovedFilesCount=" + allMovedFilesCounter +
                ", movedToDevDirectoryCount=" + movedToDevDirectoryCounter +
                ", movedToTestDirectoryCount=" + movedToTestDirectoryCounter;
    }
}
