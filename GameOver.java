import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.io.IOException;

public class GameOver {

    private int score=0;
    private static Scene scene;

    public static void displayOver(int score){
        System.out.println("On gameover screen");
        SaveGame.deleteSavedGame();
        try {
            LeaderBoard leaderBoard=SaveGame.getSavedLeaderboard();
            leaderBoard.addScore(score);
            SaveGame.saveLeaderBoard(leaderBoard);
        } catch (IOException | ClassNotFoundException e) {
            LeaderBoard leaderBoard=new LeaderBoard();
            leaderBoard.addScore(score);
            try {
                SaveGame.saveLeaderBoard(leaderBoard);
                System.out.println("Game saved");
            } catch (IOException e1) {
                System.out.println("Unable to save score");
            }
        }

        VBox vBox=new VBox();
        Text text=new Text("Score");
        text.setStyle("-fx-font-family: 'Raleway Dots ';-fx-font-size: 50px;");
        Text scr_int=new Text(String.valueOf(score));
        scr_int.setStyle("-fx-font-size: 80px; -fx-font-family: 'Comfortaa Regular';");

        Button home=new Button("Home");
        Button restart=new Button("Restart");
        Button leaderboard=new Button("LeaderBoard");

        home.setStyle("-fx-background-color: #f4f4f4; -fx-padding: 10; -fx-min-width: 250; -fx-font-size: 16; -fx-font-family:'Comfortaa Regular';");
        restart.setStyle("-fx-background-color: #f4f4f4; -fx-padding: 10; -fx-min-width: 250; -fx-font-size: 16; -fx-font-family:'Comfortaa Regular';");
        leaderboard.setStyle("-fx-background-color: #f4f4f4; -fx-padding: 10; -fx-min-width: 250; -fx-font-size: 16; -fx-font-family:'Comfortaa Regular';");

        vBox.getChildren().addAll(text,scr_int,home,restart,leaderboard);
        vBox.setAlignment(Pos.CENTER);
        vBox.setStyle("-fx-background-color: #ffffff;");
        vBox.setSpacing(10);
        scene=new Scene(vBox,400,700);
        Main.stage.setScene(scene);

        home.setOnMouseClicked(e ->{
            Main.resume.setDisable(true);
            Main.stage.setScene(Main.scene);
        });

        restart.setOnMouseClicked(e -> {
            Main.game=new Game();
        });

        leaderboard.setOnMouseClicked(e ->{
            try {
                LeaderBoard leader=SaveGame.getSavedLeaderboard();
                leader.displayLeaderboard();
            } catch (IOException | ClassNotFoundException e1) {
                LeaderBoard leaderBoard=new LeaderBoard();
                leaderBoard.displayLeaderboard();
            }
        });
    }
}
