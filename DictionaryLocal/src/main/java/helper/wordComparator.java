package helper;

import app.dictionary.Word;

import java.util.Comparator;

public class wordComparator implements Comparator<Word> {

    @Override
    public int compare(Word o1, Word o2) {
        return o1.getWord_target().compareTo(o2.getWord_target());
    }
}
