package com.example.gustavo.tictac;

import android.net.Uri;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

public class MainActivity extends AppCompatActivity {

    //0 means yellow ,1 red
    int activePlayer = 0;
    boolean  gameIsActive =true;
    //2 means unplayed
    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};
    int [][] winningPositions = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client2;


    public void dropIn(View view) {

        ImageView counter = (ImageView) view;

        int tappedCounter = Integer.parseInt(counter.getTag().toString());

        if (gameState[tappedCounter] == 2&& gameIsActive) {

            gameState[tappedCounter] = activePlayer;

            counter.setTranslationY(-1000f);

            if (activePlayer == 0) {
                counter.setImageResource(R.drawable.yellow);
                activePlayer = 1;
            } else {
                counter.setImageResource(R.drawable.red);
                activePlayer = 0;
            }

            counter.animate().rotation(360).translationYBy(1000f).setDuration(300);

            for (int[] winninposition : winningPositions) {

                if (gameState[winninposition[0]] == gameState[winninposition[1]] && gameState[winninposition[0]] == gameState[winninposition[2]] && gameState[winninposition[0]] != 2) {
                    gameIsActive= false;

                    String winner = "Red";

                    if (gameState[winninposition[0]] == 0) {
                        winner = "Yellow";
                    }


                    // System.out.println("has won"+gameState[winninposition[0]]);

                    TextView winmessage = (TextView) findViewById(R.id.winnerMessage);
                    winmessage.setText(winner + " has won");

                    LinearLayout layout = (LinearLayout) findViewById(R.id.playagainLayout);
                    layout.setVisibility(View.VISIBLE);
                }


                else {

                    boolean gameIsOver= true;

                    for (int counterState :gameState){
                        if(counterState==2){
                            gameIsOver= false;
                        }
                    }

                    if(gameIsOver){

                        TextView winmessage = (TextView) findViewById(R.id.winnerMessage);
                        winmessage.setText("it is a draw");

                        LinearLayout layout = (LinearLayout) findViewById(R.id.playagainLayout);
                        layout.setVisibility(View.VISIBLE);


                    }
                }

            }

        }
    }

       public void playAgain(View view){
           gameIsActive= true;
           LinearLayout layout = (LinearLayout) findViewById(R.id.playagainLayout);
           layout.setVisibility(View.INVISIBLE);
            //0 means yellow ,1 red

             activePlayer = 0;

            //2 means unplayed

            for (int i=0;i<gameState.length;i++){
             gameState[i]=2;
            }

            GridLayout gridLayout = (GridLayout)  findViewById(R.id.gridLayout);

        for(int i=0; i<gridLayout.getChildCount();i++){
            ((ImageView) gridLayout.getChildAt(i)).setImageResource(0);

            }
        }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.

    }
}





