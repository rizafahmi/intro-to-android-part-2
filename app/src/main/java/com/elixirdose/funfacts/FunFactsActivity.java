package com.elixirdose.funfacts;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class FunFactsActivity extends AppCompatActivity {

    private Button mButton;
    private TextView mTextView;
    private RelativeLayout mRelativeLayout;

    private FactBook mFactBook = new FactBook();
    private ColorWheel mColorWheel = new ColorWheel();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fun_facts);

        mButton = (Button) findViewById(R.id.button);
        mTextView = (TextView) findViewById(R.id.textView);
        mRelativeLayout = (RelativeLayout) findViewById(R.id.relativeLayout);

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Update the screen with our dynamic fact
                String fact = mFactBook.getFact();
                mTextView.setText(fact);
                int color = mColorWheel.getColor();
                mRelativeLayout.setBackgroundColor(color);
            }
        };

        Toast.makeText(FunFactsActivity.this, "This is a toast", Toast.LENGTH_SHORT).show();
        mButton.setOnClickListener(listener);
    }
}
