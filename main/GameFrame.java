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
package main;

import brickbreaker.GameBoard;
import brickbreaker.HomeMenu;
import brickbreaker.InfoScreen;
import brickbreaker.Leaderboard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;


public class GameFrame extends JFrame implements WindowFocusListener {
    private static final String DEF_TITLE = "Brick Destroy";

    private GameBoard gameBoard;
    private HomeMenu homeMenu;
    private InfoScreen infoScreen;
    private Leaderboard leaderboard;

    private boolean gaming;
    private boolean clickedFromGameOver;

    /**
     * Constructs and initializes different JComponents
     */

    public GameFrame() {
        super();

        gaming = false;

        this.setLayout(new BorderLayout());

        gameBoard = new GameBoard(this);

        homeMenu = new HomeMenu(this ,new Dimension(450,300));

        infoScreen = new InfoScreen(this,new Dimension(450,300));

        leaderboard = new Leaderboard(this,new Dimension(450,300));

        this.add(homeMenu,BorderLayout.CENTER);

        this.setUndecorated(true);

    }

    /**
     * Initializes the JFrame
     */
    public void initialize() {
        this.setTitle(DEF_TITLE);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    /**
     *Add the GameBoard to the frame
     */
    public void enableGameBoard() {
        this.dispose();
        this.remove(homeMenu);
        this.remove(leaderboard);
        this.add(gameBoard, BorderLayout.CENTER);
        this.setUndecorated(false);
        initialize();
        /*to avoid problems with graphics focus controller is added here*/
        this.addWindowFocusListener(this);

    }

    /**
     * Add the InfoScreem to the frame
     */
    public void enableInfoScreen(){
        this.dispose();
        this.remove(homeMenu);
        this.add(infoScreen, BorderLayout.CENTER);
        this.setUndecorated(false);
        initialize();
        /*to avoid problems with graphics focus controller is added here*/
        this.addWindowFocusListener(this);
    }

    /**
     * Add the HomeMenu to the frame
     */
    public void enableHomeMenu(){
        this.dispose();
        this.remove(infoScreen);
        this.remove(leaderboard);
        this.remove(gameBoard);
        this.add(homeMenu, BorderLayout.CENTER);
        this.setUndecorated(false);
        initialize();
        /*to avoid problems with graphics focus controller is added here*/
        this.addWindowFocusListener(this);
    }

    /**
     * Add the LeaderBoard to the frame
     */
    public void enableLeaderboard(){
        this.dispose();
        this.remove(homeMenu);
        this.remove(gameBoard);
        this.add(leaderboard, BorderLayout.CENTER);
        this.setUndecorated(false);
        initialize();
        /*to avoid problems with graphics focus controller is added here*/
        this.addWindowFocusListener(this);
    }

    /**
     * Check whether the back button is clicked from where
     * @return the status of getClickedFromGameOver
     */
    public boolean getClickedFromGameOver(){

        return clickedFromGameOver;
    }

    /**
     *Set the clickedFromGameOver to true or false
     * @param ClickedFromGameOver is the true or false value
     */
    public void setClickedFromGameOver(boolean ClickedFromGameOver){
        clickedFromGameOver = ClickedFromGameOver;
    }

    @Override
    public void windowGainedFocus(WindowEvent windowEvent) {
        /*
           the first time the frame loses focus is because
            it has been disposed to install the GameBoard,
            so went it regains the focus it's ready to play.
            of course calling a method such as 'onLostFocus'
            is useful only if the GameBoard as been displayed
           at least once
        */
        gaming = true;
    }
    @Override
    public void windowLostFocus(WindowEvent windowEvent) {
        if(gaming)
            gameBoard.onLostFocus();

    }
}
