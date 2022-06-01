import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;

public class DirectoryWatcher {

    private static final String PATH_TO_USER_HOME_DIRECTORY = System.getProperty("user.home");
    private static final String PATH_TO_DEV_DIRECTORY = "DEV";
    private static final String PATH_TO_TEST_DIRECTORY = "TEST";
    private static final String PATH_TO_HOME_DIRECTORY = "HOME";

    public void run() throws IOException, InterruptedException {

        Path devDirectory = createDirectory(PATH_TO_USER_HOME_DIRECTORY, PATH_TO_DEV_DIRECTORY);
        Path testDirectory = createDirectory(PATH_TO_USER_HOME_DIRECTORY, PATH_TO_TEST_DIRECTORY);
        Path homeDirectory = createDirectory(PATH_TO_USER_HOME_DIRECTORY, PATH_TO_HOME_DIRECTORY);

        WatchService watchService = FileSystems.getDefault().newWatchService();
        homeDirectory.register(watchService, StandardWatchEventKinds.ENTRY_CREATE);
        WatchKey key;

        Counter counter = new Counter(homeDirectory);
        FileMover fileMover = new FileMover();
        while ((key = watchService.take()) != null) {
            for (WatchEvent<?> event : key.pollEvents()) {
                String fileName = String.valueOf(event.context());
                fileMover.move(devDirectory, testDirectory, homeDirectory, counter, fileName);
            }
            key.reset();
        }
    }


    private Path createDirectory(String rootDirectory, String directoryName) throws IOException {
        return Files.createDirectories(createPathToFolder(rootDirectory, directoryName));
    }

    private Path createPathToFolder(String rootDirectory, String directoryName) {
        return Path.of(rootDirectory, directoryName);
    }
}
