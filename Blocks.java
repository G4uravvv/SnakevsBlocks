import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.util.Random;

public class Blocks {

    private int value;
    private double pos_x;
    private double pos_y;
    private StackPane stackPane;
    private Text text;
    private AnchorPane pane;

    public Blocks(double pos_x, double pos_y, AnchorPane pane) {
        this.pos_x = pos_x;
        this.pos_y = pos_y;
        this.pane=pane;
        Rectangle rectangle=new Rectangle(70,70);
        Random random=new Random();
        this.value=random.nextInt(50)+1;
        rectangle.setFill(Color.rgb(0,149,255));
        rectangle.setArcHeight(15);
        rectangle.setArcWidth(15);
        text=new Text(String.valueOf(this.value));
        text.setStyle("-fx-font-size: 20px; -fx-font-family: 'Comfortaa Regular ';");
        stackPane=new StackPane();
        stackPane.getChildren().addAll(rectangle,text);
        stackPane.setLayoutX(pos_x);
        stackPane.setLayoutY(pos_y);
        pane.getChildren().add(stackPane);
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
        text.setText(String.valueOf(value));
    }

    public double getPos_x() {
        return pos_x;
    }

    public void setPos_x(double pos_x) {
        this.pos_x = pos_x;
        stackPane.setLayoutX(pos_x);
        stackPane.setLayoutY(pos_y);
    }

    public double getPos_y() {
        return pos_y;
    }

    public void setPos_y(double pos_y) {
        this.pos_y = pos_y;
        stackPane.setLayoutX(pos_x);
        stackPane.setLayoutY(pos_y);
    }

    public void removeBlock(){
        pane.getChildren().remove(stackPane);
    }
}