package ru.ifmo.cet.javabasics;
import java.io.IOException;
import java.util.*;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
public class WarAndPeaceExercise {

    public static String warAndPeace()
            throws IOException {
        //считывание в мапу
        final Path tome12Path = Paths.get("src", "main", "resources", "WAP12.txt");
        final Path tome34Path = Paths.get("src", "main", "resources", "WAP34.txt");
        HashMap < String, Integer > vocabulary = new HashMap < > ();
        final Charset charset = Charset.forName("windows-1251");
        List < String > strings = Files.readAllLines(tome12Path, charset);
        strings.addAll(Files.readAllLines(tome34Path, charset));
        for (String string: strings) {
            string = string.toLowerCase();
            string = string.replaceAll("[^a-z\u0430-\u044f]", " ");
            String[] words = string.split(" ");
            for (String word: words) {
                if (word.length() < 4) continue;
                Integer count = vocabulary.get(word);
                if (vocabulary.get(word) == null) {
                    vocabulary.put(word, 1);
                } else {
                    vocabulary.put(word, ++count);
                }
            }
        }
        //удаление слов<10
        Iterator < HashMap.Entry < String, Integer >> it;
        for (it = vocabulary.entrySet().iterator(); it.hasNext();) {
            HashMap.Entry < String, Integer > entry = it.next();
            if (entry.getValue() < 10) {
                it.remove();
            }
        }
        ArrayList < String > res_array = new ArrayList < > ();
        while (!vocabulary.isEmpty()) {
            HashMap.Entry < String, Integer > max = vocabulary.entrySet().iterator().next();
            for (Map.Entry < String, Integer > pair: vocabulary.entrySet()) {
                if (max.getValue() < pair.getValue()) {
                    max = pair;
                } else if (max.getValue().equals(pair.getValue()) && max.getKey().compareTo(pair.getKey()) > 0) {
                    max = pair;
                }
            }
            String string = max.getKey() + " - " + max.getValue();
            res_array.add(string);
            vocabulary.remove(max.getKey());
        }

        String res_string = "";
        for (String s: res_array) {
            res_string += s + "\n";
        }
        res_string = res_string.substring(0, res_string.length() - 1);
        return res_string;
    }
}