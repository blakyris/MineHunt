package App.Menu;

import App.Components.UIButton;
import com.sun.scenario.effect.Effect;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.applet.AudioClip;

public class MenuView extends Stage {

    private MenuViewController controller;
    private Scene scene;
    private StackPane root;
    private GridPane grid;

    public MenuView(MenuViewController ctrl) {
        super();

        this.controller = ctrl;
        this.createView();
        this.controller.playAudio();
    }

    private void createView() {
        this.root = new StackPane();
        this.scene = new Scene(root);
        this.root.setStyle(
                            "-fx-background-color: black;" +
                            "-fx-background-image: url('App/resources/menuBackground.jpg');" +
                            "-fx-background-repeat: no-repeat;" +
                            "-fx-background-position: center;" +
                            "-fx-background-size: contain, cover;"
        );

        this.root.setAlignment(Pos.TOP_CENTER);

        Image cursorImage = new Image("App/resources/ui/cursor.png", 32, 32, true, true);
        this.root.setCursor(new ImageCursor(cursorImage, 0, 0));

        this.grid = new GridPane();
        this.grid.setHgap(50);
        this.grid.setVgap(80);
        this.grid.setAlignment(Pos.BOTTOM_CENTER);

        this.root.getChildren().add(this.grid);
        this.grid.setPadding(new Insets(0,0,200,0));

        this.grid.add(this.createMenuButton("Play"), 0, 0);
        this.grid.add(this.createMenuButton("Settings"), 1, 0);
        this.grid.add(this.createMenuButton("Score Board"), 2, 0);
        this.grid.add(this.createMenuButton("Help"), 3, 0);

        this.setTitle("Demineur - Menu");
        this.setScene(scene);
        this.setMaximized(true);
    }

    private UIButton createMenuButton(String text) {
        UIButton btn = new UIButton(text, 230, 100  );

        btn.setPadding(new Insets(0));
        btn.setStyle(
                "-fx-font-size: 24px;" +
                "-fx-font-family: 'DS-Digital';" +
                "-fx-text-fill: #DBD8B9;" +
                "-fx-text-alignment: center;"
        );

        btn.setTransparent();
        setButtonAction(btn);

        return (btn);
    }

    private void setButtonAction(UIButton btn) {
        btn.setOnAction((event) -> {
            this.controller.handleClick(btn.getText());
        });
    }

}
