package com.guilherme.almeirim.Activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.guilherme.almeirim.DB.DBProject;
import com.guilherme.almeirim.DB.Models.ProjectModel;
import com.guilherme.almeirim.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView listview;
    FloatingActionButton addProject;
    private static final String TAG = "MainActivity";
    private boolean noProjects = false;
    List<ProjectModel> projects;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listview = (ListView) findViewById(R.id.project_list);
        addProject = (FloatingActionButton) findViewById(R.id.floating_action_button_fab_with_listview);
        addProject.setOnClickListener(addProjectListener);

        loadProjects();

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, final View view,
                                    int position, long id) {
                if (!noProjects) {
                    final ProjectModel item = (ProjectModel) parent.getItemAtPosition(position);
                    Log.d(TAG, item.toString());
                    Log.d("id", Integer.toString(item.getId()));
                    Intent myIntent = new Intent(MainActivity.this, ReportListActivity.class);
                    myIntent.putExtra("Project", item.getId());
                    MainActivity.this.startActivity(myIntent);
                }
            }
        });
    }

    private void addProjectDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Nome do projeto");

        // Set up the input
        final EditText input = new EditText(this);
        // Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setView(input);

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
                String name = input.getText().toString();
                if (name.equals("")) {
                    input.setBackgroundColor(Color.RED);
                }
                else {
                    addProject(name);
                    dialog.dismiss();
                }

            }
        });
    }

    private void loadProjects(){
        projects = new DBProject(this).getAllProjects();

        noProjects = projects.size() == 0;

        if (noProjects) {
            List<String> projList = new ArrayList<>();
            projList.add("Nenhum projeto existente. Adicione projetos clicando no botão +");

            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_list_item_1, android.R.id.text1, projList);
            listview.setAdapter(adapter);
        }
        else {
            ArrayAdapter<ProjectModel> adapter = new ArrayAdapter<ProjectModel>(this,
                    android.R.layout.simple_list_item_1, android.R.id.text1, projects);
            listview.setAdapter(adapter);
        }
    }

    private void addProject(String name){
        ProjectModel project = new ProjectModel();
        project.setName(name);
        new DBProject(this).addProject(project);

        loadProjects();
    }

    private View.OnClickListener addProjectListener = new View.OnClickListener() {
        public void onClick(View v) {
            Log.d(TAG, "Add a new task");
            addProjectDialog();
        }
    };
}
