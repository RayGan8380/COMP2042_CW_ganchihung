package brickbreaker;

import java.awt.*;
//import java.awt.Point;
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



    public class Crack{

        private static final int CRACK_SECTIONS = 3;
        private static final double JUMP_PROBABILITY = 0.7;

        public static final int LEFT = 10;
        public static final int RIGHT = 20;
        public static final int UP = 30;
        public static final int DOWN = 40;
        public static final int VERTICAL = 100;
        public static final int HORIZONTAL = 200;

        private GeneralPath crack;

        private int crackDepth;
        private int steps;

        /**
         * Constructor of Crack
         * @param crackDepth the crack depth to the brick
         * @param steps the steps
         */
        public Crack(int crackDepth, int steps){

            crack = new GeneralPath();
            this.crackDepth = crackDepth;
            this.steps = steps;

        }

        /**
         * Return the crack
         * @return the crack path
         */
        public GeneralPath draw(){

            return crack;
        }

        /**
         * Reset the crack
         */
        public void reset(){
            crack.reset();
        }

        /**
         * Draw the crack
         * @param point the preferred position to make crack
         * @param direction the direction to make crack
         */
        protected void makeCrack(Point2D point, int direction){
            Rectangle bounds = Brick.this.brickFace.getBounds();

            Point impact = new Point((int)point.getX(),(int)point.getY());
            Point start = new Point();
            Point end = new Point();


            switch(direction){
                case LEFT:
                    start.setLocation(bounds.x + bounds.width, bounds.y);
                    end.setLocation(bounds.x + bounds.width, bounds.y + bounds.height);
                    Point tmp = makeRandomPoint(start,end,VERTICAL);
                    makeCrack(impact,tmp);

                    break;
                case RIGHT:
                    start.setLocation(bounds.getLocation());
                    end.setLocation(bounds.x, bounds.y + bounds.height);
                    tmp = makeRandomPoint(start,end,VERTICAL);
                    makeCrack(impact,tmp);

                    break;
                case UP:
                    start.setLocation(bounds.x, bounds.y + bounds.height);
                    end.setLocation(bounds.x + bounds.width, bounds.y + bounds.height);
                    tmp = makeRandomPoint(start,end,HORIZONTAL);
                    makeCrack(impact,tmp);
                    break;
                case DOWN:
                    start.setLocation(bounds.getLocation());
                    end.setLocation(bounds.x + bounds.width, bounds.y);
                    tmp = makeRandomPoint(start,end,HORIZONTAL);
                    makeCrack(impact,tmp);

                    break;

            }
        }

        /**
         * Set the crackness of the brick
         * @param start value more than the minimum value of the brick length
         * @param end value less than the maximum value of the brick length
         */
        protected void makeCrack(Point start, Point end){

            GeneralPath path = new GeneralPath();


            path.moveTo(start.x,start.y);

            double w = (end.x - start.x) / (double)steps;
            double h = (end.y - start.y) / (double)steps;

            int bound = crackDepth;
            int jump  = bound * 5;

            double x,y;

            for(int i = 1; i < steps;i++){

                x = (i * w) + start.x;
                y = (i * h) + start.y + randomInBounds(bound);

                if(inMiddle(i,CRACK_SECTIONS,steps))
                    y += jumps(jump,JUMP_PROBABILITY);

                path.lineTo(x,y);

            }

            path.lineTo(end.x,end.y);
            crack.append(path,true);
        }

        /**
         * Generate random number to make each crack with
         * different length
         * @param bound the crack depth
         * @return the value of random number minus crack depth
         */
        private int randomInBounds(int bound){
            int n = (bound * 2) + 1;
            return rnd.nextInt(n) - bound;
        }

        /**
         * Check whether th crack path is in middle
         * @param i the current step
         * @param steps the crack section
         * @param divisions the final steps
         * @return
         */
        private boolean inMiddle(int i,int steps,int divisions){
            int low = (steps / divisions);
            int up = low * (divisions - 1);

            return  (i > low) && (i < up);
        }

        /**
         * Top generate random number of the crack depth
         * @param bound the crack depth * 5
         * @param probability the probability of jump
         * @return the random number of crack depth
         */
        private int jumps(int bound,double probability){

            if(rnd.nextDouble() > probability)
                return randomInBounds(bound);
            return  0;

        }

        /**
         * Generate random crack point
         * @param from the start point of crack
         * @param to the end point of crack
         * @param direction the direction of crack
         * @return the crack's point
         */
        private Point makeRandomPoint(Point from,Point to, int direction){

            Point out = new Point();
            int pos;

            switch(direction){
                case HORIZONTAL:
                    pos = rnd.nextInt(to.x - from.x) + from.x;
                    out.setLocation(pos,to.y);
                    break;
                case VERTICAL:
                    pos = rnd.nextInt(to.y - from.y) + from.y;
                    out.setLocation(to.x,pos);
                    break;
            }
            return out;
        }

    }

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





