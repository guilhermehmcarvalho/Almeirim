package com.guilherme.almeirim.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.guilherme.almeirim.DB.Models.PatioModel;
import com.guilherme.almeirim.DB.Models.TransporteModel;
import com.guilherme.almeirim.R;

public class NewTransporteActivity extends AppCompatActivity {

    TransporteModel tranporte;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_transporte);

        setTitle("Romaneio de transporte");
    }
}
