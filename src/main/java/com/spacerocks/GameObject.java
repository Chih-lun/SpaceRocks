package com.spacerocks;

//Import the relevant packages
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

//Import the relevant packages
public abstract class GameObject{

    protected final Polygon polygon;
    private double speed;
    //initial spawning place
    protected double spawnX;
    protected double spawnY;
    protected int angle;
    static SpawnListener spawnListener;

    //sets the variables for the game object
    public GameObject(Polygon polygon, double speed) {
        this.polygon = polygon;
        this.speed = speed;

        // set game object white
        this.polygon.setFill(Color.WHITE);
    }

    public Polygon getPolygon() {
        return polygon;
    }

    //Getter for the spawn_x
    public double getSpawnX(){return spawnX;}

    //Getter for the spawn_y
    public double getSpawnY() {return spawnY;}

    public double getCurrentXPosition() { return polygon.getTranslateX(); }
    public double getCurrentYPosition() { return polygon.getTranslateY(); }

    //Getter for the speed
    public double getSpeed() {
        return speed;
    }

    //Setter for the speed
    public void setSpeed(double speed) {
        this.speed = speed;
    }

    //Turn the object
//    public void turn(){
//        this.polygon.setRotate(this.polygon.getRotate() + angle);
//    }
    public void turn(int angle) { this.polygon.setRotate(this.polygon.getRotate() + angle); }

    //Move the object
    public void move(){
        double swiftX = Math.cos(Math.toRadians(this.polygon.getRotate()));
        double swiftY = Math.sin(Math.toRadians(this.polygon.getRotate()));

        this.polygon.setTranslateX(this.polygon.getTranslateX() + swiftX * this.speed);
        this.polygon.setTranslateY(this.polygon.getTranslateY() + swiftY * this.speed);

        // stay in the window
        checkInRange();
    }

    //Check if object is in range
    protected void checkInRange(){
        if (this.polygon.getBoundsInParent().getCenterX() < 0) {
            this.polygon.setTranslateX(this.polygon.getTranslateX() + Screen.getScreenWidth());
        }

        if (this.polygon.getBoundsInParent().getCenterX() > Screen.getScreenWidth()) {
            this.polygon.setTranslateX(this.polygon.getTranslateX() % Screen.getScreenWidth());
        }

        if (this.polygon.getBoundsInParent().getCenterY() < 0) {
            this.polygon.setTranslateY(this.polygon.getTranslateY() + Screen.getScreenHeight());
        }

        if (this.polygon.getBoundsInParent().getCenterY() > Screen.getScreenHeight()) {
            this.polygon.setTranslateY(this.polygon.getTranslateY() % Screen.getScreenHeight());
        }
    }

    public boolean hasCollided(GameObject object){
        //checks coordinates bounded by both objects and if there is overlap returns true
        return this.getPolygon().getBoundsInParent().intersects(object.getPolygon().getBoundsInParent());
    }

    public void setSpawnListener(SpawnListener spawnListener) { this.spawnListener = spawnListener; }

}
