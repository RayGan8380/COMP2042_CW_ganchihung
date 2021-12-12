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
package wall;

import ball.BallController;
import brick.Brick;
import brick.CementBrick;
import brick.ClayBrick;
import brick.SteelBrick;
import brick.TitaniumBrick;
import ball.Ball;
import ball.BallModel;
import player.Player;
import player.PlayerController;
import player.PlayerModel;

import java.awt.*;
import java.util.Random;


public class Wall {

    public static Brick[] bricks;
    public static Ball ball;
    public Player player;
    public static PlayerController playerController;
    public static BallController ballController;

    /**
     * Constructor of Wall
     * @param drawArea the size of this component
     * @param brickCount the bricks will be generated each round of game
     * @param lineCount the lines that contains the bricks
     * @param brickDimensionRatio the brick size ratio
     * @param ballPos the current ball's position
     */
    public Wall(Rectangle drawArea, int brickCount, int lineCount, double brickDimensionRatio, Point ballPos){

        WallModel.startPoint = new Point(ballPos);

        Level.levels = Level.makeLevels(drawArea,brickCount,lineCount,brickDimensionRatio);
        Level.level = 0;

        WallModel.ballCount = 3;
        WallModel.ballLost = false;

        WallModel.rnd = new Random();

        WallController.makeBall(ballPos);
        int speedX,speedY;
        do{
            speedX = WallModel.rnd.nextInt(5) - 2;
        }while(speedX == 0);
        do{
            speedY = WallModel.rnd.nextInt(3);
        }while(speedY == 0);

        BallModel.setSpeed(speedX,speedY);

        player = new Player((Point) ballPos.clone(),150,10, drawArea);

        WallModel.area = drawArea;

    }

    /**
     * Make brick at preferred position, size and selected brick's type
     * @param point the (x,y) coordinates
     * @param size the size of the brick
     * @param type the type of brick
     * @return 0
     */
    public static Brick makeBrick(Point point, Dimension size, int type){
        Brick out;
        switch(type){
            case WallModel.CLAY:
                out = new ClayBrick(point,size);
                break;
            case WallModel.STEEL:
                out = new SteelBrick(point,size);
                break;
            case WallModel.CEMENT:
                out = new CementBrick(point, size);
                break;
            case WallModel.TITANIUM:
                out = new TitaniumBrick(point, size);
                break;
            default:
                throw  new IllegalArgumentException(String.format("Unknown Type:%d\n",type));
        }
        return  out;
    }


}
