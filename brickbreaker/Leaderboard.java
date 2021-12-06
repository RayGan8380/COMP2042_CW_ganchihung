package brickbreaker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;

public class Leaderboard extends JComponent implements MouseListener, MouseMotionListener {
    private GameFrame owner;

    private static final String BACK_TEXT = "Back";

    private Rectangle leaderboardFace;
    private Rectangle backButton;

    private Font buttonFont;

    private boolean backClicked;

    public Leaderboard(GameFrame owner, Dimension area){
        this.setFocusable(true);
        this.requestFocusInWindow();

        this.addMouseListener(this);
        this.addMouseMotionListener(this);

        this.owner = owner;
        leaderboardFace = new Rectangle(new Point(0,0), area);
        this.setPreferredSize(area);

       buttonFont = new Font("Monospaced",Font.BOLD,20);
       backButton = new Rectangle (new Dimension(area.width/3, area.height/12));

    }

    public void paint(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(new Color(0, 255, 255));
        g2d.fill(leaderboardFace);

        FontRenderContext frc = g2d.getFontRenderContext();
        Rectangle2D backRect = buttonFont.getStringBounds(BACK_TEXT,frc);                     //back button
        int centerX = (leaderboardFace.width - backButton.width) / 2;
        int centerY = (leaderboardFace.height - backButton.height) / 2;;
        backButton.setLocation(centerX,centerY);

        centerX = (int)(leaderboardFace.getWidth() - backRect.getWidth()) / 2;
        centerY += 20;
        g2d.setColor(new Color(0,128,255));
        g2d.setFont(buttonFont);
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
