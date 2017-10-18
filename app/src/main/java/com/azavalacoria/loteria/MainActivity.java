package com.azavalacoria.loteria;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    int counter;
    FloatingActionButton floatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        floatingActionButton = (FloatingActionButton) findViewById(R.id.floatingActionButtonStart);

        /*
        floatingActionButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                Log.e("MOTIONEVENT", "Action: " + motionEvent.getAction());
                Log.e("MOTIONEVENT", "Pressure: " + motionEvent.getPressure());

                return false;
            }
        });
        */
        counter = 0;
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(getApplicationContext(), "Has dado clicl" , Toast.LENGTH_LONG).show();
                counter++;
                Log.e("CLICKED: ", "" + counter );
            }
        });
        /*
        floatingActionButton.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Toast.makeText(getApplicationContext(), "Quieres reiniciar?" , Toast.LENGTH_LONG).show();
                return false;
            }
        });
        */
    }
}
