/*
 *  Brick Destroy - A simple Arcade video game
 *   Copyright (C) 2017  Filippo Ranza
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package brickbreaker;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.Random;


public class Wall {

    private static final int LEVELS_COUNT = 5;
    private static final int CLAY = 1;
    private static final int STEEL = 2;
    private static final int CEMENT = 3;
    private static final int TITANIUM = 4;

    private Random rnd;
    private Rectangle area;

    Brick[] bricks;
    Ball ball;
    Player player;

    private Brick[][] levels;
    private int level;
    public int elapsedTime = 0;
    private int seconds = 0;
    private int minutes = 0;
    public int newPoints = 1450;
    public String seconds_string = String.format("%02d", seconds);
    public String minutes_string = String.format("%02d", minutes);
    public String newPoints_string = String.format("%04d", newPoints);

    private Point startPoint;
    private int brickCount;
    private int ballCount;
    private boolean ballLost;

    /**
     * Constructor of Wall
     * @param drawArea the size of this component
     * @param brickCount the bricks will be generated each round of game
     * @param lineCount the lines that contains the bricks
     * @param brickDimensionRatio the brick size ratio
     * @param ballPos the current ball's position
     */
    public Wall(Rectangle drawArea, int brickCount, int lineCount, double brickDimensionRatio, Point ballPos){

        this.startPoint = new Point(ballPos);

        levels = makeLevels(drawArea,brickCount,lineCount,brickDimensionRatio);
        level = 0;

        ballCount = 3;
        ballLost = false;

        rnd = new Random();

        makeBall(ballPos);
        int speedX,speedY;
        do{
            speedX = rnd.nextInt(5) - 2;
        }while(speedX == 0);
        do{
            speedY = rnd.nextInt(3);
        }while(speedY == 0);

        ball.setSpeed(speedX,speedY);

        player = new Player((Point) ballPos.clone(),150,10, drawArea);

        area = drawArea;

    }

    /**
     * Generate the arrangement of the brick in 3 lines with the selected types of brick
     * @param drawArea the size of this component
     * @param brickCnt the bricks will be generated
     * @param lineCnt the lines that contains the bricks
     * @param brickSizeRatio the brick size ratio
     * @param type the type of brick
     * @return the arrangement of bricks
     */
    private Brick[] makeSingleTypeLevel(Rectangle drawArea, int brickCnt, int lineCnt, double brickSizeRatio, int type){
        /*
          if brickCount is not divisible by line count,brickCount is adjusted to the biggest
          multiple of lineCount smaller then brickCount
         */
        brickCnt -= brickCnt % lineCnt;

        int brickOnLine = brickCnt / lineCnt;

        double brickLen = drawArea.getWidth() / brickOnLine;
        double brickHgt = brickLen / brickSizeRatio;

        brickCnt += lineCnt / 2;

        Brick[] tmp  = new Brick[brickCnt];

        Dimension brickSize = new Dimension((int) brickLen,(int) brickHgt);
        Point p = new Point();

        int i;
        for(i = 0; i < tmp.length; i++){
            int line = i / brickOnLine;
            if(line == lineCnt)
                break;
            double x = (i % brickOnLine) * brickLen;
            x =(line % 2 == 0) ? x : (x - (brickLen / 2));
            double y = (line) * brickHgt;
            p.setLocation(x,y);
            tmp[i] = makeBrick(p,brickSize,type);
        }

        for(double y = brickHgt;i < tmp.length;i++, y += 2*brickHgt){
            double x = (brickOnLine * brickLen) - (brickLen / 2);
            p.setLocation(x,y);
            tmp[i] = new ClayBrick(p,brickSize);
        }
        return tmp;

    }
    /**
     * Generate the arrangement of the brick in 3 lines with the selected types of brick
     * @param drawArea the size of this component
     * @param brickCnt the bricks will be generated
     * @param lineCnt the lines that contains the bricks
     * @param brickSizeRatio the brick size ratio
     * @param typeA the type of first brick
     * @param typeB the type of second brick
     * @return the arrangement of bricks
     */
    private Brick[] makeChessboardLevel(Rectangle drawArea, int brickCnt, int lineCnt, double brickSizeRatio, int typeA, int typeB){
        /*
          if brickCount is not divisible by line count,brickCount is adjusted to the biggest
          multiple of lineCount smaller then brickCount
         */
        brickCnt -= brickCnt % lineCnt;

        int brickOnLine = brickCnt / lineCnt;

        int centerLeft = brickOnLine / 2 - 1;
        int centerRight = brickOnLine / 2 + 1;

        double brickLen = drawArea.getWidth() / brickOnLine;
        double brickHgt = brickLen / brickSizeRatio;

        brickCnt += lineCnt / 2;

        Brick[] tmp  = new Brick[brickCnt];

        Dimension brickSize = new Dimension((int) brickLen,(int) brickHgt);
        Point p = new Point();

        int i;
        for(i = 0; i < tmp.length; i++){
            int line = i / brickOnLine;
            if(line == lineCnt)
                break;
            int posX = i % brickOnLine;
            double x = posX * brickLen;
            x =(line % 2 == 0) ? x : (x - (brickLen / 2));
            double y = (line) * brickHgt;
            p.setLocation(x,y);

            boolean b = ((line % 2 == 0 && i % 2 == 0) || (line % 2 != 0 && posX > centerLeft && posX <= centerRight));
            tmp[i] = b ?  makeBrick(p,brickSize,typeA) : makeBrick(p,brickSize,typeB);
        }

        for(double y = brickHgt;i < tmp.length;i++, y += 2*brickHgt){
            double x = (brickOnLine * brickLen) - (brickLen / 2);
            p.setLocation(x,y);
            tmp[i] = makeBrick(p,brickSize,typeA);
        }
        return tmp;
    }

    /**
     * Make the ball at preferred position
     * @param ballPos the preferred position
     */
    private void makeBall(Point2D ballPos){
        ball = new RubberBall(ballPos);
    }

    /**
     * Generate different levels of brick arrangement and store in temp
     * @param drawArea the size of the component
     * @param brickCount the bricks will be generated
     * @param lineCount the lines contains the bricks
     * @param brickDimensionRatio the brick size ratio
     * @return a 2d array of bricks
     */
    private Brick[][] makeLevels(Rectangle drawArea,int brickCount,int lineCount,double brickDimensionRatio){
        Brick[][] tmp = new Brick[LEVELS_COUNT][];
        tmp[0] = makeSingleTypeLevel(drawArea,brickCount,lineCount,brickDimensionRatio,CLAY);
        tmp[1] = makeChessboardLevel(drawArea,brickCount,lineCount,brickDimensionRatio,CLAY,CEMENT);
        tmp[2] = makeChessboardLevel(drawArea,brickCount,lineCount,brickDimensionRatio,CLAY,STEEL);
        tmp[3] = makeChessboardLevel(drawArea,brickCount,lineCount,brickDimensionRatio,STEEL,CEMENT);
        tmp[4] = makeChessboardLevel(drawArea,brickCount,lineCount,brickDimensionRatio,STEEL,TITANIUM);
        return tmp;
    }

    /**
     * Check the movement of player and ball
     */
    public void move(){
        player.move();
        ball.move();
    }

    /**
     * Check the impacts between ball and player or ball and bricks
     */
    public void findImpacts(){
        if(player.impact(ball)){
            ball.reverseY();
        }
        else if(impactWall()){
            /*for efficiency reverse is done into method impactWall
            * because for every brick program checks for horizontal and vertical impacts
            */
            brickCount--;
            newPoints += 50;
        }
        else if(impactBorder()) {
            ball.reverseX();
        }
        else if(ball.getPosition().getY() < area.getY()){
            ball.reverseY();
        }
        else if(ball.getPosition().getY() > area.getY() + area.getHeight()){
            ballCount--;
            newPoints -= 150;
            ballLost = true;
        }
    }

    /**
     * Impact the bricks
     * @return a boolean value to check whether brick is broken or not
     */
    public boolean impactWall(){
        for(Brick b : bricks){
            switch(b.findImpact(ball)) {
                //Vertical Impact
                case Brick.UP_IMPACT:
                    ball.reverseY();
                    return b.setImpact(ball.down, Brick.Crack.UP);
                case Brick.DOWN_IMPACT:
                    ball.reverseY();
                    return b.setImpact(ball.up,Brick.Crack.DOWN);

                //Horizontal Impact
                case Brick.LEFT_IMPACT:
                    ball.reverseX();
                    return b.setImpact(ball.right,Brick.Crack.RIGHT);
                case Brick.RIGHT_IMPACT:
                    ball.reverseX();
                    return b.setImpact(ball.left,Brick.Crack.LEFT);
            }
        }
        return false;
    }

    /**
     * Check whether the ball hit the left or right border of the frame
     * @return a boolean value that states
     * whether the ball hit the left or right border of the frame
     */
    private boolean impactBorder(){
        Point2D p = ball.getPosition();
        return ((p.getX() < area.getX()) ||(p.getX() > (area.getX() + area.getWidth())));
    }

    /**
     *
     * @return the value of brickCount
     */
    public int getBrickCount(){
        return brickCount;
    }

    /**
     *
     * @return the value of ballCount
     */
    public int getBallCount(){
        return ballCount;
    }

    /**
     * Check whether a ball is lost
     * @return a boolean value that states the lost of a ball
     */
    public boolean isBallLost(){
        return ballLost;
    }

    /**
     * Reset to player, ball and ballLost boolean variable to initial state
     */
    public void ballReset(){
        player.moveTo(startPoint);
        ball.moveTo(startPoint);
        int speedX,speedY;
        do{
            speedX = rnd.nextInt(5) - 2;
        }while(speedX == 0);
        do{
            speedY = -rnd.nextInt(3);
        }while(speedY == 0);

        ball.setSpeed(speedX,speedY);
        ballLost = false;
    }

    /**
     * Reset the wall  and the ballCount to 3
     */
    public void wallReset(){
        for(Brick b : bricks)
            b.repair();
        brickCount = bricks.length;
        ballCount = 3;
    }

    /**
     *
     * @return ballCount = 0 if true
     */
    public boolean ballEnd(){
        return ballCount == 0;
    }

    /**
     *
     * @return ballCount = 0 if true
     */
    public boolean isDone(){
        return brickCount == 0;
    }

    /**
     * Increase the level by 1 and set brickCount to default length
     */
    public void nextLevel(){
        bricks = levels[level++];
        this.brickCount = bricks.length;
    }

    /**
     * check whether the current level reach length of levels
     * @return a boolean value that states
     * whether the current level reach length of levels
     */
    public boolean hasLevel(){
        return level < levels.length;
    }

    /**
     * Set the speed of x-axis of the ball
     * @param s the value of speed of x
     */
    public void setBallXSpeed(int s){
        ball.setXSpeed(s);
    }
    /**
     * Set the speed of y-axis of the ball
     * @param s the value of speed of y
     */
    public void setBallYSpeed(int s){
        ball.setYSpeed(s);
    }

    /**
     * Reset the ballCount to 3
     */
    public void resetBallCount(){
        ballCount = 3;
    }

    /**
     * Make brick at preferred position, size and selected brick's type
     * @param point the (x,y) coordinates
     * @param size the size of the brick
     * @param type the type of brick
     * @return 0
     */
    private Brick makeBrick(Point point, Dimension size, int type){
        Brick out;
        switch(type){
            case CLAY:
                out = new ClayBrick(point,size);
                break;
            case STEEL:
                out = new SteelBrick(point,size);
                break;
            case CEMENT:
                out = new CementBrick(point, size);
                break;
            case TITANIUM:
                out = new TitaniumBrick(point, size);
                break;
            default:
                throw  new IllegalArgumentException(String.format("Unknown Type:%d\n",type));
        }
        return  out;
    }

    /**
     * Start the time and calculate the points
     */
    public void timePointCalculation(){
        elapsedTime +=10;
        seconds = (elapsedTime / 1000) % 60;
        minutes = (elapsedTime / 60000) % 60;

        float elapsedTimeInFloat = elapsedTime;
        if (elapsedTimeInFloat % 1000 == 0 )
            newPoints -= 2;

        if (newPoints < 0)
            newPoints = 0;

        newPoints_string = String.format("%04d", newPoints);
        seconds_string = String.format("%02d", seconds);
        minutes_string = String.format("%02d", minutes);
    }

    /**
     * Reset the time to 0 and points to 1450
     */
    public void timePointsReset(){
        elapsedTime =0;
        minutes = 0;
        seconds = 0;
        newPoints = 1450;
        newPoints_string = String.format("%04d", newPoints);
        seconds_string = String.format("%02d", seconds);
        minutes_string = String.format("%02d", minutes);
    }

}
