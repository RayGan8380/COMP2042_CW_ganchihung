/*
 *  Brick Destroy - A simple Arcade video game
 *   Copyright (C) 2017  Filippo Ranza
 *  erwrtewer
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
package debug;

import wall.WallModel;
import wall.WallController;
import wall.Wall;
import brickbreaker.*;
import ball.Ball;
import ball.BallModel;

import javax.swing.*;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;


public class DebugConsole extends JDialog implements WindowListener{

    private static final String TITLE = "Debug Console";
    private DebugPanel debugPanel;
    private DebugModel debugModel;
    private GameBoard gameBoard;
    private Wall wall;

    /**
     * Constructor of the DebugConsole
     * @param wall wall component
     * @param gameBoard gameboard component
     */
    public DebugConsole(Wall wall,GameBoard gameBoard){

        this.wall = wall;

        this.gameBoard = gameBoard;
        initialize();

        debugModel = new DebugModel();
        debugPanel = new DebugPanel();
        this.add(debugPanel,BorderLayout.CENTER);

        addingListener();

        this.pack();
    }

    /**
     * Initialize the debug console
     */
    private void initialize(){
        this.setModal(true);
        this.setTitle(TITLE);
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.addWindowListener(this);
        this.setFocusable(true);
    }

    /**
     * Add listener to each buttons and sliders
     */
    public void addingListener () {
        ActionListener skipLevelButton = skiplevel -> {
            WallController.nextLevel();
            WallController.timePointsReset();
        };

        ActionListener resetButton = skiplevel -> {
            WallModel.resetBallCount();
            WallController.timePointsReset();
        };
        ChangeListener ballXspeed = ballX -> WallModel.setBallXSpeed(debugPanel.ballXSpeed.getValue());
        ChangeListener ballYspeed = ballX -> WallModel.setBallYSpeed(debugPanel.ballYSpeed.getValue());

        debugPanel.skipLevel.addActionListener(skipLevelButton);
        debugPanel.resetBalls.addActionListener(resetButton);
        debugPanel.ballXSpeed.addChangeListener(ballXspeed);
        debugPanel.ballYSpeed.addChangeListener(ballYspeed);
    }
    @Override
    public void windowOpened(WindowEvent windowEvent) {

    }

    @Override
    public void windowClosing(WindowEvent windowEvent) {
        gameBoard.repaint();
    }

    @Override
    public void windowClosed(WindowEvent windowEvent) {

    }

    @Override
    public void windowIconified(WindowEvent windowEvent) {

    }

    @Override
    public void windowDeiconified(WindowEvent windowEvent) {

    }

    @Override
    public void windowActivated(WindowEvent windowEvent) {
        this.setLocationRelativeTo(null);
        Ball b = wall.ball;
        debugModel.setValues(BallModel.getSpeedX(), BallModel.getSpeedY());
    }

    @Override
    public void windowDeactivated(WindowEvent windowEvent) {

    }


    }
