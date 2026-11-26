package storage;

public class InMemoryScoreStorage implements HighScoreStorage {
    private int highScore = 0;
    public int loadHighScore(){
        return highScore;
    }
    public void saveHighScore(int score){
        if (score > highScore){
            highScore = score;
        }
    }

}
