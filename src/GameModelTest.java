import model.GameModel;

public class GameModelTest {
    public static void main(String[] args) {
        GameModel gameModel = new GameModel();
        System.out.println(gameModel.getScore());
        System.out.println(gameModel.getLives());
        System.out.println(gameModel.getBall().getX());
        System.out.println(gameModel.getBall().getY());
        System.out.println(gameModel.getPaddle().getX());
        System.out.println(gameModel.getPaddle().getY());
        System.out.println(gameModel.getBricks()[0][0].getX());
        System.out.println(gameModel.getBricks()[0][0].getY());
        System.out.println(gameModel.getBricks()[4][7].getX());
        System.out.println(gameModel.getBricks()[4][7].getY());
    }

}