package Fallin.engine;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Treasure extends Cell {

    public Treasure(){
        Rectangle treasureCell = new Rectangle(50, 50);
        treasureCell.setFill(Color.web("#FFC72C"));
        treasureCell.setStroke(Color.BLACK);

        getChildren().add(treasureCell);
    }
}