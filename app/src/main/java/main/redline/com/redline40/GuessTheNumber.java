package main.redline.com.redline40;

import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class GuessTheNumber extends AppCompatActivity {

    ImageButton imageButtonEasy, imageButtonHard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guess_the_number);

        // Init image buttons
        imageButtonEasy = (ImageButton) findViewById(R.id.imageButtonEasy);
        imageButtonHard = (ImageButton) findViewById(R.id.imageButtonHard);
        initButtons();

    }

    private void initButtons() {
        imageButtonEasy.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        setContentView(R.layout.guess_the_number_playing);
                                        Toast.makeText(GuessTheNumber.this, "Lätt: Du har 5 gissningar på ett tal mellan 1 - 10", Toast.LENGTH_SHORT).show();
                                        maxGuesses = 5;
                                        highestGuess = 10;
                                        startGame();
                                    }
                                }
        );

        imageButtonHard.setOnClickListener(new View.OnClickListener() {
                                               @Override
                                               public void onClick(View v) {
                                                   setContentView(R.layout.guess_the_number_playing);
                                                   Toast.makeText(GuessTheNumber.this, "Svår : Du har 7 gissningar på ett tal mellan 1 - 100", Toast.LENGTH_SHORT).show();
                                                   maxGuesses = 7;
                                                   highestGuess = 100;
                                                   startGame();
                                               }
                                           }
        );
    }


    private int correctNumber;
    private int guesses;
    private int maxGuesses;
    private int highestGuess;

    private Button buttonGuess;
    private Button buttonIncrease;
    private Button buttonDecrease;

    private TextView labelNumber;
    private TextView resultLabel;

    private ListView higherThan;
    private ArrayList<Integer> higherThanContent;
    private ListView lowerThan;
    private ArrayList<Integer> lowerThanContent;

    private void startGame() {
        // Init buttons
        buttonGuess = (Button) findViewById(R.id.buttonGuess);
        buttonIncrease = (Button) findViewById(R.id.buttonIncrease);
        buttonDecrease = (Button) findViewById(R.id.buttonDecrease);

        buttonGuess.setOnClickListener(new View.OnClickListener() {
                                           @Override
                                           public void onClick(View v) {
                                               doGuess( Integer.parseInt(labelNumber.getText().toString()) );
                                           }
                                       }
        );


        buttonIncrease.setOnClickListener(new View.OnClickListener() {
                                           @Override
                                           public void onClick(View v) {
                                               increaseGuess();
                                           }
                                       }
        );

        buttonDecrease.setOnClickListener(new View.OnClickListener() {
                                           @Override
                                           public void onClick(View v) {
                                               decreaseGuess();
                                           }
                                       }
        );


        labelNumber = (TextView) findViewById(R.id.labelNumber);
        resultLabel = (TextView) findViewById(R.id.resultLabel);
        higherThan = (ListView) findViewById(R.id.listViewHigh);
        lowerThan = (ListView) findViewById(R.id.listViewLow);

        // Set defaults
        guesses = 0;
        higherThanContent = new ArrayList<Integer>();
        lowerThanContent = new ArrayList<Integer>();
        gameOver = false;

        // Randomize number for player to guess
        Random random = new Random();
        correctNumber = 1 + random.nextInt(highestGuess);

    }

    private void increaseGuess() {
        Integer guess = Integer.parseInt(labelNumber.getText().toString());

        if(guess < highestGuess)
            guess++;

        labelNumber.setText(guess.toString());
    }

    private void decreaseGuess() {
        Integer guess = Integer.parseInt(labelNumber.getText().toString());
        if(guess >= 2) {
            guess--;
        }

        labelNumber.setText(guess.toString());
    }

    private boolean gameOver;
    private void doGuess(int guess) {

        System.out.println(guess);
        System.out.println(correctNumber);

        if(!gameOver) {
            guesses++;

            final ArrayAdapter adapter;
            if(guess > correctNumber) {

                // Gues was too high- add to listbox
                lowerThanContent.add(guess);
                adapter = new ArrayAdapter(this,
                        android.R.layout.simple_list_item_1, lowerThanContent);
                lowerThan.setAdapter(adapter);
            }

            else if(guess < correctNumber) {

                // Guess was too low - add to listbox
                higherThanContent.add(guess);
                adapter = new ArrayAdapter(this,
                        android.R.layout.simple_list_item_1, higherThanContent);
                higherThan.setAdapter(adapter);
            }

            else {
                resultLabel.setText("DU VANN!");
                gameOver = true;
            }

            buttonGuess.setText("CHANSA (" + (guesses + 1) + "/ " + highestGuess + ")" );

            if(guesses >= maxGuesses) {
                gameOver = true;
                resultLabel.setText("DU FÖRLORADE!");
            }
        }
    }


}
