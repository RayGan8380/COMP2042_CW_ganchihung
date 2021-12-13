package player;

import ball.Ball;
import ball.BallModel;

import java.awt.*;

public class PlayerController {

    /**
     *Check whether the player intersect with the ball
     * @param b the constructor of ball
     * @return true or false value
     */
    public static boolean impact(Ball b){
        return Player.playerFace.contains(BallModel.getPosition()) && Player.playerFace.contains(Ball.down) ;
    }

    /**
     *Turn the player from static state to moving
     */
    public static void move(){
        double x = Player.center.getX() + PlayerModel.moveAmount;
        if(x < PlayerModel.min || x > PlayerModel.max)
            return;
        Player.center.setLocation(x,Player.center.getY());
        Player.playerFace.setLocation(Player.center.x - (int)Player.playerFace.getWidth()/2,Player.center.y);
    }
    /**
     * Move the player to preferred position
     * @param p the preferred position
     */
    public static void moveTo(Point p){
        Player.center.setLocation(p);
        Player.playerFace.setLocation(Player.center.x - (int)Player.playerFace.getWidth()/2,Player.center.y);
    }
    /**
     * Move the player to left
     */
    public static void moveLeft(){
        PlayerModel.setToLeft();
    }
    /**
     * Move the player to right
     */
    public static void moveRight(){
        PlayerModel.setToRight();
    }
    /**
     * Stop the player
     */
    public static void stop(){
        PlayerModel.setToStop();
    }

}
