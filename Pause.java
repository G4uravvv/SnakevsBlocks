import javafx.animation.AnimationTimer;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class Pause {

    public static Scene scene;

    public static void setDisplay(AnimationTimer animationTimer) {

        Button back =new Button("Back");
        back.setStyle("-fx-background-color: #f4f4f4; -fx-padding: 10; -fx-min-width: 250; -fx-font-size: 16; -fx-font-family:'Comfortaa Regular';");
        Button restart=new Button("Restart");
        restart.setStyle("-fx-background-color: #f4f4f4; -fx-padding: 10; -fx-min-width: 250; -fx-font-size: 16; -fx-font-family:'Comfortaa Regular';");
        Button exit=new Button("Exit");
        exit.setStyle("-fx-background-color: #f4f4f4; -fx-padding: 10; -fx-min-width: 250; -fx-font-size: 16; -fx-font-family:'Comfortaa Regular';");
        VBox vBox=new VBox(back, restart, exit);
        vBox.setSpacing(10);
        vBox.setAlignment(Pos.CENTER);
        vBox.setStyle("-fx-background-color: #ffffff; -fx-padding: 20;");

        scene=new Scene(vBox,400,700);
        Main.stage.setScene(scene);

        back.setOnMouseClicked(event ->{
            Main.stage.setScene(Game.scene);
            animationTimer.start();
        });

        exit.setOnMouseClicked(event -> {
            try {
                SaveGame.saveGame(Main.game);
                Main.resume.setDisable(false);
            } catch (IOException e) {
                System.out.println("Unable to save game");
            }
            Main.stage.setScene(Main.scene);
        });

        restart.setOnMouseClicked(event -> {
            Main.game=new Game();
        });
    }
}
