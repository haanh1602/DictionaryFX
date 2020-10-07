package app.dictionary;

public class Dictionary {
    public Word[] words = new Word[100000000];
    public int numOfWord = 0;
    DictionaryCommandline dictionaryCommandline = new DictionaryCommandline(this);
    public DictionaryManagement dictionaryManagement = new DictionaryManagement(this);
}

