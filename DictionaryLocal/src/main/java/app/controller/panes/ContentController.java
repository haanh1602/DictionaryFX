package app.controller.panes;

import app.dictionary.Dictionary;
import helper.GoogleAPI;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class ContentController {
    protected Dictionary dictionary = new Dictionary();

    @FXML
    protected TextField word_target;
    @FXML
    protected ListView<String> search_list;
    @FXML
    protected AnchorPane explainArea;

    protected ExplainAnchorController explainAnchorController;

    public void resetListViewWords() {
        search_list.getItems().clear();
    }

    public void selectItemListView(MouseEvent event) {
        String selectedItem = search_list.getSelectionModel().getSelectedItem();
        if(selectedItem == null) {
            return;
        }
        word_target.setText(selectedItem);
        try {
            translate_fixed();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void translate_fixed() throws IOException {
        String[] result = GoogleAPI.translate("en", "vi", word_target.getText()).split("\n");
        if(result.length < 2) {
            explainAnchorController.reset();
            return;
        }
        String explainWord = result[0];
        String pronounce = result[1];
        explainAnchorController.loadData(word_target.getText(), explainWord, pronounce);
    }

    public void getListViewSearchFromFile(String fileName) {
        resetListViewWords();
        resetDictionary(fileName);
        dictionary.dictionaryManagement.insertFromFile();
        ArrayList<String> listView = new ArrayList<>();
        int i;
        for(i = 0; i < dictionary.numOfWord; i++) {
            if(dictionary.words.get(i).getWord_target().trim().startsWith(word_target.getText().trim())) {
                break;
            }
        }
        for(int j = i; j < dictionary.numOfWord; j++) {
            if(dictionary.words.get(j).getWord_target().trim().startsWith(word_target.getText().trim())) {
                listView.add(dictionary.words.get(j).getWord_target().trim());
            } else break;
        }
        search_list.getItems().addAll(listView);
    }

    public ArrayList sortList(ArrayList<String> arrayList) {
        Collections.sort(arrayList);
        return arrayList;
    }


    public boolean isExist(String word_target, Dictionary dictionary) {
        return (dictionary.dictionaryManagement.dictionaryLookup(word_target) != null);
    }

    public void addHistory() {
        if(word_target.getText().trim().equals("")) return;
        Dictionary history = new Dictionary();
        history.dictionaryManagement.getFile("History.txt");
        history.dictionaryManagement.insertFromFile();
        if(!isExist(word_target.getText(), history)) {
            try {
                String[] result = GoogleAPI.translate(
                        "en", "vi", word_target.getText()).split("\n");
                String word_explain = result[0];
                String pronounce = result[1];
                history.dictionaryManagement.addWordToFile(word_target.getText().trim(), word_explain, pronounce);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void resetData() {
        word_target.setText("");
        this.resetListViewWords();
        explainAnchorController.reset();
    }

    public void resetFile(String fileName) {
        String filePath = new File("").getAbsolutePath() + "/src/main/resources/data/" + fileName;
        try {
            FileWriter fw = new FileWriter(new File(filePath));
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write("");
            bw.close();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void resetDictionary(String fileName) {
        dictionary = new Dictionary(fileName);
    }

    public void initExplainArea() {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("../../../interface/ExplainAnchor.fxml"));
        AnchorPane explain;
        try {
            explain = fxmlLoader.load();
        } catch (IOException e) {
            System.out.println("Error load view word pane.");
            return;
        }
        explainArea.getChildren().addAll(explain);
        explainAnchorController = fxmlLoader.getController();
    }

}
