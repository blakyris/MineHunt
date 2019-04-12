package App.Menu;

import App.Game.GameViewController;
import javafx.scene.media.AudioClip;

public class MenuViewController {

    private AudioClip audio;
    private MenuView view;

    public MenuViewController() {
        this.audio = new AudioClip(this.getClass().getResource("../resources/music.wav").toString());
        this.view = new MenuView(this);
    }

    public void playAudio() {
        this.audio.setCycleCount(10);
        this.audio.play();
        this.audio.setVolume(0.6);
    }

    public MenuView getView() {
        return this.view;
    }

    public void handleClick(String action) {
        if (action.equals("Play")) {
            this.view.close();
            this.audio.stop();
            GameViewController game = new GameViewController(this);
            game.getView().show();
        }
    }

}
