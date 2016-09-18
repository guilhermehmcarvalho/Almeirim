package com.guilherme.almeirim.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.guilherme.almeirim.DB.DBProject;
import com.guilherme.almeirim.DB.Models.PatioModel;
import com.guilherme.almeirim.R;

public class NewPatioActivity extends AppCompatActivity {

    PatioModel patio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_patio);

        Bundle b = getIntent().getExtras();
        if(b != null) {
            int projectKey = b.getInt("Project");
            //patio = new DBPatio(this).get(projectKey);
            //setTitle("Romaneio " + patio.getNumRomaneio());
        }


    }
}
