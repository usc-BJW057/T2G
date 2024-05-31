package Fallin.engine;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Player extends Cell {

    private int health;
    private int score;

    public Player() {

        // Setting up the player to be represented by a small black circle
        Circle playerCircle = new Circle(5);
        playerCircle.setFill(Color.BLACK);

        /*
        Player starts with 2 health, so in the case that they
        begin surrounded by traps, they still have a chan
        */
        health = 1;
        score = 0;

        getChildren().add(playerCircle);
    }

    public int getHealth(){
        return health;
    }

    public void setHealth(int health){
        this.health = health;
    }

    public void lowerHealth(){
        health -= 1 ;
    }

    public int getScore(){
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void increaseScore(){
        score += 1 ;
    }

}