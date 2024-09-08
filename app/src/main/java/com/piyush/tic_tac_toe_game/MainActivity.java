package com.piyush.tic_tac_toe_game;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;

public class MainActivity extends AppCompatActivity {

    int flag = 0;
    int count = 0;
    CardView cNew;
    String b1, b2, b3, b4, b5, b6, b7, b8, b9;
    Button btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    private void init() {
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);
        btn5 = findViewById(R.id.btn5);
        btn6 = findViewById(R.id.btn6);
        btn7 = findViewById(R.id.btn7);
        btn8 = findViewById(R.id.btn8);
        btn9 = findViewById(R.id.btn9);
        cNew = findViewById(R.id.cNew);

        // Setting onClickListener for each button
        btn1.setOnClickListener(this::check);
        btn2.setOnClickListener(this::check);
        btn3.setOnClickListener(this::check);
        btn4.setOnClickListener(this::check);
        btn5.setOnClickListener(this::check);
        btn6.setOnClickListener(this::check);
        btn7.setOnClickListener(this::check);
        btn8.setOnClickListener(this::check);
        btn9.setOnClickListener(this::check);

        // Setting onClickListener for the new game card
        cNew.setOnClickListener(this::resetGame);
    }

    public void check(View view) {
        Button btnCurrent = (Button) view;
        if (btnCurrent.getText().toString().isEmpty()) {
            count++;
            if (flag == 0) {
                btnCurrent.setText("X");
                flag = 1; // toggling
            } else {
                btnCurrent.setText("O");
                flag = 0; // toggling
            }
            if (count > 4) {
                updateBoard();
                checkWinner();
            }
        }
    }

    private void updateBoard() {
        b1 = btn1.getText().toString();
        b2 = btn2.getText().toString();
        b3 = btn3.getText().toString();
        b4 = btn4.getText().toString();
        b5 = btn5.getText().toString();
        b6 = btn6.getText().toString();
        b7 = btn7.getText().toString();
        b8 = btn8.getText().toString();
        b9 = btn9.getText().toString();
    }

    private void checkWinner() {
        // Conditions to be checked
        if (b1.equals(b2) && b2.equals(b3) && !b1.isEmpty()) {
            highlightWinningButtons(btn1, btn2, btn3);
            announceWinner(b1);
        } else if (b4.equals(b5) && b5.equals(b6) && !b4.isEmpty()) {
            highlightWinningButtons(btn4, btn5, btn6);
            announceWinner(b4);
        } else if (b7.equals(b8) && b8.equals(b9) && !b7.isEmpty()) {
            highlightWinningButtons(btn7, btn8, btn9);
            announceWinner(b7);
        } else if (b1.equals(b4) && b4.equals(b7) && !b1.isEmpty()) {
            highlightWinningButtons(btn1, btn4, btn7);
            announceWinner(b1);
        } else if (b2.equals(b5) && b5.equals(b8) && !b2.isEmpty()) {
            highlightWinningButtons(btn2, btn5, btn8);
            announceWinner(b2);
        } else if (b3.equals(b6) && b6.equals(b9) && !b3.isEmpty()) {
            highlightWinningButtons(btn3, btn6, btn9);
            announceWinner(b3);
        } else if (b1.equals(b5) && b5.equals(b9) && !b1.isEmpty()) {
            highlightWinningButtons(btn1, btn5, btn9);
            announceWinner(b1);
        } else if (b3.equals(b5) && b5.equals(b7) && !b3.isEmpty()) {
            highlightWinningButtons(btn3, btn5, btn7);
            announceWinner(b3);
        } else if (count == 9) {
            Toast.makeText(this, "Game is Drawn", Toast.LENGTH_SHORT).show();
        }
    }

    private void announceWinner(String winner) {
        Toast.makeText(this, "The Winner is: " + winner, Toast.LENGTH_SHORT).show();

        // Reset the game after 2 seconds
        new Handler().postDelayed(this::resetGame, 2000);
    }

    private void highlightWinningButtons(Button btn1, Button btn2, Button btn3) {
        btn1.setBackgroundColor(ContextCompat.getColor(this, android.R.color.holo_green_light));
        btn2.setBackgroundColor(ContextCompat.getColor(this, android.R.color.holo_green_light));
        btn3.setBackgroundColor(ContextCompat.getColor(this, android.R.color.holo_green_light));

        // Reset background after 1 second
        new Handler().postDelayed(() -> {
            btn1.setBackgroundResource(R.drawable.button_background);
            btn2.setBackgroundResource(R.drawable.button_background);
            btn3.setBackgroundResource(R.drawable.button_background);
        }, 1000);
    }

    private void resetGame(View view) {
        resetGame();
    }

    private void resetGame() {
        // Reset all buttons
        resetButton(btn1);
        resetButton(btn2);
        resetButton(btn3);
        resetButton(btn4);
        resetButton(btn5);
        resetButton(btn6);
        resetButton(btn7);
        resetButton(btn8);
        resetButton(btn9);

        // Reset flag and count
        flag = 0;
        count = 0;
    }

    private void resetButton(Button button) {
        button.setText("");
        button.setBackgroundResource(R.drawable.button_background); // Set background to default
    }
}
