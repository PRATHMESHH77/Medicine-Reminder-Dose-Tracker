package medicine;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

// Package-private class and method demonstrate default access.
class FileManager {
    void makeByteBackup(String sourceName, String backupName) throws IOException {
        File source = new File(sourceName);
        if (!source.exists()) {
            System.out.println("Save the medicine list first, then create a backup.");
            return;
        }

        // Byte streams: copy file bytes from one file to another.
        try (FileInputStream input = new FileInputStream(source);
             FileOutputStream output = new FileOutputStream(backupName)) {
            int value;
            while ((value = input.read()) != -1) {
                output.write(value);
            }
        }
        System.out.println("Byte-stream backup created: " + backupName);
    }
}
