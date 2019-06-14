import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class Bonus {

    private int type=0;
    private double pos_x;
    private double pos_y;
    public ImageView imageView;
    private AnchorPane pane;

    public Bonus(int type, double pos_x, double pos_y, ImageView imageView, AnchorPane pane) {
        this.type = type;
        this.pos_x = pos_x;
        this.pos_y = pos_y;
        this.imageView=imageView;
        this.pane=pane;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public double getPos_x() {
        return pos_x;
    }

    public void setPos_x(double pos_x) {
        this.pos_x = pos_x;
        imageView.setX(pos_x);
    }

    public double getPos_y() {
        return pos_y;
    }

    public void setPos_y(double pos_y) {
        this.pos_y = pos_y;
        imageView.setY(pos_y);
    }

    public ImageView getImageView() {
        return imageView;
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }

    public void removeBonus(){
        pane.getChildren().remove(imageView);
    }
}
