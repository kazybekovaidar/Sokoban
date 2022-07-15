public class Model {
    private static final String[] LEVEL01 = {
            "!!#####!",
            "###   #!",
            "# $ # ##",
            "# #  . #",
            "#    # #",
            "## #   #",
            "!#@  ###",
            "!#####!!"
    };

    private static final String[] LEVEL02 = {
            "!!#####!",
            "###   #!",
            "# $ # ##",
            "# #  . #",
            "#    # #",
            "##$#.  #",
            "!#@  ###",
            "!#####!!"
    };

    private static final String[] LEVEL03 = {
            "######!!",
            "###   #!",
            "# $ # ##",
            "# #  . #",
            "# .  # #",
            "##$#.$ #",
            "!#@  ###",
            "!#####!!"
    };

    private static final String[][] LEVELS = {LEVEL01, LEVEL02, LEVEL03};


    private int currentLevel;
    private Puzzle currentPuzzle;

    public Model() {
        currentLevel = 0;
        currentPuzzle = new Puzzle(LEVELS[currentLevel]);
    }


    void moveUP() {
        currentPuzzle.moveUP();
    }

    void moveDown() {
        currentPuzzle.moveDown();
    }

    void moveLeft() {
        currentPuzzle.moveLeft();
    }

    void moveRight() {
        currentPuzzle.moveRight();
    }

    public boolean hasWon() {
        return currentPuzzle.hasWon();
    }

    public int getRobotColumn() {
        return currentPuzzle.getRobotColumn();
    }

    public int getRobotRow() {
        return currentPuzzle.getRobotRow();
    }

    public int getPuzzleWidth() {
        return currentPuzzle.getPuzzleWidth();
    }

    public int getPuzzleHeight() {
        return currentPuzzle.getPuzzleHeight();
    }

    public char elementAt(int r, int c) {
        return currentPuzzle.elementAt(r, c);
    }

    public void nextLevel() {
        currentLevel++;
        if (currentLevel == LEVELS.length) {
            currentLevel = 0;
        }
        currentPuzzle = new Puzzle(LEVELS[currentLevel]);
    }

    public int getCurrentLevel(){
        return currentLevel;
    }

    public int getMoves(){
        return currentPuzzle.getMoves();
    }

    public void reset() {
        currentPuzzle = new Puzzle(LEVELS[currentLevel]);
    }
}
