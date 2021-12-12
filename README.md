# COMP2042_CW_ganchihung

## Basic Maintenance
1. I have deleted some of the unused resources of the original file such unused code, unused libraries. Those codes and libraries are never been accessed before.
2. The encapsulation is done for the classes needed.
3. All the classes and methods are named correctly.
4. These two functions are replaced with setLocationRelativeTo(null)
- At GameFrame.class, line 72 to line 76 (private void autoLocate())
- At DebugConsole.class, line 60 to line 64  (private void setLocation())
5. Most of the classes are redesigned in MVC pattern.


## Additions
1. Crearted new class called InfoScreen
..*a button is created in HomeMenu class which can access to InfoScreen
..*InfoScreen will describe the basics instructions of how to play the game and method access to pausemanu and debugconsole.
2. Created new class called LeaderBoard 
..*a button is created in HomeMenu class which can access to LeaderBoard and a text will be created to store the leaderboard's data.
..*after each game, the player'score will be showed on LeaderBoard as current score and if the current score is higher the five high score in Leaderboard, the rank will be replaced. 
3. a timePointcalculation function is created in wallController.class
..*A timer and point will show up when the game starts. 
..*While the timer increases by 1 second, the player's score will decrease by 2 points. The player'score will decrease by 150, if one ball is lost.
..*The player's score will increase by 50, if a brick is broken.
..*The initial player's score will be 1450( ( 500 seconds(roughly 9 mins) x 2 = 1000 ) + (3 (inital ball)x 150 = 450))
..*This is to increase the reward and penalty of the game
4. created a new class called titanium brick
..*This is another level5 of this game
..*Titanium brick will have less probability to break than the steel brick.
5.A home menu button is created at pause menu
..*The user can go back to home menu anytime by clicking ESC button and click on the HomeMenu
