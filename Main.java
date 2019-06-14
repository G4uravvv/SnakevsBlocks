import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class Main extends Application {

    public static Scene scene;
    public static Stage stage;
    public static Game game;
    public static Button resume;

    @Override
    public void start(Stage primaryStage) {
        Main.stage=primaryStage;
        primaryStage.setTitle("Snake vs Block game");
        Font.loadFont(Main.class.getResource("Fonts/CR.ttf").toExternalForm(), 20);
        Font.loadFont(Main.class.getResource("Fonts/RR.ttf").toExternalForm(), 20);
        Text text=new Text("Snake vs Block");
        text.setStyle("-fx-font-size: 50; -fx-font-family: 'Raleway Dots ';");

        resume=new Button("Resume");
        Button start=new Button("Start");
        Button leaderboard=new Button("LeaderBoard");

        resume.setStyle("-fx-background-color: #f4f4f4; -fx-padding: 10; -fx-min-width: 250; -fx-font-size: 16; -fx-font-family:'Comfortaa Regular';");
        start.setStyle("-fx-background-color: #f4f4f4; -fx-padding: 10; -fx-min-width: 250; -fx-font-size: 16; -fx-font-family:'Comfortaa Regular';");
        leaderboard.setStyle("-fx-background-color: #f4f4f4; -fx-padding: 10; -fx-min-width: 250; -fx-font-size: 16; -fx-font-family:'Comfortaa Regular';");
        if (!SaveGame.isSaveGameAvailable()){
            resume.setDisable(true);
        }
        VBox vBox=new VBox(text,resume,start,leaderboard);
        vBox.setAlignment(Pos.CENTER);
        vBox.setStyle("-fx-background-color: #ffffff;");
        vBox.setSpacing(10);
        scene=new Scene(vBox,400, 700);
        primaryStage.setScene(scene);
        primaryStage.show();


        resume.setOnMouseClicked(event -> {
            System.out.println("Resume clicked");
            try {
                game=SaveGame.getSavedGame();
                Game game2=new Game(game.getScore_int(),game.getSnklen());
                game=game2;
            } catch (IOException | ClassNotFoundException e) {
                System.out.println("unable to load save");
                resume.setDisable(true);
            }
        });

        start.setOnMouseClicked(event -> {
            System.out.println("start clicked");
            game=new Game();
        });

        leaderboard.setOnMouseClicked(event -> {
            System.out.println("leaderboard clicked");
            ObjectInputStream objectInputStream=null;
            try {
                objectInputStream=new ObjectInputStream(new FileInputStream("leaderboard.txt"));
                LeaderBoard lb=(LeaderBoard) objectInputStream.readObject();
                lb.displayLeaderboard();
            } catch (IOException e) {
                System.out.println("File not found");
                LeaderBoard leaderBoard=new LeaderBoard();
                leaderBoard.displayLeaderboard();
            } catch (ClassNotFoundException e1) {
                System.out.println("Class not found exception");
            }
        });
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
