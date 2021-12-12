package brick;

import ball.Ball;

import java.awt.*;
import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;
import java.util.Random;

/**
 * Created by filippo on 04/09/16.112122
 *
 */
abstract public class Brick  {

    public static final int DEF_CRACK_DEPTH = 1;
    public static final int DEF_STEPS = 35;


    public static final int UP_IMPACT = 100;
    public static final int DOWN_IMPACT = 200;
    public static final int LEFT_IMPACT = 300;
    public static final int RIGHT_IMPACT = 400;



    private static Random rnd;

    private String name;
    Shape brickFace;

    private Color border;
    private Color inner;

    private int fullStrength;
    private int strength;

    private boolean broken;

    /**
     * Initialize the brick
     * @param name type of brick
     * @param pos the position of brick
     * @param size the size of brick
     * @param border the color of the border
     * @param inner the inner color of the border
     * @param strength the impacts needed to break the brick
     */
    public Brick(String name, Point pos,Dimension size,Color border,Color inner,int strength){
        rnd = new Random();
        broken = false;
        this.name = name;
        brickFace = makeBrickFace(pos,size);
        this.border = border;
        this.inner = inner;
        this.fullStrength = this.strength = strength;

    }

    protected abstract Shape makeBrickFace(Point pos,Dimension size);
    /**
     * Check whether the brick is getting impact from ball
     * @param point the point where brick get hit
     * @param dir the direction of crack
     * @return boolean value form isBroken
     */
    public  boolean setImpact(Point2D point , int dir){
        if(broken)
            return false;
        impact();
        return  broken;
    }

    public abstract Shape getBrick();

    /**
     *
     * @return the color of the border
     */
    public Color getBorderColor(){
        return  border;
    }

    /**
     *
     * @return the inner color of the border
     */
    public Color getInnerColor(){
        return inner;
    }

    /**
     * Check the impact direction of the brick
     * @param b the position of bal
     * @return 0
     */
    public final int findImpact(Ball b){
        if(broken)
            return 0;
        int out  = 0;
        if(brickFace.contains(b.right))
            out = LEFT_IMPACT;
        else if(brickFace.contains(b.left))
            out = RIGHT_IMPACT;
        else if(brickFace.contains(b.up))
            out = DOWN_IMPACT;
        else if(brickFace.contains(b.down))
            out = UP_IMPACT;
        return out;
    }

    /**
     *
     * @return the status of broken whether is broken or not.
     */
    public final boolean isBroken(){
        return broken;
    }

    /**
     * Reset the brick status to original state
     */
    public void repair() {
        broken = false;
        strength = fullStrength;
    }

    /**
     * decrease the strength of brick by 1 and check
     * if the brick is broken or not
     */
    public void impact(){
        strength--;
        broken = (strength == 0);
    }


}





