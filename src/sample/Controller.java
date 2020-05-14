package sample;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.image.Image;

import java.util.Random;

import javafx.scene.control.Alert;
import javafx.stage.Stage;

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

    @FXML
    private ToolBar menu_bar; //блок с кнопками

    public class Bet {
        private CheckBox bet;

        public void forBet(CheckBox[] group,  AnchorPane resultBlock) { //этот массив управляет переключением чекбоксов
            //в блоках для ставок - активирует и убирает соответствующий блок результата.
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

            if (group[finalI].isSelected()) { //ставка сделана - блок активировался
                bet = group[finalI];
                resultBlock.setStyle("-fx-border-color: black;");
                label_num.setStyle("-fx-text-fill: black");
                label_win.setStyle("-fx-text-fill: black");
                label_win.setText("Результат");
            }
            else { //ставку убрали - блок деактивировался обратно
                bet = null;
                resultBlock.setStyle("-fx-border-color: gray");
                label_num.setStyle("-fx-text-fill: gray");
                label_win.setStyle("-fx-text-fill: gray");
                label_win.setText("Результат");
            }
                    }
                });
            }
        }

        public CheckBox startBet(CheckBox[] group) { //этот метод получает ставки по клику на кнопку start
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
        public String forEven; //это число, которое выпало на рулетке, чтобы посчитать четность

    public void initialize() {
        ObservableList help_buttons = menu_bar.getItems(); //тут я получаю кнопки справки
        Button menu_btn_faq = (Button) help_buttons.get(0);
        Button menu_btn_aboutme = (Button) help_buttons.get(1);

        Tooltip faq = new Tooltip();
        faq.setText("Как это работает?");
        menu_btn_faq.setTooltip(faq);

        Tooltip aboutme = new Tooltip();
        aboutme.setText("О разработчике");
        menu_btn_aboutme.setTooltip(aboutme);

        menu_btn_faq.setOnAction(new EventHandler<ActionEvent>() { //когда кликаешь по кнопке с вопросиком, происходит это
            @Override
            public void handle(ActionEvent event) {
                Alert faq = new Alert(Alert.AlertType.INFORMATION);
                faq.setWidth(500);
                faq.setHeight(400);
                ((Stage)faq.getDialogPane().getScene().getWindow()).getIcons().add(new Image("roulette.png"));

                faq.setTitle("Об игре");
                faq.setHeaderText(null);
                faq.setContentText("Игра основана на генераторе случайных чисел, любой результат случаен.\n\n" +
                        "В игре есть три типа ставок:\n1) На число (не более одного варианта)\n" +
                        "2) На цвет (не более одного варианта)\n3) На четность (не более одного варианта)\n" +
                        "Можно делать делать одновременно 1, 2 и 3 любых ставки.\n\n" +
                        "В начале игры у вас есть 10 очков.\n" +
                        "Ставка на число при проигрыше дает -1 очкo, при выигрыше +4 очка.\n" +
                        "Ставки на цвет и на четность при проигрыше дают -1 очко, при выигрыше +1 очко.\n\n" +
                        "Максимального и минимального количества очков в игре нет. Чтобы начать с начала, запустите приложение заново.");

                faq.showAndWait();
            }
        });
        menu_btn_aboutme.setOnAction(new EventHandler<ActionEvent>() { //а когда по кнопке с буквой i - это
            @Override
            public void handle(ActionEvent event) {
                Alert aboutme = new Alert(Alert.AlertType.INFORMATION);
                aboutme.setWidth(500);
                aboutme.setHeight(150);
                ((Stage)aboutme.getDialogPane().getScene().getWindow()).getIcons().add(new Image("roulette.png"));

                aboutme.setTitle("О разработчике");
                aboutme.setHeaderText(null);
                aboutme.setContentText("Made by krist89 (Ксения Храмина)\n" +
                        "Контакты:\n" +
                        "E-mail: krist89@yandex.ru\n" +
                        "Facebook: https://www.facebook.com/ksenia.hramina");
                aboutme.showAndWait();
            }
        });


        CheckBox[] numbersGroup = new CheckBox[numbers.getChildren().size()]; //массив по количеству чекбоксов с цифрами
        for (int i = 0; i < numbers.getChildren().size(); i++) {
            numbersGroup[i] = (CheckBox)numbers.getChildren().get(i); //записываем числа в массив
        }

        CheckBox[] colorsGroup = new CheckBox[colors.getChildren().size()]; //массив чекбоксов с цветами
        for (int i = 0; i < colors.getChildren().size(); i++) {
            colorsGroup[i] = (CheckBox)colors.getChildren().get(i); //записываем цвета в массив
        }

        CheckBox[] evenGroup = new CheckBox[even.getChildren().size()]; //массив чекбоксов с четностью
        for (int i = 0; i < even.getChildren().size(); i++) {
            evenGroup[i] = (CheckBox)even.getChildren().get(i); //записываем четность в массив
        }

        ObservableList resultBlocks =  result.getChildren(); //это блоки результатов
        AnchorPane resultNum = (AnchorPane) resultBlocks.get(0);
        AnchorPane resultCol = (AnchorPane) resultBlocks.get(2);
        AnchorPane resultEven = (AnchorPane) resultBlocks.get(1);

        counter.setText("Всего очков " + score); //пишем стартовое количество очков в поле счетчика

        Bet BetNum = new Bet();
        BetNum.forBet(numbersGroup, resultNum);
        Bet BetCol = new Bet();
        BetCol.forBet(colorsGroup,resultCol);
        Bet BetEven = new Bet();
        BetEven.forBet(evenGroup,resultEven);

        btn_start.setOnAction(new EventHandler<ActionEvent>() { //клик по кнопке Старт
            @Override
            public void handle(ActionEvent event) {
                numberBet = BetNum.startBet(numbersGroup);
                colorBet = BetCol.startBet(colorsGroup);
                evenBet = BetEven.startBet(evenGroup); //ставки получили

                int [] black = new int[] {2, 4, 6, 8, 10, 11, 13, 15, 17, 20, 22, 24, 26, 28, 29, 31, 33, 35}; //эти номера - черные
                int [] red = new int[] {1, 3, 5, 7, 9, 12, 14, 16, 18, 19, 21, 23, 25, 27, 30, 32, 34, 36}; //эти красные
                boolean isBlack = false;
                boolean isRed = false;
                boolean isEven = false; //четный

                Random r = new Random();
                int num = r.nextInt(37); //рандомное число, выпавшее на рулетке
                String numText = "num"+num; //приводим выпавшее число к виду id чекбокса

                if (numberBet != null) {
                    String numberBetString = numberBet.getId(); //id чекбокса, на который сделана ставка
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
                            score+=4;
                        }
                        else {
                            label_win.setText("Поражение");
                            label_num.setStyle("-fx-text-fill: red;");
                            label_win.setStyle("-fx-text-fill: red;");
                            resultNum.setStyle("-fx-border-color: red;");
                            score--;
                        }
                }
                if (colorBet != null) {
                    String colorBetString = colorBet.getText();
                    ObservableList<Node> resultLabels = resultCol.getChildren();
                    Label label_col = (Label)resultLabels.get(0); //это лейбл, куда пишется выпавший цвет
                    Label label_win = (Label)resultLabels.get(1); //это лейбл результата
                    for (int i = 0; i < black.length; i++) if (num == black[i]) isBlack = true;
                    for (int i = 0; i < black.length; i++) if (num == red[i]) isRed = true;
                    String color = "";
                    if (isBlack) {
                        color = "Черное";
                    }
                    if (isRed) {
                        color = "Красное";
                    }
                    if (color != "") {
                        label_col.setText(color);
                        if(colorBetString.equals(color)) {
                            label_win.setText("Победа");
                            label_col.setStyle("-fx-text-fill: green;");
                            label_win.setStyle("-fx-text-fill: green;");
                            resultCol.setStyle("-fx-border-color: green;");
                            score++;
                        }
                        else {
                            label_win.setText("Поражение");
                            label_col.setStyle("-fx-text-fill: red;");
                            label_win.setStyle("-fx-text-fill: red;");
                            resultCol.setStyle("-fx-border-color: red;");
                            score--;
                        }
                    }
                    else {
                        label_col.setText("Zero!");
                        label_win.setText("Ничья");
                        resultCol.setStyle("-fx-border-color: black;");
                        label_col.setStyle("-fx-text-fill: black");
                        label_win.setStyle("-fx-text-fill: black");
                    }
                }
                if (evenBet != null) {
                    String evenBetString = evenBet.getText();
                    ObservableList<Node> resultLabels = resultEven.getChildren();
                    Label label_even = (Label)resultLabels.get(0); //это лейбл, куда пишется выпавшая четность
                    Label label_win = (Label)resultLabels.get(1); //это лейбл результата

                    for (int i = 0; i < numbersGroup.length; i++) {
                        int finalI = i;
                        if (numbersGroup[finalI].getId().equals(numText)) {
                            forEven = numbersGroup[finalI].getText();
                        }
                    }
                    if (!forEven.equals("0") && (!forEven.equals("00"))) {
                        int intForEven = Integer.parseInt(forEven);
                        if (intForEven % 2 == 0) isEven = true;
                        if (isEven) {
                            label_even.setText("Четное");
                            if (evenBetString.equals("Четное")) {
                                label_win.setText("Победа");
                                label_even.setStyle("-fx-text-fill: green;");
                                label_win.setStyle("-fx-text-fill: green;");
                                resultEven.setStyle("-fx-border-color: green;");
                                score++;
                            } else if (evenBetString.equals("Нечетное")) {
                                label_win.setText("Поражение");
                                label_even.setStyle("-fx-text-fill: red;");
                                label_win.setStyle("-fx-text-fill: red;");
                                resultEven.setStyle("-fx-border-color: red;");
                                score--;
                            }
                        }
                        else {
                            label_even.setText("Нечетное");
                            if (evenBetString.equals("Четное")) {
                                label_win.setText("Поражение");
                                label_even.setStyle("-fx-text-fill: red;");
                                label_win.setStyle("-fx-text-fill: red;");
                                resultEven.setStyle("-fx-border-color: red;");
                                score--;
                            } else if (evenBetString.equals("Нечетное")) {
                                label_win.setText("Победа");
                                label_even.setStyle("-fx-text-fill: green;");
                                label_win.setStyle("-fx-text-fill: green;");
                                resultEven.setStyle("-fx-border-color: green;");
                                score++;
                            }
                        }
                    }
                    else {
                        label_even.setText("Zero!");
                        label_win.setText("Ничья");
                        resultEven.setStyle("-fx-border-color: black;");
                        label_even.setStyle("-fx-text-fill: black");
                        label_win.setStyle("-fx-text-fill: black");
                    }
                }
                if (score < 0) {
                    counter.setStyle("-fx-text-fill: red");
                }
                else {
                    counter.setStyle("-fx-text-fill: black");
                }
                counter.setText("Всего очков " + score);
            }
        });
    }
}