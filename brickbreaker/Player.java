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


public class Player {


    public static final Color BORDER_COLOR = Color.GREEN.darker().darker();
    public static final Color INNER_COLOR = Color.GREEN;

    private static final int DEF_MOVE_AMOUNT = 5;

    private Rectangle playerFace;
    private Point center;
    private int moveAmount;
    private int min;
    private int max;

    /**
     * Constructor of Player
     * @param ballPoint the current ball position
     * @param width width of the player
     * @param height  height of the player
     * @param container the frame size
     */
    public Player(Point ballPoint,int width,int height,Rectangle container) {
        this.center = ballPoint;
        moveAmount = 0;
        playerFace = makeRectangle(width, height);
        min = container.x + (width / 2);
        max = min + container.width - width;

    }

    /**
     * Set the position and size of th rectangle
     * @param width width of the rectangle
     * @param height height of the rectangle
     * @return rectangle with preferred position and size
     */
    private Rectangle makeRectangle(int width,int height){
        Point p = new Point((int)(center.getX() - (width / 2)),(int)center.getY());
        return  new Rectangle(p,new Dimension(width,height));
    }

    /**
     *Check whether the player intersect with the ball
     * @param b the constructor of ball
     * @return true or false value
     */
    public boolean impact(Ball b){
        return playerFace.contains(b.getPosition()) && playerFace.contains(b.down) ;
    }

    /**
     *Turn the player from static state to moving
     */
    public void move(){
        double x = center.getX() + moveAmount;
        if(x < min || x > max)
            return;
        center.setLocation(x,center.getY());
        playerFace.setLocation(center.x - (int)playerFace.getWidth()/2,center.y);
    }

    /**
     * Let the player move left
     */
    public void moveLeft(){
        moveAmount = -DEF_MOVE_AMOUNT;
    }

    /**
     * Let the player move right
     */
    public void movRight(){
        moveAmount = DEF_MOVE_AMOUNT;
    }

    /**
     * Stop the player
     */
    public void stop(){
        moveAmount = 0;
    }

    /**
     * Get the shape of player
     * @return the shape of player
     */
    public Shape getPlayerFace(){
        return  playerFace;
    }

    /**
     * Move the player to preferred position
     * @param p the preferred position
     */
    public void moveTo(Point p){
        center.setLocation(p);
        playerFace.setLocation(center.x - (int)playerFace.getWidth()/2,center.y);
    }
}
