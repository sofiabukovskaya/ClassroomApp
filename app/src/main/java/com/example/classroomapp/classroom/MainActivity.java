package com.example.classroomapp.classroom;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.classroomapp.R;
import com.example.classroomapp.contract.MainContract;
import com.example.classroomapp.presenter.MainPresenter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity implements MainContract.View, ClassroomAdapter.CallBackPosition {

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

    }

    @Override
    public void deleteClassGetPosition(int position) {
        presenter.alertToDeleteClass(position);
        classroomAdapter.notifyItemRemoved(position);
        classroomAdapter.notifyDataSetChanged();
    }

}