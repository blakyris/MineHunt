package App.Components;

import App.App;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class LeaderBoardCell extends HBox {

    private VBox userInfoContainer;
    private VBox gameDetailsContainer;
    private VBox scoreContainer;

    public LeaderBoardCell(String user, String datestr, int score) {
        super();

        this.userInfoContainer = new VBox();
        this.gameDetailsContainer = new VBox();
        this.scoreContainer = new VBox();

        this.setSpacing(50);
        this.setPadding(new Insets(0, 0, 20, 0));
        this.gameDetailsContainer.setSpacing(3);

        // USER INFOS
        Label username = new Label(user);

        userInfoContainer.getChildren().add(username);

        // GAMES DETAILS
        Label date = new Label(datestr);
        Label nbClicks = new Label("12");
        Label nbErrors = new Label("2");

        gameDetailsContainer.getChildren().add(date);
        gameDetailsContainer.getChildren().add(nbClicks);
        gameDetailsContainer.getChildren().add(nbErrors);

        // SCORE
        Label scoretxt = new Label(Integer.toString(score) + " pts");

        scoreContainer.getChildren().add(scoretxt);

        this.getChildren().add(userInfoContainer);
        this.getChildren().add(gameDetailsContainer);
        this.getChildren().add(scoreContainer);
    }

}
