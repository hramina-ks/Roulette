package sample;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;

final class bets {                              //Это класс для получения списка опций по каждому варианту ставки и создания togglgroup для них
    private final ToggleGroup ChildrenGroup;
    private final ObservableList<Node> xChildren;

    public bets(GridPane x) {
        this.xChildren = x.getChildren();
        this.ChildrenGroup = new ToggleGroup();
    }

    public ObservableList<Node> getxChildren() { //возвращает список потомков в виде Node листа
        return xChildren;
    }

    public ToggleGroup getChildrenGroup(ObservableList<Node> xChildren){ //формирует ToggleGroup
        for (int i = 0; i < xChildren.size(); i++) { //добавить в группу все элементы блока
            RadioButton temp = (RadioButton)xChildren.get(i);
            temp.setToggleGroup(ChildrenGroup);
        }
        return ChildrenGroup;
    }
}