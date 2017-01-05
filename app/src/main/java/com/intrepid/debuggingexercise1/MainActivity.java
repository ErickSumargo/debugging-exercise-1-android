package com.intrepid.debuggingexercise1;

import android.os.Bundle;
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
    private Handler handler = new Handler();
    private final int MESSAGE_DURATION = (int) TimeUnit.SECONDS.toMillis(5);

    @BindView(R.id.text)
    TextView text;

    @OnClick(R.id.button)
    public void onButtonClick() {
        text.setText("Button pressed " + ++count + " times.\nThis message will disappear in 5 seconds.\nTap anywhere on the screen to dismiss this message immediately.\n\nWhat happens if you tap the \"Click Here\" button several times?\nDoes the message always appear for 5 seconds?");
        text.setVisibility(VISIBLE);
        Runnable timerElapsedCallback = () -> {
            // Hide the message when the timer has elapsed
            text.setVisibility(INVISIBLE);
        };
        handler.postDelayed(timerElapsedCallback, MESSAGE_DURATION);
    }

    @OnClick(R.id.activity_main)
    public void onActivityClick() {
        text.setVisibility(INVISIBLE);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }
}
