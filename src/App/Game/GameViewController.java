package App.Game;

import App.Components.Dialogs.GameOverDialog;
import App.Menu.MenuViewController;

public class GameViewController {

    private GameModel model;
    private GameView view;
    private MenuViewController menu;
    private int rows;
    private int cols;

    public GameViewController(MenuViewController menu) {
        this.rows = 10;
        this.cols = 10;
        this.menu = menu;
        initializeNewGame();
    }

    public void initializeNewGame() {
        this.model = new GameModel(this.rows, this.cols);
        this.view = new GameView(this);
    }

    public void replay() {
        this.view.stopLevelAudio();
        this.view.close();
        this.initializeNewGame();
        this.view.show();
    }

    public GameModel getModel() {
        return this.model;
    }

    public GameView getView() {
        return this.view;
    }

    public int handleClick(int row, int col, int mouseButton) {
        if (mouseButton == 0) {
            this.model.addClick();
            if (!this.model.isOpen(row, col))
            {
                int cellValue = this.model.getCellValue(row, col);
                this.model.open(row, col);
                return cellValue;
            }
            else return -2;
        } else if (mouseButton == 1) {
            if (model.isFlagged(row, col)) {
                return 1;
            } else {
                model.setFlagged(row, col);
            }
        }
    }

    public void gameIsOver() {
        this.view.close();

        GameOverDialog dialog = new GameOverDialog();
        if (dialog.getAnswer().equals("REPLAY"))
            this.replay();
        else if (dialog.getAnswer().equals("MENU")) {
            this.view.stopLevelAudio();
            menu.getView().show();
            menu.playAudio();
        }
        else if (dialog.getAnswer().equals("CLOSE"))
            System.exit(0);
    }

}
