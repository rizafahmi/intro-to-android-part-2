package com.elixirdose.funfacts;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class FunFactsActivity extends AppCompatActivity {

    private Button mButton;
    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fun_facts);

        mButton = (Button) findViewById(R.id.button);
        mTextView = (TextView) findViewById(R.id.textView);
    }
}
