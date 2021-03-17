package com.example.classroomapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Toast;

import com.example.classroomapp.contract.MainContract;
import com.example.classroomapp.model.ClassroomModel;
import com.example.classroomapp.presenter.MainPresenter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

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
        classroomAdapter = new ClassroomAdapter(getApplicationContext(), presenter.loadAllDataInRecyclerView(), recyclerView, presenter);
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

    @Override
    public void showCurrentClass(ClassroomModel currentClass) {
        Intent intent = new Intent(this, ShowCurrentClassActivity.class);
        intent.putExtra("classroomId", currentClass.getId());
        intent.putExtra("classroomName", currentClass.getClassroomName());
        intent.putExtra("classroomRoom", currentClass.getClassroomRoomNumber());
        intent.putExtra("classroomFloor", currentClass.getClassroomFloor());
        intent.putExtra("classroomStudentsInfo", currentClass.getStudentCount());
        startActivity(intent);
    }

}