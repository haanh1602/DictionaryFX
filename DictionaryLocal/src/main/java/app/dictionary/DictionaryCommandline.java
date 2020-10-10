package app.dictionary;

public class DictionaryCommandline extends Dictionary{
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

    public void showAllWords() {
        System.out.println("No      " + "| " + "English                        " + "| " + "Vietnamese" + "\n"
                + "--------" + "|-" + "-------------------------------" + "|-" + "--------------");
        for (int i = 0; i < numOfWord; i++) {
            System.out.println(i + 1 + no_length(i + 1)
                    + "| " + words[i].getWord_target() + eng_length(words[i].getWord_target())
                    + "| " + words[i].getWord_explain());
        }
    }

    public void dictionaryBasic() {
        super.dictionaryManagement.insertFromCommandline();
        showAllWords();
    }

    public void dictionaryAdvance() {
        super.dictionaryManagement.insertFromFile();
        showAllWords();
        super.dictionaryManagement.dictionaryLookup("");
    }
}
