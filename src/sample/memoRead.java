package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import sample.datamodel.Memo;

public class memoRead {
    @FXML
    private Label labelDate;

    @FXML
    private Label labelTitle;

    @FXML
    private Label labelContent;

    public void showMemo(Memo selectedMemo){
        labelDate.setText("kuupäev "+selectedMemo.getKuupaev());
        labelTitle.setText(selectedMemo.getTiitel());
        labelContent.setText(selectedMemo.getSisu());
    }
}
