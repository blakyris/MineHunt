package App.Game;

import App.Components.Dialogs.GameOverDialog;
import javafx.beans.binding.Binding;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.geometry.Side;
import javafx.scene.ImageCursor;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.File;

import App.Game.GameViewController;
import App.Components.CellButton;

public class GameView extends Stage {

    private GameViewController controller;
    private GameModel model;
    private String map;

    private Screen screen;
    private Rectangle2D bounds;
    private Scene scene;
    private StackPane root;
    private HBox container;
    private GridPane grid;
    private VBox counters;
    private VBox controls;
    private Button cheatButton;
    private Button replayButton;
    private Font digitalFont;
    private Font armyRangersFont;
    private Font digitalDareFont;

    // Theme config
    private String gridBorderColor;
    private String gridTextColor;
    private String countersTextColor;
    private boolean enableCellButtonBorders;
    private boolean randomizeDecals;
    private double gridCellGap;
    private AudioClip levelMusic;

    public GameView(GameViewController controller) {
        super();

        this.controller = controller;
        this.model = controller.getModel();
        this.digitalFont = Font.loadFont(getClass().getResourceAsStream("../resources/fonts/DS-DIGIT.TTF"), 36);
        this.armyRangersFont = Font.loadFont(getClass().getResourceAsStream("../resources/fonts/armyrangerslaserital.ttf"), 36);
        this.digitalDareFont = Font.loadFont(getClass().getResourceAsStream("../resources/fonts/DigitalDare.ttf"), 36);
        this.setTheme("military");
        this.createView();
        String soundFile = "../resources/" + this.map + "/music.wav";
        this.levelMusic = new AudioClip(this.getClass().getResource(soundFile).toString());
        this.levelMusic.play();
    }

    private void setTheme(String theme) {
       if (theme.equals("military")) {
            this.map = new String("map_military");
            this.countersTextColor = new String("black");
            this.gridBorderColor = new String("black");
            this.gridTextColor = new String("#D6D9C1");
            this.gridCellGap = 7;
            this.enableCellButtonBorders = false;
            this.randomizeDecals = false;
       }
    }

    private void createView() {
        this.screen = Screen.getPrimary();
        this.bounds = this.screen.getVisualBounds();
        this.root = new StackPane();
        this.root.setBackground(new Background(
                new BackgroundFill(Paint.valueOf("#000"), CornerRadii.EMPTY, Insets.EMPTY))
        );
        this.setCursor("App/resources/ui/cursor.png");
        this.scene = new Scene(root, bounds.getWidth(), bounds.getWidth(), Paint.valueOf("#000"));

        // Main container
        this.container = new HBox(20);
        this.container.setPadding(new Insets(0, 0, 0, 0));
        this.container.setStyle(
                "-fx-background-image: url('App/resources/" + this.map + "/background.png');" +
                "-fx-background-size: cover;"
        );
        this.root.setAlignment(this.container, Pos.CENTER);

        buildGridView();
        initCountersWidget();
        initControls();

        // Adding Elements to root node.
        this.root.getChildren().add(container);
        this.container.getChildren().add(this.counters);
        this.container.getChildren().add(this.grid);
        this.container.getChildren().add(this.controls);
        // Showing the view
        this.setTitle("MineHunt");
        this.setScene(scene);
        this.setMaximized(true);
    }

    private void setCursor(String cursorImage) {
        Image image = new Image(cursorImage, 32, 32, true, true);
        this.root.setCursor(new ImageCursor(image, 0, 0));
    }

    private void buildGridView() {
        this.grid = new GridPane();
        this.grid.setPadding(new Insets(20, 200, 0, 100));
        this.grid.setAlignment(Pos.CENTER);
        this.grid.setHgap(this.gridCellGap);
        this.grid.setVgap(this.gridCellGap);
        int r, c;

        for (r = 0; r < this.model.getGridHeight() + 2; r++) {
            for (c = 0; c < this.model.getGridWith() + 2; c++) {
                CellButton btn = createCellButton(r, c);
                this.grid.add(btn, c, r);
            }
        }
    }

    private CellButton createCellButton(int row, int col) {
        CellButton btn = new CellButton(row, col);
        if (model.getGridWith() <= 10) {
            btn.setMinSize(70,70);
        } else btn.setMinSize(30,30);
        this.initializeCellButtonProperties(btn, row, col);

        return (btn);
    }

