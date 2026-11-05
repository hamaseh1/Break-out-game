import model.Paddle;

public class PaddleTest {
    public static void main(String[] args) {
        Paddle paddle = new Paddle(350, 545);
        System.out.println("before movement of the paddle");
        System.out.println(paddle.getX());
        System.out.println("after movment of the paddle to the left: ");
        paddle.moveLeft();
        System.out.println(paddle.getX());
        System.out.println("after movment of the paddle to the right: ");
        paddle.moveRight();
        System.out.println(paddle.getX());
        Paddle paddle2 = new Paddle(5, 545);
        paddle2.moveLeft();
        System.out.println(paddle2.getX());
        Paddle paddle3 = new Paddle(695, 545);
        paddle3.moveRight();
        System.out.println(paddle3.getX());
    }

}
