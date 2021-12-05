package brickbreaker;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import javax.swing.*;


public class InfoScreen extends JComponent implements MouseListener, MouseMotionListener {
    private GameFrame owner;

    private static final String INFO1 = "Press A key will move left";
    private static final String INFO2 = "Press D key will move right";
    private static final String INFO3 = "Press SPACE key will start";
    private static final String INFO4 = "and pause the game";
    private static final String INFO5 = "Press ESC key will enter Menu";
    private static final String INFO6 = "Press SHIFT + ALT + F1 key";
    private static final String INFO7 = "will enter Settings";
    private static final String BACK_TEXT = "Back";
    private Font infoFont;

    private Rectangle infoFace;
    private Rectangle backButton;

    private boolean backClicked;


    public InfoScreen(GameFrame owner, Dimension area){
        this.setFocusable(true);
        this.requestFocusInWindow();

        this.addMouseListener(this);
        this.addMouseMotionListener(this);

        this.owner = owner;
        infoFace = new Rectangle(new Point(0,0), area);
        this.setPreferredSize(area);

        infoFont = new Font("Monospaced",Font.BOLD,20);
        backButton = new Rectangle (new Dimension(area.width/3, area.height/12));

    }

    public void paint(Graphics g ){
        Graphics2D g2d = (Graphics2D) g;                                                    //fill background
        g2d.setColor(new Color(255, 229, 204));
        g2d.fill(infoFace);

        g2d.setColor(new Color (0, 204,0));                                         //print info text
        FontRenderContext frc = g2d.getFontRenderContext();

        Rectangle2D info1Rect = infoFont.getStringBounds(INFO1,frc);
        Rectangle2D info2Rect = infoFont.getStringBounds(INFO2,frc);
        Rectangle2D info3Rect = infoFont.getStringBounds(INFO3,frc);
        Rectangle2D info4Rect = infoFont.getStringBounds(INFO4,frc);
        Rectangle2D info5Rect = infoFont.getStringBounds(INFO5,frc);
        Rectangle2D info6Rect = infoFont.getStringBounds(INFO6,frc);
        Rectangle2D info7Rect = infoFont.getStringBounds(INFO7,frc);

        int centerX,centerY;

        centerX = (int)(infoFace.getWidth() - info1Rect.getWidth()) / 2;
        centerY = (int)(infoFace.getHeight() / 6);
        g2d.setFont(infoFont);
        g2d.drawString(INFO1,centerX,centerY);
        centerX = (int)(infoFace.getWidth() - info2Rect.getWidth()) / 2;
        centerY += 25;
        g2d.drawString(INFO2,centerX,centerY);
        centerX = (int)(infoFace.getWidth() - info3Rect.getWidth()) / 2;
        centerY += 25;
        g2d.drawString(INFO3,centerX,centerY);
        centerX = (int)(infoFace.getWidth() - info4Rect.getWidth()) / 2;
        centerY += 25;
        g2d.drawString(INFO4,centerX,centerY);
        centerX = (int)(infoFace.getWidth() - info5Rect.getWidth()) / 2;
        centerY += 25;
        g2d.drawString(INFO5,centerX,centerY);
        centerX = (int)(infoFace.getWidth() - info6Rect.getWidth()) / 2;
        centerY += 25;
        g2d.drawString(INFO6,centerX,centerY);
        centerX = (int)(infoFace.getWidth() - info7Rect.getWidth()) / 2;
        centerY += 25;
        g2d.drawString(INFO7,centerX,centerY);


        Rectangle2D backRect = infoFont.getStringBounds(BACK_TEXT,frc);                     //back button
        centerX = (infoFace.width - backButton.width) / 2;
        centerY += 50;
        backButton.setLocation(centerX,centerY);

        centerX = (int)(infoFace.getWidth() - backRect.getWidth()) / 2;
        centerY += 20;
        g2d.setColor(new Color(0,128,255));
        g2d.setFont(infoFont);
        g2d.drawString(BACK_TEXT,centerX,centerY);

        if(backClicked){
            Color tmp = g2d.getColor();
            g2d.setColor(new Color(153,204,255));
            g2d.draw(backButton);
            g2d.setColor(new Color(153,204,255));
            g2d.drawString(BACK_TEXT,centerX,centerY);
            g2d.setColor(tmp);
        }
        else{
            g2d.draw(backButton);
            g2d.drawString(BACK_TEXT,centerX,centerY);
        }

    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        Point p = mouseEvent.getPoint();
        if(backButton.contains(p)){
         owner.enableHomeMenu();

        }
    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {
        Point p = mouseEvent.getPoint();
        if(backButton.contains(p)){
            backClicked = true;
            repaint(backButton.x,backButton.y,backButton.width+1,backButton.height+1);

        }
    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {
        if(backClicked ){
            backClicked = false;
            repaint(backButton.x,backButton.y,backButton.width+1,backButton.height+1);
        }
    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseDragged(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseMoved(MouseEvent mouseEvent) {
        Point p = mouseEvent.getPoint();
        if(backButton.contains(p))
            this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        else
            this.setCursor(Cursor.getDefaultCursor());

    }

}
