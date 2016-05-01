package com.elixirdose.funfacts;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class FunFactsActivity extends AppCompatActivity {

    private Button mButton;
    private TextView mTextView;

    private FactBook mFactBook = new FactBook();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fun_facts);

        mButton = (Button) findViewById(R.id.button);
        mTextView = (TextView) findViewById(R.id.textView);

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Update the screen with our dynamic fact
                String fact = mFactBook.getFact();
                mTextView.setText(fact);
            }
        };

        mButton.setOnClickListener(listener);
    }
}
