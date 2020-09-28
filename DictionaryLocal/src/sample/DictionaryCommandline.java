package sample;

public class DictionaryCommandline {
    Dictionary dictionary;
    private static int noLength = 8;
    private static int engLength = 31;

    private  String no_length(int n) {
        String res = "";
        for(int i = 0; i < noLength - Integer.toString(n).length(); i++) {
            res += " ";
        }
        return res;
    }

    private String eng_length(String key) {
        String res = "";
        for(int i = 0; i < engLength - key.length(); i++) {
            res += " ";
        }
        return res;
    }

    DictionaryCommandline(Dictionary dictionary) {
        this.dictionary = dictionary;
    }

    public void showAllWords() {
        System.out.println("No      " + "| " + "English                        " + "| " + "Vietnamese" + "\n"
                + "--------" + "|-" + "-------------------------------" + "|-" + "--------------");
        for (int i = 0; i < dictionary.numOfWord; i++) {
            System.out.println(i + 1 + no_length(i + 1)
                    + "| " + dictionary.words[i].getWord_target() + eng_length(dictionary.words[i].getWord_target())
                    + "| " + dictionary.words[i].getWord_explain());
        }
    }

    public void dictionaryBasic() {
        dictionary.dictionaryManagement.insertFromCommandline();
        showAllWords();
    }

    public void dictionaryAdvance() {
        dictionary.dictionaryManagement.insertFromFile();
        showAllWords();
        dictionary.dictionaryManagement.dictionaryLookup();
    }
}
