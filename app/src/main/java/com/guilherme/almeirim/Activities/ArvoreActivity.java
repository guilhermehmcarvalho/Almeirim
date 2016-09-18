package com.guilherme.almeirim.Activities;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.guilherme.almeirim.DB.DBArvore;
import com.guilherme.almeirim.DB.DBProject;
import com.guilherme.almeirim.DB.Models.ArvoreModel;
import com.guilherme.almeirim.DB.Models.ProjectModel;
import com.guilherme.almeirim.R;

import java.util.ArrayList;
import java.util.List;

public class ArvoreActivity extends AppCompatActivity {

    Button btnImportar;
    Button btnAdd;
    ListView listview;
    ProjectModel project;
    EditText txtPlaca;
    EditText txtUT;
    EditText txtEspecie;
    EditText txtCap;

    List<ArvoreModel> listArvores;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arvore);

        btnAdd = (Button) findViewById(R.id.btnAddArvore);
        txtPlaca = (EditText) findViewById(R.id.textPlaca);
        txtCap = (EditText) findViewById(R.id.textCap);
        txtUT = (EditText) findViewById(R.id.textUT);
        txtEspecie = (EditText) findViewById(R.id.textEspecie);
        listview = (ListView) findViewById(R.id.listArvores);

        btnAdd.setOnClickListener(btnAddListener);
        Drawable bg = txtPlaca.getBackground();

        Bundle b = getIntent().getExtras();
        if(b != null) {
            int projectKey = b.getInt("ProjectId");
            project = new DBProject(this).getProjectWithKey(projectKey);
        }

        loadArvores();

        setTitle("Listagem de árvores");
    }

    View.OnClickListener btnAddListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (checkFields()) {
                addArvore();
                resetFields();
                loadArvores();
            }
        }
    };

    private void addArvore(){
        ArvoreModel model = new ArvoreModel();
        model.setCap(Integer.parseInt(txtCap.getText().toString()));
        model.setPlaca(Integer.parseInt(txtPlaca.getText().toString()));
        model.setEspecie(txtEspecie.getText().toString());
        model.setUT(Integer.parseInt(txtUT.getText().toString()));
        model.setIdProjeto(project.getId());
        new DBArvore(getApplicationContext()).addItem(model);
    }

    private void loadArvores(){
        listArvores = new DBArvore(this).getAllArvores(project.getId());

        if (listArvores.size() == 0) {
            List<String> arvList = new ArrayList<>();
            arvList.add("Sem árvores para exibir.");

            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_list_item_1, android.R.id.text1, arvList);
            listview.setAdapter(adapter);
        }
        else {
            final ArrayAdapter<ArvoreModel> adapter = new ArrayAdapter<ArvoreModel>(this,
                    android.R.layout.simple_list_item_1, android.R.id.text1, listArvores);
            listview.setAdapter(adapter);
        }
    }

    private void resetFields(){
        txtPlaca.setText("");
        txtCap.setText("");
        txtUT.setText("");
        txtEspecie.setText("");

        //txtPlaca.setBackgroundColor(Color.TRANSPARENT);
        //txtCap.setBackgroundColor(Color.WHITE);
        //txtUT.setBackgroundColor(Color.WHITE);
        //txtEspecie.setBackgroundColor(Color.WHITE);
    }

    private boolean checkFields(){
        boolean result = true;
        if (txtEspecie.getText().length() == 0) {
            //txtEspecie.setBackgroundColor(Color.RED);
            result =  false;
        }

        if (txtUT.getText().length() == 0) {
            //txtUT.setBackgroundColor(Color.RED);
            result =  false;
        }

        if (txtCap.getText().length() == 0) {
            //txtCap.setBackgroundColor(Color.RED);
            result =  false;
        }

        if (txtPlaca.getText().length() == 0) {
            //txtPlaca.setBackgroundColor(Color.RED);
            result =  false;
        }

        return result;
    }
}
