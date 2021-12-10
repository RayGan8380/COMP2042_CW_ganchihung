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

    private static final String  INFO = "Press A key will move left,Press D key will move right,Press SPACE key will start,and pause the game,Press ESC key will enter Menu,Press SHIFT + ALT + F1 key,will enter Settings";
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
        Graphics2D g2d = (Graphics2D) g;
        FontRenderContext frc = g2d.getFontRenderContext();

        drawContainer(g2d);
        drawButton(g2d, frc);
        drawText(g2d, frc);

    }

    private void drawContainer(Graphics2D g2d){
        g2d.setColor(new Color(255, 229, 204));
        g2d.fill(infoFace);

    }

    private void drawButton(Graphics2D g2d, FontRenderContext frc){                              //back button

        Rectangle2D backRect = infoFont.getStringBounds(BACK_TEXT,frc);
        int centerX = (infoFace.width - backButton.width) / 2;
        int centerY = 270;
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
    private void drawText(Graphics2D g2d, FontRenderContext frc){                               //print info text
        g2d.setColor(new Color (0, 204,0));
        g2d.setFont(infoFont);
        int centerX,centerY, spacing=0;
        String[]line = INFO.split(",");

        for(String lines : line){
            Rectangle2D infoRect = infoFont.getStringBounds(lines, frc);

            centerX = (int)(infoFace.getWidth() - infoRect.getWidth()) / 2;
            centerY = (int)(infoFace.getHeight() / 6) + spacing;

            g2d.drawString(lines,centerX,centerY);

            spacing += 25;

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
