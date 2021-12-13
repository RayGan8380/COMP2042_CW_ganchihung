package brick;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.Random;

public class TitaniumBrick extends Brick{
    private static final String NAME = "Titanium Brick";
    private static final Color DEF_INNER = new Color(210, 212, 178);
    private static final Color DEF_BORDER = Color.BLACK;
    private static final int TITANIUM_STRENGTH = 1;
    private static final double TITANIUM_PROBABILITY = 0.3;

    private Random rnd;
    private Shape brickFace;

    /**
     * Constructor of TitaniumBrick
     * @param point the preferred position
     * @param size the preferred brick size
     */
    public TitaniumBrick(Point point, Dimension size){
        super(NAME,point,size,DEF_BORDER,DEF_INNER,TITANIUM_STRENGTH);
        rnd = new Random();
        brickFace = super.brickFace;
    }

    @Override
    protected Shape makeBrickFace(Point pos, Dimension size) {
        return new Rectangle(pos,size);
    }

    @Override
    public Shape getBrick() {
        return brickFace;
    }
    /**
     * Check whether the brick is getting impact from ball
     * @param point the point where brick get hit
     * @param dir the direction of crack
     * @return boolean value form isBroken
     */
    public  boolean setImpact(Point2D point , int dir){
        if(super.isBroken())
            return false;
        impact();
        return  super.isBroken();
    }
    /**
     * generating random number to see whether it is less then
     * the broken probability
     */
    public void impact(){
        if(rnd.nextDouble() < TITANIUM_PROBABILITY){
            super.impact();
        }
    }
}
