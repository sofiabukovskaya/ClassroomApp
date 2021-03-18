package com.example.classroomapp.classroom.mainPageClassroom;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Toast;

import com.example.classroomapp.R;
import com.example.classroomapp.classroom.addClassroom.AddClassroomActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity implements MainContract.View, ClassroomAdapter.CallBackPosition {

    MainContract.Presenter presenter;
    public RecyclerView recyclerView;
    public FloatingActionButton floatingActionButton;
    public ClassroomAdapter classroomAdapter;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerView);

        floatingActionButton = findViewById(R.id.floatingActionButton);
        presenter = new MainPresenter(this, getApplicationContext());

        recyclerView.setHasFixedSize(true);
        classroomAdapter = new ClassroomAdapter(getApplicationContext(), presenter.loadAllDataInRecyclerView(), recyclerView, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(classroomAdapter);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddClassroomActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onSuccess(String messageAlert) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(MainActivity.this, messageAlert, Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                    }
                },1000);
            }
        });
    }

    @Override
     public void deleteClassGetPosition(int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Are you sure you want to delete this classroom?")
                .setMessage("Please, select")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        progressDialog = ProgressDialog.show(MainActivity.this,"Deleting class","deleting...");
                        presenter.alertToDeleteClass(position);
                        classroomAdapter.notifyItemRemoved(position);
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this, "Okay, your classroom in safe", Toast.LENGTH_SHORT).show();
                    }
                });
        builder.create().show();
    }
}