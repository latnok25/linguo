package com.linguo.admin.linguo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity{

static {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
        }

    private Button btn, btn1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = findViewById(R.id.buttonContinue);
        btn1 = findViewById(R.id.buttonContinue1);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(MainActivity.this, TranslateFragment.class);
                MainActivity.this.startActivity(myIntent);
                finish();
            }
        });

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(MainActivity.this, ImportFragment.class);
                MainActivity.this.startActivity(myIntent);
                finish();
            }
        });
    }
}
