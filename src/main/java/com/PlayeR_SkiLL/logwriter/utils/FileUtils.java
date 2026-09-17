package com.PlayeR_SkiLL.logwriter.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileUtils {

    public static void appendLine(File file, String line) throws IOException {
        if (!file.exists()) {
            file.createNewFile();
        }
        try (FileWriter fw = new FileWriter(file, true)) {
            fw.write(line);
            fw.write(System.lineSeparator());
        }
    }
}
