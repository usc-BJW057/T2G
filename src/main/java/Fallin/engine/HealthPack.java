package Fallin.engine;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class HealthPack extends Cell {

    public HealthPack(){
        Rectangle healthPack = new Rectangle(50, 50);
        healthPack.setFill(Color.BLACK);
        healthPack.setStroke(Color.BLACK);

        getChildren().add(healthPack);
    }
}