package app.dictionary;

import java.util.ArrayList;

public class Dictionary {
    public ArrayList<Word> words = new ArrayList<>();
    public int numOfWord = 0;
    public DictionaryCommandline dictionaryCommandline = new DictionaryCommandline(this);
    public DictionaryManagement dictionaryManagement = new DictionaryManagement(this);

    public Dictionary() { }

    public Dictionary(String fileName) {
        this.dictionaryManagement.getFile(fileName);
    }

}

