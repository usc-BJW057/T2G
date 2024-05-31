package Fallin.gui;

import Fallin.engine.Cell;
import Fallin.engine.GameEngine;
import Fallin.engine.Player;
import Fallin.engine.Trap;
import Fallin.engine.Treasure;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

import java.awt.*;

public class Controller {
    @FXML
    private GridPane gridPane;

    @FXML
    private Text currentHealth;

    @FXML
    private Text currentScore;

    @FXML
    private TextField difficultyInput;

    private int playerRow;
    private int playerCol;

    GameEngine engine;

    @FXML
    public void initialize() {

        // Game starts with 0 as the default difficult
        setDifficulty(0);
        playerRow = engine.getPlayerRow();
        playerCol = engine.getPlayerCol();
        updateGui();
        updateStats();
    }

    private void updateGui() {
        //Clear old GUI grid pane
        gridPane.getChildren().clear();

        // Add each cell into grid pane
        for(int i = 0; i < engine.getSize(); i++) {
            for (int j = 0; j < engine.getSize(); j++) {
                Cell cell = engine.getMap()[i][j];
                gridPane.add(cell, j, i);
            }
        }

        // Starting cell remains green at all times.
        engine.getMap()[9][0].setStyle("-fx-background-color: #a3ff84");

        gridPane.setGridLinesVisible(true);
    }

    private void updateStats(){
        Player player = engine.getPlayer();
        currentHealth.setText("Health: " + player.getHealth());
        currentScore.setText("Score: " + player.getScore());
    }

    @FXML
    private void Up(){
        if (playerRow > 0){
            movePlayer(playerRow - 1, playerCol);
        }
    }

    @FXML
    private void Down(){
        if (playerRow < engine.getSize() - 1) {
            movePlayer(playerRow + 1, playerCol);
        }
    }

    @FXML
    private void Left(){
        if (playerRow > 0) {
            movePlayer(playerRow, playerCol - 1);
        }
    }

    @FXML
    private void Right(){
        if (playerCol < engine.getSize() - 1) {
            movePlayer(playerRow, playerCol + 1);
        }
    }

    private void movePlayer(int newRow, int newCol){
        Player player = engine.getPlayer();
        Cell newCell = engine.getMap()[newRow][newCol];

        if (newCell instanceof Treasure) {
            player.increaseScore();
        } else if (newCell instanceof Trap) {
            player.lowerHealth();
        }

        // Check if the player's health goes below 1, the game is reset
        if (player.getHealth() < 1){
            resetGame();
            return;
        }

        // Remove player from its current location
        engine.getMap()[playerRow][playerCol] = new Cell();

        // Update player position
        playerRow = newRow;
        playerCol = newCol;
        engine.setPlayerPosition(playerRow, playerCol);

        // Place player at the new location
        engine.getMap()[playerRow][playerCol] = player;

        // Whatever cell the player's dot was moved to was turning green
        // This line fixes this, now only the starting cell is green, and remains green
        engine.getMap()[playerRow][playerCol].setStyle(null);

        updateGui();
        updateStats();
    }

    // Move the player back to the start and reset the health and score stats
    private void resetGame() {
        int difficulty;
        try {
            difficulty = Integer.parseInt(difficultyInput.getText());
            if (difficulty < 5 || difficulty > 15) {
                difficulty = 5; // Default difficulty
            }
        } catch (NumberFormatException e) {
            difficulty = 5; // Default difficulty if input is invalid
        }

        engine = new GameEngine(10, difficulty);
        Player player = engine.getPlayer();
        player.setHealth(2);
        player.setScore(0);
        playerRow = 9;
        playerCol = 0;
        engine.setPlayerPosition(playerRow, playerCol);
        updateGui();
        updateStats();
    }

    @FXML
    private void setDifficulty() {
        int difficulty;
        try {
            difficulty = Integer.parseInt(difficultyInput.getText());
            if (difficulty < 5 || difficulty > 15) {
                difficulty = 5; // Default difficulty
            }
        } catch (NumberFormatException e) {
            difficulty = 5; // Default difficulty if input is invalid
        }
        engine = new GameEngine(10, difficulty);
        playerRow = engine.getPlayerRow();
        playerCol = engine.getPlayerCol();
        updateGui();
        updateStats();
    }

    private void setDifficulty(int difficulty) {
        engine = new GameEngine(10, difficulty);
        playerRow = engine.getPlayerRow();
        playerCol = engine.getPlayerCol();
        updateGui();
        updateStats();
    }
}