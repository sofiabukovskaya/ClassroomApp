package com.example.classroomapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.classroomapp.contract.MainContract;
import com.example.classroomapp.model.ClassroomModel;
import com.example.classroomapp.presenter.MainPresenter;

public class ShowCurrentClassActivity extends AppCompatActivity{

    private TextView currentClassID, currentClassName,currentClassCabinet, currentClassFloor, currentClassStudents;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_current_class);
        currentClassID = findViewById(R.id.current_classroom_id);
        currentClassName = findViewById(R.id.current_classroom_name);
        currentClassCabinet = findViewById(R.id.room_number);
        currentClassFloor = findViewById(R.id.floor_text);
        currentClassStudents = findViewById(R.id.count_students);
        getInformationAboutCurrentClassroom();
    }

    @SuppressLint("SetTextI18n")
    public void getInformationAboutCurrentClassroom(){
        Intent intent = getIntent();
        currentClassID.setText(String.valueOf(intent.getIntExtra("classroomId",0)));
        currentClassName.setText(String.valueOf(intent.getStringExtra("classroomName")));
        currentClassCabinet.setText("# " +String.valueOf(intent.getIntExtra("classroomRoom",0)));
        currentClassFloor.setText(String.valueOf(intent.getIntExtra("classroomFloor",0)) + "th floor");
        currentClassStudents.setText(String.valueOf(intent.getIntExtra("classroomStudentsInfo",0)) + " students");
    }

}