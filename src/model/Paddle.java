package model;

public class Paddle {
    private double x;
    private double y;
    private int width;
    private int height;
    private double speed;
    public Paddle(double x, double y){
        this.x = x;
        this.y = y;
        this.width = 100;
        this.height = 15;
        this.speed = 8;
    }
    public void moveLeft(){
        if (x >= speed){
            x -= speed;
        }
        else if( x < speed){
            x = 0;
        }

    }
    public void moveRight(){
        if (x + speed + width<= 800){
            x += speed;
        }
        else if( 700 - x <= speed){
            x = 700;
        }
    }
    public double getX(){
        return x;
    }
    public double getY(){
        return y;
    }
    public int getWidth(){
        return width;
    }
    public int getHeight(){
        return height;
    }
    public double getSpeed(){
        return speed;
    }
    public void setX(double value){
        this.x = value;
    }
    public void setY(double value){
        this.y = value;
    }

}
