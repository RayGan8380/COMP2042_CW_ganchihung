package player;

public class PlayerModel {
    public static final int DEF_MOVE_AMOUNT = 5;
    public static int moveAmount;
    public static int min;
    public static int max;

    /**
     * Set the player to left
     */
    public static void setToLeft(){
        moveAmount = -DEF_MOVE_AMOUNT;
    }

    /**
     * Set the player to right
     */
    public static void setToRight(){
        moveAmount = DEF_MOVE_AMOUNT;
    }
    /**
     * Set the player to stop
     */
    public static void setToStop(){
        moveAmount = 0;
    }

}
