package com.example.classroomapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity implements MainContract.View {

    MainContract.Presenter presenter;
    public RecyclerView recyclerView;
    public FloatingActionButton floatingActionButton;
    public ClassroomAdapter classroomAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerView);

        floatingActionButton = findViewById(R.id.floatingActionButton);
        presenter = new MainPresenter(this, getApplicationContext());
        recyclerView.setHasFixedSize(true);
        classroomAdapter = new ClassroomAdapter(getApplicationContext(), presenter.loadAllDataInRecyclerView(),recyclerView,presenter);
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

    }

    @Override
    public void onError(String messageErrorAlert) {

    }

    @Override
    public void updateListAfterDeleting(int position) {
        classroomAdapter.notifyItemRemoved(position);
        classroomAdapter.notifyDataSetChanged();
    }

    @Override
    public void updateData(int position) {

    }
}