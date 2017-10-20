package com.azavalacoria.loteria;

import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.GridView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private GridView gridView;
    private GridCardAdapter gridCardAdapter;

    int position = 0;
    ArrayList<Integer> cards;
    ArrayList<Integer> playerCards;
    TextView textView;
    FloatingActionButton floatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        floatingActionButton = (FloatingActionButton) findViewById(R.id.floatingActionButtonStart);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (position < cards.size()) {
                    final Integer integer = cards.get(position);
                    position++;
                    if (playerCards.contains(integer)) {

                        changeGridCardAttribute(integer, Color.RED, Color.WHITE);

                        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                        builder.setTitle("¡Qué suerte!");
                        builder.setMessage("\nLa ficha " +integer.toString() + " se encuentra en tu tablero. ¿Deseas agregarla?");
                        builder.setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                changeGridCardAttribute(integer, Color.BLACK, Color.WHITE);
                            }
                        });
                        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                changeGridCardAttribute(integer, Color.TRANSPARENT, Color.BLACK);
                            }
                        });
                        Dialog dialog = builder.create();
                        dialog.setCanceledOnTouchOutside(false);

                        Window window = dialog.getWindow();
                        window.getDecorView().findViewById(android.R.id.content).setForeground(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                        window.setNavigationBarColor(Color.TRANSPARENT);

                        WindowManager.LayoutParams layoutParams = window.getAttributes();
                        layoutParams.gravity = Gravity.BOTTOM;
                        layoutParams.flags &= ~WindowManager.LayoutParams.FLAG_DIM_BEHIND;

                        window.setAttributes(layoutParams);

                        dialog.show();
                    } else {
                        textView.setText("Estamos en el position: "+ position +" y salió: " + integer.toString());
                    }
                }
            }
        });
        createCards();
        textView = (TextView) findViewById(R.id.actualCard);
        textView.setText("Turno actual");
        gridView = (GridView) findViewById(R.id.gridview);
        playerCards = new ArrayList<>(cards.subList(0, 25));
        gridCardAdapter = new GridCardAdapter(this.getApplicationContext(), playerCards);
        gridView.setAdapter(gridCardAdapter);
        Collections.shuffle(cards);
    }

    private void changeGridCardAttribute(Integer integer, int background, int textColor) {
        int index = playerCards.indexOf(integer);
        View v = gridCardAdapter.getView(index, gridView.getChildAt(index), null);
        if (v != null) {
            v.setBackgroundColor(background);
            TextView textView = (TextView) v.findViewById(R.id.grid_title);
            textView.setTextColor(textColor);
        }
    }

    private void createCards() {
        cards = new ArrayList<Integer>();
        for (int i = 1; i <= 90; i++)
            cards.add(new Integer(i));
        Collections.shuffle(cards);
    }
    private ArrayList<Integer> generateCards(int lenght) {
        ArrayList <Integer> arrayList = new ArrayList<>();

        for (int i = 0; i < lenght; i++) {
            arrayList.add(new Integer(new Random().nextInt(90)));
        }

        return arrayList;
    }
}
