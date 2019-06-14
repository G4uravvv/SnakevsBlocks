import javafx.animation.AnimationTimer;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import javax.swing.*;
import java.io.Serializable;
import java.util.Random;

public class Game implements Serializable {

    public static Scene scene;
    private int score_int;
    private int snklen;

    public Game() {
        score_int=0;
        snklen=1;
        startGame();
    }

    public Game(int score, int len){
        this.score_int=score;
        this.snklen=len;
        startGame();
    }

    private void startGame(){
        Button pause=new Button();
        pause.setGraphic(new ImageView("Images/pause.png"));
        pause.setStyle("-fx-background-color: transparent; -fx-padding: 10;");
        Text score=new Text(String.valueOf(score_int));
        score.setStyle("-fx-font-family: 'Comfortaa Regular'; -fx-font-size: 40;");
        ImageView imageView=new ImageView();
        imageView.setFitHeight(25);
        imageView.setFitWidth(25);
        HBox toph=new HBox(imageView,score,pause);
        toph.setAlignment(Pos.TOP_RIGHT);
        toph.setStyle("-fx-padding: 20; -fx-background-color: #ffffff;");
        toph.setSpacing(120);

        GridPane snakegrid=new GridPane();
        snakegrid.setAlignment(Pos.BOTTOM_CENTER);
        Snake snake=new Snake(Color.rgb(49,49,49),snklen);
        snake.DisplaySnake(snakegrid);

        AnchorPane anchorPane=new AnchorPane();
        AnchorPane.setRightAnchor(toph,0.0);
        AnchorPane.setTopAnchor(snakegrid,554.0);
        AnchorPane.setLeftAnchor(snakegrid,snake.getPos_x());
        anchorPane.setStyle("-fx-background-color: #ffffff;");

        anchorPane.getChildren().addAll(toph,snakegrid);

        Random random=new Random();
        final Blocks[][] blocks = {new Blocks[5]};
        final Walls[][] walls={new Walls[5]};
        final Bonus[] ex_bonus = {null};
        final Timer[] timer = {null};
        final int[] btnd={-1};
        final int[] flags = {1};
        final double[] speed = {1};
        AnimationTimer animationTimer=new AnimationTimer() {
            @Override
            public void handle(long now) {
                speed[0] = speed[0] +0.00001;
                if (btnd[0]!=-1){
                    blocks[0][btnd[0]].removeBlock();
                    blocks[0][btnd[0]]=null;
                }

                if (flags[0]==3){
                    System.out.println("over");
                    GameOver.displayOver(score_int);
                    stop();
                }

                btnd[0]=-1;
                flags[0] =1;

                int blocks_num=random.nextInt(5)+1;
                if (blocks[0][0]!=null && blocks[0][0].getPos_y()>700.0 || blocks[0][1]!=null && blocks[0][1].getPos_y()>700.0 || blocks[0][2]!=null && blocks[0][2].getPos_y()>700.0 || blocks[0][3]!=null && blocks[0][3].getPos_y()>700.0|| blocks[0][4]!=null && blocks[0][4].getPos_y()>700.0 || (blocks[0][0]==null && blocks[0][1]==null && blocks[0][2]==null && blocks[0][3]==null && blocks[0][4]==null)){
                    blocks[0] =new Blocks[5];
                    for (int i=0;i<blocks_num;i++){
                        if (random.nextBoolean()){
                            blocks[0][i]=new Blocks(i*80+10, -80,anchorPane);
                        }else {
                            blocks[0][i]=null;
                        }
                    }
                }else {
                    for (int i=0; i<5; i++){
                        if (blocks[0][i]!=null){
                            blocks[0][i].setPos_y(blocks[0][i].getPos_y()+3* speed[0]);
                        }
                    }
                }

                int wall_num=random.nextInt(5);
                if (walls[0][0]!=null && walls[0][0].getPos_y()>700 || walls[0][1]!=null && walls[0][1].getPos_y()>700 || walls[0][2]!=null && walls[0][2].getPos_y()>700 || walls[0][3]!=null && walls[0][3].getPos_y()>700 || walls[0][4]!=null && walls[0][4].getPos_y()>700 || (walls[0][0]==null && walls[0][1]==null && walls[0][2]==null && walls[0][3]==null && walls[0][4]==null)){
                    walls[0]=new Walls[5];
                    for (int i=0;i<wall_num;i++){
                        if (random.nextBoolean()){
                            walls[0][i]=new Walls(i*80,-400,anchorPane);
                        }else {
                            walls[0][i]=null;
                        }
                    }
                }else {
                    for (int i=0; i<5; i++){
                        if (walls[0][i]!=null){
                            walls[0][i].setPos_y(walls[0][i].getPos_y()+3* speed[0]);
                        }
                    }
                }

                int bonus=random.nextInt(5);
                if (ex_bonus[0] ==null || ex_bonus[0].getPos_y()>700.0){
                    if (bonus==0){
                        ex_bonus[0] =new Ball(random.nextInt(350)+10,-100,anchorPane);
                    }else if (bonus==1){
                        ex_bonus[0] =new Destroy(random.nextInt(350)+10,-100,anchorPane);
                    }else if (bonus==2){
                        ex_bonus[0] =new Shield(random.nextInt(350)+10,-100,anchorPane);
                    }else if (bonus==3){
                        ex_bonus[0] =new Magnet(random.nextInt(350)+10,-100,anchorPane);
                    }else if (bonus==4){
                        ex_bonus[0] =new Coin(random.nextInt(350)+10,-100,anchorPane);
                    }
                }else {
                    ex_bonus[0].setPos_y(ex_bonus[0].getPos_y()+3* speed[0]);
                }


                ///check for block collision
                //System.out.println("animation");
                for (int i=0;i<5;i++){
                    //System.out.println("loop");
                    if (blocks[0][i]!=null && blocks[0][i].getPos_x()<=snake.getPos_x() && blocks[0][i].getPos_x()+70>=snake.getPos_x() && blocks[0][i].getPos_y()>=483.0 && blocks[0][i].getPos_y()<=554.0){
                        System.out.println("collision "+i);
                        //System.out.println("x: "+blocks[0][i].getPos_x());
                        //System.out.println("y: "+blocks[0][i].getPos_y());
                        stop();

                        int finalI = i;
                        timer[0]=new Timer(50, e -> {
                            if (blocks[0][finalI].getValue()<=0 && snake.getLenght()>0){
                                btnd[0] =finalI;
                                start();
                                timer[0].stop();
                            }else {
                                if (snake.getBonus()!=null && snake.getBonus().getType()==2){
                                    score_int=score_int+blocks[0][finalI].getValue();
                                    score.setText(String.valueOf(score_int));
                                    blocks[0][finalI].setValue(0);
                                    btnd[0] =finalI;
                                    start();
                                    timer[0].stop();
                                }else if (snake.getLenght()<=0){
                                    flags[0]=3;
                                    System.out.println("game over flag="+flags[0]);
                                    start();
                                    timer[0].stop();
                                }else {
                                    blocks[0][finalI].setValue(blocks[0][finalI].getValue() - 1);
                                    snake.setLenght(snake.getLenght() - 1);
                                    //anchorPane.getChildren().remove(snakegrid);
                                    //snake.DisplaySnake(snakegrid,0,0);
                                    score_int++;
                                    score.setText(String.valueOf(score_int));
                                    snakegrid.requestLayout();
                                    //System.out.println("value:" +blocks[0][finalI].getValue());
                                }
                            }
                        });
                        timer[0].start();
                    }
                }



                if (ex_bonus[0]!=null && ex_bonus[0].getPos_x()<=snake.getPos_x() && ex_bonus[0].getPos_x()+30>=snake.getPos_x() && ex_bonus[0].getPos_y()>=524.0 && ex_bonus[0].getPos_y()<=554.0){
                    System.out.println("bonus "+ex_bonus[0].getType());
                    ex_bonus[0].removeBonus();
                    snake.setBonus(ex_bonus[0]);
                    ex_bonus[0]=null;
                    switch (snake.getBonus().getType()){
                        case 0: snake.setLenght(snake.getLenght()+1); snakegrid.requestLayout();break;
                        case 1:
                            for (int i=0;i<5;i++){
                                if (blocks[0][i]!=null){
                                    score_int=score_int+blocks[0][i].getValue();
                                    score.setText(String.valueOf(score_int));
                                    blocks[0][i].removeBlock();
                                    blocks[0][i]=null;
                                }
                            }
                            break;
                        case 2:
                            imageView.setImage(new Image("Images/shield.png"));
                            final int[] tm = {0};
                            Timer[] b_timer={null};
                            b_timer[0]=new Timer(1000, e -> {
                                tm[0]++;
                                if (tm[0]==5){
                                    snake.setBonus(null);
                                    imageView.setImage(null);
                                    b_timer[0].stop();
                                }
                            });
                            b_timer[0].start();
                            break;
                        case 3:
                            imageView.setImage(new Image("Images/magnet.png"));
                            final int[] tm2 = {0};
                            Timer[] b_timer2={null};
                            b_timer2[0]=new Timer(1000, e -> {
                                tm2[0]++;
                                if (ex_bonus[0]!=null && ex_bonus[0].getType()==4 && ex_bonus[0].getPos_y()>250.0){
                                    score_int+=100;
                                    score.setText(String.valueOf(score_int));
                                    ex_bonus[0].removeBonus();
                                    snake.setBonus(ex_bonus[0]);
                                    ex_bonus[0]=null;
                                }
                                if (tm2[0]==5){
                                    snake.setBonus(null);
                                    imageView.setImage(null);
                                    b_timer2[0].stop();
                                }
                            });
                            b_timer2[0].start();
                            break;
                        case 4:
                            score_int+=100;
                            score.setText(String.valueOf(score_int));
                            break;
                    }
                }
            }
        };


        animationTimer.start();

        scene=new Scene(anchorPane,400,700);


        scene.setOnMouseMoved(event ->{
            //System.out.println(event.getX());
            if (snake.getLenght()>0) {
                snake.setPos_x(event.getX());
                AnchorPane.setLeftAnchor(snakegrid, snake.getPos_x());
                if (timer[0] != null) {
                    timer[0].stop();
                    timer[0] = null;
                    animationTimer.start();
                }



            }
        });

        Main.stage.setScene(scene);

        pause.setOnMouseClicked(event -> {
            snklen=snake.getLenght();
            System.out.println("Paused clicked");
            animationTimer.stop();
            Pause.setDisplay(animationTimer);
        });
    }

    public int getScore_int() {
        return score_int;
    }

    public int getSnklen() {
        return snklen;
    }
}