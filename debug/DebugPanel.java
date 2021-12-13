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
package debug;

import javax.swing.*;
import java.awt.*;

public class DebugPanel extends JPanel {


    public JButton skipLevel;
    public JButton resetBalls;

    public static JSlider ballXSpeed;
    public static JSlider ballYSpeed;

    /**
     * Constructor of the DebugPanel
     */
    public DebugPanel(){

        initialize();

        skipLevel = new JButton("Skip Level");
        resetBalls = new JButton("Reset Balls");

        ballXSpeed = new JSlider(-4,4);
        ballYSpeed = new JSlider(-4,4);


        this.add(skipLevel);
        this.add(resetBalls);

        this.add(ballXSpeed);
        this.add(ballYSpeed);

    }

    /**
     * Initialize the background and layout of debug panel
     */
    private void initialize(){
        this.setBackground(DebugModel.DEF_BKG);
        this.setLayout(new GridLayout(2,2));
    }

}
