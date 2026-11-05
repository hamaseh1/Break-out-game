import model.Brick;
public class BrickTest {
    public static void main(String[] args) {
        Brick brick = new Brick(39, 60);
        System.out.println(brick.isActive());
        brick.destroy();
        System.out.println(brick.isActive());
        Brick[][] bricks = new Brick[5][8];
        for( int r = 0; r < 5; r ++ ){
            for( int c = 0; c < 8; c ++){
                int x = 39 + c * 91;
                int y = 60 + r * 31;
                bricks[r][c] = new Brick(x, y);
            }
        }
        System.out.println(bricks[0][0].getX());
        System.out.println(bricks[0][0].getY());
        System.out.println(bricks[4][7].getX());
        System.out.println(bricks[4][7].getY());
    }
}
