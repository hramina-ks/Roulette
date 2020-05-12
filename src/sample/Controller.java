package sample;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import java.util.Random;

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

    @FXML
    private TextField counter; //поле для счета игрока

    public class Bet {
        private CheckBox bet;

        public void forBet(CheckBox[] group,  AnchorPane resultBlock) {
            for (int i = 0; i < group.length; i++) {
                int finalI = i;
                group[i].selectedProperty().addListener(new ChangeListener<Boolean>() {
                    @Override
                    public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {for (CheckBox j : group) {
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
                });
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
        public int score = 10; //это количество очков у пользователя

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

        counter.setText("Всего очков " + score); //пишем очки в поле счетчика

        Bet BetNum = new Bet();
        BetNum.forBet(numbersGroup, resultNum);
        Bet BetCol = new Bet();
        BetCol.forBet(colorsGroup,resultCol);
        Bet BetEven = new Bet();
        BetEven.forBet(evenGroup,resultEven);

        btn_start.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                numberBet = BetNum.startBet(numbersGroup);
                colorBet = BetCol.startBet(colorsGroup);
                evenBet = BetEven.startBet(evenGroup);
                String colorBetString;
                String evenBetString;
                int [] black = new int[] {2, 4, 6, 8, 10, 11, 13, 15, 17, 20, 22, 24, 26, 28, 29, 31, 33, 35};
                int [] red = new int[] {1, 3, 5, 7, 9, 12, 14, 16, 18, 19, 21, 23, 25, 27, 30, 32, 34, 36};
                boolean isBlack = false;
                boolean isRed = false;
                boolean isEven = false; //четный

                if (numberBet != null) {
                    String numberBetString = numberBet.getId(); //id чекбокса, на который сделана ставка
                    Random r = new Random();
                    int num = r.nextInt(37); //рандомное число, выпавшее на рулетке
                    String numText = "num"+num; //приводим число к виду id чекбокса
                    ObservableList<Node> resultLabels = resultNum.getChildren();
                    Label label_num = (Label)resultLabels.get(0); //это лейбл, куда пишется выпавшее число
                    Label label_win = (Label)resultLabels.get(1); //это лейбл результата
                    for (int i = 0; i < numbersGroup.length; i++) {
                        int finalI = i;
                        if (numbersGroup[finalI].getId().equals(numText)) {
                            label_num.setText(numbersGroup[finalI].getText());
                        }
                    }
                        if (numText.equals(numberBetString)) {
                            label_win.setText("Победа");
                            label_num.setStyle("-fx-text-fill: green;");
                            label_win.setStyle("-fx-text-fill: green;");
                            resultNum.setStyle("-fx-border-color: green;");
                            score++;
                            counter.setText("Всего очков " + score);
                        }
                        else {
                            label_win.setText("Поражение");
                            label_num.setStyle("-fx-text-fill: red;");
                            label_win.setStyle("-fx-text-fill: red;");
                            resultNum.setStyle("-fx-border-color: red;");
                            score--;
                            counter.setText("Всего очков " + score);
                        }
                }

                System.out.println(numberBet);
                System.out.println(colorBet);
                System.out.println(evenBet);
            }
        });
    }
}