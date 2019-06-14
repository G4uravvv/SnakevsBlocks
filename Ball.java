import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class Ball extends Bonus{

    public Ball(double pos_x, double pos_y, AnchorPane pane) {
        super(0,pos_x,pos_y,new ImageView("Images/ball.png"),pane);
        imageView.setFitHeight(30);
        imageView.setFitWidth(30);
        imageView.setX(pos_x);
        imageView.setY(pos_y);
        pane.getChildren().add(imageView);
    }

    public double getPos_x() {
        return super.getPos_x();
    }

    public void setPos_x(double pos_x) {
        super.setPos_x(pos_x);
        imageView.setX(super.getPos_x());
    }

    public double getPos_y() {
        return super.getPos_y();
    }

    public void setPos_y(double pos_y) {
        super.setPos_y(pos_y);
        imageView.setY(super.getPos_y());
    }
}
