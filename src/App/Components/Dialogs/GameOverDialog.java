package App.Components.Dialogs;

import javafx.beans.property.ReadOnlyStringProperty;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;

import java.util.Optional;

public class GameOverDialog extends Alert {

    private ButtonType replayButton;
    private ButtonType leaderBoardButton;
    private ButtonType closeButton;
    private String answer;

    public GameOverDialog() {
        super(AlertType.CONFIRMATION);

        String title = "Game Over";
        String content = "Do you want to replay ?";
        this.createDialog(title, content);
    }

    public void createDialog(String title, String content) {
        this.setTitle(title);
        this.setHeaderText(null);
        this.setContentText(content);
        this.getDialogPane().setCenterShape(true);

        this.replayButton = new ButtonType("Replay");
        this.leaderBoardButton = new ButtonType("Go to Menu");
        this.closeButton = new ButtonType("Close", ButtonBar.ButtonData.CANCEL_CLOSE);

        this.getButtonTypes().setAll(replayButton, closeButton, leaderBoardButton);

        Optional<ButtonType> result = this.showAndWait();

        if (result.get() == this.replayButton) {
            this.answer = new String("REPLAY");
        } else if (result.get() == this.leaderBoardButton) {
            this.answer = new String("MENU");
        } else {
            this.answer = new String("CLOSE");
        }
    }

    public String getAnswer() {
        return this.answer;
    }

}
