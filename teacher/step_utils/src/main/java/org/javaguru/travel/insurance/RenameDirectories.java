package org.javaguru.travel.insurance;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;


public class RenameDirectories {

    public static void main(String[] args) {

        for (int i = 16; i >= 1; i--) {
            String sourceDirPath = "C:\\javaguru\\projects\\java_software_development_part_2\\teacher\\step_";
            String targetDirPath = "C:\\javaguru\\projects\\java_software_development_part_2\\teacher\\step_";

            sourceDirPath = sourceDirPath + i;
            targetDirPath = targetDirPath + (i + 2);
            renameDir(sourceDirPath, targetDirPath);
        }

    }

    private static void renameDir(String sourceDirPath,
                                  String targetDirPath) {
        try {
            // Create Path objects for source and target directories
            Path sourceDir = Paths.get(sourceDirPath);
            Path targetDir = Paths.get(targetDirPath);

            // Rename the directory
            Files.move(sourceDir, targetDir, StandardCopyOption.REPLACE_EXISTING);

            System.out.println("Directory renamed successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
