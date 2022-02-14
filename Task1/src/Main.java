import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args){
        String filename = args[0];
        try(Reader reader = new InputStreamReader(new FileInputStream(filename))) {
            int wordCount = 0;
            Map<String, Word> set = new HashMap<>();
            StringBuilder str = new StringBuilder();
            int symbolNum;
            do{
                symbolNum = reader.read();
                char symbol = (char)symbolNum;
                if(Character.isLetterOrDigit(symbol)){
                    str.append(symbol);
                }
                else {
                    String key = str.toString();
                    Word newWord = set.get(key);
                    if (newWord == null) {
                        newWord = new Word(key);
                        set.put(key, newWord);
                    } else {
                        newWord.freq++;
                    }
                    wordCount++;
                    str.setLength(0);
                }
            }while(symbolNum != -1);

            ArrayList<Word> list = new ArrayList<>(set.values());
            Collections.sort(list);
            for(Word w : list){
                System.out.printf("%s %d %.1f%%%n", w.word, w.freq, w.freq * 100.0f / wordCount);
            }
        }
        catch(IOException e) {
            System.err.println("Error while reading file:" + e.getLocalizedMessage());
        }

    }
}
