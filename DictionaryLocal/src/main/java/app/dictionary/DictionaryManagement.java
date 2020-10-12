package app.dictionary;

import java.io.*;
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
                    Word input = new Word();
                    input.setWord_target(word[0]);
                    if (word.length > 1) {
                        input.setWord_explain(word[1]);
                    }
                    dictionary.words.add(input);
                    dictionary.numOfWord++;
                }
            }
            fr.close();
            br.close();
        } catch (Exception e) {
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
                        + "\t" + dictionary.words.get(count++).getWord_explain() + "\n");
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
            if (wordLookup.equals(dictionary.words.get(i).getWord_target())) {
                return dictionary.words.get(i);
            }
        }
        return null;
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

    public void editWord() {
        System.out.print("Edit word: ");
        String word = sc.nextLine();
        for(int i = 0; i < dictionary.numOfWord; i++) {
            if(word.equals(dictionary.words.get(i).getWord_target())) {
                System.out.print("Repair word target: ");
                dictionary.words.get(i).setWord_target(sc.nextLine());
                System.out.print("Repair word explain: ");
                dictionary.words.get(i).setWord_explain(sc.nextLine());
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

