import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

import java.io.Serializable;

public class Snake implements Serializable {

    private Color color=Color.BLACK;
    private int lenght=1;
    private double pos_x=200.0;
    private Bonus bonus;
    private Text len_str;
    private GridPane gridPane;

    public Snake(Color color, int lenght) {
        this.color = color;
        this.lenght = lenght;

    }

    public Snake() {
    }

    public void DisplaySnake(GridPane gridPane) {
        this.gridPane=gridPane;
        int count=0;
        for (int i=0;i<lenght;i++){
            if (count==6){
                break;
            }
            Circle circle=new Circle();
            circle.setRadius(10.0f);
            circle.setFill(color);
            gridPane.add(circle,1,i+1);
            count++;
        }
        len_str=new Text(String.valueOf(lenght));
        len_str.setStyle("-fx-font-size: 18px; -fx-font-family: 'Comfortaa Regular ';");
        gridPane.add(len_str,2,1);
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getLenght() {
        return lenght;
    }

    public void setLenght(int lenght) {
        this.lenght = lenght;
        len_str.setText(String.valueOf(lenght));
    }

    public double getPos_x() {
        return pos_x;
    }

    public void setPos_x(double pos_x) {
        this.pos_x = pos_x;
    }

    public Bonus getBonus() {
        return bonus;
    }

    public void setBonus(Bonus bonus) {
        this.bonus = bonus;
    }


}
