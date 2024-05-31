package Fallin.engine;

import javafx.scene.text.Text;
import java.util.Random;

public class GameEngine {

    private Cell[][] map;
    private Player player;
    private int playerRow;
    private int playerCol;

    public GameEngine(int size, int difficulty) {
        map = new Cell[size][size];

        // Initialize the map with normal cells
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                Cell cell = new Cell();
                // Text text = new Text(i + "," + j);
                // cell.getChildren().add(text);
                map[i][j] = cell;
            }
        }

        // Add traps at random locations based on difficulty
        insertTraps(size, difficulty);

        // Add treasure at random locations
        insertTreasure(size);

        // Add 2 health points into the grid
        // insertHPs(size, 2);

        // Create new player at the starting cell location
        player = new Player();
        playerRow = 9;
        playerCol = 0;
        map[playerRow][playerCol] = player;

        // Highlight the start and end cells with a green and blue color
        map[9][0].setStyle("-fx-background-color: #a3ff84");
        map[0][9].setStyle("-fx-background-color: #42a1ff");
    }

    // Traps are inserted at random locations on the grid
    private void insertTraps(int size, int trapCount) {
        Random rand = new Random();
        int trapsPlaced = 0;

        while (trapsPlaced < trapCount) {
            int i = rand.nextInt(size);
            int j = rand.nextInt(size);

            // Determine if a cell is occupied by another item
            boolean safeSquare = (i == 9 && j == 0) || (i == 0 && j == 9) || (map[i][j] instanceof Treasure) || (map[i][j] instanceof Trap);

            // If a cell is NOT occupied, a trap can be placed there
            if (!safeSquare) {
                map[i][j] = new Trap();
                trapsPlaced++;
            }
        }
    }

    // Similar to insertTraps, insertTreasure sets certain cells to have treasure
    private void insertTreasure(int size) {
        Random rand = new Random();
        int treasurePlaced = 0;

        while (treasurePlaced < 3) {
            int i = rand.nextInt(size);
            int j = rand.nextInt(size);

            // Make sure the location is not occupied by another treasure or trap
            boolean safeSquare = (i == 9 && j == 0) || (i == 0 && j == 9) || (map[i][j] instanceof Treasure) || (map[i][j] instanceof Trap);

            if (!safeSquare) {
                map[i][j] = new Treasure();
                treasurePlaced++;
            }
        }
    }

    // Similar to how insertTraps and insertTreasure are setup, 2 healthPacks are put onto the map
    // The healthPacks are located at different locations each game

    // This seciton is commented out because there were some bugs that caused the game to not run

    /* private void insertHPs(int size, int hpCount) {
        Random rand = new Random();
        int hpPlaced = 0;

        while (hpPlaced < hpCount) {
            int i = rand.nextInt(size);
            int j = rand.nextInt(size);

            // Determine if a cell is occupied by another item
            boolean safeSquare = (i == 9 && j == 0) || (i == 0 && j == 9) || (map[i][j] instanceof Treasure) || (map[i][j] instanceof Trap);

            // If a cell is NOT occupied, a trap can be placed there
            if (!safeSquare) {
                map[i][j] = new Trap();
                hpCount++;
            }
        }
    } */

    // Getter methods used to access the private objects, values and methods

    public int getSize() {
        return map.length;
    }

    public Cell[][] getMap() {
        return map;
    }

    public Player getPlayer() {
        return player;
    }

    public int getPlayerRow() {
        return playerRow;
    }

    public int getPlayerCol() {
        return playerCol;
    }

    public void setPlayerPosition(int row, int col) {
        playerRow = row;
        playerCol = col;
    }

    public static void main(String[] args) {
        GameEngine engine = new GameEngine(10, 8);
        System.out.printf("The size of map is %d * %d\n", engine.getSize(), engine.getSize());
    }
}
