package com.guilherme.almeirim.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;

import com.guilherme.almeirim.R;

public class ArvoreActivity extends AppCompatActivity {

    Button btnImportar;
    Button btnAdd;
    ListView listview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arvore);
    }
}
