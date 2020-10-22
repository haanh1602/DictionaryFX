package app.dictionary;

import helper.wordComparator;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class DictionaryManagement {
    protected Dictionary dictionary;

    Scanner sc = new Scanner(System.in);
    public String inputFileName = "input.txt";
    public String outputFileName = "input.txt";

    public DictionaryManagement(Dictionary dictionary) {
        this.dictionary = dictionary;
    }

    public void insertFromCommandline() {
        Word word = new Word();
        System.out.print("Word target: ");
        word.setWord_target(sc.nextLine());
        System.out.print("Word explain: ");
        word.setWord_explain(sc.nextLine());
        dictionary.words.add(word);
        dictionary.numOfWord++;
        dictionaryExportToFile();
    }

    public void addWordToFile(String word_target, String word_explain, String pronounce) {
        Word word = new Word(word_target, word_explain, pronounce);
        dictionary.words.add(word);
        dictionary.words = sortWords(dictionary.words);
        dictionary.numOfWord++;
        dictionaryExportToFile();
    }

    public ArrayList sortWords(ArrayList<Word> words) {
        Collections.sort(words, new wordComparator());
        return words;
    }

    public String filePath(String file) {
        return new File("").getAbsolutePath() + "/src/main/resources/data/" + file;
    }

    public void getFile(String inputFileName, String outputFileName) {
        this.inputFileName = inputFileName;
        this.outputFileName = outputFileName;
    }

    public void getFile(String fileName) {
        this.getFile(fileName, fileName);
    }

    public void insertFromFile() {
        try {
            FileReader fr = new FileReader(filePath(inputFileName));
            BufferedReader br = new BufferedReader(fr);
            String line;
            while((line = br.readLine()) != null) {
                line = line.trim();
                if(!line.equals("")) {
                    String[] word = line.split("\\t");
                    Word input = new Word(word[0], "null", "null");
                    if (word.length > 1) {
                        input.setWord_explain(word[1]);
                        if(word.length > 2) {
                            input.setPronounce(word[2]);
                        }
                    }
                    dictionary.words.add(input);
                    dictionary.numOfWord++;
                }
            }
            fr.close();
            br.close();
        } catch (Exception e) {
            System.out.println("Cannot insertFromFile!");
            System.out.println(e.getMessage());
        }
    }

    public void dictionaryExportToFile() {
        try {
            FileWriter fw = new FileWriter(filePath(outputFileName));
            BufferedWriter bw = new BufferedWriter(fw);
            int count = 0;
            while(count < dictionary.numOfWord) {
                bw.write(dictionary.words.get(count).getWord_target()
                        + "\t" + dictionary.words.get(count).getWord_explain()
                        + "\t" + dictionary.words.get(count++).getPronounce() + "\n");
            }
            bw.close();
            fw.close();
        } catch (Exception e) {
            System.out.println("Cannot exportToFile");
            System.out.println(e.getMessage());
        }
    }

    public Word dictionaryLookup(String wordLookup) {
        wordLookup = wordLookup.toLowerCase().trim();
        for (int i = 0; i < dictionary.numOfWord; i++) {
            if (wordLookup.equals(dictionary.words.get(i).getWord_target())) {
                return dictionary.words.get(i);
            }
        }
        return null;
    }

    public int dictionaryLookupIndex(String wordLookup) {
        wordLookup = wordLookup.toLowerCase().trim();
        for (int i = 0; i < dictionary.numOfWord; i++) {
            if (wordLookup.equals(dictionary.words.get(i).getWord_target())) {
                return i;
            }
        }
        return -1;
    }

    public void deleteWord(String word) {
        for(int i = 0; i < dictionary.numOfWord; i++) {
            if(word.equals(dictionary.words.get(i).getWord_target())) {
                dictionary.words.remove(i);
                dictionary.numOfWord--;
                dictionary.dictionaryManagement.dictionaryExportToFile();
                return;
            }
        }
        System.out.println("Cannot found: " + word);
    }

    public boolean deleteWord_FX(String word) {
        for(int i = 0; i < dictionary.numOfWord; i++) {
            if(word.equals(dictionary.words.get(i).getWord_target())) {
                dictionary.words.remove(i);
                dictionary.numOfWord--;
                dictionary.dictionaryManagement.dictionaryExportToFile();
                return true;
            }
        }
        return false;
    }

    public void editWord() {
        System.out.print("Edit word: ");
        String word = sc.nextLine();
        for(int i = 0; i < dictionary.numOfWord; i++) {
            if(word.equals(dictionary.words.get(i).getWord_target())) {
                System.out.print("Repair word target: ");
                dictionary.words.get(i).setWord_target(sc.nextLine());
                System.out.print("Repair word explain: ");
                dictionary.words.get(i).setWord_explain(sc.nextLine());
                System.out.print("Repair pronounce: ");
                dictionary.words.get(i).setPronounce(sc.nextLine());
                dictionary.dictionaryManagement.dictionaryExportToFile();
                return;
            }
        }
        System.out.println("Cannot found: " + word);
    }

    public boolean editWord(String wordBeEdited, Word word) {
        for(int i = 0; i < dictionary.numOfWord; i++) {
            if(wordBeEdited.equals(dictionary.words.get(i).getWord_target())) {
                dictionary.words.get(i).setWord_target(word.word_target);
                dictionary.words.get(i).setWord_explain(word.word_explain);
                dictionary.words.get(i).setPronounce(word.pronounce);
                dictionaryExportToFile();
                return true;
            }
        }
        return false;
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

