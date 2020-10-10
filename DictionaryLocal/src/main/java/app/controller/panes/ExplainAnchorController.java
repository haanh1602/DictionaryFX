package app.controller.panes;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;

public class ExplainAnchorController extends ContentController {

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
}
