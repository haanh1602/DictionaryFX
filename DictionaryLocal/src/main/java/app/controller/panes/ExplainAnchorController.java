package app.controller.panes;

import app.dictionary.Dictionary;
import app.dictionary.Word;
import helper.GoogleAPI;
import helper.wordComparator;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.awt.*;
import java.io.*;
import java.net.URL;
import java.util.Collection;
import java.util.Collections;
import java.util.ResourceBundle;

public class ExplainAnchorController implements Initializable {
    @FXML
    private Label word_explain;
    @FXML
    private Label word_target;
    @FXML
    private Label pronounce;
    @FXML
    private ImageView mark;

    public ExplainAnchorController() {
    }

    @FXML
    public void SpeakEn (MouseEvent event) {
        String spelling = word_target.getText().trim();
        speakEn(spelling);
    }

    @FXML
    public void SpeakVi (MouseEvent event) {
        String spelling = word_explain.getText().trim();
        speakVi(spelling);
    }

    public void speakEn (String word) {
        if(word.equals("")) {
            word = "Please enter something!";
        }
        try {
            GoogleAPI.speak("en", word);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void speakVi (String word) {
        if(word.equals("")) {
            word = "Xin hãy nhập gì đó!";
        }
        try {
            GoogleAPI.speak("vi", word);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void marked(MouseEvent event) {
        if(word_target.getText().trim().equals("")) return;
        Word word = new Word(word_target.getText(), word_explain.getText(), pronounce.getText());
        if(!isMarked(word)) {
            this.setMarked();
            Dictionary bookmark = new Dictionary("Bookmark.txt");
            bookmark.dictionaryManagement.insertFromFile();
            bookmark.dictionaryManagement.addWordToFile(
                    word_target.getText().trim(), word_explain.getText(), pronounce.getText());
        }
        else {
            this.setNotMark();
            Dictionary bookmark = new Dictionary("Bookmark.txt");
            bookmark.dictionaryManagement.insertFromFile();
            for(int i = 0; i < bookmark.numOfWord; i++) {
                if(word_target.getText().trim().equalsIgnoreCase(bookmark.words.get(i).getWord_target())) {
                    bookmark.words.remove(i);
                    break;
                }
            }
            bookmark.numOfWord--;
            bookmark.dictionaryManagement.dictionaryExportToFile();
        }
    }

    public void loadData(String word_target, String word_explain, String pronounce) {
        this.word_target.setText(word_target);
        this.word_explain.setText(word_explain);
        this.pronounce.setText(pronounce);
        if(isMarked(new Word(word_target, word_explain, pronounce))) {
            this.setMarked();
        } else {
            this.setNotMark();
        }
    }

    public void setMarked() {
        this.mark.setImage(new Image(String.valueOf(getClass().getResource("/data/icons/marked.png"))));
        this.mark.setStyle("-fx-effect: dropshadow(three-pass-box, white, 20, 0.4, 0, 0)");
    }

    public void setNotMark() {
        this.mark.setImage(new Image(String.valueOf(getClass().getResource("/data/icons/notMark.png"))));
        this.mark.setStyle(null);
    }

    public static String rightMargin(String explain, int widthPerLine) {
        if(explain.trim().equals("")) return "";
        String res = "";
        String[] words = explain.trim().split(" ");
        int count = 0;
        while(count < words.length) {
            int lengthOfLine = 0;
            if(words[count].length() >= widthPerLine) {
                res += words[count++];
            }
            else {
                while((count < words.length) && (lengthOfLine + words[count].length() < widthPerLine)) {
                    res += words[count] + " ";
                    lengthOfLine += words[count].length() + 1;
                    count++;
                }
            }
            res += "\n";
        }
        return res;
    }

    public boolean isMarked(Word word) {
        Dictionary bookmark = new Dictionary("Bookmark.txt");
        String filePath = new File("").getAbsolutePath() + "/src/main/resources/data/Bookmark.txt";
        try {
            FileReader fr = new FileReader(filePath);
            BufferedReader br = new BufferedReader(fr);
            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if(line.equals("")) break;
                String[] words = line.split("\\t");
                if(words[0].equalsIgnoreCase(word.getWord_target())) {
                    return true;
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error: Cannot process isMarked function!");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Cannot read " + filePath);
            e.printStackTrace();
        }
        return false;
    }

    public void reset() {
        this.word_target.setText("");
        this.word_explain.setText("");
        this.pronounce.setText("");
        this.setNotMark();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
