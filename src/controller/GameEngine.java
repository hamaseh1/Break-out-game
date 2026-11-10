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
        else if (keyEvent.getKeyCode() == KeyEvent.VK_SPACE){
            if (gameModel.getBall().getLaunched() == false){
                 gameModel.getBall().setVx(0);
                 gameModel.getBall().setVy(-6);
                 gameModel.getBall().setLaunched(true);
            }

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
                if(gameModel.getBall().getLaunched() ==  true){
                    gameModel.getBall().move();
                    canvas.updateBall();
                }
                if (gameModel.getBall().getX() >= 800 - 16){
                    gameModel.getBall().setVx( - gameModel.getBall().getVx());
                }
                else if (gameModel.getBall().getX() <= 0){
                    gameModel.getBall().setVx( - gameModel.getBall().getVx());
                } 
                if (gameModel.getBall().getY() <= 0) {
                    gameModel.getBall().setVy( - gameModel.getBall().getVy());
                }
                if (gameModel.getBall().getSize() + gameModel.getBall().getX() >= gameModel.getPaddle().getX()
                && gameModel.getBall().getX() <= gameModel.getPaddle().getX() + gameModel.getPaddle().getWidth()
                && gameModel.getBall().getSize() + gameModel.getBall().getY() >= gameModel.getPaddle().getY()
                && gameModel.getBall().getVy() > 0) {
                    gameModel.getBall().setVy( - gameModel.getBall().getVy());
                    gameModel.getBall().setY(gameModel.getPaddle().getY() - gameModel.getBall().getSize());
                    canvas.updateBall();

                }

            }
        });
        canvas.addKeyListener(this);
        gameTimer.start();
    }
    public void movePaddleLeft (){
        gameModel.getPaddle().moveLeft();
        canvas.updatePaddle();
        if(gameModel.getBall().getLaunched() ==  false){
            gameModel.getBall().setX(gameModel.getPaddle().getX() + ((gameModel.getPaddle().getWidth() - 16) / 2));
            canvas.updateBall();
        }  
    }
    public void movePaddleRight(){
        gameModel.getPaddle().moveRight();
        canvas.updatePaddle();
        if(gameModel.getBall().getLaunched() ==  false){
            gameModel.getBall().setX(gameModel.getPaddle().getX() + ((gameModel.getPaddle().getWidth() - 16) / 2));
            canvas.updateBall();
        }  
    }

}
