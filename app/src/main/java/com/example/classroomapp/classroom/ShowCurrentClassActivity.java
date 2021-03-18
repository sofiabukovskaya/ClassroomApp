package com.example.classroomapp.classroom;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.classroomapp.R;
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
        currentClassID.setText(String.valueOf(getIntent().getIntExtra("classroomId",0)));
        currentClassName.setText(String.valueOf(getIntent().getStringExtra("classroomName")));
        currentClassCabinet.setText("# " +String.valueOf(getIntent().getIntExtra("classroomRoom",0)));
        currentClassFloor.setText(String.valueOf(getIntent().getIntExtra("classroomFloor",0)) + "th floor");
        currentClassStudents.setText(String.valueOf(getIntent().getIntExtra("classroomStudentsInfo",0)) + " students");
    }

}