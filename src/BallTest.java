import model.Ball;
public class BallTest {
    public static void main(String[] args) {

        Ball ball = new Ball(100,200 , 6, -6);
        System.out.println("before moving: ");
        System.out.println(ball.getX());
        System.out.println(ball.getY());
        System.out.println("after moving: ");
        ball.move();
        System.out.println(ball.getX());
        System.out.println(ball.getY());


    }
}
