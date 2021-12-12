package wall;

import brick.Brick;
import brick.ClayBrick;
import player.Player;
import player.PlayerController;

import java.awt.*;

public class Level {

    public static Player player;
    public static PlayerController playerController;

    public static Brick[][] levels;
    public static int level;

    /**
     * Generate the arrangement of the brick in 3 lines with the selected type of brick
     * @param drawArea the size of this component
     * @param brickCnt the bricks will be generated
     * @param lineCnt the lines that contains the bricks
     * @param brickSizeRatio the brick size ratio
     * @param type the type of brick
     * @return the arrangement of bricks
     */
    public static Brick[] makeSingleTypeLevel(Rectangle drawArea, int brickCnt, int lineCnt, double brickSizeRatio, int type){
        /*
          if brickCount is not divisible by line count,brickCount is adjusted to the biggest
          multiple of lineCount smaller then brickCount
         */
        brickCnt -= brickCnt % lineCnt;

        int brickOnLine = brickCnt / lineCnt;

        double brickLen = drawArea.getWidth() / brickOnLine;
        double brickHgt = brickLen / brickSizeRatio;

        brickCnt += lineCnt / 2;

        Brick[] tmp  = new Brick[brickCnt];

        Dimension brickSize = new Dimension((int) brickLen,(int) brickHgt);
        Point p = new Point();

        int i;
        for(i = 0; i < tmp.length; i++){
            int line = i / brickOnLine;
            if(line == lineCnt)
                break;
            double x = (i % brickOnLine) * brickLen;
            x =(line % 2 == 0) ? x : (x - (brickLen / 2));
            double y = (line) * brickHgt;
            p.setLocation(x,y);
            tmp[i] = Wall.makeBrick(p,brickSize,type);
        }

        for(double y = brickHgt;i < tmp.length;i++, y += 2*brickHgt){
            double x = (brickOnLine * brickLen) - (brickLen / 2);
            p.setLocation(x,y);
            tmp[i] = new ClayBrick(p,brickSize);
        }
        return tmp;

    }
    /**
     * Generate the arrangement of the brick in 3 lines with the selected types of brick
     * @param drawArea the size of this component
     * @param brickCnt the bricks will be generated
     * @param lineCnt the lines that contains the bricks
     * @param brickSizeRatio the brick size ratio
     * @param typeA the type of first brick
     * @param typeB the type of second brick
     * @return the arrangement of bricks
     */
    public static Brick[] makeChessboardLevel(Rectangle drawArea, int brickCnt, int lineCnt, double brickSizeRatio, int typeA, int typeB){
        /*
          if brickCount is not divisible by line count,brickCount is adjusted to the biggest
          multiple of lineCount smaller then brickCount
         */
        brickCnt -= brickCnt % lineCnt;

        int brickOnLine = brickCnt / lineCnt;

        int centerLeft = brickOnLine / 2 - 1;
        int centerRight = brickOnLine / 2 + 1;

        double brickLen = drawArea.getWidth() / brickOnLine;
        double brickHgt = brickLen / brickSizeRatio;

        brickCnt += lineCnt / 2;

        Brick[] tmp  = new Brick[brickCnt];

        Dimension brickSize = new Dimension((int) brickLen,(int) brickHgt);
        Point p = new Point();

        int i;
        for(i = 0; i < tmp.length; i++){
            int line = i / brickOnLine;
            if(line == lineCnt)
                break;
            int posX = i % brickOnLine;
            double x = posX * brickLen;
            x =(line % 2 == 0) ? x : (x - (brickLen / 2));
            double y = (line) * brickHgt;
            p.setLocation(x,y);

            boolean b = ((line % 2 == 0 && i % 2 == 0) || (line % 2 != 0 && posX > centerLeft && posX <= centerRight));
            tmp[i] = b ?  Wall.makeBrick(p,brickSize,typeA) : Wall.makeBrick(p,brickSize,typeB);
        }

        for(double y = brickHgt;i < tmp.length;i++, y += 2*brickHgt){
            double x = (brickOnLine * brickLen) - (brickLen / 2);
            p.setLocation(x,y);
            tmp[i] = Wall.makeBrick(p,brickSize,typeA);
        }
        return tmp;
    }
    /**
     * Generate different levels of brick arrangement and store in temp
     * @param drawArea the size of the component
     * @param brickCount the bricks will be generated
     * @param lineCount the lines contains the bricks
     * @param brickDimensionRatio the brick size ratio
     * @return a 2d array of bricks
     */
    public static Brick[][] makeLevels(Rectangle drawArea,int brickCount,int lineCount,double brickDimensionRatio){
        Brick[][] tmp = new Brick[WallModel.LEVELS_COUNT][];
        tmp[0] = makeSingleTypeLevel(drawArea,brickCount,lineCount,brickDimensionRatio,WallModel.CLAY);
        tmp[1] = makeChessboardLevel(drawArea,brickCount,lineCount,brickDimensionRatio,WallModel.CLAY,WallModel.CEMENT);
        tmp[2] = makeChessboardLevel(drawArea,brickCount,lineCount,brickDimensionRatio,WallModel.CLAY,WallModel.STEEL);
        tmp[3] = makeChessboardLevel(drawArea,brickCount,lineCount,brickDimensionRatio,WallModel.STEEL,WallModel.CEMENT);
        tmp[4] = makeChessboardLevel(drawArea,brickCount,lineCount,brickDimensionRatio,WallModel.STEEL,WallModel.TITANIUM);
        return tmp;
    }
}
