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
package gameboard;

import leaderboard.Leaderboard;
import main.GameFrame;
import player.PlayerController;
import wall.WallModel;
import wall.WallController;
import wall.Wall;
import brick.Brick;
import ball.Ball;
import ball.BallModel;
import player.Player;
import debug.DebugConsole;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.font.FontRenderContext;



public class GameBoard extends JComponent implements KeyListener, MouseListener, MouseMotionListener{

    public static Timer gameTimer;

    private Leaderboard leaderboard;
    public GameFrame gameFrame;

    public static Wall wall;
    public static  WallController wallController;
    public static  WallModel wallModel;
    public static  GameBoardModel gameBoardModel;
    public static GameBoardController gameBoardController;


    private Font menuFont;
    private Font timeFont;

    public static Rectangle continueButtonRect;
    public static Rectangle exitButtonRect;
    public static Rectangle restartButtonRect;
    public static Rectangle homeMenuButtonRect;

    public static DebugConsole debugConsole;

    /**
     * Constructor of Gameboard
     * @param owner frame
     */
    public GameBoard(GameFrame owner){
        super();
        leaderboard = new Leaderboard(gameFrame, new Dimension(450,300));
        gameFrame = owner;
        gameBoardModel = new GameBoardModel();
        gameBoardController = new GameBoardController();
        gameBoardModel.setStrLen(0);
        GameBoardModel.setShowPauseMenu(false);

        menuFont = new Font("Monospaced",Font.PLAIN, GameBoardModel.getTextSize());
        timeFont = new Font("Monospace",Font.PLAIN, 15);

        this.initialize();
        GameBoardModel.setMessage("");
        wallController = new WallController();
        wall = new Wall(new Rectangle(0,0, GameBoardModel.getDefWidth(), GameBoardModel.getDefHeight()),30,3,6/2,new Point(300,430));
        wallModel = new WallModel();
        debugConsole = new DebugConsole(wall,this);

        //initialize the first level
        WallController.nextLevel();
        WallController.ballReset();
        WallController.timePointsReset();

        gameTimer = new Timer(10,e ->{
            wallController.timePointCalculation();
            WallController.move();
            WallController.findImpacts();
            WallModel.newPoints_string = String.format("%04d", WallModel.newPoints);
            GameBoardModel.setMessage(String.format("Bricks: %d Balls %d", WallModel.getBrickCount(), WallModel.getBallCount()));
            if(WallModel.isBallLost()){
                if(WallModel.ballEnd()){
                    gameFrame.setClickedFromGameOver(true);
                    leaderboard.pointsCompare(WallModel.newPoints);
                    gameFrame.enableLeaderboard();
                    WallController.wallReset();
                    WallController.timePointsReset();
                    GameBoardModel.setMessage("Game over");
                }
                WallController.ballReset();
                gameTimer.stop();
            }
            else if(WallModel.isDone()){
                if(WallController.hasLevel()){
                    GameBoardModel.setMessage("Go to Next Level");
                    gameTimer.stop();
                    WallController.ballReset();
                    WallController.wallReset();
                    WallController.nextLevel();
                    gameFrame.setClickedFromGameOver(true);
                    leaderboard.pointsCompare(WallModel.newPoints);
                    gameFrame.enableLeaderboard();
                    WallController.timePointsReset();
                }
                else{
                    GameBoardModel.setMessage("ALL WALLS DESTROYED");
                    gameTimer.stop();
                    leaderboard.pointsCompare(WallModel.newPoints);
                }
            }
            repaint();
        });
    }

    /**
     * Initialize the component
     */
    private void initialize(){
        this.setPreferredSize(new Dimension(GameBoardModel.getDefWidth(), GameBoardModel.getDefHeight()));
        this.setFocusable(true);
        this.requestFocusInWindow();
        this.addKeyListener(this);
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
    }

    public void paint(Graphics g){

        Graphics2D g2d = (Graphics2D) g;

        clear(g2d);

        g2d.setColor(Color.BLUE);
        g2d.drawString(gameBoardModel.getMessage(),250,250);

        g2d.setFont(timeFont);
        g2d.drawString("Time: "+ WallModel.minutes_string + ":" + WallModel.seconds_string, 250, 225);
        g2d.drawString("Points: "+ WallModel.newPoints_string, 250, 180);

        drawBall(Wall.ball,g2d);

        for(Brick b : Wall.bricks)
            if(!b.isBroken())
                drawBrick(b,g2d);

        drawPlayer(wall.player,g2d);

        if(GameBoardModel.isShowPauseMenu())
            drawMenu(g2d);

        Toolkit.getDefaultToolkit().sync();
    }

    /**
     * Clear the current graphics
     * @param g2d the component used to draw
     */
    private void clear(Graphics2D g2d){
        Color tmp = g2d.getColor();
        g2d.setColor(GameBoardModel.getBgColor());
        g2d.fillRect(0,0,getWidth(),getHeight());
        g2d.setColor(tmp);
    }

