package ru.rgordeev.tictactoe;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private boolean turn = true;
    private int[][] field = new int[3][3];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                field[i][j] = 0;
            }
        }
    }

    public void onButtonClick(View view) {
        Button button = (Button) view;

        int[] coords = parseTag(button.getTag().toString());
        if (!validate(field, coords)) {
            Toast.makeText(getApplicationContext(), "WRONG TURN", Toast.LENGTH_LONG).show();
            return;
        }

        if (turn) {
            field[coords[0]][coords[1]] = 1;
            button.setText("X");
            turn = false;
            if (xWin(field)) {
                Toast.makeText(getApplicationContext(), "X WON", Toast.LENGTH_LONG).show();
            }
        } else {
            field[coords[0]][coords[1]] = 2;
            button.setText("O");
            turn = true;
            if (oWin(field)) {
                Toast.makeText(getApplicationContext(), "O WON", Toast.LENGTH_LONG).show();
            }

        }
    }

    private static boolean validate(int[][] field, int[] coords) {
        if (coords[0] < 0 || coords[0] > 2) {
            return false;
        }
        if (coords[1] < 0 || coords[1] > 2) {
            return false;
        }
        if (field[coords[0]][coords[1]] != 0) {
            return false;
        }
        return true;
    }

    private int[] parseTag(String tag) {
        String[] values = tag.split("_");
        int[] result = new int[2];
        result[0] = Integer.valueOf(values[0]).intValue();
        result[1] = Integer.valueOf(values[1]).intValue();
        return result;
    }

    private int[] extractRow(int[][] field, int i) {
        return field[i];
    }

    private int[] extractColumn(int[][] field, int j) {
        int[] result = new int[3];
        for (int i = 0; i < 3; i++) {
            result[i] = field[i][j];
        }
        return result;
    }

    private int[] extractMainDiag(int[][] field) {
        int[] result = new int[3];
        for (int i = 0; i < 3; i++) {
            result[i] = field[i][i];
        }
        return result;
    }

    private int[] extractSlaveDiag(int[][] field) {
        int[] result = new int[3];
        for (int i = 0; i < 3; i++) {
            result[i] = field[i][2 - i];
        }
        return result;
    }

    private static boolean allInArray(int[] a, int template) {
        for (int i = 0; i < a.length; i++) {
            if (a[i] != template) {
                return false;
            }
        }
        return true;
    }

    private boolean isAWinner(int[][] field, int player) {
        for (int i = 0; i < 3; i++) {
            if (allInArray(extractRow(field, i), player)) {
                return true;
            }
        }
        for (int j = 0; j < 3; j++) {
            if (allInArray(extractColumn(field, j), player)) {
                return true;
            }
        }
        if (allInArray(extractMainDiag(field), player)) {
            return true;
        }
        if (allInArray(extractSlaveDiag(field), player)) {
            return true;
        }
        return false;
    }

    private boolean xWin(int[][] field) {
        return isAWinner(field, 1);
    }

    private boolean oWin(int[][] field) {
        return isAWinner(field, 2);
    }

    private boolean nobodyWon(int[][] field) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (field[i][j] == 0) {
                    return false;
                }
            }
        }
        return true;
    }

}




















