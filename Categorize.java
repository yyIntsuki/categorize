import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Categorize {
    /* Global variables */
    static String srcPath;
    static String tgtPath;

    /* Alphanumeric check */
    private static boolean isAlphaNumeric(String s) {
        return s != null && s.matches("^[a-zA-Z0-9_]*$");
    }

    /* Gets all the alphanumerical files from specified path */
    private static List<String> getSrc(String path) throws IOException {
        File folder = new File(path);
        File[] files = folder.listFiles();
        ArrayList<String> fileNames = new ArrayList<>();

        for (int i = 0; i < files.length; i++) {
            if (files[i].isFile() && isAlphaNumeric(files[i].getName().split("-", -1)[0])) {
                fileNames.add(files[i].getCanonicalPath());
            }
        }
        return fileNames;
    }

    /* Gets all the folders from specified path */
    private static List<String> getTgt(String path) throws IOException {
        File folder = new File(path);
        File[] files = folder.listFiles();
        ArrayList<String> folderNames = new ArrayList<>();

        for (int i = 0; i < files.length; i++) {
            folderNames.add(files[i].getCanonicalPath());
        }
        return folderNames;
    }

    /* Matches file from source to target folders */
    private static void match(List<String> source, List<String> target) throws IOException {
        String filter;
        /* Move files if matches filter */
        for (String file : source) {
            filter = file.replace(srcPath, "").split("-", -1)[0];
            for (String folder : target) {
                if (folder.contains(filter)) {
                    System.out.println("moving " + file + "\nto " + folder);
                    move(file, folder + "\\" + file.replace(srcPath, ""));
                }
            }
        }
    }

    /* Move file */
    private static void move(String source, String target) throws IOException {
        Files.move(Paths.get(source), Paths.get(target), StandardCopyOption.REPLACE_EXISTING);
    }

    public static void main(String[] args) throws IOException {
        List<String> sourceFiles = getSrc(srcPath);
        List<String> targetFolders = getTgt(tgtPath);
        match(sourceFiles, targetFolders);
    }
}