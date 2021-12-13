package wall;

import ball.BallModel;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.Random;

public class WallModel {
    public static final int LEVELS_COUNT = 5;
    public static final int CLAY = 1;
    public static final int STEEL = 2;
    public static final int CEMENT = 3;
    public static final int TITANIUM = 4;

    public static Random rnd;
    public static Rectangle area;

    public static Point startPoint;
    public static int brickCount;
    public static int ballCount;
    public static boolean ballLost;
    public static int elapsedTime = 0;
    public static int seconds = 0;
    public static int minutes = 0;
    public static int newPoints = 1450;
    public static String seconds_string = String.format("%02d", seconds);
    public static String minutes_string = String.format("%02d", minutes);
    public static String newPoints_string = String.format("%04d", newPoints);

    /**
     * Check whether the ball hit the left or right border of the frame
     * @return a boolean value that states
     * whether the ball hit the left or right border of the frame
     */
    public static boolean impactBorder(){
        Point2D p = BallModel.getPosition();
        return ((p.getX() < area.getX()) ||(p.getX() > (area.getX() + area.getWidth())));
    }

    /**
     *
     * @return the value of brickCount
     */
    public static int getBrickCount(){
        return brickCount;
    }

    /**
     *
     * @return the value of ballCount
     */
    public static int getBallCount(){
        return ballCount;
    }

    /**
     * Check whether a ball is lost
     * @return a boolean value that states the lost of a ball
     */
    public static boolean isBallLost(){
        return ballLost;
    }
    /**
     *
     * @return ballCount = 0 if true
     */
    public static boolean ballEnd(){
        return ballCount == 0;
    }

    /**
     *
     * @return ballCount = 0 if true
     */
    public static boolean isDone(){
        return brickCount == 0;
    }

    /**
     * Set the speed of x-axis of the ball
     * @param s the value of speed of x
     */
    public static void setBallXSpeed(int s){
        BallModel.setXSpeed(s);
    }
    /**
     * Set the speed of y-axis of the ball
     * @param s the value of speed of y
     */
    public static void setBallYSpeed(int s){
        BallModel.setYSpeed(s);
    }

    /**
     * Reset the ballCount to 3
     */
    public static void resetBallCount(){
        ballCount = 3;
    }
}
