package app.dictionary;

public class Word {
    String word_target;
    String word_explain;
    String pronounce;

    public String getWord_target() {
        return word_target;
    }

    public void setWord_target(String word_target) {
        this.word_target = word_target;
    }

    public String getWord_explain() {
        return word_explain;
    }

    public void setWord_explain(String word_explain) {
        this.word_explain = word_explain;
    }

    public String getPronounce() {
        return pronounce;
    }

    public void setPronounce(String pronounce) {
        this.pronounce = pronounce;
    }

    public Word() { }

    public Word(String word_target, String word_explain, String pronounce) {
        this.word_target = word_target;
        this.word_explain = word_explain;
        this.pronounce = pronounce;
    }
}
