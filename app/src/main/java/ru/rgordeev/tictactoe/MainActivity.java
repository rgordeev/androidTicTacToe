package ru.rgordeev.tictactoe;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Field field;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        field = new Field();
    }

    public void onButtonClick(View view) {
        ImageView image = (ImageView) view;

        Coordinates coords = parseTag(image.getTag().toString());
        if (!field.valid(coords.getX(), coords.getY())) {
            Toast.makeText(getApplicationContext(), "WRONG TURN", Toast.LENGTH_LONG).show();
            return;
        }


        String imageName = field.turn(coords.getX(), coords.getY());
        int imageId = getApplicationContext()
                .getResources()
                .getIdentifier(
                        imageName,
                        "drawable",
                        getApplicationContext().getPackageName()
                );
        image.setImageResource(imageId);

        if (field.won()) {
            Toast.makeText(getApplicationContext(), String.format("%s WON", imageName), Toast.LENGTH_LONG).show();
        }

    }

    private Coordinates parseTag(String tag) {
        String[] values = tag.split("_");
        int[] result = new int[2];
        result[0] = Integer.valueOf(values[0]).intValue();
        result[1] = Integer.valueOf(values[1]).intValue();
        return new Coordinates(result[0], result[1]);
    }

}




















