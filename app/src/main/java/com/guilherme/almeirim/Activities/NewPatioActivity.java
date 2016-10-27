package com.guilherme.almeirim.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.guilherme.almeirim.DB.DBArvore;
import com.guilherme.almeirim.DB.DBProject;
import com.guilherme.almeirim.DB.Models.ArvoreModel;
import com.guilherme.almeirim.DB.Models.PatioModel;
import com.guilherme.almeirim.R;

public class NewPatioActivity extends AppCompatActivity {

    PatioModel patio;

    EditText arvoreText;

    TextView labelUT;
    TextView labelEspecie;

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

        assignComponents();
    }

    private void assignComponents(){
        labelUT = (TextView) findViewById(R.id.labelUT);
        labelEspecie = (TextView) findViewById(R.id.labelEspecie);
        arvoreText = (EditText) findViewById(R.id.textArvore);

        arvoreText.setOnFocusChangeListener(arvoreFocus);
    }

    View.OnFocusChangeListener arvoreFocus = new View.OnFocusChangeListener() {
        @Override
        public void onFocusChange(View v, boolean hasFocus) {
            if (!hasFocus){
                String text = arvoreText.getText().toString();
                Integer placa = Integer.valueOf(text);

                if (text.length() > 0) {
                    ArvoreModel arvore = new DBArvore(getApplicationContext()).getArvoreWithPlaca(placa);

                    if (arvore != null) {
                        labelUT.setText(String.valueOf(arvore.getUT()));
                        labelEspecie.setText(String.valueOf(arvore.getEspecie()));
                    }
                }

            }
        }
    };

}
