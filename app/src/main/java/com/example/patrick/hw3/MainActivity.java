package com.example.patrick.hw3;

import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.io.IOException;
import java.util.Random;


public class MainActivity extends ActionBarActivity {

    Button zero;
    Button one;
    Button two;
    Button playButton;
    TextView playText;
    RadioGroup radioGroup;
    TextView scoreBoard;
    int humanWins=0;
    int compWins=0;
    int win;
    int max = 2;
    int min = 0;
    Random random = new Random();
    Boolean bZero =false, bOne=false, bTwo=false;
    final MediaPlayer mp = new MediaPlayer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        zero = (Button) findViewById(R.id.rockButton);
        one = (Button) findViewById(R.id.paperButton);
        two = (Button) findViewById(R.id.spearButton);
        playButton = (Button) findViewById(R.id.playButton);
        playText = (TextView) findViewById(R.id.playText);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        scoreBoard = (TextView) findViewById(R.id.scoreBoard);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onZeroClick(View view){
        clear();
        bZero = true;
        bOne = false;
        bTwo = false;

    }

    public void onOneClick(View view){
        clear();
        bZero = false;
        bOne = true;
        bTwo = false;
    }

    public void onTwoClick(View view){
        clear();
        bZero = false;
        bOne = false;
        bTwo = true;
    }

    public void clear(){
        bZero = false;
        bOne = false;
        bTwo = false;
    }

    public void play(View view){

        if(mp.isPlaying())
            mp.stop();
        mp.reset();
        try {
            AssetFileDescriptor afd;
            afd = getAssets().openFd("click.mp3");
            mp.setDataSource(afd.getFileDescriptor(),afd.getStartOffset(),afd.getLength());
            mp.prepare();
            mp.start();
        }
        catch(IOException e){
            e.printStackTrace();
        }
        int cChoice = compChoice();
        int hChoice = 3;
        if(bZero)
            hChoice= 0;
        if(bOne)
            hChoice= 1;
        if(bTwo)
            hChoice= 2;
        if(hChoice == 3) {
            bZero=false;
            bOne = false;
            bTwo= false;
            return;
        }
        int result = winner(hChoice, cChoice);
        if (cChoice == 0) {
            if (result == 0) {
                playText.setText("Computer chose Rock, YOU LOSE");
                compWins++;
            }
            if (result == 1) {
                playText.setText("Computer chose Rock, YOU WIN!");
                humanWins++;
            }
        }
        if (cChoice == 1) {
            if (result == 0) {
                playText.setText("Computer chose Paper, YOU LOSE");
                compWins++;
            }
            if (result == 1) {
                playText.setText("Computer chose Paper, YOU WIN!");
                humanWins++;
            }
        }
        if (cChoice == 2) {
            if (result == 0) {
                playText.setText("Computer chose Spear, YOU LOSE");
                compWins++;
            }
            if (result == 1) {
                playText.setText("Computer chose Spear, YOU WIN!");
                humanWins++;
            }
        }

        if (result == 2)
            playText.setText("User and Computer TIED, Try again ");

        playButton.setText("Play Again");
        radioGroup.clearCheck();
        scoreBoard.setText("User:" + humanWins +"   "+ "Computer:"+ compWins);
        return;
    }

    public int compChoice(){
        int result = random.nextInt(max-min +1);
        return result;
    }

    public int winner (int h, int c){
        if((c==0 && h ==2)|(c==1 && h ==0)|(c==2 && h ==1)) {
            win = 0;
            return win;
        }
        else if((h==0 && c ==2)|(h==1 && c ==0)|(h==2 && c ==1)) {
            win = 1;
            return win;
        }
        else {
            win= 2;
            return win;
        }
    }

}
