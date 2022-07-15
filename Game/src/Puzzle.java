public class Puzzle {
    private final char[][] data;

    private int robotRow;
    private int robotColumn;
    private final int puzzleWidth;
    private final int puzzleHeight;
    private int goalR;
    private int goalC;
    private boolean onIt;
    private int numberOfGoals;
    private int moves;

    public Puzzle(String[] level) {
        puzzleHeight = level.length;
        puzzleWidth = level[0].length();
        moves = 0;
        data = new char[puzzleHeight][puzzleWidth];
        for (int r = 0; r < puzzleHeight; r++) {
            for (int c = 0; c < puzzleWidth; c++) {
                data[r][c] = level[r].charAt(c);
                if (data[r][c] == '@') {
                    robotRow = r;
                    robotColumn = c;
                    data[r][c] = ' ';
                }
                if (data[r][c] == '.') {
                    numberOfGoals++;
                }
            }
        }
    }

    void moveUP() {
        if (canMove('u')) {
            moveBox('u');
            if (data[robotRow-1][robotColumn]=='.'){
                data[robotRow-1][robotColumn] = '@';
                data[robotRow][robotColumn] = ' ';
                goalR = robotRow-1;
                goalC = robotColumn;
                onIt = true;
            } else {
                data[robotRow - 1][robotColumn] = '@';
                data[robotRow][robotColumn] = ' ';
                if (onIt){
                    data[goalR][goalC] = '.';
                }
            }
            robotRow--;
            moves++;
        }
    }

    void moveDown() {
        if (canMove('d')) {
            moveBox('d');
            if (data[robotRow+1][robotColumn]=='.'){
                data[robotRow+1][robotColumn] = '@';
                data[robotRow][robotColumn] = ' ';
                goalR = robotRow+1;
                goalC = robotColumn;
                onIt = true;
            } else {
                data[robotRow + 1][robotColumn] = '@';
                data[robotRow][robotColumn] = ' ';
                if (onIt){
                    data[goalR][goalC] = '.';
                }
            }
            robotRow++;
            moves++;
        }
    }

    void moveLeft() {
        if (canMove('l')) {
            moveBox('l');
            if (data[robotRow][robotColumn-1]=='.'){
                data[robotRow][robotColumn-1] = '@';
                data[robotRow][robotColumn] = ' ';
                goalR = robotRow;
                goalC = robotColumn-1;
                onIt = true;
            } else {
                data[robotRow][robotColumn-1] = '@';
                data[robotRow][robotColumn] = ' ';
                if (onIt){
                    data[goalR][goalC] = '.';
                }
            }
            robotColumn--;
            moves++;
        }
    }

    void moveRight() {
        if (canMove('r')) {
            moveBox('r');
            if (data[robotRow][robotColumn+1]=='.'){
                data[robotRow][robotColumn+1] = '@';
                data[robotRow][robotColumn] = ' ';
                goalR = robotRow;
                goalC = robotColumn+1;
                onIt = true;
            } else {
                data[robotRow][robotColumn+1] = '@';
                data[robotRow][robotColumn] = ' ';
                if (onIt){
                    data[goalR][goalC] = '.';
                }
            }
            robotColumn++;
            moves++;
        }
    }

    private void moveBox(char dir) {
        switch (dir) {
            case 'u':
                if (data[robotRow - 1][robotColumn] == '$' && data[robotRow - 2][robotColumn] == ' ') {
                    data[robotRow - 2][robotColumn] = '$';
                    data[robotRow - 1][robotColumn] = ' ';
                }
                if (data[robotRow - 1][robotColumn] == '$' && data[robotRow - 2][robotColumn] == '.') {
                    data[robotRow - 2][robotColumn] = '$';
                    data[robotRow - 1][robotColumn] = ' ';
                    numberOfGoals--;
                }
                break;
            case 'd':
                if (data[robotRow + 1][robotColumn] == '$' && data[robotRow + 2][robotColumn] == ' ') {
                    data[robotRow + 2][robotColumn] = '$';
                    data[robotRow + 1][robotColumn] = ' ';
                }
                if (data[robotRow + 1][robotColumn] == '$' && data[robotRow + 2][robotColumn] == '.') {
                    data[robotRow + 2][robotColumn] = '$';
                    data[robotRow + 1][robotColumn] = ' ';
                    numberOfGoals--;
                }
                break;
            case 'l':
                if (data[robotRow][robotColumn - 1] == '$' && data[robotRow][robotColumn - 2] == ' ') {
                    data[robotRow][robotColumn - 2] = '$';
                    data[robotRow][robotColumn - 1] = ' ';
                }
                if (data[robotRow][robotColumn - 1] == '$' && data[robotRow][robotColumn - 2] == '.') {
                    data[robotRow][robotColumn - 2] = '$';
                    data[robotRow][robotColumn - 1] = ' ';
                    numberOfGoals--;
                }
                break;
            case 'r':
                if (data[robotRow][robotColumn + 1] == '$' && data[robotRow][robotColumn + 2] == ' ') {
                    data[robotRow][robotColumn + 2] = '$';
                    data[robotRow][robotColumn + 1] = ' ';
                }
                if (data[robotRow][robotColumn + 1] == '$' && data[robotRow][robotColumn + 2] == '.') {
                    data[robotRow][robotColumn + 2] = '$';
                    data[robotRow][robotColumn + 1] = ' ';
                    numberOfGoals--;
                }
                break;
        }
    }

    private boolean canMove(char dir) {
        boolean res = false;
        switch (dir) {
            case 'u':
                if(data[robotRow-1][robotColumn]=='$' && data[robotRow-2][robotColumn]=='#'){
                    res=false;
                } else if (data[robotRow - 1][robotColumn] == ' ' || data[robotRow - 1][robotColumn] == '$' || data[robotRow - 1][robotColumn] == '.') {
                    res = true;
                }
                break;
            case 'd':
                if(data[robotRow+1][robotColumn]=='$' && data[robotRow+2][robotColumn]=='#'){
                    res=false;
                } else if (data[robotRow + 1][robotColumn] == ' ' || data[robotRow + 1][robotColumn] == '$' || data[robotRow + 1][robotColumn] == '.') {
                    res = true;
                }
                break;
            case 'l':
                if(data[robotRow][robotColumn-1]=='$' && data[robotRow][robotColumn-2]=='#'){
                    res=false;
                } else if (data[robotRow][robotColumn - 1] == ' ' || data[robotRow][robotColumn - 1] == '$' || data[robotRow][robotColumn - 1] == '.') {
                    res = true;
                }
                break;
            case 'r':
                if(data[robotRow][robotColumn+1]=='$' && data[robotRow][robotColumn+2]=='#'){
                    res=false;
                } else if (data[robotRow][robotColumn + 1] == ' ' || data[robotRow][robotColumn + 1] == '$' || data[robotRow][robotColumn + 1] == '.') {
                    res = true;
                }
                break;
        }
        return res;
    }

    public int getMoves(){
        return moves;
    }

    public boolean hasWon() {
        return numberOfGoals == 0;
    }

    public int getRobotColumn() {
        return robotColumn;
    }

    public int getRobotRow() {
        return robotRow;
    }

    public int getPuzzleWidth() {
        return puzzleWidth;
    }

    public int getPuzzleHeight() {
        return puzzleHeight;
    }

    public char elementAt(int r, int c) {
        return data[r][c];
    }
}