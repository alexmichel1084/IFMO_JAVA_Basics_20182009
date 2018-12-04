package ru.ifmo.cet.javabasics;
import java.io.IOException;
import java.util.*;
import java.io.File;
public class WarAndPeaceExercise {
    public static String warAndPeace() throws IOException {
//reading on map
        Scanner in = new Scanner(new File("src/main/resources/WAP12.txt"));
        HashMap<String,Integer> vocabulary= new HashMap<>();
        readWords(in, vocabulary);
        in = new Scanner(new File("src/main/resources/WAP34.txt"));
        readWords(in, vocabulary);
//delete words<10
        Iterator<HashMap.Entry<String, Integer>> it;//using only next for
        for(it=vocabulary.entrySet().iterator();it.hasNext();) {
            HashMap.Entry<String, Integer> entry = it.next();
            if (entry.getValue() < 10) {
                it.remove();
            }
        }
        ArrayList<String> res_array= new ArrayList<>();
        while (!vocabulary.isEmpty()){
            HashMap.Entry<String, Integer> max=vocabulary.entrySet().iterator().next();
            for (Map.Entry<String, Integer> pair : vocabulary.entrySet()) {
                if (max.getValue() < pair.getValue()) {
                    max = pair;
                } else if (max.getValue().equals(pair.getValue())) {
                    if (max.getKey().compareTo(pair.getKey()) > 0) {
                        max = pair;
                    }
                }
            }
            String string=max.getKey()+ " - "+max.getValue();
            res_array.add(string);
            vocabulary.remove(max.getKey());
        }
        String res_string="";
        for(String s:res_array) {
            res_string+=s+"\n";
        }
            res_string = res_string.substring(0, res_string.length() - 1);
        return res_string;
    }
    private static void readWords(Scanner in, HashMap<String, Integer> vocabulary) {
        while(in.hasNext()){
            String[] words= (in.nextLine().toLowerCase().replaceAll("[^a-z\u0430-\u044f]", " ")).split(" ");
            for (String word:words) {
                if(word.length()<4) continue;
                Integer value=vocabulary.get(word);
                if (vocabulary.get(word) == null) {
                    vocabulary.put(word,1);
                }else{
                    vocabulary.put(word,++value);
                }
            }
        }
    }
}