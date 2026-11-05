package model;
public class Ball {
    private double x;
    private double y;
    private double vx;
    private double vy;
    public Ball(double x, double  y, double vx, double vy){
        this.x = x;
        this.y = y;
        this.vx = vx;
        this.vy = vy;

    }
    public void move(){
        x = x + vx;
        y = y + vy;
    }
    public double getX(){
        return x;
    }
    public double getY(){
        return y;
    }
    public void setX(double value ){
        this.x = value;
    }
    public void setY(double value){
        this.y = value;
    }
    public double getVx(){
        return vx;
    }
    public double getVy(){
        return vy;
    }
    public void setVx(double speed){
        this.vx = speed;
    }
    public void setVy(double speed){
        this.vy = speed;
    }

}
