package com.guilherme.almeirim.Activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Debug;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TabHost;

import com.guilherme.almeirim.DB.DBPatio;
import com.guilherme.almeirim.DB.DBProject;
import com.guilherme.almeirim.DB.DBTransporte;
import com.guilherme.almeirim.DB.Models.PatioModel;
import com.guilherme.almeirim.DB.Models.ProjectModel;
import com.guilherme.almeirim.DB.Models.TransporteModel;
import com.guilherme.almeirim.R;

import java.util.ArrayList;
import java.util.List;

public class ReportListActivity extends AppCompatActivity {

    ListView patioList;
    ListView transportList;
    TabHost tabHost;
    ProjectModel project;
    Boolean noPatio;
    Boolean noTransport;
    Toolbar toolbar;
    Button arvores;

    List<PatioModel> patioItems;
    List<TransporteModel> transporteItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);

        transportList = (ListView) findViewById(R.id.transport_list);
        patioList = (ListView) findViewById(R.id.patio_list);
        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        arvores = (Button) findViewById(R.id.btn_arvores);
        arvores.setOnClickListener(arvoreListener);
        setSupportActionBar(toolbar);

        Bundle b = getIntent().getExtras();
        if(b != null) {
            int projectKey = b.getInt("Project");
            project = new DBProject(this).getProjectWithKey(projectKey);
            toolbar.setTitle(project.getName());
        }

        setupTabs();
        loadPatio();
        loadTransporte();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(addItemListener);
    }

    private void loadPatio(){
        patioItems = new DBPatio(this).getAllRomaneios(project.getId());

        noPatio = patioItems.size() == 0;

        if (noPatio) {
            List<String> projList = new ArrayList<>();
            projList.add("Sem romaneios para este projeto. Adicione romaneios clicando no botão +");

            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_list_item_1, android.R.id.text1, projList);
            patioList.setAdapter(adapter);
        }
        else {
            final ArrayAdapter<PatioModel> adapter = new ArrayAdapter<PatioModel>(this,
                    android.R.layout.simple_list_item_1, android.R.id.text1, patioItems);
            patioList.setAdapter(adapter);
        }
    }

    private void loadTransporte(){
        transporteItems = new DBTransporte(this).getAllTransporte(project.getId());
        noTransport = transporteItems.size() == 0;

        if (noTransport) {
            List<String> transpList = new ArrayList<>();
            transpList.add("Sem romaneios de transporte para este projeto. Adicione romaneios clicando no botão +");

            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_list_item_1, android.R.id.text1, transpList);
            transportList.setAdapter(adapter);
        }
        else {
            final ArrayAdapter<TransporteModel> adapter = new ArrayAdapter<TransporteModel>(this,
                    android.R.layout.simple_list_item_1, android.R.id.text1, transporteItems);
            transportList.setAdapter(adapter);
        }
    }

    private void setupTabs(){
        tabHost = (TabHost) findViewById(R.id.tabHost);
        tabHost.setup();

        //Tab 1
        TabHost.TabSpec spec = tabHost.newTabSpec("Pátio");
        spec.setContent(R.id.patioTab);
        spec.setIndicator("Pátio");
        tabHost.addTab(spec);

        //Tab 2
        spec = tabHost.newTabSpec("Transporte");
        spec.setContent(R.id.transTab);
        spec.setIndicator("Transporte");
        tabHost.addTab(spec);
    }

    private View.OnClickListener addItemListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            switch(tabHost.getCurrentTabTag()) {
                case "Pátio":
                    AddPatio();
                    break;
                case "Transporte":
                    AddTransporte();
                    break;
            }
        }
    };

    private View.OnClickListener arvoreListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(ReportListActivity.this, ArvoreActivity.class);
            intent.putExtra("ProjectId", project.getId());
            ReportListActivity.this.startActivity(intent);
        }
    };

    private void AddPatio(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Novo romaneio de pátio");
        LayoutInflater inflater = getLayoutInflater();
        View convertView = (View) inflater.inflate(R.layout.dialog_patio, null);
        final EditText numRomaneio = (EditText) convertView.findViewById(R.id.txtNumRomaneio);
        final EditText nomeFuncionario = (EditText) convertView.findViewById(R.id.txtFunc);
        builder.setView(convertView);

        // Set up the buttons
        builder.setPositiveButton("OK", null);
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        final AlertDialog dialog = builder.create();
        dialog.show();
        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String romaneio = numRomaneio.getText().toString();
                String funcionario = nomeFuncionario.getText().toString();
                if (romaneio.length() == 0) {
                    numRomaneio.setBackgroundColor(Color.RED);
                }
                if (funcionario.length() == 0) {
                    nomeFuncionario.setBackgroundColor(Color.RED);
                }

                if (romaneio.length() > 0 &&
                        funcionario.length() > 0) {
                    criaRomaneioPatio(funcionario, Integer.parseInt(romaneio));
                    dialog.dismiss();
                }
            }
        });
    }

    private void AddTransporte(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Novo romaneio de transporte");
        LayoutInflater inflater = getLayoutInflater();
        View convertView = (View) inflater.inflate(R.layout.dialog_transporte, null);
        final EditText numRomaneio = (EditText) convertView.findViewById(R.id.DiagTransRomaneio);
        final EditText txtmotorista = (EditText) convertView.findViewById(R.id.DiagTransMotorista);
        final EditText txtcliente = (EditText) convertView.findViewById(R.id.DiagTransCliente);
        final EditText txtplaca = (EditText) convertView.findViewById(R.id.DiagTransPlaca);
        builder.setView(convertView);

        // Set up the buttons
        builder.setPositiveButton("OK", null);
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        final AlertDialog dialog = builder.create();
        dialog.show();
        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String romaneio = numRomaneio.getText().toString();
                String motorista = txtmotorista.getText().toString();
                String placa = txtplaca.getText().toString();
                String cliente = txtcliente.getText().toString();

                if (romaneio.length() == 0) {
                    numRomaneio.setBackgroundColor(Color.RED);
                }
                if (motorista.length() == 0) {
                    txtmotorista.setBackgroundColor(Color.RED);
                }
                if (placa.length() == 0) {
                    txtplaca.setBackgroundColor(Color.RED);
                }
                if (cliente.length() == 0) {
                    txtcliente.setBackgroundColor(Color.RED);
                }

                if (cliente.length() > 0 &&
                        motorista.length() > 0 &&
                        romaneio.length() > 0 &&
                        placa.length() > 0) {
                    criaRomaneioTransporte(motorista, placa, cliente, Integer.parseInt(romaneio));
                    dialog.dismiss();
                }
            }
        });
    }

    private void criaRomaneioPatio(String funcionario, int romaneio){
        if (this.project != null) {
            PatioModel model = new PatioModel();
            model.setFuncionario(funcionario);
            model.setNumRomaneio(romaneio);
            model.setProjectId(this.project.getId());
            model.setDataDigitacao("11");
            DBPatio dbPatio = new DBPatio(this);
            dbPatio.addRomaneio(model);

            Intent newPatioIntent = new Intent(ReportListActivity.this, NewPatioActivity.class);
            newPatioIntent.putExtra("PatioId", model.getPatioId());
            ReportListActivity.this.startActivity(newPatioIntent);

            loadPatio();
        }
    }

    private void criaRomaneioTransporte(String motorista, String placa, String cliente, int romaneio){
        if (this.project != null) {
            TransporteModel model = new TransporteModel();
            model.setMotorista(motorista);
            model.setIdRomaneio(romaneio);
            model.setProjectId(this.project.getId());
            model.setPlaca(placa);
            model.setCliente(cliente);

            DBTransporte dbTranp = new DBTransporte(this);
            dbTranp.addTransporte(model);

            Intent newTransIntent = new Intent(ReportListActivity.this, NewTransporteActivity.class);
            newTransIntent.putExtra("TransporteId", model.getId());
            ReportListActivity.this.startActivity(newTransIntent);

            loadTransporte();
        }
    }
}
