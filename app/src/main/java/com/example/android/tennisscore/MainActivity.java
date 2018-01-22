package com.example.android.tennisscore;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    //Scores for the teams
    int scoreTeamA = 0;
    int scoreTeamB = 0;
    //Advantage score
    int adScoreA = 0;
    int adScoreB = 0;
    //How many sets won
    int setScoreTeamA;
    int setScoreTeamB;
    //Strings changing text from a textview
    String winMessage;
    String valueTeamA;
    String valueTeamB;
    //Booleans to check deuce/advantage/winner
    boolean deuce = false;
    boolean adInA = false;
    boolean adInB = false;
    boolean setWinner = false;
    //Visibility
    LinearLayout visLayout;
    LinearLayout visWinner;
    Button visButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        visLayout = (LinearLayout) findViewById(R.id.invisLayout);
        visButton = (Button) findViewById(R.id.invisButton);
        visWinner = (LinearLayout) findViewById(R.id.invisWinner);
    }

    public void addPointTeamA(View view){
        //Win condition
        if (scoreTeamA == 40 && scoreTeamB == 0 || scoreTeamA == 40 && scoreTeamB == 15 || scoreTeamA == 40 && scoreTeamB == 30){
            setWinner = true;
        }
        //Adding Points
        if (scoreTeamA == 0 || scoreTeamA == 15){
            scoreTeamA = scoreTeamA + 15;
            valueTeamA = "";
        } else if (scoreTeamA == 30){
            scoreTeamA = scoreTeamA + 10;
            valueTeamA = "";
        }
        //Checking for Love/Deuce
        if (scoreTeamA == 15 && scoreTeamB == 0 || scoreTeamA == 30 && scoreTeamB == 0 || scoreTeamA == 40 && scoreTeamB == 0) {
            valueTeamB = "Love";
        }
        if (deuce && !adInA && !adInB || !deuce && adScoreA == 1){
            adInA = true;
            adInB = false;
            adScoreA++;
            adScoreB = 0;
            deuce = false;
            valueTeamA = "Advantage";
            valueTeamB = "";
        } if (scoreTeamA == 40 && scoreTeamB == 40 && !deuce && !adInA && !adInB){
            deuce = true;
            valueTeamA = "Deuce";
            valueTeamB = "Deuce";
        } else if (adScoreB == 1 && !adInA){
            deuce = true;
            valueTeamA = "Deuce";
            valueTeamB = "Deuce";
            adScoreA = 0;
            adScoreB = 0;
            adInA = false;
            adInB = false;
        }

        //Win condition
        if (adScoreA == 2){
            setWinner = true;
        }

        //Checking Winrate
        if (setWinner){
            setScoreTeamA++;
            scoreTeamA = 0;
            scoreTeamB = 0;
            valueTeamA = "";
            valueTeamB = "";
            deuce = false;
            adInA = false;
            adInB = false;
            adScoreA = 0;
            adScoreB = 0;
            setWinner = false;
        }
        //Displaying messages
        displayForTeamA(scoreTeamA);
        displayForTeamB(scoreTeamB);
        displayAMessage(valueTeamA);
        displayBMessage(valueTeamB);
        displayScoreForTeamA(setScoreTeamA);

        //Changing visibility when won
        if (setScoreTeamA == 2 && setScoreTeamB == 0 || setScoreTeamA - setScoreTeamB >= 1 && setScoreTeamB != 0){
            winMessage = "Player 1 has won the game!";
            visLayout.setVisibility(View.GONE);
            visButton.setVisibility(View.GONE);
            visWinner.setVisibility(View.VISIBLE);
            winnerMessage(winMessage);
        }
    }

    public void addPointTeamB(View view){
        //Win condition
        if (scoreTeamB == 40 && scoreTeamA == 0 || scoreTeamB == 40 && scoreTeamA == 15 || scoreTeamB == 40 && scoreTeamA == 30) {
            setWinner = true;
        }
        //Adding Points
        if (scoreTeamB == 0 || scoreTeamB == 15){
            scoreTeamB = scoreTeamB + 15;
            valueTeamB = "";
        } else if (scoreTeamB == 30){
            scoreTeamB = scoreTeamB + 10;
            valueTeamB = "";
        }
        //Checking for Love/Deuce
        if (scoreTeamB == 15 && scoreTeamA == 0 || scoreTeamB == 30 && scoreTeamA == 0 || scoreTeamB == 40 && scoreTeamA == 0) {
            valueTeamA = "Love";
        }
        if (deuce && !adInA && !adInB || !deuce && adScoreB == 1){
            adInB = true;
            adInA = false;
            adScoreB++;
            adScoreA = 0;
            deuce = false;
            valueTeamB = "Advantage";
            valueTeamA = "";
        } if (scoreTeamA == 40 && scoreTeamB == 40 && !deuce && !adInA && !adInB){
            deuce = true;
            valueTeamA = "Deuce";
            valueTeamB = "Deuce";
        } else if (adScoreA == 1 && !adInB){
            deuce = true;
            valueTeamA = "Deuce";
            valueTeamB = "Deuce";
            adScoreA = 0;
            adScoreB = 0;
            adInA = false;
            adInB = false;
        }

        //Win condition
        if (adScoreB == 2){
            setWinner = true;
        }

        //Checking Winrate
        if (setWinner){
            setScoreTeamB++;
            scoreTeamA = 0;
            scoreTeamB = 0;
            valueTeamA = "";
            valueTeamB = "";
            deuce = false;
            adInA = false;
            adInB = false;
            adScoreA = 0;
            adScoreB = 0;
            setWinner = false;
        }
        //Displaying messages
        displayForTeamA(scoreTeamA);
        displayForTeamB(scoreTeamB);
        displayAMessage(valueTeamA);
        displayBMessage(valueTeamB);
        displayScoreForTeamB(setScoreTeamB);

        //Changing visibility when won
        if (setScoreTeamB == 2 && setScoreTeamA == 0 || setScoreTeamB - setScoreTeamA >= 1 && setScoreTeamA != 0){
            winMessage = "Player 2 has won the game!";
            visLayout.setVisibility(View.GONE);
            visButton.setVisibility(View.GONE);
            visWinner.setVisibility(View.VISIBLE);
            winnerMessage(winMessage);
        }
    }

    public void reset(View view){
        scoreTeamA = 0;
        scoreTeamB = 0;
        adScoreA = 0;
        adScoreB = 0;
        setScoreTeamA = 0;
        setScoreTeamB = 0;
        deuce = false;
        adInA = false;
        adInB = false;
        setWinner = false;
        valueTeamA = "";
        valueTeamB = "";
        displayForTeamA(scoreTeamA);
        displayForTeamB(scoreTeamB);
        displayAMessage(valueTeamA);
        displayBMessage(valueTeamB);
        displayScoreForTeamA(setScoreTeamA);
        displayScoreForTeamB(setScoreTeamB);
    }

    public void newGame (View view) {
        visLayout.setVisibility(View.VISIBLE);
        visButton.setVisibility(View.VISIBLE);
        visWinner.setVisibility(View.GONE);
        //Resetting the scores
        scoreTeamA = 0;
        scoreTeamB = 0;
        adScoreA = 0;
        adScoreB = 0;
        setScoreTeamA = 0;
        setScoreTeamB = 0;
        deuce = false;
        adInA = false;
        adInB = false;
        setWinner = false;
        valueTeamA = "";
        valueTeamB = "";
        //Displaying Scores
        displayForTeamA(scoreTeamA);
        displayForTeamB(scoreTeamB);
        displayAMessage(valueTeamA);
        displayBMessage(valueTeamB);
        displayScoreForTeamA(setScoreTeamA);
        displayScoreForTeamB(setScoreTeamB);
    }

    /**
     * Displays the given score for Team A.
     */
    public void displayForTeamA(int score) {
        TextView scoreView = (TextView) findViewById(R.id.team_a_score);
        scoreView.setText(String.valueOf(score));
    }
    /**
     * Displays the given score for Team B.
     */
    public void displayForTeamB(int score) {
        TextView scoreView = (TextView) findViewById(R.id.team_b_score);
        scoreView.setText(String.valueOf(score));
    }

    private void displayAMessage(String message) {
        TextView priceTextView = (TextView) findViewById(R.id.team_a_value);
        priceTextView.setText(message);
    }
    private void displayBMessage(String message) {
        TextView priceTextView = (TextView) findViewById(R.id.team_b_value);
        priceTextView.setText(message);
    }

    public void displayScoreForTeamA(int score) {
        TextView scoreView = (TextView) findViewById(R.id.sets_win_team_a);
        scoreView.setText(String.valueOf(score));
    }

    public void displayScoreForTeamB(int score) {
        TextView scoreView = (TextView) findViewById(R.id.sets_win_team_b);
        scoreView.setText(String.valueOf(score));
    }
    private void winnerMessage(String message) {
        TextView priceTextView = (TextView) findViewById(R.id.winnerText);
        priceTextView.setText(message);
    }
}
