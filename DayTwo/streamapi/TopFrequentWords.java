package DayTwo.streamapi;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.Comparator;

public class TopFrequentWords {

    @FunctionalInterface
    interface WorldTransformer{
        String transform(String word);
    }

    public static void main(String[] args) throws IOException{
        Path filePath = Paths.get("DayTwo/inputfile");

        Predicate<String> isMeaningful = word -> word.length() >=3;
        WorldTransformer transformer = word -> word.toLowerCase().replaceAll("[^a-z]", "");
        int topN = 5;

        List<String> words = Files.lines(filePath)
                .flatMap(line -> Arrays.stream(line.split("\\s+")))
                .collect(Collectors.toList());

        System.out.println(words);

        Map<String, Long> wordFrequencies = words.stream()
                .map(transformer :: transform)
                .filter(isMeaningful)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));


        List<Map.Entry<String, Long>> topWords = wordFrequencies.entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue(Comparator.reverseOrder()))
                .limit(topN)
                .collect(Collectors.toList());

        // Display result
        System.out.println("Top " + topN + " frequent words:");
        topWords.forEach(entry -> System.out.println(entry.getKey() + ": " + entry.getValue()));
    }
}
