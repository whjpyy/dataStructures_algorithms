package ch07.set;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class FileOperation {

    public static boolean readFile(String filename, List<String> words) {
        Scanner scanner = null;
        try {
            String path = FileOperation.class.getClassLoader().getResource(filename).getPath();
            FileInputStream fis = new FileInputStream(new File(path));
            BufferedInputStream bs = new BufferedInputStream(fis);
            scanner = new Scanner(bs);
            scanner.useLocale(Locale.ENGLISH);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        while (scanner.hasNextLine()){
            words.addAll(Arrays.asList(scanner.nextLine().split(" ")));
        }
        return true;
    }
}
