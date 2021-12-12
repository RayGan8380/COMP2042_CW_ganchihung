package debug;

import java.awt.*;

public class DebugModel {
    public static final Color DEF_BKG = Color.WHITE;


    /**
     * Set the x-axis and y-axis speed of the ball
     * @param x
     * @param y
     */
    public void setValues(int x,int y){
        DebugPanel.ballXSpeed.setValue(x);
        DebugPanel.ballYSpeed.setValue(y);
    }

}