    private void initializeCellButtonProperties(CellButton btn, int row, int col) {
        if (row == 0) {
            if (col == 0) {
                btn.setImage("../resources/" + this.map + "/map_TopLeft.png");
            }
            else if (col == this.model.getGridWith() + 1) {
                btn.setImage("../resources/" + this.map + "/map_TopRight.png");
            }
            else {
                btn.setImage("../resources/" + this.map + "/map_TopCenter.png");
            }
        } else if (row == this.model.getGridHeight() + 1) {
            if (col == 0) {
                btn.setImage("../resources/" + this.map + "/map_BottomLeft.png");
            }
            else if (col == this.model.getGridWith() + 1) {
                btn.setImage("../resources/" + this.map + "/map_BottomRight.png");
            }
            else {
                btn.setImage("../resources/" + this.map + "/map_BottomCenter.png");
            }
        } else {
            if (col == 0) {
                btn.setImage("../resources/" + this.map + "/map_CenterLeft.png");
            }
            else if (col == this.model.getGridWith() + 1) {
                btn.setImage("../resources/" + this.map + "/map_CenterRight.png");
            }
            else {
                btn.setImage("../resources/" + this.map + "/map_Center.png");
                btn.setLogicIndex(row - 1, col - 1);
                if (this.enableCellButtonBorders)
                    btn.setAutoBorderFromIndex(this.model.getGridWith() - 1, this.model.getGridHeight() - 1, this.gridBorderColor);
                this.setButtonAction(btn);
            }
        }
    }

