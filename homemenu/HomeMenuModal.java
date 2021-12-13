package homemenu;

import main.GameFrame;

import java.awt.*;
import java.awt.image.BufferedImage;

public class HomeMenuModal {
    private Rectangle menuFace;
    private Rectangle startButton;
    private Rectangle menuButton;
    private Rectangle leaderboardButton;
    private Rectangle infoButton;

    private static BufferedImage backgroundImage;
    private static GameFrame owner;

    private static boolean startClicked;
    private static boolean menuClicked;
    private static boolean leaderboardClicked;
    private static boolean infoClicked;

    /**
     *
     * @return Rectangle of menuface
     */
    public Rectangle getMenuFace() {
        return menuFace;
    }

    /**
     * Set new menuFace
     * @param menuFace new menuFace
     */
    public void setMenuFace(Rectangle menuFace) {
        this.menuFace = menuFace;
    }

    /**
     *
     * @return rectangle of startButton
     */
    public Rectangle getStartButton() {
        return startButton;
    }

    /**
     * Set new start button
     * @param startButton new rectangle of startButton
     */
    public void setStartButton(Rectangle startButton) {
        this.startButton = startButton;
    }

    /**
     *
     * @return rectangle of menu button
     */
    public Rectangle getMenuButton() {
        return menuButton;
    }

    /**
     * Set new menu button
     * @param menuButton new rectangle of menu button
     */
    public void setMenuButton(Rectangle menuButton) {
        this.menuButton = menuButton;
    }

    /**
     *
     * @return rectangle of leaderboard button
     */
    public Rectangle getLeaderboardButton() {
        return leaderboardButton;
    }

    /**
     * Set new leaderboard button
     * @param leaderboardButton new rectangle of leaderboard button
     */
    public void setLeaderboardButton(Rectangle leaderboardButton) {
        this.leaderboardButton = leaderboardButton;
    }

    /**
     *
     * @return current info button
     */
    public Rectangle getInfoButton() {
        return infoButton;
    }

    /**
     * Set new info button
     * @param infoButton new info button
     */
    public void setInfoButton(Rectangle infoButton) {
        this.infoButton = infoButton;
    }

    /**
     *
     * @return current background image
     */
    public BufferedImage getBackgroundImage() {
        return backgroundImage;
    }

    /**
     * Set new background image
     * @param backgroundImage  new background image
     */
    public void setBackgroundImage(BufferedImage backgroundImage) {
        HomeMenuModal.backgroundImage = backgroundImage;
    }

    /**
     *
     * @return the current owner
     */
    public GameFrame getOwner() {
        return owner;
    }

    /**
     * Set new owner
     * @param owner new  owner
     */
    public void setOwner(GameFrame owner) {
        this.owner = owner;
    }

    /**
     *
     * @return value of current boolean StartClicked
     */
    public static boolean isStartClicked() {
        return startClicked;
    }

    /**
     * Set new value of boolean StartClicked
     * @param startClicked new value of boolean startClicked
     */
    public static void setStartClicked(boolean startClicked) {
        HomeMenuModal.startClicked = startClicked;
    }

    /**
     *
     * @return boolean value of menuClicked
     */
    public static boolean isMenuClicked() {
        return menuClicked;
    }

    /**
     * Set new boolean value of menuClicked
     * @param menuClicked new boolean value of menuClicked
     */
    public static void setMenuClicked(boolean menuClicked) {
        HomeMenuModal.menuClicked = menuClicked;
    }

    /**
     *
     * @return value of boolean leaderboardClicked
     */
    public static boolean isLeaderboardClicked() {
        return leaderboardClicked;
    }

    /**
     * Set new value of boolean leaderboardClicked
     * @param leaderboardClicked new boolean value
     */
    public static void setLeaderboardClicked(boolean leaderboardClicked) {
        HomeMenuModal.leaderboardClicked = leaderboardClicked;
    }

    /**
     *
     * @return the boolean value of infoClicked
     */
    public static boolean isInfoClicked() {
        return infoClicked;
    }

    /**
     * Set new value of boolean infoClicked
     * @param infoClicked new value of boolean infoClicked
     */
    public static void setInfoClicked(boolean infoClicked) {
        HomeMenuModal.infoClicked = infoClicked;
    }


}
