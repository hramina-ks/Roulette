package sample;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import jdk.internal.org.objectweb.asm.Handle;

import java.awt.event.MouseEvent;

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

    public class Bet {
        private CheckBox bet;

        public void forBet(CheckBox[] group, int finalI, AnchorPane resultBlock) {
            for (CheckBox j : group) {
                if (j.equals(bet)) {
                    j.setSelected(false);
                }
            }
            ObservableList resultLabels = resultBlock.getChildren();
            Label label_num = (Label)resultLabels.get(0);
            Label label_win = (Label)resultLabels.get(1);

            if (group[finalI].isSelected()) {
                bet = group[finalI];
                resultBlock.setStyle("-fx-border-color: black;");
                label_num.setStyle("-fx-text-fill: black");
                label_win.setStyle("-fx-text-fill: black");
            }
            else {
                bet = null;
                resultBlock.setStyle("-fx-border-color: gray");
                label_num.setStyle("-fx-text-fill: gray");
                label_win.setStyle("-fx-text-fill: gray");
            }
        }

        public CheckBox startBet(CheckBox[] group) {
            for (CheckBox i : group){
                if (i.isSelected()) {
                    bet = i;
                    break;
                }
            }
            return bet;
        }
    }

        public CheckBox numberBet = null;
        public CheckBox colorBet = null;
        public CheckBox evenBet = null;


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

        ObservableList resultBlocks =  result.getChildren(); //это блоки результатов
        AnchorPane resultNum = (AnchorPane) resultBlocks.get(0);
        AnchorPane resultCol = (AnchorPane) resultBlocks.get(2);
        AnchorPane resultEven = (AnchorPane) resultBlocks.get(1);

        Bet Bet = new Bet();

        for (int i = 0; i < numbersGroup.length; i++) {
            int finalI = i;
            numbersGroup[i].selectedProperty().addListener(new ChangeListener<Boolean>() {
                @Override
                public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                    Bet.forBet(numbersGroup, finalI,resultNum);
                }
            });
        }

        btn_start.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                numberBet = Bet.startBet(numbersGroup);
                System.out.println(numberBet);
            }
        });
    }
}