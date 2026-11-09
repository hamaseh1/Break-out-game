package controller;
import model.GameModel;
import view.BreakoutCanvas;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import javax.swing.Timer;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
public class GameEngine implements KeyListener{
    public void keyPressed(KeyEvent keyEvent){
        if (keyEvent.getKeyCode() == KeyEvent.VK_LEFT){
            movePaddleLeft();
        }
        else if (keyEvent.getKeyCode() == KeyEvent.VK_RIGHT){
            movePaddleRight();
        }
    }
    public void keyReleased(KeyEvent keyEvent){

    }
    public void keyTyped(KeyEvent keyEvent){

    }
    private GameModel gameModel;
    private BreakoutCanvas canvas;
    private Timer gameTimer;
    public GameEngine(GameModel model, BreakoutCanvas canvas){
        this.gameModel = model;
        this.canvas = canvas;
        gameTimer = new Timer(16 , new ActionListener() {
            @Override 
            public void actionPerformed(ActionEvent e){
                gameModel.getBall().move();
                canvas.updateBall();
            }
        });
        canvas.addKeyListener(this);
        gameTimer.start();
    }
    public void movePaddleLeft (){
        gameModel.getPaddle().moveLeft();
        canvas.updatePaddle();
    }
    public void movePaddleRight(){
        gameModel.getPaddle().moveRight();
        canvas.updatePaddle();
    }

}
