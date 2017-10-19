package com.azavalacoria.loteria;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.GridView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
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
                    Integer integer = cards.get(position);

                    position++;
                    if (playerCards.contains(integer)) {
                        textView.setText("¡Lo tienes! En el position: "+ position +" y salió: " + integer.toString());

                        int index = playerCards.indexOf(integer);

                        View v = gridCardAdapter.getView(index, gridView.getChildAt(index), null);

                        if (v != null) {
                            v.setBackgroundColor(Color.BLACK);

                            TextView textView = (TextView) v.findViewById(R.id.grid_title);
                            textView.setTextColor(Color.WHITE);
                        }
                    } else {
                        textView.setText("Estamos en el position: "+ position +" y salió: " + integer.toString());
                    }
                }
                //shuffleCards();
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
        Log.e("GRID_COUNT", ""+ gridCardAdapter.getCount());
    }

    private void shuffleCards() {

        createCards();
        //cards = generateCards(90);
        List<Integer> playerCards = new ArrayList<>(cards.subList(0, 25));
        int equals = 0;
        int asserted = 0;
        int failed = 0;
        Collections.shuffle(cards);
        //createCards();
        String string = "";
        for (int i = 0; i < cards.size(); i++) {

            if (playerCards.contains(cards.get(i))){
                equals++;
                asserted++;
                playerCards.remove(playerCards.indexOf(cards.get(i)));
                string = string + "Turno Asertado" + "\n";
            } else {
                failed++;
                string = string + "Turno Fallado" + "\n";
            }
            /*
            if (playerCards.get(i).equals(cards.get(i))) {
                equals++;
                string = string + (i + 1) + "\t .- " + cards.get(i).toString() + "\t-\t" + playerCards.get(i).toString()+ "<> \n";
            } else {
                string = string + (i + 1) + "\t .- " + cards.get(i).toString() + "\t-\t" + playerCards.get(i).toString()+ '\n';
            }
            */
        }

        //setTitle("Iguales: " + equals);
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
