package DayTwo.streamapi;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class FileProcessor {

    public static void main(String[] args) {
        Path path = Paths.get("DayTwo/inputfile");
        Map<String,Integer> wordCount = new HashMap<>();
        Scanner scanner = null;

        try{
            if(!Files.exists(path)){
                throw new FileNotFoundException("File not found: "+path.toAbsolutePath());
            }

            // read all lines
            List<String> lines = Files.readAllLines(path);
            scanner = new Scanner(String.join(" ",lines));

            while (scanner.hasNext()) {
                String word = scanner.next().toLowerCase().replaceAll("[^a-z]", "");
                if (word.isEmpty()) {
                    throw new WordParsingException("Invalid word encountered during parsing.");
                }
                wordCount.put(word, wordCount.getOrDefault(word, 0) + 1);
            }

            // Print word frequencies
            wordCount.entrySet().stream()
                    .sorted(Map.Entry.<String, Integer>comparingByValue(Comparator.reverseOrder()))
                    .limit(5)
                    .forEach(e -> System.out.println(e.getKey() + ": " + e.getValue()));

        } catch (FileNotFoundException e) {
            System.err.println("Error: " + e.getMessage());
        } catch (WordParsingException e) {
            System.err.println("Parsing error: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("I/O error: " + e.getMessage());
        } finally {
            if (scanner != null) {
                scanner.close();
            }
            System.out.println("File processing complete.");
        }
    }
}
