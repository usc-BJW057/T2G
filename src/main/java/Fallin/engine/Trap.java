package Fallin.engine;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Trap extends Cell {

    public Trap(){
        Rectangle trapCell = new Rectangle(50, 50);
        trapCell.setFill(Color.web("#EF3340"));
        trapCell.setStroke(Color.BLACK);

        getChildren().add(trapCell);
    }
}