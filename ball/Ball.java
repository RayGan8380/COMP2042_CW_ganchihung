package ball;

import java.awt.*;
import java.awt.geom.Point2D;

/**
 * Created by filippo on 04/09/16.
 *sdsfwerq
 */
abstract public class Ball {

    public static Point2D up;
    public static Point2D down;
    public static Point2D left;
    public static Point2D right;
    /**
     * Constructor of the ball
     * @param center the center of the ball position
     * @param radiusA the horizontal radius of the ball
     * @param radiusB the vertical radius of the ball
     * @param inner the inner colour of the ball
     * @param border the border color of the ball
     */
    public Ball(Point2D center,int radiusA,int radiusB,Color inner,Color border){
        BallModel.setPosition(center);

        up = new Point2D.Double();
        down = new Point2D.Double();
        left = new Point2D.Double();
        right = new Point2D.Double();

        up.setLocation(center.getX(),center.getY()-(radiusB / 2));
        down.setLocation(center.getX(),center.getY()+(radiusB / 2));

        left.setLocation(center.getX()-(radiusA /2),center.getY());
        right.setLocation(center.getX()+(radiusA /2),center.getY());


        BallModel.ballFace = makeBall(center,radiusA,radiusB);
        BallModel.setBorderColor(border);
        BallModel.setInnerColor(inner);
        BallModel.setXSpeed(0);
        BallModel.setYSpeed(0);
    }

    /**
     * make the shape of the ball
     * @param center position of the center of the ball
     * @param radiusA horizontal radius of the ball
     * @param radiusB vertical radius of the ball
     * @return the shape of the ball
     */
    protected abstract Shape makeBall(Point2D center,int radiusA,int radiusB);







}
