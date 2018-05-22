package com.zhixin.materiladesigndemo;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.snackbar_demo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar
                        .make(v,"come out",Snackbar.LENGTH_LONG)
                        .setAction("action1", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(MainActivity.this,"action1 click",Toast.LENGTH_SHORT).show();
                    }
                })
                        .setAction("action2", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(MainActivity.this,"action2 click",Toast.LENGTH_SHORT).show();

                    }
                })
                        .show();
            }
        });
    }
}
