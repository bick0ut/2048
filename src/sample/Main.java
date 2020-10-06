package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

import static javafx.scene.paint.Color.BLACK;
import static sample.Board.Direction.*;

public class Main extends Application {
    public Board model;
    public Text bottomText;
    private List<Color> colors = new ArrayList<>();

    @Override
    public void init() throws Exception {
        super.init();
        this.model = new Board();
        this.colors.add(Color.WHITE); // 0
        this.colors.add(Color.GREY); // 1
        this.colors.add(Color.RED); // 2
        this.colors.add(Color.ORANGE); // 3
        this.colors.add(Color.YELLOW); // 4
        this.colors.add(Color.GREEN); // 5
        this.colors.add(Color.CYAN); // 6
        this.colors.add(Color.BLUE); // 7
        this.colors.add(Color.INDIGO); // 8
        this.colors.add(Color.VIOLET); // 9
        this.colors.add(Color.PURPLE); // 10
        this.colors.add(Color.PINK); // 11

    }

    @Override
    public void start(Stage primaryStage) throws Exception{


        BorderPane bp = new BorderPane();
        GridPane gp = new GridPane();
        gp.setAlignment(Pos.CENTER);
        bp.setCenter(gp);
        update(gp);

        Button restart = new Button();
        restart.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                model = new Board();
                update(gp);
            }
        });
        StackPane top = new StackPane();
        top.getChildren().add(restart);
        bp.setTop(top);

        StackPane stack = new StackPane();
        bottomText = new Text(("WASD or Arrow Keys To Play"));
        bottomText.setStyle("-fx-font: 36 arial;");
        stack.getChildren().addAll(bottomText);
        bp.setBottom(stack);

        Scene scene = new Scene(bp, 600, 480);
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if(model.isGameOver()){
                    return;
                }
                if(event.getCode() == KeyCode.UP || event.getCode() == KeyCode.W){
                    if(model.makeMove(UP)) {
                        model.spawnSquare();
                    }
                } else if(event.getCode() == KeyCode.DOWN || event.getCode() == KeyCode.S){
                    if(model.makeMove(DOWN)) {
                        model.spawnSquare();
                    }
                } else if(event.getCode() == KeyCode.LEFT || event.getCode() == KeyCode.A){
                    if(model.makeMove(LEFT)) {
                        model.spawnSquare();
                    }
                } else if(event.getCode() == KeyCode.RIGHT || event.getCode() == KeyCode.D){
                    if(model.makeMove(RIGHT)) {
                        model.spawnSquare();
                    }
                }
                update(gp);
            }
        });

        primaryStage.setTitle("2048");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void update(GridPane gp) {
        for(int x = 0; x < Board.ROW; x++){
            for(int y = 0; y < Board.COL; y++){
                Square sq = this.model.getBoard()[x][y];
                StackPane stack = new StackPane();
                Rectangle rec = new Rectangle(100, 100);
                rec.setFill(this.colors.get( sq.getTier() % 12 ) );
                rec.setStroke((BLACK));
                Text text = new Text();
                rec.setStrokeWidth(3);
                if(!sq.isEmpty()){
                    text.setText(""+(int)Math.pow(2, sq.getTier()));
                    text.setStyle("-fx-font: 36 arial;");
                }
                stack.getChildren().addAll(rec, text);
                gp.add(stack, y, x);
            }
        }
        if(model.isGameOver()){
            this.bottomText.setText("Game Over! Final Score: " + model.getScore());
        } else if(model.getScore() > 0){
            this.bottomText.setText("Score: " + model.getScore());
        }
        System.out.println(model.toString());
    }


    public static void main(String[] args) {
        launch(args);
    }

}