    /**
     * Draw the brick
     * @param brick the current brick
     * @param g2d component used to draw
     */
    private void drawBrick(Brick brick,Graphics2D g2d){
        Color tmp = g2d.getColor();

        g2d.setColor(brick.getInnerColor());
        g2d.fill(brick.getBrick());

        g2d.setColor(brick.getBorderColor());
        g2d.draw(brick.getBrick());


        g2d.setColor(tmp);
    }

    /**
     * Draw the curent ball
     * @param ball the current ball
     * @param g2d component used to draw
     */
    private void drawBall(Ball ball,Graphics2D g2d){
        Color tmp = g2d.getColor();

        Shape s = BallModel.getBallFace();

        g2d.setColor(BallModel.getInnerColor());
        g2d.fill(s);

        g2d.setColor(BallModel.getBorderColor());
        g2d.draw(s);

        g2d.setColor(tmp);
    }

    /**
     * Draw the player
     * @param p the current player
     * @param g2d component used to draw
     */
    private void drawPlayer(Player p,Graphics2D g2d){
        Color tmp = g2d.getColor();

        Shape s = p.getPlayerFace();
        g2d.setColor(Player.INNER_COLOR);
        g2d.fill(s);

        g2d.setColor(Player.BORDER_COLOR);
        g2d.draw(s);

        g2d.setColor(tmp);
    }

    /**
     * Draw the menu
     * @param g2d component used to draw
     */
    private void drawMenu(Graphics2D g2d){
        obscureGameBoard(g2d);
        drawPauseMenu(g2d);
    }

    /**
     * Put the GameBoard behind permanently
     * @param g2d component used to draw
     */
    private void obscureGameBoard(Graphics2D g2d){

        Composite tmp = g2d.getComposite();
        Color tmpColor = g2d.getColor();

        AlphaComposite ac = AlphaComposite.getInstance(AlphaComposite.SRC_OVER,0.55f);
        g2d.setComposite(ac);

        g2d.setColor(Color.BLACK);
        g2d.fillRect(0,0, GameBoardModel.getDefWidth(), GameBoardModel.getDefHeight());

        g2d.setComposite(tmp);
        g2d.setColor(tmpColor);
    }

    /**
     * Draw the pause menu
     * @param g2d component used to draw
     */
    private void drawPauseMenu(Graphics2D g2d){
        Font tmpFont = g2d.getFont();
        Color tmpColor = g2d.getColor();


        g2d.setFont(menuFont);
        g2d.setColor(GameBoardModel.getMenuColor());

        if(gameBoardModel.getStrLen() == 0){
            FontRenderContext frc = g2d.getFontRenderContext();
            gameBoardModel.setStrLen(menuFont.getStringBounds(GameBoardModel.getPAUSE(),frc).getBounds().width);
        }

        int x = (this.getWidth() - gameBoardModel.getStrLen()) / 2;
        int y = this.getHeight() / 10;

        g2d.drawString(GameBoardModel.getPAUSE(),x,y);

        x = this.getWidth() / 8;
        y = this.getHeight() / 4;


        if(continueButtonRect == null){
            FontRenderContext frc = g2d.getFontRenderContext();
            continueButtonRect = menuFont.getStringBounds(GameBoardModel.getCONTINUE(),frc).getBounds();
            continueButtonRect.setLocation(x,y-continueButtonRect.height);
        }

        g2d.drawString(GameBoardModel.getCONTINUE(),x,y);

        y += 100;

        if(restartButtonRect == null){
            restartButtonRect = (Rectangle) continueButtonRect.clone();
            restartButtonRect.setLocation(x,y-restartButtonRect.height);
        }

        g2d.drawString(GameBoardModel.getRESTART(),x,y);

        y += 100;

        if(homeMenuButtonRect == null){
            homeMenuButtonRect = (Rectangle) restartButtonRect.clone();
            homeMenuButtonRect.setLocation(x,y-restartButtonRect.height);
        }

        g2d.drawString(GameBoardModel.getHOMEMENU(),x,y);

        y += 100;

        if(exitButtonRect == null){
            exitButtonRect = (Rectangle) continueButtonRect.clone();
            exitButtonRect.setLocation(x,y-exitButtonRect.height);
        }

        g2d.drawString(GameBoardModel.getEXIT(),x,y);



        g2d.setFont(tmpFont);
        g2d.setColor(tmpColor);
    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        gameBoardController.keyPressed(e, this);

    }

    @Override
    public void keyReleased(KeyEvent e) {
        PlayerController.stop();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        gameBoardController.mouseClicked(e, this , gameFrame);
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        gameBoardController.mouseMoved(e, this, gameBoardModel);
    }
    public void onLostFocus(){
        gameTimer.stop();
        GameBoardModel.setMessage("Focus Lost");
        repaint();
    }

}
