package ball;

import java.awt.*;
import java.awt.geom.Point2D;

public class BallModel {
    public static Point2D center;
    public static Shape ballFace;

    public static Color border;
    public static Color inner;

    public static int speedX;
    public static int speedY;
    /**
     * Set the speed of x-axis and y-axis of the ball
     * @param x the value of x
     * @param y the value of y
     * */
    public static void setSpeed(int x,int y){
        speedX = x;
        speedY = y;
    }

    /**
     * Set the speed of x-axis of the ball
     * @param s the value of x
     */
    public static void setXSpeed(int s){
        speedX = s;
    }

    /**
     * Set the speed of y-axis of the ball
     * @param s the value of y-axis
     */
    public static void setYSpeed(int s){
        speedY = s;
    }

    /**
     * Reverse the speed of x-axis of the ball
     */
    public static void reverseX(){
        speedX *= -1;
    }

    /**
     * Reverse the y-axis of the ball
     */
    public static void reverseY(){
        speedY *= -1;
    }


    /**
     *
     * @return the position of the center of the ball
     */
    public static Point2D getPosition(){
        return center;
    }
    /**
     *
     * @return set the position of the center of the ball
     */
    public static Point2D setPosition(Point2D centerpoint){
        center = centerpoint;
        return center;
    }
    /**
     *
     * @return the speed of x-axis of the ball
     */
    public static int getSpeedX(){
        return speedX;
    }

    /**
     *
     * @return the speed of y-axis of the ball
     */
    public static int getSpeedY(){
        return speedY;
    }
    /**
     *
     * @return the border colour
     */
    public static Color getBorderColor(){
        return border;
    }
    /**
     * Set the border color
     * @return the border colour
     */
    public static Color setBorderColor(Color borderColor){
        border = borderColor;
        return border;
    }
    /**
     *
     * @return the inner color of the ball
     */
    public static Color getInnerColor(){
        return inner;
    }
    /**
     * Set the inner color
     * @return the inner color of the ball
     */
    public static Color setInnerColor(Color innerColor){
        inner = innerColor;
        return inner;
    }


    /**
     *
     * @return the ball shape
     */
    public static Shape getBallFace(){
        return ballFace;
    }

}
