package app.dictionary;

import java.io.*;
import java.util.Scanner;

public class DictionaryManagement extends Dictionary{
    Scanner sc = new Scanner(System.in);
    public String inputFileName = "input.txt";
    public String outputFileName = "input.txt";

    public void insertFromCommandline() {
        Word word = new Word();
        System.out.print("Word target: ");
        word.setWord_target(sc.nextLine());
        System.out.print("Word explain: ");
        word.setWord_explain(sc.nextLine());
        words[numOfWord++] = word;
        dictionaryExportToFile();
    }

    public String filePath(String file) {
        return new File("").getAbsolutePath() + "/src/main/resources/data/" + file;
    }

    public void insertFromFile() {
        try {
            //String f = new File("").getAbsolutePath() + "/src/main/resources/data/input.txt";
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
                    //Word input = new Word(line.split("\\t")[0], line.split("\\t")[1]);
                    words[numOfWord++] = input;
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
            //String f = new File("").getAbsolutePath() + "/src/main/resources/data/input.txt";
            FileWriter fw = new FileWriter(filePath(outputFileName));
            BufferedWriter bw = new BufferedWriter(fw);
            int count = 0;
            while(count < numOfWord) {
                bw.write(words[count].getWord_target()
                        + "\t" + words[count++].getWord_explain() + "\n");
            }
            fw.close();
            bw.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public Word dictionaryLookup(String wordLookup) {
        wordLookup = wordLookup.toLowerCase().trim();
        for (int i = 0; i < numOfWord; i++) {
            if (wordLookup.equals(words[i].getWord_target())) {
                return words[i];
            }
        }
        return null;
    }

    public void deleteWord(String word) {
        for(int i = 0; i < numOfWord; i++) {
            if(word.equals(words[i].getWord_target())) {
                for(int j = i; j < numOfWord - 1; j++) {
                    words[j] = words[j + 1];
                }
                words[numOfWord] = null;
                numOfWord--;
                dictionaryManagement.dictionaryExportToFile();
                return;
            }
        }
        System.out.println("Cannot found: " + word);
    }

    public void editWord() {
        System.out.print("Edit word: ");
        String word = sc.nextLine();
        for(int i = 0; i < numOfWord; i++) {
            if(word.equals(words[i].getWord_target())) {
                System.out.print("Repair word target: ");
                words[i].setWord_target(sc.nextLine());
                System.out.print("Repair word explain: ");
                words[i].setWord_explain(sc.nextLine());
                dictionaryManagement.dictionaryExportToFile();
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

