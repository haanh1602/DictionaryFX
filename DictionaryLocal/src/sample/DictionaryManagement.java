package sample;

import java.io.*;
import java.util.Scanner;

public class DictionaryManagement {
    Dictionary dictionary;
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
            String f = new File("").getAbsolutePath() + "/src/sample/input.txt";
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
            String f = new File("").getAbsolutePath() + "/src/sample/input.txt";
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

    public String dictionaryLookup(String wordLookup) {
        //String lookup = sc.nextLine();
        /*if (lookup.equals("Look up")) {
            System.out.print("Type in your word: ");
            String wordLookup = sc.nextLine();*/
            for (int i = 0; i < dictionary.numOfWord; i++) {
                if (wordLookup.equals(dictionary.words[i].getWord_target())) {
                    /*System.out.println("The word you looking for: "
                            + dictionary.words[i].getWord_target() + " - " + dictionary.words[i].getWord_explain());*/
                    return dictionary.words[i].getWord_explain();
                }
            }
            //System.out.println("Not found!");
            return "Not found!";
        //}
    }

    public void deleteWord() {
        System.out.print("Delete word: ");
        String wordDeleted = sc.nextLine();
        for(int i = 0; i < dictionary.numOfWord; i++) {
            if(wordDeleted.equals(dictionary.words[i].getWord_target())) {
                for(int j = i; j < dictionary.numOfWord - 1; j++) {
                    dictionary.words[j] = dictionary.words[j + 1];
                }
                dictionary.words[dictionary.numOfWord] = null;
                dictionary.numOfWord--;
                dictionary.dictionaryManagement.dictionaryExportToFile();
                return;
            }
        }
        System.out.println("Cannot found: " + wordDeleted);
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
                deleteWord();
                break;
            case "3":
                editWord();
                break;
            default:
                System.out.println("Wrong key!");
        }
    }
}

