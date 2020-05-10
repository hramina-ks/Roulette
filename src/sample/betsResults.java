/*Этот класс отвечает за отрисовку блоков с результатом, когда они становятся активны (игрок кликнул на одну из радиокнопок)*/

package sample;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;

final class betsResults {
    private final String text;
    private final Label bet1;

    public betsResults() {
        this.bet1 = new Label();
        this.text = new String();
    }
    public Label getBet1 (AnchorPane y) {
        y.setDisable(!y.isDisabled());
        if (!y.isDisabled()){
            y.setStyle("-fx-border-color: black;");
        }
        else {
            y.setStyle("-fx-border-color: gray;");
        }
        ObservableList<Node> result1 = y.getChildren();
        Label bet1 = (Label)result1.get(0);
        return bet1;
    }

    public String gettext(ToggleGroup x) {
        Toggle numbersSelect = x.getSelectedToggle();
        RadioButton text = (RadioButton)numbersSelect;
        return text.getText();
    }
}
