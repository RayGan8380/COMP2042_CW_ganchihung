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
package player;

import java.awt.*;


public class Player {
    public static final Color BORDER_COLOR = Color.GREEN.darker().darker();
    public static final Color INNER_COLOR = Color.GREEN;
    public static Rectangle playerFace;
    public static Point center;

    /**
     * Contructor of Player
     * @param ballPoint (x,y) coordinates
     * @param width width of player
     * @param height height of player
     * @param container rectangle component
     */
    public Player(Point ballPoint,int width,int height,Rectangle container) {
        this.center = ballPoint;
        PlayerModel.moveAmount = 0;
        playerFace = makeRectangle(width, height);
        PlayerModel.min = container.x + (width / 2);
        PlayerModel.max = PlayerModel.min + container.width - width;

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
     * Get the shape of player
     * @return the shape of player
     */
    public Shape getPlayerFace(){
        return  playerFace;
    }


}
