package net.kezzler.ssp.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import net.kezzler.ssp.utils.frameworkengine.OsCheck;
import org.apache.commons.lang.RandomStringUtils;

public class FrameworkFileUtils {
    private static final String CSV_CODES_PATH = "target/generated/csv/";
    private static final String ENV = System.getProperty("env");
    private static final String PROJECT_SCHEMA_DIRECTORY = "src/test/resources/environment/" + ENV + "/schemas/";

    public static String readTestSchema(final String filename) {
        try {
            return String.join("\n", Files.readAllLines(Paths.get(PROJECT_SCHEMA_DIRECTORY + filename)));
        } catch (IOException e) {
            throw new RuntimeException("Couldn't read file: " + PROJECT_SCHEMA_DIRECTORY + filename);
        }
    }

    public static String createCsvCodesFile() {
        Path outputPath = Paths.get(CSV_CODES_PATH + "code-" + RandomStringUtils.random(8, false, true) + ".csv");
        List<String> codeList = new ArrayList<>();
        for (int index = 0; index < 10; index++) {
            codeList.add(RandomStringUtils.random(15, true, true).toUpperCase());
        }
        try {
            Files.createDirectories(Paths.get(CSV_CODES_PATH));
            Files.write(outputPath, String.join("\n", codeList).getBytes());
        } catch (IOException e) {
            throw new RuntimeException("Couldn't write generated codes-*****.csv file");
        }
        return OsCheck.getOperatingSystemType().equals(OsCheck.OSType.Windows)
                ? Paths.get("").resolve(outputPath).toAbsolutePath().normalize().toString()
                : outputPath.toUri().getPath();
    }
}
