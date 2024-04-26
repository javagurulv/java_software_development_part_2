package org.javaguru.travel.insurance;

import org.apache.tomcat.util.http.fileupload.FileUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DeleteBuildDirectories {

    public static void main(String[] args) {

        for (int i = 0; i < 188; i++) {
            String stepDirPath = "C:\\javaguru\\projects\\java_software_development_part_1\\teacher\\step_";

            stepDirPath = stepDirPath + i;
            String stepLogDirPath = stepDirPath + "\\build";
            deleteDirIfExist(stepLogDirPath);
        }

    }

    private static void deleteDirIfExist(String dirPath) {
        try {
            // Create Path object for log directory
            Path logDirPath = Paths.get(dirPath);

            // Delete the directory
            if (Files.exists(logDirPath)) {
                FileUtils.deleteDirectory(logDirPath.toFile());
            }

            System.out.println("Directory deleted successfully " + dirPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
