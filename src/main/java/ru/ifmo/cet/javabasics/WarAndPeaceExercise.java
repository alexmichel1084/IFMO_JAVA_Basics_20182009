package ru.ifmo.cet.javabasics;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class WarAndPeaceExercise {

    public static String warAndPeace() throws IOException {
        final Path tome12Path = Paths.get("src", "main", "resources", "WAP12.txt");
        final Path tome34Path = Paths.get("src", "main", "resources", "WAP34.txt");
        final Charset charset = Charset.forName("windows-1251");
        List<String> string_list = Files.readAllLines(tome12Path, charset);
        string_list.addAll(Files.readAllLines(tome34Path, charset));
        List<String> words_list = new ArrayList<>();
        string_list.stream().map(line -> line.toLowerCase()).map(lowLine -> lowLine.replaceAll("[^a-z\u0430-\u044f]", " ")).
                map(lowReplaceLine -> Arrays.stream(lowReplaceLine.split(" ")).filter(word->word.length()>=4).collect(Collectors.toList())).forEach(c -> words_list.addAll(c));

        Map<String,Integer> vocabulary=new LinkedHashMap<>();
        words_list.forEach(key -> {
            vocabulary.put(key, vocabulary.containsKey(key) ? vocabulary.get(key) + 1 : 1);
        });
        vocabulary.entrySet().removeIf(pair->pair.getValue()<10);

        List<Map.Entry<String, Integer>> sorted_vocabulary = new ArrayList(vocabulary.entrySet());
        sorted_vocabulary.sort(Map.Entry.comparingByKey());
        sorted_vocabulary.sort(Map.Entry.<String, Integer>comparingByValue().reversed());

        String res_answer=sorted_vocabulary.stream().map(entry->entry.getKey() + " - " + entry.getValue()).collect(Collectors.joining("\n"));
        return res_answer;
    }
}