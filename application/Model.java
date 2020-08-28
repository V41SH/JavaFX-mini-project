package application;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class Model {
	
	public void save(TextFile textFile) {
        try {
            Files.write(textFile.fileGet(), textFile.getText(), StandardOpenOption.CREATE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public IOfile<TextFile> load(Path file) {
        try {
            List<String> lines = Files.readAllLines(file);
            return new IOfile<>(true, new TextFile(file, lines));
        } catch (IOException e) {
            e.printStackTrace();
            return new IOfile<>(false, null);
        }
    }

    public void close() {
        System.exit(0);
    }
}
