package view;
import acm.graphics.GCanvas;
import acm.graphics.GOval;
import acm.graphics.GRect;
import model.GameModel;
import model.Paddle;
import model.Ball;
import model.Brick;

public class BreakoutCanvas extends GCanvas {
    private GameModel gameModel;
    private GRect paddleShape;
    private GOval ballShape;
    private GRect[][] brickShape;
    public BreakoutCanvas(GameModel model){
        this.gameModel = model;
        Paddle paddle = gameModel.getPaddle();
        Ball ball = gameModel.getBall();
        Brick[][] bricks = gameModel.getBricks();
        brickShape = new GRect[5][8];
        paddleShape = new GRect(paddle.getX(), paddle.getY(), paddle.getWidth(), paddle.getHeight());
        ballShape = new GOval(ball.getX(), ball.getY(), 16, 16);
        add(paddleShape);
        add(ballShape);
        for( int r = 0; r < 5; r ++){
            for(int c = 0; c < 8; c ++){
                if(bricks[r][c].isActive() == true){
                    brickShape[r][c] = new GRect(bricks[r][c].getX(), bricks[r][c].getY(), bricks[r][c].getWidth(), bricks[r][c].getHeight());
                    add(brickShape[r][c]);
                }

            }
        }
    }
    public void updatePaddle(){
        paddleShape.setLocation(gameModel.getPaddle().getX(),gameModel.getPaddle().getY());
    }
    public void updateBall(){
        ballShape.setLocation(gameModel.getBall().getX(), gameModel.getBall().getY());
    }
    public void removeBrick(int r, int c){
        remove(brickShape[r][c]);
        
    }


}
