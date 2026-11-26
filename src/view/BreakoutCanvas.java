package view;
import acm.graphics.GCanvas;
import acm.graphics.GOval;
import acm.graphics.GRect;
import acm.graphics.GRoundRect;
import acm.graphics.GLabel;
import model.GameModel;
import model.Paddle;
import model.Ball;
import model.Brick;
import java.awt.Color;

public class BreakoutCanvas extends GCanvas {
    private GameModel gameModel;
    private GRoundRect paddleShape;
    private GOval ballShape;
    private GRoundRect[][] brickShape;
    private GLabel livesLabel;
    private GLabel gameOverLabel;
    private GLabel scoresLabel;
    private GLabel finalScoreLabel;
    private GLabel highScoreLabel;
    private GLabel pausedLabel;
    private GLabel winLabel;
    private int highScore;
    public BreakoutCanvas(GameModel model){
        this.gameModel = model;
        setBackground(Color.decode("#25243A"));
        Paddle paddle = gameModel.getPaddle();
        Ball ball = gameModel.getBall();
        Brick[][] bricks = gameModel.getBricks();
        brickShape = new GRoundRect[5][8];
        paddleShape = new GRoundRect(paddle.getX(), paddle.getY(), paddle.getWidth(), paddle.getHeight(), 20, 20);
        paddleShape.setColor(Color.decode("#E9A5C8"));
        paddleShape.setFilled(true);
        ballShape = new GOval(ball.getX(), ball.getY(), 16, 16);
        ballShape.setColor(Color.decode("#E9C78D"));
        ballShape.setFilled(true);
        livesLabel = new GLabel("lives : "  + gameModel.getLives(), 20, 30);
        scoresLabel = new GLabel("Score : " + gameModel.getScore(), 20, 50);
        livesLabel.setColor(Color.decode("#E9A5C8"));
        scoresLabel.setColor(Color.decode("#E9C78D"));
        scoresLabel.setFont("Monospaced-bold-18");
        livesLabel.setFont("Monospaced-bold-18");
        add(paddleShape);
        add(ballShape);
        add(livesLabel);
        add(scoresLabel);
        for( int r = 0; r < 5; r ++){
            for(int c = 0; c < 8; c ++){
                if(bricks[r][c].isActive() == true){
                    brickShape[r][c] = new GRoundRect(bricks[r][c].getX(), bricks[r][c].getY(), bricks[r][c].getWidth(), bricks[r][c].getHeight());
                    if (r == 0) {
                        brickShape[r][c].setColor(Color.decode("#E9A5C8"));
                    }
                    else if (r == 1) {
                        brickShape[r][c].setColor(Color.decode("#A9A4E8"));
                    }
                    else if (r == 2) {
                        brickShape[r][c].setColor(Color.decode("#8298E8"));
                    }
                    else if (r == 3) {
                        brickShape[r][c].setColor(Color.decode("#82D6D0"));
                    }
                    else if (r == 4) {
                        brickShape[r][c].setColor(Color.decode("#E9C78D"));
                    }
                    brickShape[r][c].setFilled(true);
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
    public void updateLivesLabel(){
        livesLabel.setLabel("lives : " + gameModel.getLives());
    }
    public void showGameOver(){
        gameOverLabel = new GLabel("GAME OVER", 0, 300);
        gameOverLabel.setColor(Color.decode("#E9A5C8"));
        gameOverLabel.setFont("Monospaced-bold-48");
        gameOverLabel.setLocation((800 - gameOverLabel.getWidth()) / 2, 300);
        add(gameOverLabel);
    }
    public void showScores(){
        scoresLabel.setLabel("Score : " + gameModel.getScore());
    }
    public void finalScore(){
        finalScoreLabel = new GLabel("Final Score : " + gameModel.getScore(), 0, 330);
        finalScoreLabel.setFont("Monospaced-bold-20");
        finalScoreLabel.setColor(Color.decode("#E9C78D"));
        finalScoreLabel.setLocation((800 - finalScoreLabel.getWidth()) / 2, 350);
        add(finalScoreLabel);
    }
    public void showPaused(){
        pausedLabel = new GLabel("PAUSED", 0, 300);
        pausedLabel.setFont("Monospaced-bold-36");
        pausedLabel.setColor(Color.decode("#A9A4E8"));
        pausedLabel.setLocation((800 - pausedLabel.getWidth()) / 2, 300);
        add(pausedLabel);
    }
    public void removePaused(){
        remove(pausedLabel);
    }
    public void highScore(int highscore){
        this.highScore = highscore;
        highScoreLabel = new GLabel("High Score : " + highscore, 600, 30);
        highScoreLabel.setColor(Color.decode("#E9C78D"));
        highScoreLabel.setFont("Monospaced-bold-18");
        add(highScoreLabel);
    }
    public void showHighScore(int highscore){
        this.highScore = highscore;
        highScoreLabel.setLabel("High score : " + highscore);
    }
    public void showWin(){
        winLabel = new GLabel("VICTORY!", 0, 300);
        winLabel.setFont("Monospaced-bold-36");
        winLabel.setColor(Color.decode("#82D6D0"));
        winLabel.setLocation((800 - winLabel.getWidth()) / 2, 300);
        add(winLabel);
    }
    public void resetCanvas(){
        removeAll();
        Paddle paddle = gameModel.getPaddle();
        Ball ball = gameModel.getBall();
        Brick[][] bricks = gameModel.getBricks();
        brickShape = new GRoundRect[5][8];
        paddleShape = new GRoundRect(paddle.getX(), paddle.getY(), paddle.getWidth(), paddle.getHeight(), 20, 20);
        paddleShape.setColor(Color.decode("#E9A5C8"));
        paddleShape.setFilled(true);
        ballShape = new GOval(ball.getX(), ball.getY(), 16, 16);
        ballShape.setColor(Color.decode("#E9C78D"));
        ballShape.setFilled(true);
        livesLabel = new GLabel("lives : "  + gameModel.getLives(), 20, 30);
        scoresLabel = new GLabel("Score : " + gameModel.getScore(), 20, 50);
        livesLabel.setColor(Color.decode("#E9A5C8"));
        scoresLabel.setColor(Color.decode("#E9C78D"));
        scoresLabel.setFont("Monospaced-bold-18");
        livesLabel.setFont("Monospaced-bold-18");
        add(paddleShape);
        add(ballShape);
        add(livesLabel);
        add(scoresLabel);
        for( int r = 0; r < 5; r ++){
            for(int c = 0; c < 8; c ++){
                if(bricks[r][c].isActive() == true){
                    brickShape[r][c] = new GRoundRect(bricks[r][c].getX(), bricks[r][c].getY(), bricks[r][c].getWidth(), bricks[r][c].getHeight());
                    if (r == 0) {
                        brickShape[r][c].setColor(Color.decode("#E9A5C8"));
                    }
                    else if (r == 1) {
                        brickShape[r][c].setColor(Color.decode("#A9A4E8"));
                    }
                    else if (r == 2) {
                        brickShape[r][c].setColor(Color.decode("#8298E8"));
                    }
                    else if (r == 3) {
                        brickShape[r][c].setColor(Color.decode("#82D6D0"));
                    }
                    else if (r == 4) {
                        brickShape[r][c].setColor(Color.decode("#E9C78D"));
                    }
                    brickShape[r][c].setFilled(true);
                    add(brickShape[r][c]);
                }

            }
        }
        highScoreLabel = new GLabel("High Score : " + this.highScore, 600, 30);
        highScoreLabel.setColor(Color.decode("#E9C78D"));
        highScoreLabel.setFont("Monospaced-bold-18");
        add(highScoreLabel);
    }


}