    private void setButtonAction(CellButton btn) {
        btn.setOnMouseClicked((MouseEvent event) -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                int retValue = controller.handleClick(btn.getLogicRowPosition(), btn.getLogicColPosition(), 0);
                if (retValue > 0) {
                    btn.setText(Integer.toString(retValue));
                    btn.setTextAlignment(TextAlignment.CENTER);
                    btn.setFont(digitalFont);
                    btn.setTextFill(Color.valueOf(this.gridTextColor));
                } else if (retValue == 0) {
                    String soundFile = "../resources/" + this.map + "/audio/no_mine.wav";
                    AudioClip audio = new AudioClip(this.getClass().getResource(soundFile).toString());
                    audio.setVolume(0.8);
                    audio.play();
                    if (this.randomizeDecals)
                        btn.setIcon(this.getRandomDecal(), 30, 50);
                    else
                        btn.setIcon("../resources/" + this.map + "/decals/nobomb.png", 40, 40);
                    //this.unlockNeighbourCells(btn);
                } else if (retValue == -1) {
                    this.model.setMines(this.model.getMines() - 1);
                    btn.setIcon("../resources/" + this.map + "/decals/explosion.png", 55, 37);

                    String soundFile = "../resources/" + this.map + "/audio/explosion.wav";
                    AudioClip audio = new AudioClip(this.getClass().getResource(soundFile).toString());
                    audio.play();

                    this.controller.gameIsOver();
                } if (this.model.isGameOver()) {
                    GameOverDialog dialog = new GameOverDialog();
                }
            } else if (event.getButton() == MouseButton.SECONDARY) {
                int retValue = controller.handleClick(btn.getLogicRowPosition(), btn.getLogicColPosition(), 1);
                btn.setIcon("../resources/" + this.map + "/decals/flag.png", 40, 40);
            }
        });
    }

    private void unlockNeighbourCells(CellButton btn) {
        int r, c;
        String iconPath;

        iconPath = null;
        if (!this.randomizeDecals)
            iconPath = "../resources/" + this.map + "/decals/nobomb.png";

        for (r = btn.getLogicRowPosition(); r < this.model.getGridHeight(); r++) {
            for (c = btn.getLogicColPosition(); c < this.model.getGridWith(); c++) {
                if (this.randomizeDecals)
                    iconPath = this.getRandomDecal();

                if (this.model.isSafe(r, c + 1))
                    this.getButtonFromGrid(r, c + 1, true).setIcon(iconPath, 40, 40);
                if (this.model.isSafe(r,  c - 1) && c - 1 > 0)
                    this.getButtonFromGrid(r, c - 1, true).setIcon(iconPath, 40, 40);
                if (this.model.isSafe(r + 1, c))
                    this.getButtonFromGrid(r + 1, c, true).setIcon(iconPath, 40, 40);
                if (this.model.isSafe(r - 1, c) && r - 1 > 0)
                    this.getButtonFromGrid(r - 1, c, true).setIcon(iconPath, 40, 40);
            }
        }
    }

    private String getRandomDecal() {
        int min = 1;
        int max = 7;
        int n = (int) (Math.random() * max + min);
        return ("../resources/" + this.map + "/decals/" + n + ".png");
    }

    private void initCountersWidget() {
        this.counters = new VBox();
        this.counters.setPadding(new Insets(20, 0, 0, 0));

        HBox clickCounter = new HBox();
        HBox mineCounter = new HBox();
        Label clickCounterText = new Label();
        Label mineCounterText = new Label();

        clickCounterText.setMinSize(250, 50);
        clickCounterText.setMaxSize(250, 50);
        mineCounterText.setMinSize(250, 50);
        mineCounterText.setMaxSize(250, 50);

        clickCounterText.textProperty().bind(model.getClicksAsProperty().asString());
        mineCounterText.textProperty().bind(new SimpleIntegerProperty(model.getMines()).asString());

        Image hudImg = new Image("App/resources/" + this.map + "/counterBackground.png");
        BackgroundImage background = new BackgroundImage(hudImg,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(100,100,true, true, false, true)
        );

        this.counters.setMinWidth(200);

        clickCounterText.setBackground(new Background(background));
        mineCounterText.setBackground(new Background(background));

        clickCounterText.setFont(this.digitalFont);
        clickCounterText.setPadding(new Insets(0, 0,5,50));
        clickCounterText.setTextFill(Color.RED);
        mineCounterText.setFont(this.digitalFont);
        mineCounterText.setPadding(new Insets(0, 0,5,50));
        mineCounterText.setTextFill(Color.RED);

        clickCounter.getChildren().add(clickCounterText);
        mineCounter.getChildren().add(mineCounterText);

        this.counters.getChildren().add(clickCounter);
        this.counters.getChildren().add(mineCounter);
    }

    /*
     *  Initialize Button Controls Widget.
     */
    private void initControls() {
        this.controls = new VBox();
        this.controls.setPadding(new Insets(20,0,0,0));
        this.controls.setSpacing(10);

        this.cheatButton = new Button("Cheat Mode");
        this.cheatModeSwitchHandler();

        this.replayButton = new Button("Replay");
        this.replayButton.setOnAction((event) -> {
            this.controller.replay();
        });

        this.replayButton.setMinWidth(100);
        this.cheatButton.setMinWidth(100);

        this.controls.getChildren().add(cheatButton);
        this.controls.getChildren().add(replayButton);
    }

    private void cheatModeSwitchHandler() {
        this.cheatButton.setOnAction((event) -> {
            this.model.setCheatMode(!this.model.isCheatModeEnabled());
            if (this.model.isCheatModeEnabled())
                this.cheatButton.setStyle("-fx-background-color: rgb(80, 181, 59);" +
                        "-fx-text-fill: white;"
                );
            else
                this.cheatButton.setStyle("-fx-background-color: rgb(180, 13, 18);" +
                        "-fx-text-fill: white;"
                );
            int r, c;
            for (r = 1; r < this.model.getGridHeight() + 2; r++) {
                for (c = 1; c < this.model.getGridWith() + 2; c++) {
                    if (this.model.isMine(r - 1, c - 1)) {
                        if (this.model.isCheatModeEnabled()) {
                            getButtonFromGrid(r, c, false).setImage("../resources/" + this.map + "/map_CheatMode.png");
                        } else {
                            getButtonFromGrid(r, c, false).setImage("../resources/" + this.map + "/map_Center.png");
                        }
                    }

                }
            }
        });
    }

    public void stopLevelAudio() {
        this.levelMusic.stop();
    }

    private CellButton getButtonFromGrid(int row, int col, boolean islogicIndex) {
        if (islogicIndex) {
            row = row + 1;
            col = col + 1;
        }
        for (Node node : this.grid.getChildren()) {
            if (node instanceof CellButton
                    && GridPane.getColumnIndex(node) == col
                    && GridPane.getRowIndex(node) == row) {
                return (CellButton) node;
            }
        }
        return null;
    }

}
