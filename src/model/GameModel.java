package model;

public class GameModel {
    private Ball ball;
    private Paddle paddle;
    private Brick[][] bricks;
    private int score;
    private int lives;
    private int screenWidth = 800;
    private int screenHeight = 600;
    public GameModel() {
        score = 0;
        lives = 3;
        paddle = new Paddle(350,545);
        double ballX = paddle.getX() + (paddle.getWidth() - 16) / 2.0;
        double ballY = paddle.getY() - 16;
        ball = new Ball(ballX, ballY, 0, 0);
        bricks = new Brick[5][8];
        for( int r = 0; r < 5; r ++ ){
            for( int c = 0; c < 8; c ++){
                int x = 39 + c * 91;
                int y = 60 + r * 31;
                bricks[r][c] = new Brick(x, y);
            }
        }
    }
    public Ball getBall() {
        return ball;
    }
    public Paddle getPaddle() {
        return paddle;
    }
     public Brick[][] getBricks() {
        return bricks;
    }
    public int getScore() {
        return score;
    }
    public int getLives() {
        return lives;
    }
    public void increaseScore(){
         score += 100;
    }
    public int getScreenWidth(){
        return screenWidth;
    }
    public int getScreenHeight(){
        return screenHeight;
    }
    public void decreaseLives(){
        lives -= 1;
    }
    public boolean allBricksDestroyed(){
        for( int r = 0; r < 5; r ++ ){
            for( int c = 0; c < 8; c ++){
                if(bricks[r][c].isActive() == true ){
                    return false;
                }
            }
        }
        return true;
    }
    public void resetGame(){
        score = 0;
        lives = 3;
        paddle = new Paddle(350,545);
        double ballX = paddle.getX() + (paddle.getWidth() - 16) / 2.0;
        double ballY = paddle.getY() - 16;
        ball = new Ball(ballX, ballY, 0, 0);
        bricks = new Brick[5][8];
        for( int r = 0; r < 5; r ++ ){
            for( int c = 0; c < 8; c ++){
                int x = 39 + c * 91;
                int y = 60 + r * 31;
                bricks[r][c] = new Brick(x, y);
            }
        }

    }
}
