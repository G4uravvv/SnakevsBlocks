import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.Random;

public class Walls {

    private double pos_x;
    private double pos_y;
    private double width;
    private double height;
    private Rectangle rectangle;

    public Walls(double pos_x, double pos_y, AnchorPane pane) {
        this.pos_x = pos_x;
        this.pos_y = pos_y;
        Random random=new Random();
        int length=random.nextInt(400)+100;
        rectangle=new Rectangle(pos_x,pos_y,10,length);
        rectangle.setFill(Color.SILVER);
        pane.getChildren().add(rectangle);
    }


    public double getPos_x() {
        return pos_x;
    }

    public void setPos_x(double pos_x) {
        this.pos_x = pos_x;
        rectangle.setX(pos_x);
        rectangle.setY(pos_y);
    }

    public double getPos_y() {
        return pos_y;
    }

    public void setPos_y(double pos_y) {
        this.pos_y = pos_y;
        rectangle.setX(pos_x);
        rectangle.setY(pos_y);
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }
}
