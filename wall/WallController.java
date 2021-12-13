package wall;

import ball.Ball;
import ball.BallController;
import ball.BallModel;
import ball.RubberBall;
import brick.Brick;
import brick.Crack;
import player.PlayerController;

import java.awt.geom.Point2D;

public class WallController {

    /**
     * Make the ball at preferred position
     * @param ballPos the preferred position
     */
    public static void makeBall(Point2D ballPos){
        Wall.ball = new RubberBall(ballPos);
    }

    /**
     * Check the movement of player and ball
     */
    public static void move(){
        PlayerController.move();
        BallController.move();
    }

    /**
     * Check the impacts between ball and player or ball and bricks
     */
    public static void findImpacts(){
        if(PlayerController.impact(Wall.ball)){
            BallController.reverseY();
        }
        else if(impactWall()){
            /*for efficiency reverse is done into method impactWall
             * because for every brick program checks for horizontal and vertical impacts
             */
            WallModel.brickCount--;
            WallModel.newPoints += 50;
        }
        else if(WallModel.impactBorder()) {
            BallController.reverseX();
        }
        else if(BallModel.getPosition().getY() < WallModel.area.getY()){
            BallController.reverseY();
        }
        else if(BallModel.getPosition().getY() > WallModel.area.getY() + WallModel.area.getHeight()){
            WallModel.ballCount--;
            WallModel.newPoints -= 150;
            WallModel.ballLost = true;
        }
    }

    /**
     * Impact the bricks
     * @return a boolean value to check whether brick is broken or not
     */
    public static boolean impactWall(){
        for(Brick b : Wall.bricks){
            switch(b.findImpact(Wall.ball)) {
                //Vertical Impact
                case Brick.UP_IMPACT:
                    BallController.reverseY();
                    return b.setImpact(Ball.down, Crack.UP);
                case Brick.DOWN_IMPACT:
                    BallController.reverseY();
                    return b.setImpact(Ball.up,Crack.DOWN);

                //Horizontal Impact
                case Brick.LEFT_IMPACT:
                    BallController.reverseX();
                    return b.setImpact(Ball.right,Crack.RIGHT);
                case Brick.RIGHT_IMPACT:
                    BallController.reverseX();
                    return b.setImpact(Ball.left,Crack.LEFT);
            }
        }
        return false;
    }

    /**
     * Reset to player, ball and ballLost boolean variable to initial state
     */
    public static void ballReset(){
        PlayerController.moveTo(WallModel.startPoint);
        BallController.moveTo(WallModel.startPoint);
        int speedX,speedY;
        do{
            speedX = WallModel.rnd.nextInt(5) - 2;
        }while(speedX == 0);
        do{
            speedY = -WallModel.rnd.nextInt(3);
        }while(speedY == 0);

        BallModel.setSpeed(speedX,speedY);
        WallModel.ballLost = false;
    }

    /**
     * Reset the wall  and the ballCount to 3
     */
    public static void wallReset(){
        for(Brick b : Wall.bricks)
            b.repair();
        WallModel.brickCount = Wall.bricks.length;
        WallModel.ballCount = 3;
    }

    /**
     * Increase the level by 1 and set brickCount to default length
     */
    public static void nextLevel(){
        Wall.bricks = Level.levels[Level.level++];
        WallModel.brickCount = Wall.bricks.length;
    }

    /**
     * check whether the current level reach length of levels
     * @return a boolean value that states
     * whether the current level reach length of levels
     */
    public static boolean hasLevel(){
        return Level.level < Level.levels.length;
    }
    /**
     * Start the time and calculate the points
     */
    public void timePointCalculation(){
        WallModel.elapsedTime +=10;
        WallModel.seconds = ( WallModel.elapsedTime / 1000) % 60;
        WallModel.minutes = ( WallModel.elapsedTime / 60000) % 60;

        float elapsedTimeInFloat =  WallModel.elapsedTime;
        if (elapsedTimeInFloat % 1000 == 0 )
            WallModel.newPoints -= 2;

        if ( WallModel.newPoints < 0)
            WallModel.newPoints = 0;

        WallModel.newPoints_string = String.format("%04d",  WallModel.newPoints);
        WallModel.seconds_string = String.format("%02d",  WallModel.seconds);
        WallModel.minutes_string = String.format("%02d",  WallModel.minutes);
    }

    /**
     * Reset the time to 0 and points to 1450
     */
    public static void timePointsReset(){
        WallModel.elapsedTime =0;
        WallModel.minutes = 0;
        WallModel.seconds = 0;
        WallModel.newPoints = 1450;
        WallModel.newPoints_string = String.format("%04d",  WallModel.newPoints);
        WallModel.seconds_string = String.format("%02d",  WallModel.seconds);
        WallModel.minutes_string = String.format("%02d",  WallModel.minutes);
    }


}
