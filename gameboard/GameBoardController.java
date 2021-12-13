package gameboard;

import main.GameFrame;
import player.PlayerController;
import wall.Wall;
import wall.WallController;

import java.awt.*;
import java.awt.event.*;

public class GameBoardController  {

    public void keyPressed(KeyEvent keyEvent, GameBoard gameBoard) {
        switch(keyEvent.getKeyCode()){
            case KeyEvent.VK_A:
                PlayerController.moveLeft();
                break;
            case KeyEvent.VK_D:
                PlayerController.moveRight();
                break;
            case KeyEvent.VK_ESCAPE:
                GameBoardModel.setShowPauseMenu(!GameBoardModel.isShowPauseMenu());
                gameBoard.repaint();
                GameBoard.gameTimer.stop();
                break;
            case KeyEvent.VK_SPACE:
                if(!GameBoardModel.isShowPauseMenu())
                    if(GameBoard.gameTimer.isRunning()){
                        GameBoard.gameTimer.stop();
                        gameBoard.repaint();}
                    else{
                        GameBoard.gameTimer.start();
                        gameBoard.repaint();}
                break;
            case KeyEvent.VK_F1:
                if(keyEvent.isAltDown() && keyEvent.isShiftDown())
                    GameBoard.debugConsole.setVisible(true);
            default:
                PlayerController.stop();
        }
    }

    public void mouseClicked(MouseEvent mouseEvent, GameBoard gameBoard, GameFrame owner) {
        Point p = mouseEvent.getPoint();
        if(!GameBoardModel.isShowPauseMenu())
            return;
        if(GameBoard.continueButtonRect.contains(p)){
            GameBoardModel.setShowPauseMenu(false);
            gameBoard.repaint();
        }
        else if(GameBoard.restartButtonRect.contains(p)){
            GameBoardModel.setMessage("Restarting Game...");
            WallController.timePointsReset();
            GameBoard.gameTimer.stop();
            WallController.ballReset();
            WallController.wallReset();
            GameBoardModel.setShowPauseMenu(false);
            gameBoard.repaint();
        }
        else if(GameBoard.homeMenuButtonRect.contains(p)){
            WallController.timePointsReset();
            GameBoard.gameTimer.stop();
            GameBoardModel.setShowPauseMenu(false);
            owner.enableHomeMenu();
            gameBoard.repaint();
        }
        else if(GameBoard.exitButtonRect.contains(p)){
            System.exit(0);
        }

    }


    public void mousePressed(MouseEvent mouseEvent) {

    }


    public void mouseReleased(MouseEvent mouseEvent) {

    }


    public void mouseEntered(MouseEvent mouseEvent) {

    }


    public void mouseExited(MouseEvent mouseEvent) {

    }


    public void mouseDragged(MouseEvent mouseEvent) {

    }


    public void mouseMoved(MouseEvent mouseEvent, GameBoard gameBoard, GameBoardModel gameBoardModel) {
        Point p = mouseEvent.getPoint();
        if(GameBoard.exitButtonRect != null && GameBoardModel.isShowPauseMenu()) {
            if (GameBoard.exitButtonRect.contains(p) || GameBoard.continueButtonRect.contains(p) || GameBoard.restartButtonRect.contains(p) || GameBoard.homeMenuButtonRect.contains(p))
                gameBoard.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            else
                gameBoard.setCursor(Cursor.getDefaultCursor());
        }
        else{
            gameBoard.setCursor(Cursor.getDefaultCursor());
        }
    }

}
