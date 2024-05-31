package Fallin.engine;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
public class Cell extends StackPane {

    public Cell() {
        Rectangle cells = new Rectangle(50, 50); // Setting size of cells
        cells.setFill(Color.TRANSPARENT); //
        cells.setStroke(Color.BLACK);

        getChildren().add(cells);
    }
}