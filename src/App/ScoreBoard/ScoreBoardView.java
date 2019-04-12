package App.ScoreBoard;

import App.Components.LeaderBoardCell;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class ScoreBoardView extends Stage {

    private Scene scene;
    private StackPane root;
    private GridPane leaderBoardGrid;

    public ScoreBoardView() {
        super();

        createView();
    }

    private void createView() {
        this.root = new StackPane();
        this.scene = new Scene(root);

        GridPane leaderBoardGrid = new GridPane();
        leaderBoardGrid.setPadding(new Insets(20,10,20,20));
        leaderBoardGrid.setAlignment(Pos.CENTER);

        leaderBoardGrid.addRow(0, new LeaderBoardCell("Blackyris", "02/07/2018", 5485));
        leaderBoardGrid.addRow(1, new LeaderBoardCell("Blackyris", "02/07/2018", 5485));
        leaderBoardGrid.addRow(2, new LeaderBoardCell("Blackyris", "02/07/2018", 5485));
        leaderBoardGrid.addRow(3, new LeaderBoardCell("Blackyris", "02/07/2018", 5485));
        leaderBoardGrid.addRow(4, new LeaderBoardCell("Blackyris", "02/07/2018", 5485));
        leaderBoardGrid.addRow(5, new LeaderBoardCell("Blackyris", "02/07/2018", 5485));
        leaderBoardGrid.addRow(6, new LeaderBoardCell("Blackyris", "02/07/2018", 5485));
        leaderBoardGrid.addRow(7, new LeaderBoardCell("Blackyris", "02/07/2018", 5485));
        leaderBoardGrid.addRow(8, new LeaderBoardCell("Blackyris", "02/07/2018", 5485));

        root.getChildren().add(leaderBoardGrid);
        this.setTitle("Demineur - Score Board");
        this.setScene(this.scene);
        this.setResizable(false);
        this.show();
    }

}
