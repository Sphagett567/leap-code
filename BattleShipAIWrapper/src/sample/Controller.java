package sample;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class Controller {
    public BattleShip battle = new BattleShip();
    public BattleShip battle1 = new BattleShip();
    public SampleBot sampleBot = new SampleBot(battle);
    public SampleBot sampleBot1 = new SampleBot(battle1);
    @FXML
    private GridPane Grid2;
    @FXML
    private GridPane Grid1;
    @FXML
    private Button button1;
    @FXML
    private Button buttonP2;
    @FXML
    private Text label1;
    @FXML
    private Text label2;

    @FXML
    private void changeColour2(){
        final ObservableList<Node> children2 = Grid2.getChildren();
        sampleBot1.fireShot();
        for (int i = 0; i < 10; i++){
            for (int j = 0; j < 10; j++){
                if (battle1.board[i][j] == CellState.Empty){
                    ((Rectangle) children2.get((i * 10) + j)).setFill(Paint.valueOf("#696196"));
                }
                else if (battle1.board[i][j] == CellState.Hit){
                    ((Rectangle) children2.get((i * 10) + j)).setFill(Paint.valueOf("#ff3030"));
                }
                else if (battle1.board[i][j] == CellState.Miss){
                    ((Rectangle) children2.get((i * 10) + j)).setFill(Paint.valueOf("#565656"));
                }
                else {
                    ((Rectangle) children2.get((i * 10) + j)).setFill(Paint.valueOf("#000000"));
                }
            }
        }
        if (battle1.allSunk()){
            label1.setText("You Won!");
            label2.setText("You Lost!");
            button1.setDisable(true);
            buttonP2.setDisable(true);
        }
        else {
            button1.setDisable(true);
            buttonP2.setDisable(false);
        }
    }
    @FXML
    private void firebutton(KeyEvent event) {
        if (event.getCode() == KeyCode.A){
            button1.fire();
        }
        else if (event.getCode() == KeyCode.L){
            buttonP2.fire();
        }
    }
    @FXML
    private void changeColour() throws InterruptedException{
        final ObservableList<Node> children1 = Grid1.getChildren();
            sampleBot.fireShot();
            for (int i = 0; i < 10; i++){
                for (int j = 0; j < 10; j++){
                    if (battle.board[i][j] == CellState.Empty){
                        ((Rectangle) children1.get((i * 10) + j)).setFill(Paint.valueOf("#696196"));
                    }
                    else if (battle.board[i][j] == CellState.Hit){
                        ((Rectangle) children1.get((i * 10) + j)).setFill(Paint.valueOf("#ff3030"));
                    }
                    else if (battle.board[i][j] == CellState.Miss){
                        ((Rectangle) children1.get((i * 10) + j)).setFill(Paint.valueOf("#565656"));
                    }
                    else {
                        ((Rectangle) children1.get((i * 10) + j)).setFill(Paint.valueOf("#000000"));
                    }
                }
            }
        if (battle.allSunk()){
            label2.setText("You Won!");
            label1.setText("You Lost!");
            button1.setDisable(true);
            buttonP2.setDisable(true);
        }
        else {
            buttonP2.setDisable(true);
            button1.setDisable(false);
        }
        //while (!battle.allSunk()) {
        //}
        /*
        for (int i = 0; i < 4; i++){
            cell_array[i] = CellState.Empty;
        }
        final ObservableList<Node> children1 = Grid1.getChildren();
        final ObservableList<Node> children2 = Grid2.getChildren();
        for (int i = 0; i < 4; i++) {
            if (cell_array[i].toString().equals(".")){
                ((Rectangle) childr[en1.get(i)).setFill(Paint.valueOf("#565656"));
            }
            if (array2[i] == 1){
                ((Rectangle) children2.get(i)).setFill(Paint.valueOf("#565656"));
            }
        }*/
    }
}
