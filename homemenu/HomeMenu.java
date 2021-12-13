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
package homemenu;

import main.GameFrame;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.io.IOException;


public class HomeMenu extends JComponent implements MouseListener, MouseMotionListener {

    private static final String GREETINGS = "Welcome to:";
    private static final String GAME_TITLE = "Brick Destroy";
    private static final String CREDITS = "Version 2.0";
    private static final String START_TEXT = "Start";
    private static final String LEADERBOARD_TEXT = "LeaderBoard";
    private static final String INFO_TEXT = "Info";
    private static final String MENU_TEXT = "Exit";

    private static final Color BORDER_COLOR = new Color(200,8,21); //Venetian Red
    private static final Color DASH_BORDER_COLOR = new  Color(255, 216, 0);//school bus yellow
    private static final Color TEXT_COLOR = new Color(0,255,51);//egyptian blue
    private static final Color CLICKED_COLOR = new  Color(0, 204, 0);
    private static final int BORDER_SIZE = 5;
    private static final float[] DASHES = {12,6};

    private BasicStroke borderStoke;

    private HomeMenuModal homeMenuModal = new HomeMenuModal();
    private HomeMenuController homeMenuController = new HomeMenuController();

    private Font greetingsFont;
    private Font gameTitleFont;
    private Font creditsFont;
    private Font buttonFont;


    /**
     * Consturctor of the HomeMenu
     * @param owner frame
     * @param area frame size
     */

    public HomeMenu(GameFrame owner, Dimension area){

        this.setFocusable(true);
        this.requestFocusInWindow();

        this.addMouseListener(this);
        this.addMouseMotionListener(this);

        homeMenuModal.setOwner(owner);

        homeMenuModal.setMenuFace(new Rectangle(new Point(0,0),area));
        try {
            homeMenuModal.setBackgroundImage(ImageIO.read(getClass().getResourceAsStream("brickbreaker.png")));
         } catch(IOException e){
            e.printStackTrace();
         }
        this.setPreferredSize(area);

        Dimension btnDim = new Dimension(area.width / 3, area.height / 12);
        homeMenuModal.setStartButton(new Rectangle(btnDim));
        homeMenuModal.setLeaderboardButton(new Rectangle(btnDim));
        homeMenuModal.setInfoButton(new Rectangle(btnDim));
        homeMenuModal.setMenuButton(new Rectangle(btnDim));

        borderStoke = new BasicStroke(BORDER_SIZE,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND,0,DASHES,0);

        greetingsFont = new Font("Noto Mono",Font.PLAIN,15);
        gameTitleFont = new Font("Noto Mono",Font.BOLD,40);
        creditsFont = new Font("Monospaced",Font.BOLD,10);
        buttonFont = new Font("Monospaced",Font.BOLD,homeMenuModal.getStartButton().height-2);

    }

    public void paint(Graphics g){
        Graphics2D g2d = (Graphics2D) g;

        drawContainer(g2d);
        drawText(g2d);
        drawButton(g2d);
    }

