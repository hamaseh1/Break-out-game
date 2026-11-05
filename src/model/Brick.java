package model;

public class Brick {
    private double x;
    private double y;
    private int width;
    private int height;
    private boolean active;
    public Brick(double x, double y){
        this.x = x;
        this.y = y;
        this.width = 85;
        this.height = 25;
        this.active = true;
    }
    public void destroy(){
        this.active = false;
    }
    public boolean isActive(){
        return active;
    }
    public double getX(){
        return x;
    }
    public  double getY(){
        return y;
    }
    public int getWidth(){
        return width;
    }
    public int getHeight(){
        return height;
    }
}
