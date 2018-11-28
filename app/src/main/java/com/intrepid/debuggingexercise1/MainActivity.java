package com.intrepid.debuggingexercise1;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;

public class MainActivity extends AppCompatActivity {
    private int count = 0;
    private final int MESSAGE_DURATION = (int) TimeUnit.SECONDS.toMillis(5);
    private CountDownTimer countDownTimer;

    @BindView(R.id.text)
    TextView text;

    @BindView(R.id.countdown)
    TextView countdown;

    @OnClick(R.id.button)
    public void onButtonClick() {
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }

        text.setText("Button pressed " +
                             ++count +
                             " times.\nTap anywhere on the screen to dismiss this message immediately.\n\nWhat happens if you tap the \"Click Here\" button several times?\nDoes the message always appear for 5 seconds?");
        text.setVisibility(VISIBLE);
        countdown.setVisibility(VISIBLE);

        countDownTimer = new CountDownTimer(MESSAGE_DURATION, TimeUnit.SECONDS.toMillis(1)) {
            public void onTick(long millisUntilFinished) {
                countdown.setText("This message will disappear in " + millisUntilFinished / TimeUnit.SECONDS.toMillis(1) + " seconds.");
            }

            public void onFinish() {
                text.setVisibility(INVISIBLE);
                countdown.setVisibility(INVISIBLE);
                countdown.setText("");
            }
        }.start();
    }

    @OnClick(R.id.activity_main)
    public void onActivityClick() {
        text.setVisibility(INVISIBLE);
        countdown.setVisibility(INVISIBLE);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }
}