    /**
     * Draw the container
     * @param g2d component used to draw
     */
    private void drawContainer(Graphics2D g2d){

        g2d.drawImage(homeMenuModal.getBackgroundImage(),0,0,450,300,null);

        Stroke tmp = g2d.getStroke();

        g2d.setStroke(new BasicStroke(5));
        g2d.setColor(DASH_BORDER_COLOR);
        g2d.draw(homeMenuModal.getMenuFace());

        g2d.setStroke(borderStoke);
        g2d.setColor(BORDER_COLOR);
        g2d.draw(homeMenuModal.getMenuFace());

        g2d.setStroke(tmp);

    }
    /**
     * Draw the text
     * @param g2d component used to draw
     */
    private void drawText(Graphics2D g2d){

        g2d.setColor(TEXT_COLOR);

        FontRenderContext frc = g2d.getFontRenderContext();

        Rectangle2D greetingsRect = greetingsFont.getStringBounds(GREETINGS,frc);
        Rectangle2D gameTitleRect = gameTitleFont.getStringBounds(GAME_TITLE,frc);
        Rectangle2D creditsRect = creditsFont.getStringBounds(CREDITS,frc);

        int centerX,centerY;

        centerX = (int)(homeMenuModal.getMenuFace().getWidth() - greetingsRect.getWidth()) / 2;
        centerY = (int)(homeMenuModal.getMenuFace().getHeight() / 4);

        g2d.setFont(greetingsFont);
        g2d.drawString(GREETINGS,centerX,centerY);

        centerX = (int)(homeMenuModal.getMenuFace().getWidth() - gameTitleRect.getWidth()) / 2;
        centerY += (int) gameTitleRect.getHeight() * 1.1;//add 10% of String height between the two strings

        g2d.setFont(gameTitleFont);
        g2d.drawString(GAME_TITLE,centerX,centerY);

        centerX = (int)(homeMenuModal.getMenuFace().getWidth() - creditsRect.getWidth()) / 2;
        centerY += (int) creditsRect.getHeight() * 1.1;

        g2d.setFont(creditsFont);
        g2d.drawString(CREDITS,centerX,centerY);


    }
    /**
     * Draw the button
     * @param g2d component used to draw
     */
    private void drawButton(Graphics2D g2d){

        FontRenderContext frc = g2d.getFontRenderContext();

        Rectangle2D txtRect = buttonFont.getStringBounds(START_TEXT,frc);
        Rectangle2D mTxtRect = buttonFont.getStringBounds(MENU_TEXT,frc);
        Rectangle2D lTxtRect = buttonFont.getStringBounds(LEADERBOARD_TEXT,frc);
        Rectangle2D iTxtRect = buttonFont.getStringBounds(INFO_TEXT,frc);

        g2d.setFont(buttonFont);

        int x = (homeMenuModal.getMenuFace().width - homeMenuModal.getStartButton().width) / 2;                                  //START BUTTON
        int y =(int) ((homeMenuModal.getMenuFace().height - homeMenuModal.getStartButton().height) * 0.6);

        homeMenuModal.getStartButton().setLocation(x,y);

        x = (int)(homeMenuModal.getStartButton().getWidth() - txtRect.getWidth()) / 2;
        y = (int)(homeMenuModal.getStartButton().getHeight() - txtRect.getHeight()) / 2;

        x += homeMenuModal.getStartButton().x;
        y += homeMenuModal.getStartButton().y + (homeMenuModal.getStartButton().height * 0.9);

        if(HomeMenuModal.isStartClicked()){
            Color tmp = g2d.getColor();
            g2d.setColor(CLICKED_COLOR);
            g2d.draw(homeMenuModal.getStartButton());
            g2d.setColor(CLICKED_COLOR);
            g2d.drawString(START_TEXT,x,y);
            g2d.setColor(tmp);
        }
        else{
            g2d.draw(homeMenuModal.getStartButton());
            g2d.drawString(START_TEXT,x,y);
        }

        x = homeMenuModal.getStartButton().x;                                                                  //LEADERBOARD BUTTON
        y = homeMenuModal.getStartButton().y;

        y *= 1.2;

        homeMenuModal.getLeaderboardButton().setLocation(x,y);

        x = (int)(homeMenuModal.getLeaderboardButton().getWidth() - lTxtRect.getWidth()) / 2;
        y = (int)(homeMenuModal.getLeaderboardButton().getHeight() - lTxtRect.getHeight()) / 2;

        x += homeMenuModal.getLeaderboardButton().x;
        y += homeMenuModal.getLeaderboardButton().y + (homeMenuModal.getLeaderboardButton().height * 0.9);

        if(homeMenuModal.isLeaderboardClicked()){
            Color tmp = g2d.getColor();
            g2d.setColor(CLICKED_COLOR);
            g2d.draw(homeMenuModal.getLeaderboardButton());
            g2d.setColor(CLICKED_COLOR);
            g2d.drawString(LEADERBOARD_TEXT,x,y);
            g2d.setColor(tmp);
        }
        else{
            g2d.draw(homeMenuModal.getLeaderboardButton());
            g2d.drawString(LEADERBOARD_TEXT,x,y);
        }

        x = homeMenuModal.getLeaderboardButton().x;                                                                     //INFO BUTTON
        y = homeMenuModal.getLeaderboardButton().y;

        y *= 1.2;

        homeMenuModal.getInfoButton().setLocation(x,y);

        x = (int)(homeMenuModal.getInfoButton().getWidth() - iTxtRect.getWidth()) / 2;
        y = (int)(homeMenuModal.getInfoButton().getHeight() - iTxtRect.getHeight()) / 2;

        x += homeMenuModal.getInfoButton().x;
        y += homeMenuModal.getInfoButton().y + (homeMenuModal.getInfoButton().height * 0.9);

        if(HomeMenuModal.isInfoClicked()){
            Color tmp = g2d.getColor();

            g2d.setColor(CLICKED_COLOR);
            g2d.draw(homeMenuModal.getInfoButton());
            g2d.setColor(CLICKED_COLOR);
            g2d.drawString(INFO_TEXT,x,y);
            g2d.setColor(tmp);
        }
        else{
            g2d.draw(homeMenuModal.getInfoButton());
            g2d.drawString(INFO_TEXT,x,y);
        }

        x = homeMenuModal.getInfoButton().x;                                                                  //MENU BUTTON
        y = homeMenuModal.getInfoButton().y;

        y *= 1.15;

        homeMenuModal.getMenuButton().setLocation(x,y);

        x = (int)(homeMenuModal.getMenuButton().getWidth() - mTxtRect.getWidth()) / 2;
        y = (int)(homeMenuModal.getMenuButton().getHeight() - mTxtRect.getHeight()) / 2;

        x += homeMenuModal.getMenuButton().x;
        y += homeMenuModal.getMenuButton().y + (homeMenuModal.getMenuButton().height * 0.8);

        if(homeMenuModal.isMenuClicked()){
            Color tmp = g2d.getColor();
            g2d.setColor(CLICKED_COLOR);
            g2d.draw(homeMenuModal.getMenuButton());
            g2d.setColor(CLICKED_COLOR);
            g2d.drawString(MENU_TEXT,x,y);
            g2d.setColor(tmp);
        }
        else{
            g2d.draw(homeMenuModal.getMenuButton());
            g2d.drawString(MENU_TEXT,x,y);
        }
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        homeMenuController.mouseClicked(mouseEvent, homeMenuModal);
    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {
        homeMenuController.mousePressed(mouseEvent, this, homeMenuModal);
    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {
        homeMenuController.mouseReleased(mouseEvent, this, homeMenuModal);
    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }


    @Override
    public void mouseDragged(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseMoved(MouseEvent mouseEvent) {
        homeMenuController.mouseMoved(mouseEvent, this, homeMenuModal);
    }
}
