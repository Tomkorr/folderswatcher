import java.io.IOException;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        DirectoryWatcher directoryWatcher = new DirectoryWatcher();
        directoryWatcher.run();
    }
}
