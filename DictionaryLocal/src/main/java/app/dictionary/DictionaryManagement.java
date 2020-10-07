package app.dictionary;

import java.io.*;
import java.util.Scanner;

public class DictionaryManagement {
    public Dictionary dictionary;
    Scanner sc = new Scanner(System.in);

    DictionaryManagement(Dictionary dictionary) {
        this.dictionary = dictionary;
    }

    public void insertFromCommandline() {
        Word word = new Word();
        System.out.print("Word target: ");
        word.setWord_target(sc.nextLine());
        System.out.print("Word explain: ");
        word.setWord_explain(sc.nextLine());
        dictionary.words[dictionary.numOfWord++] = word;
        dictionary.dictionaryManagement.dictionaryExportToFile();
    }

    public void insertFromFile() {
        try {
            String f = new File("").getAbsolutePath() + "/src/main/resources/data/input.txt";
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);
            String line;
            while((line = br.readLine()) != null) {
                line = line.trim();
                if(!line.equals("")) {
                    String[] word = line.split("\\t");
                    Word input = new Word();
                    input.setWord_target(word[0]);
                    if (word.length > 1) {
                        input.setWord_explain(word[1]);
                    }
                    //Word input = new Word(line.split("\\t")[0], line.split("\\t")[1]);
                    dictionary.words[dictionary.numOfWord++] = input;
                }
            }
            fr.close();
            br.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            //System.out.println(String.valueOf(getClass().getResourceAsStream("/sample/input.txt")));
        }
    }

    public void dictionaryExportToFile() {
        try {
            String f = new File("").getAbsolutePath() + "/src/main/resources/data/input.txt";
            FileWriter fw = new FileWriter(f);
            BufferedWriter bw = new BufferedWriter(new FileWriter(f));
            int count = 0;
            while(count < dictionary.numOfWord) {
                bw.write(dictionary.words[count].getWord_target()
                        + "\t" + dictionary.words[count++].getWord_explain() + "\n");
            }
            fw.close();
            bw.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public Word dictionaryLookup(String wordLookup) {
        wordLookup = wordLookup.toLowerCase().trim();
        for (int i = 0; i < dictionary.numOfWord; i++) {
            if (wordLookup.equals(dictionary.words[i].getWord_target())) {
                return dictionary.words[i];
            }
        }
        return null;
    }

    public void deleteWord(String word) {
        for(int i = 0; i < dictionary.numOfWord; i++) {
            if(word.equals(dictionary.words[i].getWord_target())) {
                for(int j = i; j < dictionary.numOfWord - 1; j++) {
                    dictionary.words[j] = dictionary.words[j + 1];
                }
                dictionary.words[dictionary.numOfWord] = null;
                dictionary.numOfWord--;
                dictionary.dictionaryManagement.dictionaryExportToFile();
                return;
            }
        }
        System.out.println("Cannot found: " + word);
    }

    public void editWord() {
        System.out.print("Edit word: ");
        String word = sc.nextLine();
        for(int i = 0; i < dictionary.numOfWord; i++) {
            if(word.equals(dictionary.words[i].getWord_target())) {
                System.out.print("Repair word target: ");
                dictionary.words[i].setWord_target(sc.nextLine());
                System.out.print("Repair word explain: ");
                dictionary.words[i].setWord_explain(sc.nextLine());
                dictionary.dictionaryManagement.dictionaryExportToFile();
                return;
            }
        }
        System.out.println("Cannot found: " + word);
    }

    public void setting() {
        System.out.println("Option: \n[1] Add / [2] Delete / [3] Edit");
        String option = sc.nextLine().trim();
        switch (option) {
            case "1":
                insertFromCommandline();
                break;
            case "2":
                deleteWord(" ");
                break;
            case "3":
                editWord();
                break;
            default:
                System.out.println("Wrong key!");
        }
    }
}

