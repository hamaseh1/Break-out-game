package controller;
import model.Brick;
import model.GameModel;
import view.BreakoutCanvas;
import storage.HighScoreStorage;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import javax.swing.Timer;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
public class GameEngine implements KeyListener{
    public void keyPressed(KeyEvent keyEvent){
        if (keyEvent.getKeyCode() == KeyEvent.VK_LEFT){
            if(gameModel.getLives() > 0){
                holdLeftKey = true;
            }
        }
        else if (keyEvent.getKeyCode() == KeyEvent.VK_RIGHT){
            if(gameModel.getLives() > 0){
                holdRightKey = true;
            }
        }
        else if (keyEvent.getKeyCode() == KeyEvent.VK_SPACE){
            if (gameModel.getBall().getLaunched() == false){
                if( gameModel.getLives() > 0){
                    gameModel.getBall().setVx(0);
                    gameModel.getBall().setVy(-6);
                    gameModel.getBall().setLaunched(true);
                }
            }

        }
        if(keyEvent.getKeyCode() == KeyEvent.VK_R){
            gameModel.resetGame();
            holdLeftKey = false;
            holdRightKey = false;
            canvas.resetCanvas();
            gameEnded = false;
            isPaused = false;
            gameTimer.start();
        }
        if(keyEvent.getKeyCode() == KeyEvent.VK_P){
            if(isPaused == false){
                isPaused = true;
                canvas.showPaused();
            }
            else if (isPaused == true){
                isPaused = false;
                canvas.removePaused();
            }
        }
    }
    public void keyReleased(KeyEvent keyEvent){
        if(keyEvent.getKeyCode() == KeyEvent.VK_LEFT){
            holdLeftKey = false;
        }
        if(keyEvent.getKeyCode() == KeyEvent.VK_RIGHT){
            holdRightKey = false;
        }

    }
    public void keyTyped(KeyEvent keyEvent){

    }
    private GameModel gameModel;
    private BreakoutCanvas canvas;
    private HighScoreStorage highScoreStorage;
    private boolean isPaused = false;
    private boolean gameEnded = false;
    private Timer gameTimer;
    private boolean holdLeftKey = false;
    private boolean holdRightKey = false;
    public GameEngine(GameModel model, BreakoutCanvas canvas, HighScoreStorage highScoreStorage){
        this.gameModel = model;
        this.canvas = canvas;
        this.highScoreStorage = highScoreStorage;
        int highscore = highScoreStorage.loadHighScore();
        canvas.highScore(highscore);
        gameTimer = new Timer(16 , new ActionListener() {
            @Override 
            public void actionPerformed(ActionEvent e){
                if(isPaused == true){
                    return ;
                }
                if (holdLeftKey == true){
                    if(gameModel.getLives() > 0){
                        movePaddleLeft();
                    }
                }
                if(holdRightKey == true){
                    if(gameModel.getLives() > 0){
                        movePaddleRight();
                    }
                }
                if(gameModel.getBall().getLaunched() ==  true){
                    double previousX = gameModel.getBall().getX();
                    double previousY = gameModel.getBall().getY();
                    gameModel.getBall().move();
                    canvas.updateBall();
                    if (gameModel.getBall().getX() >= gameModel.getScreenWidth() - gameModel.getBall().getSize()){
                    gameModel.getBall().setVx( - gameModel.getBall().getVx());
                    gameModel.getBall().setX(gameModel.getScreenWidth() - gameModel.getBall().getSize());
                    }
                    else if (gameModel.getBall().getX() <= 0){
                        gameModel.getBall().setVx( - gameModel.getBall().getVx());
                        gameModel.getBall().setX(0);
                    } 
                    if (gameModel.getBall().getY() <= 0) {
                        gameModel.getBall().setVy( - gameModel.getBall().getVy());
                        gameModel.getBall().setY(0);
                    }
                    if (gameModel.getBall().getSize() + gameModel.getBall().getX() >= gameModel.getPaddle().getX()
                    && gameModel.getBall().getX() <= gameModel.getPaddle().getX() + gameModel.getPaddle().getWidth()
                    && previousX +gameModel.getBall().getSize() >= gameModel.getPaddle().getX()
                    && previousX <= gameModel.getPaddle().getX() + gameModel.getPaddle().getWidth()
                    && gameModel.getBall().getVy() > 0
                    && previousY + gameModel.getBall().getSize() < gameModel.getPaddle().getY()
                    && gameModel.getBall().getY() + gameModel.getBall().getSize() >= gameModel.getPaddle().getY()) {
                        double deltaX = gameModel.getBall().getCenterX() - gameModel.getPaddle().getCenterX();
                        double offset = deltaX / (gameModel.getPaddle().getWidth() / 2.0 );
                        offset = Math.max(-1, Math.min(1, offset));
                        double angle = offset * Math.toRadians(60);
                        double speed = Math.hypot(gameModel.getBall().getVx(), gameModel.getBall().getVy());
                        gameModel.getBall().setVx(speed * Math.sin(angle));
                        gameModel.getBall().setVy(- Math.abs(speed * Math.cos(angle)));
                        gameModel.getBall().setY(gameModel.getPaddle().getY() - gameModel.getBall().getSize());
                        canvas.updateBall();
                    }
                    boolean collision = false;
                    for( int r = 0; r < 5; r ++){
                        for(int c = 0; c < 8; c ++){
                            Brick brick = gameModel.getBricks()[r][c];
                            if(brick.isActive()){
                                if(gameModel.getBall().getX() <= gameModel.getBricks()[r][c].getX() + gameModel.getBricks()[r][c].getWidth()
                                && gameModel.getBall().getX() + gameModel.getBall().getSize() >= gameModel.getBricks()[r][c].getX()
                                && gameModel.getBall().getY() + gameModel.getBall().getSize() >= gameModel.getBricks()[r][c].getY()
                                && gameModel.getBall().getY() <= gameModel.getBricks()[r][c].getY() + gameModel.getBricks()[r][c].getHeight()){
                                    double overLapX = Math.min(gameModel.getBall().getX() + gameModel.getBall().getSize(),
                                    gameModel.getBricks()[r][c].getX() +  gameModel.getBricks()[r][c].getWidth())
                                    - Math.max(gameModel.getBall().getX(), gameModel.getBricks()[r][c].getX());
                                    double overLapY = Math.min(gameModel.getBall().getY() + gameModel.getBall().getSize(),
                                    gameModel.getBricks()[r][c].getY() +  gameModel.getBricks()[r][c].getHeight())
                                    - Math.max(gameModel.getBall().getY(), gameModel.getBricks()[r][c].getY());
                                    if(collision == false){
                                        if (overLapX < overLapY){
                                            if(gameModel.getBall().getVx() > 0){
                                                gameModel.getBall().setX(brick.getX() - gameModel.getBall().getSize());
                                            }
                                            else if(gameModel.getBall().getVx() < 0){
                                                gameModel.getBall().setX(brick.getX() + brick.getWidth());
                                            }
                                            gameModel.getBall().setVx(- gameModel.getBall().getVx());
                                        }
                                        else{
                                            if(gameModel.getBall().getVy() > 0){
                                                gameModel.getBall().setY(brick.getY() - gameModel.getBall().getSize());
                                            }
                                            else if(gameModel.getBall().getVy() < 0){
                                                gameModel.getBall().setY(brick.getY() + brick.getHeight());
                                            }
                                            gameModel.getBall().setVy(- gameModel.getBall().getVy());
                                        }
                                        collision = true;
                                    }
                                    brick.destroy();
                                    canvas.removeBrick(r, c);
                                    gameModel.increaseScore();
                                    canvas.showScores();
                                    if(gameModel.allBricksDestroyed() == true ){
                                        gameModel.getBall().setLaunched(false);
                                        gameModel.getBall().setVx(0);
                                        gameModel.getBall().setVy(0);
                                        canvas.showWin();
                                        canvas.finalScore();
                                        highScoreStorage.saveHighScore(gameModel.getScore());
                                        int loadHighScore = highScoreStorage.loadHighScore();
                                        canvas.showHighScore(loadHighScore);
                                        gameEnded = true;
                                        gameTimer.stop();

                                    }
                                }
                            }
                        }
                    }
                    if(gameModel.getBall().getY() + gameModel.getBall().getSize() >= gameModel.getScreenHeight()
                    && gameModel.getBall().getLaunched()){
                        gameModel.decreaseLives();
                        canvas.updateLivesLabel();
                        if(gameModel.getLives() == 0){
                            canvas.showGameOver();
                            highScoreStorage.saveHighScore(gameModel.getScore());
                            int loadHighScore = highScoreStorage.loadHighScore();
                            canvas.showHighScore(loadHighScore);
                            canvas.finalScore();
                            gameEnded = true;
                            gameTimer.stop();
                        }
                        gameModel.getBall().setLaunched(false);
                        gameModel.getBall().setX(gameModel.getPaddle().getCenterX() - (gameModel.getBall().getSize() / 2.0));
                        gameModel.getBall().setY(gameModel.getPaddle().getY() - gameModel.getBall().getSize());
                        canvas.updateBall();
                    }
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
