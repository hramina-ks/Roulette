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

    public void initialize() {
        bets numbersData;
        numbersData = new bets(numbers);
        ObservableList<Node> roundNumbers = numbersData.getxChildren();
        ToggleGroup numbersGroup = numbersData.getChildrenGroup(roundNumbers); //делаем ToggleGroup для цифр

        bets colorsData = new bets(colors);
        ObservableList<Node> roundColors = colorsData.getxChildren();
        ToggleGroup colorsGroup = colorsData.getChildrenGroup(roundColors); //ToggleGroup для цветов

        bets evenData = new bets(even);
        ObservableList<Node> roundEven = evenData.getxChildren();
        ToggleGroup evenGroup = evenData.getChildrenGroup(roundEven); //ToggleGroup для четности

        ObservableList<Node> roundResult = result.getChildren(); //тут получаем и опознаем блоки для результата (отдельно число, цвет и четность)
        AnchorPane resultNum = (AnchorPane)roundResult.get(0);
        AnchorPane resultColor = (AnchorPane)roundResult.get(2);
        AnchorPane resultEven = (AnchorPane)roundResult.get(1);

        numbersGroup.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            @Override
            public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {
                RadioButton oldNumber = (RadioButton) oldValue;
                RadioButton selectedNumber = (RadioButton) newValue;
                System.out.println(oldNumber);
                System.out.println(selectedNumber);

                /*betsResults betsResults = new betsResults();
                    Label bet1 = betsResults.getBet1(resultNum);
                    String numtext = betsResults.gettext(numbersGroup);
                    bet1.setText(numtext);
                    RadioButton numbersSelect = (RadioButton)numbersGroup.getSelectedToggle();
                    if (bet1.isDisabled()) {
                        numbersSelect = (RadioButton)numbersGroup.getSelectedToggle();
                        numbersSelect.setSelected(!numbersSelect.isSelected());
                        bet1.setText("XX");
                    }*/
            }
        });

        /*for (int i = 0; i  < roundNumbers.size(); i++) { //Если юзер выбрал один из вариантов ставки на число - активируем блок результата
            
            roundNumbers.get(i).addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    betsResults betsResults = new betsResults();
                    Label bet1 = betsResults.getBet1(resultNum);
                    String numtext = betsResults.gettext(numbersGroup);
                    bet1.setText(numtext);
                    RadioButton numbersSelect = (RadioButton)numbersGroup.getSelectedToggle();
                    RadioButton clickButton = (RadioButton)event.getSource();
                    String num = clickButton.getText();
                    if (bet1.isDisabled()) {
                        numbersSelect = (RadioButton)numbersGroup.getSelectedToggle();
                        numbersSelect.setSelected(!numbersSelect.isSelected());
                        bet1.setText("XX");
                    }
                }
            });
        }*/

        for (int i = 0; i  < roundColors.size(); i++) { //если юзер выбрал цвет, активируем блок результата для цвета
            int finalI = i;
            roundColors.get(i).addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    betsResults betsResults = new betsResults();
                    Label bet1 = betsResults.getBet1(resultColor);
                    String coltext = betsResults.gettext(colorsGroup);
                    bet1.setText(coltext);
                }
            });
        }

        for (int i = 0; i < roundEven.size(); i++) { //если юзер выбрал четность, активируем блок результата для четности
            int finalI = i;
            roundEven.get(i).addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    betsResults betsResults = new betsResults();
                    Label bet1 = betsResults.getBet1(resultEven);
                    String eventext = betsResults.gettext(evenGroup);
                    bet1.setText(eventext);
                }
            });
        }

        for (int i = 0; i < roundResult.size(); i++) {
            int finalI = i;
            roundResult.get(i).addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    Toggle numbersSelect = numbersGroup.getSelectedToggle();
                    Toggle colorsSelect = colorsGroup.getSelectedToggle();
                    Toggle evenSelect = evenGroup.getSelectedToggle();

                    betsResults betsResults1 = new betsResults();
                    Label bet1 = betsResults1.getBet1((AnchorPane)roundResult.get(finalI));
                    betsResults betsResults2 = new betsResults();
                    Label bet2 = betsResults2.getBet1((AnchorPane)roundResult.get(finalI));
                    betsResults betsResults3 = new betsResults();
                    Label bet3 = betsResults3.getBet1((AnchorPane)roundResult.get(finalI));

                    if (bet1.getId().equals("result_num")) {
                        bet1.setText("XX");
                        numbersSelect.setSelected(false);
                    }
                    if (bet2.getId().equals("result_color")) {
                        bet1.setText("Цвет");
                        colorsSelect.setSelected(false);
                    }
                    if (bet3.getId().equals("result_even")) {
                        bet1.setText("Четность");
                        evenSelect.setSelected(false);
                    }
                }
            });
        }

        btn_start.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() { //юзер кликнул на кнопку старт и сработал основной алгоритм
            @Override
            public void handle(MouseEvent event) {
                Toggle numbersSelect = numbersGroup.getSelectedToggle();
                Toggle colorsSelect = colorsGroup.getSelectedToggle();
                Toggle evenSelect = evenGroup.getSelectedToggle();
                System.out.println(numbersSelect + " " + colorsSelect + " " + evenSelect);
            }
        });
    }
}