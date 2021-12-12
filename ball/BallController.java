package ball;

import java.awt.*;
import java.awt.geom.RectangularShape;

public class BallController {

    /**
     *Check and set the position of the ball
     */
    public static void move(){
        RectangularShape tmp = (RectangularShape) BallModel.ballFace;
        BallModel.center.setLocation((BallModel.center.getX() + BallModel.speedX),(BallModel.center.getY() + BallModel.speedY));
        double w = tmp.getWidth();
        double h = tmp.getHeight();

        tmp.setFrame((BallModel.center.getX() -(w / 2)),(BallModel.center.getY() - (h / 2)),w,h);
        setPoints(w,h);


        BallModel.ballFace = tmp;
    }

    /**
     * Set the ball to the center of the frame
     * @param p (x,y) coordinate
     */
    public static void moveTo(Point p){
        BallModel.center.setLocation(p);

        RectangularShape tmp = (RectangularShape) BallModel.ballFace;
        double w = tmp.getWidth();
        double h = tmp.getHeight();

        tmp.setFrame((BallModel.center.getX() -(w / 2)),(BallModel.center.getY() - (h / 2)),w,h);
        BallModel.ballFace = tmp;
    }
    /**
     * Reverse the speed of x-axis of the ball
     */
    public static void reverseX(){
        BallModel.speedX *= -1;
    }

    /**
     * Reverse the y-axis of the ball
     */
    public static void reverseY(){
        BallModel.speedY *= -1;
    }
    /**
     * Set to position of the call
     * @param width the width of the ball
     * @param height the height of the ball
     */
    public static void setPoints(double width,double height){
        Ball.up.setLocation(BallModel.center.getX(), BallModel.center.getY()-(height / 2));
        Ball.down.setLocation(BallModel.center.getX(), BallModel.center.getY()+(height / 2));

        Ball.left.setLocation(BallModel.center.getX()-(width / 2), BallModel.center.getY());
        Ball.right.setLocation(BallModel.center.getX()+(width / 2), BallModel.center.getY());
    }

}
