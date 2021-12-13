package homemenu;

import java.awt.*;
import java.awt.event.MouseEvent;

public class HomeMenuController {

    public void mouseClicked(MouseEvent mouseEvent, HomeMenuModal homeMenuModal) {
        Point p = mouseEvent.getPoint();
        if(homeMenuModal.getStartButton().contains(p)){
            homeMenuModal.getOwner().enableGameBoard();

        }
        else if(homeMenuModal.getMenuButton().contains(p)){
            System.out.println("Goodbye " + System.getProperty("user.name"));
            System.exit(0);
        }
        else if(homeMenuModal.getLeaderboardButton().contains(p)){
            homeMenuModal.getOwner().enableLeaderboard();
        }
        else if(homeMenuModal.getInfoButton().contains(p)){
            homeMenuModal.getOwner().enableInfoScreen();
        }
    }

    public void mousePressed(MouseEvent mouseEvent, HomeMenu homeMenu, HomeMenuModal homeMenuModal) {
        Point p = mouseEvent.getPoint();
        if(homeMenuModal.getStartButton().contains(p)){
            HomeMenuModal.setStartClicked(true);
            homeMenu.repaint(homeMenuModal.getStartButton().x,homeMenuModal.getStartButton().y,homeMenuModal.getStartButton().width+1,homeMenuModal.getStartButton().height+1);

        }
        else if(homeMenuModal.getMenuButton().contains(p)){
            HomeMenuModal.setMenuClicked(true);
            homeMenu.repaint(homeMenuModal.getMenuButton().x,homeMenuModal.getMenuButton().y,homeMenuModal.getMenuButton().width+1,homeMenuModal.getMenuButton().height+1);
        }
        else if(homeMenuModal.getLeaderboardButton().contains(p)){
            HomeMenuModal.setLeaderboardClicked(true);
            homeMenu.repaint(homeMenuModal.getLeaderboardButton().x,homeMenuModal.getLeaderboardButton().y,homeMenuModal.getLeaderboardButton().width+1,homeMenuModal.getLeaderboardButton().height+1);
        }
        else if(homeMenuModal.getInfoButton().contains(p)){
            HomeMenuModal.setInfoClicked(true);
            homeMenu.repaint(homeMenuModal.getInfoButton().x,homeMenuModal.getInfoButton().y,homeMenuModal.getInfoButton().width+1,homeMenuModal.getInfoButton().height+1);
        }
    }


    public void mouseReleased(MouseEvent mouseEvent, HomeMenu homemenu, HomeMenuModal homeMenuModal) {
        if(HomeMenuModal.isStartClicked()){
            HomeMenuModal.setStartClicked(false);
            homemenu.repaint(homeMenuModal.getStartButton().x,homeMenuModal.getStartButton().y,homeMenuModal.getStartButton().width+1,homeMenuModal.getStartButton().height+1);
        }
        else if(HomeMenuModal.isMenuClicked()){
            HomeMenuModal.setMenuClicked(false);
            homemenu.repaint(homeMenuModal.getMenuButton().x,homeMenuModal.getMenuButton().y,homeMenuModal.getMenuButton().width+1,homeMenuModal.getMenuButton().height+1);
        }
        else if(HomeMenuModal.isLeaderboardClicked()){
            HomeMenuModal.setLeaderboardClicked(false);
            homemenu.repaint(homeMenuModal.getLeaderboardButton().x,homeMenuModal.getLeaderboardButton().y,homeMenuModal.getLeaderboardButton().width+1,homeMenuModal.getLeaderboardButton().height+1);
        }
        else if(HomeMenuModal.isInfoClicked()){
            HomeMenuModal.setInfoClicked(false);
            homemenu.repaint(homeMenuModal.getInfoButton().x,homeMenuModal.getInfoButton().y,homeMenuModal.getInfoButton().width+1,homeMenuModal.getInfoButton().height+1);
        }
    }


    public void mouseEntered(MouseEvent mouseEvent) {

    }


    public void mouseExited(MouseEvent mouseEvent) {

    }



    public void mouseDragged(MouseEvent mouseEvent) {

    }


    public void mouseMoved(MouseEvent mouseEvent, HomeMenu homeMenu, HomeMenuModal homeMenuModal) {
        Point p = mouseEvent.getPoint();
        if(homeMenuModal.getStartButton().contains(p) || homeMenuModal.getMenuButton().contains(p))
            homeMenu.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        else if (homeMenuModal.getLeaderboardButton().contains(p) || homeMenuModal.getInfoButton().contains(p))
            homeMenu.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        else
            homeMenu.setCursor(Cursor.getDefaultCursor());

    }
}
