import javax.swing.JFrame;

import controller.GameEngine;
import model.GameModel;
import view.BreakoutCanvas;

public class BreakoutProgram extends JFrame {
    public BreakoutProgram(){
        setTitle("Breakout Game");
        setSize(800 , 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        GameModel model = new GameModel();
        BreakoutCanvas canvas = new BreakoutCanvas(model);
        GameEngine gameEngine = new GameEngine(model, canvas);
        add(canvas);
        setVisible(true);
    }
    public static void main(String[] args) {
        BreakoutProgram breakoutProgram = new BreakoutProgram();

    }
}
