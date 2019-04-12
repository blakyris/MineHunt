package App;

import App.Game.GameView;
import App.Game.GameViewController;
import App.Menu.MenuView;
import App.Menu.MenuViewController;
import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        MenuViewController menu = new MenuViewController();
        menu.getView().show();
        /*
        GameViewController game = new GameViewController();
        game.getView().show();
        */
    }

    public static void main(String[] args) {
        launch(args);
    }

}
