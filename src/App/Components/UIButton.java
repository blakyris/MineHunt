package App.Components;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class UIButton extends Button {

    public UIButton(String text, int width, int height) {
        super();

        this.setText(text);
    }

    public void setImage(String url) {
        Image icon = new Image(getClass().getResourceAsStream(url), 230, 60, false, false);
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
}
