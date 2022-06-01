import java.io.IOException;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class FileMover {
    public void move(Path devDirectory, Path testDirectory, Path homeDirectory, Counter counter, String fileName) throws IOException {
        Path pathToCreatedFile = Path.of(homeDirectory.toString(), fileName);
        BasicFileAttributes fileAttributes = Files.readAttributes(pathToCreatedFile, BasicFileAttributes.class);
        if (!fileAttributes.isDirectory()) {
            FileTime fileTime = fileAttributes.creationTime();
            LocalDateTime fileCreationLocalDateTime = LocalDateTime.ofInstant(fileTime.toInstant(), ZoneId.systemDefault());
            if (creationHourIsEven(fileCreationLocalDateTime) && isJarFile(pathToCreatedFile)) {
                Files.move(pathToCreatedFile, createCopyTargetPath(devDirectory, fileName));
                counter.fileAddedToDevDirectory();
            }
            if (!creationHourIsEven(fileCreationLocalDateTime) && isJarFile(pathToCreatedFile)) {
                Files.move(pathToCreatedFile, createCopyTargetPath(testDirectory, fileName));
                counter.fileAddedToTestDirectory();
            }
            if (isXmlFile(pathToCreatedFile)) {
                Files.move(pathToCreatedFile, createCopyTargetPath(devDirectory, fileName), StandardCopyOption.REPLACE_EXISTING);
                counter.fileAddedToDevDirectory();
            }
        }
    }

    private Path createCopyTargetPath(Path path, String fileName) {
        return Path.of(path.toString(), fileName);
    }

    private boolean creationHourIsEven(LocalDateTime fileCreationLocalDateTime) {
        return fileCreationLocalDateTime.getHour() % 2 == 0;
    }

    private boolean isJarFile(Path pathToCreatedFile) {
        return pathToCreatedFile.toString().endsWith(".jar");
    }

    private boolean isXmlFile(Path pathToCreatedFile) {
        return pathToCreatedFile.toString().endsWith(".xml");
    }

}
