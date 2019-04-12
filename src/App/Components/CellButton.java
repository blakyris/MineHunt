package App.Components;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.io.FileInputStream;

public class CellButton extends Button {

    private int row;
    private int col;
    private int row_logicindex;
    private int col_logicindex;

    public CellButton(int row, int col) {
        super();
        this.row = row;
        this.col = col;
        this.autosize();
        this.setContentDisplay(ContentDisplay.CENTER);
    }

    public void setImage(String str) {
        Image icon = new Image(getClass().getResourceAsStream(str), 70, 70, true, false);
        BackgroundImage background = new BackgroundImage(icon,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                BackgroundSize.DEFAULT
        );
        this.setBackground(new Background(background));
    }

    public void setIcon(String path, int width, int height) {
        Image img = new Image(getClass().getResourceAsStream(path));
        ImageView imgView = new ImageView(img);
        imgView.setFitWidth(width);
        imgView.setFitHeight(height);
        this.setGraphic(imgView);
    }

    public void setColor(int r, int g, int b) {
        BackgroundFill fill = new BackgroundFill(
                Color.rgb(r, g, b),
                CornerRadii.EMPTY,
                Insets.EMPTY
        );
        Background background = new Background(fill);

        this.setBackground(background);
    }

    public void setTransparent() {
        BackgroundFill fill = new BackgroundFill(
                Color.TRANSPARENT,
                CornerRadii.EMPTY,
                Insets.EMPTY
        );
        Background background = new Background(fill);

        this.setBackground(background);
    }

    public void setAutoBorderFromIndex(int maxGridWith, int maxGridHeight, String color) {
        if (this.row_logicindex == 0) {
            if (this.col_logicindex == 0) {
                this.setStyle("-fx-border-width: 1 1 0 1; -fx-border-color: " + color +  "; -fx-border-style: dotted;");
            }
            else if (this.col_logicindex == maxGridWith) {
                this.setStyle("-fx-border-width: 1 1 0 0; -fx-border-color: " + color +  "; -fx-border-style: dotted;");
            }
            else {
                this.setStyle("-fx-border-width: 1 1 0 0; -fx-border-color: " + color +  "; -fx-border-style: dotted;");
            }
        } else if (this.row_logicindex == maxGridHeight) {
            if (this.col_logicindex == 0) {
                this.setStyle("-fx-border-width: 1 0 1 1; -fx-border-color: " + color +  "; -fx-border-style: dotted;");
            }
            else if (this.col_logicindex == maxGridWith) {
                this.setStyle("-fx-border-width: 1 1 1 1; -fx-border-color: " + color +  "; -fx-border-style: dotted;");
            }
            else {
                this.setStyle("-fx-border-width: 1 0 1 1; -fx-border-color: " + color +  "; -fx-border-style: dotted;");
            }
        } else {
            if (this.col_logicindex == 0) {
                this.setStyle("-fx-border-width: 1 0 0 1; -fx-border-color: " + color +  "; -fx-border-style: dotted;");
            }
            else if (this.col_logicindex == maxGridWith) {
                this.setStyle("-fx-border-width: 1 1 0 1; -fx-border-color: " + color +  "; -fx-border-style: dotted;");
            }
            else {
                this.setStyle("-fx-border-width: 1 0 0 1; -fx-border-color: " + color +  "; -fx-border-style: dotted;");
            }
        }
    }

    public void setRowPosition(int position) {
        this.row = position;
    }

    public void setColPositon(int position) {
        this.col = position;
    }

    public int getRowPosition() {
        return this.row;
    }

    public int getColPosition() {
        return this.col;
    }

    public void setLogicIndex(int row, int col) {
        this.row_logicindex = row;
        this.col_logicindex = col;
    }

    public int getLogicRowPosition() {
        return this.row_logicindex;
    }

    public int getLogicColPosition() {
        return this.col_logicindex;
    }

}
