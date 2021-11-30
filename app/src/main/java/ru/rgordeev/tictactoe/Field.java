package ru.rgordeev.tictactoe;

class Field {
    private boolean turn;
    private int[][] field = new int[3][3];


    public Field() {
        init();
        turn = true;
    }

    private void init() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                field[i][j] = 0;
            }
        }
    }

    public String turn(int x, int y) {
        if (turn) {
            moveX(x, y);
            turn = false;
            return "cross";
        } else {
            moveO(x, y);
            turn = true;
            return "zero";
        }
    }

    private void moveX(int x, int y) {
        field[x][y] = 1;
    }

    private void moveO(int x, int y) {
        field[x][y] = 2;
    }

    public boolean valid(int x, int y) {
        if (x < 0 || x > 2) {
            return false;
        }
        if (y < 0 || y > 2) {
            return false;
        }
        if (field[x][y] != 0) {
            return false;
        }
        return true;
    }

    public boolean won() {
        return xWin() || oWin();
    }

    private boolean xWin() {
        return isAWinner(1);
    }

    private boolean oWin() {
        return isAWinner(2);
    }

    public boolean nobodyWon() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (field[i][j] == 0) {
                    return false;
                }
            }
        }
        return true;
    }


    private int[] extractRow(int i) {
        return field[i];
    }

    private int[] extractColumn(int j) {
        int[] result = new int[3];
        for (int i = 0; i < 3; i++) {
            result[i] = field[i][j];
        }
        return result;
    }

    private int[] extractMainDiag() {
        int[] result = new int[3];
        for (int i = 0; i < 3; i++) {
            result[i] = field[i][i];
        }
        return result;
    }

    private int[] extractSlaveDiag() {
        int[] result = new int[3];
        for (int i = 0; i < 3; i++) {
            result[i] = field[i][2 - i];
        }
        return result;
    }


    private boolean allInArray(int[] a, int template) {
        for (int i = 0; i < a.length; i++) {
            if (a[i] != template) {
                return false;
            }
        }
        return true;
    }

    private boolean isAWinner(int player) {
        for (int i = 0; i < 3; i++) {
            if (allInArray(extractRow(i), player)) {
                return true;
            }
        }
        for (int j = 0; j < 3; j++) {
            if (allInArray(extractColumn(j), player)) {
                return true;
            }
        }
        if (allInArray(extractMainDiag(), player)) {
            return true;
        }
        if (allInArray(extractSlaveDiag(), player)) {
            return true;
        }
        return false;
    }

}
