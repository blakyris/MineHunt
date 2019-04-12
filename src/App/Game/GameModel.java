package App.Game;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class GameModel {

    private int[][] grid;
    private int gridHeight;
    private int gridWidth;
    private int mines;
    private int errors;
    private int clicks;
    private boolean cheatMode;
    IntegerProperty clicksProperty;

    public GameModel(int width, int height) {
        this.setGridWidth(width);
        this.setGridHeight(height);
        this.clicks = 0;
        this.clicksProperty = new SimpleIntegerProperty();
        this.mines = 0;
        buildGrid();
        //this.debug();
    }

    public void initNewGame(int nbMines) {
        buildGrid();
    }

    public void buildGrid() {
        int i, j;
        this.grid = new int[this.getGridHeight()][this.getGridWith()];

        for (i = 0; i < this.gridHeight; i++)
        {
            for (j = 0; j <= getRandomNumber(0, 2); j++) {
                int randomIndex = getRandomNumber(0, this.gridWidth - 1);
                if (this.grid[i][randomIndex] != -1) {
                    this.grid[i][randomIndex] = getRandomNumber(-1, 0);
                    if (this.grid[i][randomIndex] == -1) {
                        this.mines++;
                    }
                }
            }
        }

        this.preCalculateActions();
    }

    private void preCalculateActions() {
        int i, j;

        for (i = 0; i < this.gridHeight; i++) {
            for (j = 0; j < this.gridWidth; j++) {
                if (this.grid[i][j] != -1)
                    this.grid[i][j] = checkSurroundingCells(i, j);
            }
        }
    }

    private int checkSurroundingCells(int row, int col) {
        int counter = 0;

        boolean upleft = isMine(row - 1, col - 1);
        boolean up = isMine(row - 1, col);
        boolean upright = isMine(row - 1, col + 1);
        boolean left = isMine(row,col - 1);
        boolean right = isMine(row, col + 1);
        boolean downleft = isMine(row + 1,col - 1);
        boolean down  = isMine(row + 1, col);
        boolean downright = isMine(row + 1, col + 1);

        if (upleft) counter++;
        if (up) counter++;
        if (upright) counter++;
        if (left) counter++;
        if (right) counter++;
        if (downleft) counter++;
        if (down) counter++;
        if (downright) counter++;

        return counter;
    }

    public int[][] getMap() {
        return this.grid;
    }

    public void setGridWidth(int width) {
        this.gridWidth = width;
    }

    public void setGridHeight(int height) {
        this.gridHeight = height;
    }

    public int getGridWith() {
        return this.gridWidth;
    }

    public int getGridHeight() {
        return this.gridHeight;
    }

    public void setMines(int n) {
        this.mines = n;
    }

    public int getMines() {
        return this.mines;
    }

    public void setErrors(int n) {
        this.errors = n;
    }

    public int getErrors() {
        return this.errors;
    }

    public int getNeighborMinesCount(int row, int col) {
        return 0;
    }

    public boolean isCheatModeEnabled() {
        return this.cheatMode;
    }

    public void setCheatMode(boolean value) {
        this.cheatMode = value;
    }

    public boolean isOpen(int row, int col) {
        return false;
    }

    public boolean isMine(int row, int col) {
        if (row >= 0 && row < this.gridWidth) {
            if (col >= 0 && col < this.gridHeight) {
                if (this.grid[row][col] == -1) {
                    return true;
                } else return false;
            } else return false;
        } else return false;
    }

    public boolean isSafe(int row, int col) {
        if (row >= 0 && row < this.gridWidth) {
            if (col >= 0 && col < this.gridHeight) {
                if (this.grid[row][col] == 0) {
                    return true;
                } else return false;
            } else return false;
        } else return false;
    }

    public boolean isFlagged(int row, int col) {
        if (this.grid[row][col] == -10)
            return true;
        else return false;
    }

    public boolean isGameOver() {
        int i, j;

        for (i = 0; i < this.gridHeight; i++) {
            for (j = 0; j < this.gridWidth; j++) {
                if (this.grid[i][j] != -2 && this.grid[i][j] != -1)
                    return false;
            }
        }

        return true;
    }

    public boolean open(int row, int col) {
        if (!isOpen(row, col))
        {
            if (isFlagged(row, col))
                this.mines--;
            this.grid[row][col] = -2;
            return true;
        } else return false;
    }

    public void addClick() {
        this.clicks = this.clicks + 1;
        this.clicksProperty.set(this.clicks);
    }

    public int getClicks() {
        return (this.clicks);
    }

    public IntegerProperty getClicksAsProperty() {
       return (this.clicksProperty);
    }

    public void setFlagged(int row, int col) {
        this.grid[row][col] = -10;
    }

    public boolean getFlagState(int row, int col) {
        return false;
    }

    public int getCellValue(int row, int col) {
        return this.grid[row][col];
    }

    // PRIVATR METHODS

    private int getRandomNumber(int min, int max) {
        return (int) (Math.random() * max + min);
    }

    public void debug() {
        int i,j;

        System.out.println("---// DEBUG //---");
        System.out.println("Grid Width : " + this.gridWidth);
        System.out.println("Grid Height : " + this.gridHeight);
        System.out.println("Bombs : " + this.mines);
        System.out.println("\n-----[ GAME MAP ]-----\n");
        for (i = 0; i < this.gridHeight; i++)
        {
            for (j = 0; j < this.gridWidth; j++) {
                System.out.print(this.grid[i][j] + " | ");
            }
            System.out.print("\n");
        }
    }

}
