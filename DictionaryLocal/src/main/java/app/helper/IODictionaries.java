package app.helper;

import app.dictionary.Word;

import java.io.*;
import java.util.ArrayList;

public class IODictionaries {
    public ArrayList<Word> read() {
        return this.read("data/dictionaries.txt");
    }

    public ArrayList<Word> read(String path) {
        String line = null;
        String[] words;
        ArrayList<Word> ans = new ArrayList<>();

        try {
            InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream(path), "UTF8");

            BufferedReader br = new BufferedReader(inputStreamReader);

            while ((line = br.readLine()) != null) {
                words = line.split("\t");

                if (words.length >= 2) {
                    Word input = new Word(words[0], words[1]);
                    ans.add(input);
                }
            }

            br.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return ans;
    }

    public void write(ArrayList<Word> words) {
        this.write(words, "data/save_dictionaries.txt");
    }

    public void write(ArrayList<Word> words, String path) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(path);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream, "UTF8");
            BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);

            for (Word word : words) {
                bufferedWriter.write(word.getWord_target() + "\t" + word.getWord_explain());
                bufferedWriter.newLine();
            }

            bufferedWriter.flush();
            bufferedWriter.close();
        } catch (UnsupportedEncodingException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
