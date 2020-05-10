package sample;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

public class Controller {
    @FXML
    private Button btn_start; //кнопка старт
    @FXML
    private GridPane numbers; //блок с цифрами
    @FXML
    private GridPane colors; //блок цвета
    @FXML
    private GridPane even; //блок четности

    @FXML
    private GridPane result; //блок результатов

    public class bets {
        public CheckBox Bet;
    }

    public void initialize() {
        CheckBox[] numbersGroup = new CheckBox[numbers.getChildren().size()]; //массив по количеству чекбоксов с цифрами
        for (int i = 0; i < numbers.getChildren().size(); i++) {
            numbersGroup[i] = (CheckBox)numbers.getChildren().get(i); //записываем числа в массив
        }

        CheckBox[] colorsGroup = new CheckBox[colors.getChildren().size()]; //массив чекбоксов с цветами
        for (int i = 0; i < colors.getChildren().size(); i++) {
            colorsGroup[i] = (CheckBox)colors.getChildren().get(i); //записываем цвета в массив
        }

        CheckBox[] evenGroup = new CheckBox[even.getChildren().size()];
        for (int i = 0; i < even.getChildren().size(); i++) {
            evenGroup[i] = (CheckBox)even.getChildren().get(i); //записываем цвета в массив
        }

        

        bets numberBet = new bets();
        numberBet.Bet = null;

        for (int i = 0; i < numbersGroup.length; i++) {
            int finalI = i;
            numbersGroup[i].selectedProperty().addListener(new ChangeListener<Boolean>() {
                @Override
                public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                    for (CheckBox j : numbersGroup) {
                        if (j.equals(numberBet.Bet)) {
                            j.setSelected(false);
                        }
                    }
                    if (numbersGroup[finalI].isSelected()) {
                        numberBet.Bet = numbersGroup[finalI];
                    }
                    else {
                        numberBet.Bet = null;
                    }
                }
            });
        }
    }
}