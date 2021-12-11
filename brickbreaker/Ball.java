package brickbreaker;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.RectangularShape;

/**
 * Created by filippo on 04/09/16.
 *sdsfwerq
 */
abstract public class Ball {

    private Shape ballFace;

    private Point2D center;

    Point2D up;
    Point2D down;
    Point2D left;
    Point2D right;

    private Color border;
    private Color inner;

    private int speedX;
    private int speedY;

    /**
     * Constructor of the ball
     * @param center the center of the ball position
     * @param radiusA the horizontal radius of the ball
     * @param radiusB the vertical radius of the ball
     * @param inner the inner colour of the ball
     * @param border the border color of the ball
     */
    public Ball(Point2D center,int radiusA,int radiusB,Color inner,Color border){
        this.center = center;

        up = new Point2D.Double();
        down = new Point2D.Double();
        left = new Point2D.Double();
        right = new Point2D.Double();

        up.setLocation(center.getX(),center.getY()-(radiusB / 2));
        down.setLocation(center.getX(),center.getY()+(radiusB / 2));

        left.setLocation(center.getX()-(radiusA /2),center.getY());
        right.setLocation(center.getX()+(radiusA /2),center.getY());


        ballFace = makeBall(center,radiusA,radiusB);
        this.border = border;
        this.inner  = inner;
        speedX = 0;
        speedY = 0;
    }

    /**
     * make the shape of the ball
     * @param center position of the center of the ball
     * @param radiusA horizontal radius of the ball
     * @param radiusB vertical radius of the ball
     * @return the shape of the ball
     */
    protected abstract Shape makeBall(Point2D center,int radiusA,int radiusB);

    /**
     *Check and set the position of the ball
     */
    public void move(){
        RectangularShape tmp = (RectangularShape) ballFace;
        center.setLocation((center.getX() + speedX),(center.getY() + speedY));
        double w = tmp.getWidth();
        double h = tmp.getHeight();

        tmp.setFrame((center.getX() -(w / 2)),(center.getY() - (h / 2)),w,h);
        setPoints(w,h);


        ballFace = tmp;
    }

    /**
     * Set the speed of x-axis and y-axis of the ball
     * @param x the value of x
     * @param y the value of y
     * */
    public void setSpeed(int x,int y){
        speedX = x;
        speedY = y;
    }

    /**
     * Set the speed of x-axis of the ball
     * @param s the value of x
     */
    public void setXSpeed(int s){
        speedX = s;
    }

    /**
     * Set the speed of y-axis of the ball
     * @param s the value of y-axis
     */
    public void setYSpeed(int s){
        speedY = s;
    }

    /**
     * Reverse the speed of x-axis of the ball
     */
    public void reverseX(){
        speedX *= -1;
    }

    /**
     * Reverse the y-axis of the ball
     */
    public void reverseY(){
        speedY *= -1;
    }

    /**
     *
     * @return the border colour
     */
    public Color getBorderColor(){
        return border;
    }
    /**
     *
     * @return the inner color of the ball
     */
    public Color getInnerColor(){
        return inner;
    }

    /**
     *
     * @return the position of the center of the ball
     */
    public Point2D getPosition(){
        return center;
    }

    /**
     *
     * @return the ball shape
     */
    public Shape getBallFace(){
        return ballFace;
    }

    /**
     * Set the ball to the center of the frame
     * @param p (x,y) coordinate
     */
    public void moveTo(Point p){
        center.setLocation(p);

        RectangularShape tmp = (RectangularShape) ballFace;
        double w = tmp.getWidth();
        double h = tmp.getHeight();

        tmp.setFrame((center.getX() -(w / 2)),(center.getY() - (h / 2)),w,h);
        ballFace = tmp;
    }

    /**
     * Set to position of the call
     * @param width the width of the ball
     * @param height the height of the ball
     */
    private void setPoints(double width,double height){
        up.setLocation(center.getX(),center.getY()-(height / 2));
        down.setLocation(center.getX(),center.getY()+(height / 2));

        left.setLocation(center.getX()-(width / 2),center.getY());
        right.setLocation(center.getX()+(width / 2),center.getY());
    }

    /**
     *
     * @return the speed of x-axis of the ball
     */
    public int getSpeedX(){
        return speedX;
    }

    /**
     *
     * @return the speed of y-axis of the ball
     */
    public int getSpeedY(){
        return speedY;
    }


}
