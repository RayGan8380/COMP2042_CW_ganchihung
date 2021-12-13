package gameboard;

import java.awt.*;

public class GameBoardModel {
    private static final String CONTINUE = "Continue";
    private static final String RESTART = "Restart";
    private static final String EXIT = "Exit";
    private static final String HOMEMENU = "Home Menu";
    private static final String PAUSE = "Pause Menu";
    private static final int TEXT_SIZE = 30;
    private static final Color MENU_COLOR = new Color(0,255,0);

    private static final int DEF_WIDTH = 600;
    private static final int DEF_HEIGHT = 450;
    private static final Color BG_COLOR = Color.WHITE;
    private static boolean showPauseMenu;
    private int strLen;
    private static String message;

    /**
     *
     * @return string message
     */
    public String getMessage() {
        return message;
    }

    /**
     * Set new string message
     * @param messageGot new string message
     */
    public static void setMessage(String messageGot) {
        message = messageGot;
    }

    /**
     *
     * @return boolean showPauseMenu
     */
    public static boolean isShowPauseMenu() {
        return showPauseMenu;
    }

    /**
     * Set new boolean value of showPauseMenu
     * @param showPauseMenuGot new boolean value of showPauseMenu
     */
    public static void setShowPauseMenu(boolean showPauseMenuGot) {
        showPauseMenu = showPauseMenuGot;
    }

    /**
     *
     * @return the current  int strLen
     */

    public int getStrLen() {
        return strLen;
    }

    /**
     * Set new int strLen
      * @param strLen new int strLen
     */
    public void setStrLen(int strLen) {
        this.strLen = strLen;
    }

    /**
     *
     * @return string of CONTINUE
     */
    public static String getCONTINUE() {
        return CONTINUE;
    }
    /**
     *
     * @return string of RESTART
     */
    public static String getRESTART() {
        return RESTART;
    }
    /**
     *
     * @return string of EXIT
     */
    public static String getEXIT() {
        return EXIT;
    }
    /**
     *
     * @return string of HOMEMENU
     */
    public static String getHOMEMENU() {
        return HOMEMENU;
    }
    /**
     *
     * @return string of PAUSE
     */
    public static String getPAUSE() {
        return PAUSE;
    }
    /**
     *
     * @return int of TextSize
     */
    public static int getTextSize() {
        return TEXT_SIZE;
    }
    /**
     *
     * @return color of MenuColor
     */
    public static Color getMenuColor() {
        return MENU_COLOR;
    }
    /**
     *
     * @return int of DefWidth
     */
    public static int getDefWidth() {
        return DEF_WIDTH;
    }
    /**
     *
     * @return int of DefHeight
     */
    public static int getDefHeight() {
        return DEF_HEIGHT;
    }
    /**
     *
     * @return color of BgColor
     */
    public static Color getBgColor() {
        return BG_COLOR;
    }


}
