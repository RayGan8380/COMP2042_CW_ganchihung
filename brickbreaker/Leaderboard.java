package brickbreaker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.io.*;
import static java.lang.Integer.valueOf;


public class Leaderboard extends JComponent implements MouseListener, MouseMotionListener {
    private GameFrame owner;
    private static final String BACK_TEXT = "Back";

    private Rectangle leaderboardFace;
    private Rectangle leaderboardFrame;
    private Rectangle backButton;

    private int[] leaderboardRanking  = {0,0,0,0,0,0};
    private int currentScore = 0;
    private String line = "";
    private String[] values;

    private Font buttonFont;
    private Font textFont;
    private Font scoreFont;


    private static final Color DARKBLUE = new Color(0,76,153);
    private static final Color WHEAT = new Color(255,229,204);
    private static final Color DARKORANGE = new Color(204,102,0);
    private static final Color LIGHTORANGE = new Color(153,76,0);

    private boolean backClicked;

    /**
     * Constructor of LeaderBoard
     * @param owner frame
     * @param area frame size
     */
    public Leaderboard(GameFrame owner, Dimension area){
        this.setFocusable(true);
        this.requestFocusInWindow();

        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        this.owner = owner;
        leaderboardFace = new Rectangle(new Point(0,0), area);
        leaderboardFrame = new Rectangle(new Point(10,10), new Dimension(430,280));
        this.setPreferredSize(area);

        try {                                           //create file or access previous file
            BufferedReader reader = new BufferedReader(new FileReader("Game_High_Scores.txt"));
            line = reader.readLine();
            values = line.split(",");

            for (int i = 0; i<6; i++){
            leaderboardRanking[i] = valueOf(values[i]);
            }
            reader.close();

        }catch(FileNotFoundException e) {
            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter("Game_High_Scores.txt"));
                System.out.println("Write file");
                for (int i = 0; i < 6; i++)
                    writer.write(leaderboardRanking[i] + ",");
                writer.close();

            } catch (IOException c) {
                c.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        buttonFont = new Font("Monospaced",Font.BOLD,20);
        textFont = new Font("Monospaced", Font.BOLD, 30);
        scoreFont = new Font("Monospaced", Font.ITALIC, 30);
        backButton = new Rectangle (new Dimension(area.width/3, area.height/12));

        repaint();

    }

    public void paint(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        FontRenderContext frc = g2d.getFontRenderContext();

        drawContainer(g2d);
        drawButton(g2d, frc);
        drawContent(g2d, frc);

    }
    /**
     * Draw the container
     * @param g2d component used to draw
     */
    private void drawContainer(Graphics2D g2d){
        g2d.setColor(DARKBLUE);
        g2d.fill(leaderboardFace);

        g2d.setColor(WHEAT);
        g2d.fill(leaderboardFrame);

    }
    /**
     * Draw the button
     * @param g2d component used to draw
     * @param frc container for the information needed to correctly measure text.
     */
    private void drawButton(Graphics2D g2d, FontRenderContext frc){
        Rectangle2D backRect = buttonFont.getStringBounds(BACK_TEXT,frc);                     //back button

        int centerX = (leaderboardFace.width - backButton.width) / 2;
        int centerY = (int) ((leaderboardFace.height - backButton.height) * 0.95);
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
    /**
     * Draw the text
     * @param g2d component used to draw
     * @param frc container for the information needed to correctly measure text.
     */
    private void drawContent(Graphics2D g2d, FontRenderContext frc) {
        Rectangle2D leaderboardRect = textFont.getStringBounds("LEADERBOARD",frc);

        int centerX,centerY;                                                          //leaderboard title

        centerX = (int)(leaderboardFrame.getWidth() - leaderboardRect.getWidth()) / 2;
        centerY = (int)(leaderboardFrame.getHeight() / 7);

        g2d.setColor(DARKBLUE);
        g2d.setFont(textFont);
        g2d.drawString("LEADERBOARD",centerX,centerY);

        try {                                                                         //read file to update
            BufferedReader reader = new BufferedReader(new FileReader("Game_High_Scores.txt"));
            line = reader.readLine();
            values = line.split(",");

            for ( int i = 0; i<6; i++){
                leaderboardRanking[i] = valueOf(values[i]);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String[] rank = {"1", "2", "3", "4", "5"};                                  //rank and ranking
        for(int i = 0; i <6; i++ ) {
            setFont(scoreFont);

            if (i == 0 )
                g2d.setColor(DARKORANGE);
            else if(i == 1)
                g2d.setColor(DARKBLUE);
            else
                g2d.setColor(LIGHTORANGE);

            Rectangle2D scoreRect = textFont.getStringBounds(values[i],frc);
            centerX = (int)((leaderboardFrame.getWidth() - scoreRect.getWidth()) / 1.5);
            centerY += 35;
            g2d.drawString( values[i], centerX, centerY);

            centerX = (int)(leaderboardFrame.getWidth() / 3);
            if (i == 5){
                scoreRect = textFont.getStringBounds("Current:",frc);
                centerX = (int)((leaderboardFrame.getWidth() - scoreRect.getWidth()) / 3.3);
                g2d.drawString( "Current:", centerX, centerY);
            }else
                g2d.drawString( rank[i], centerX, centerY);
        }

    }

    /**
     * pass in the current player's score and arrange it
     * in descending order with previous high scores
     * @param newPoints current player's score
     */
    public void pointsCompare(int newPoints){
        currentScore = newPoints;
        leaderboardRanking[5] = newPoints;

        for (int i = 0; i<6; i++ ){                         //arrange points in descending order
            for(int j = 0; j<6; j++){
                if (leaderboardRanking[i] > leaderboardRanking[j]) {
                    int temp = leaderboardRanking[j];
                    leaderboardRanking[j] = leaderboardRanking[i];
                    leaderboardRanking[i] = temp;
                }
            }
        }
        leaderboardRanking[5] = currentScore;                 //to display current score

        try {                                              //write and read file again
            BufferedWriter writer = new BufferedWriter(new FileWriter("Game_High_Scores.txt"));

            for (int i = 0; i < 6; i++)
                writer.write(leaderboardRanking[i] + ",");
            writer.close();

            BufferedReader reader = new BufferedReader(new FileReader("Game_High_Scores.txt"));
            line = reader.readLine();
            values = line.split(",");

            for (int i = 0; i<6; i++){
                leaderboardRanking[i] = valueOf(values[i]);
            }
            reader.close();

        }catch (IOException c){
            c.printStackTrace();
        }

    }
    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        Point p = mouseEvent.getPoint();
        if(backButton.contains(p)) {
            if (owner.getClickedFromGameOver()) {
                owner.enableGameBoard();
                owner.setClickedFromGameOver(false);
                repaint();
            } else
                owner.enableHomeMenu();
                repaint();
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
