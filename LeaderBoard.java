import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class LeaderBoard implements Serializable {

    private ArrayList<Score> list;
    public static Scene scene;

    public LeaderBoard() {
        list=new ArrayList<>();
    }

    public void displayLeaderboard() {
        sort();
        Button back =new Button("Back");
        back.setStyle("-fx-padding: 10; -fx-background-color: #f4f4f4; -fx-font-family: 'Comfortaa Regular'; -fx-font-size: 16;");
        Text text=new Text("LeaderBoard");
        text.setStyle("-fx-font-size: 35; -fx-font-family: 'Raleway Dots ';");
        HBox hBox=new HBox(back, text);
        hBox.setSpacing(20);

        GridPane gridPane=new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        Text text1=new Text("S.no   Score        Date");
        text1.setStyle("-fx-font-family: 'Comfortaa Regular'; -fx-font-size: 16;");
        gridPane.addRow(0, text1);

        if (list.size()==0){
            text1=new Text("No Scores available");
            text1.setStyle("-fx-font-family: 'Comfortaa Regular'; -fx-font-size: 16;");
            gridPane.addRow(1, text1);
        }else {
            for (int i = 0; i < list.size(); i++) {
                text1 = new Text(i + 1 + "           " + list.get(i).getScore() + "            " + list.get(i).getDatetime());
                text1.setStyle("-fx-font-family: 'Comfortaa Regular'; -fx-font-size: 16;");
                gridPane.addRow(i + 2, text1);
            }
        }

        VBox vBox=new VBox(hBox, gridPane);
        vBox.setStyle("-fx-background-color: #ffffff; -fx-padding: 20;");
        vBox.setSpacing(80);
        scene=new Scene(vBox,400,700);
        Main.stage.setScene(scene);

        back.setOnMouseClicked(event -> {
            Main.stage.setScene(Main.scene);
        });
    }

    public void addScore(int score) {
        int chk=0;
        for (Score sc:list) {
            if (sc.getScore()==score) {
                chk++;
            }
        }
        if (chk==0){
            list.add(new Score(score));
        }
    }

    public void sort(){
        Comparator<Score> comparator= (o1, o2) -> Integer.compare(o2.getScore(), o1.getScore());
        list.sort(comparator);
    }
}
