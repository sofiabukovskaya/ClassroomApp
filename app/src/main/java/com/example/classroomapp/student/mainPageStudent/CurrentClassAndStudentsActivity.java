package com.example.classroomapp.student.mainPageStudent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.classroomapp.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class CurrentClassAndStudentsActivity extends AppCompatActivity implements CurrentClassAndStudentsContract.View {

    private CurrentClassAndStudentsContract.Presenter studentPresenter;

    private TextView currentClassID, currentClassName,currentClassCabinet, currentClassFloor, currentClassStudents;
    private FloatingActionButton floatingActionButton;
    private StudentAdapter studentAdapter;
    private RecyclerView recyclerViewStudents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_current_class);
        currentClassID = findViewById(R.id.current_classroom_id);
        currentClassName = findViewById(R.id.current_classroom_name);
        currentClassCabinet = findViewById(R.id.room_number);
        currentClassFloor = findViewById(R.id.floor_text);
        currentClassStudents = findViewById(R.id.count_students);
        floatingActionButton = findViewById(R.id.floatingActionButtonStudents);
        recyclerViewStudents = findViewById(R.id.recyclerViewStudents);
        recyclerViewStudents.setHasFixedSize(true);
        studentPresenter = new CurrentClassAndStudentsPresenter();

        studentAdapter = new StudentAdapter(getApplicationContext(), studentPresenter.loadAllDataInRecyclerView(), recyclerViewStudents);
        recyclerViewStudents.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewStudents.setAdapter(studentAdapter);

        getInformationAboutCurrentClassroom();

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @SuppressLint("SetTextI18n")
    public void getInformationAboutCurrentClassroom(){
        currentClassID.setText(String.valueOf(getIntent().getIntExtra("classroomId",0)));
        currentClassName.setText(String.valueOf(getIntent().getStringExtra("classroomName")));
        currentClassCabinet.setText("# " +String.valueOf(getIntent().getIntExtra("classroomRoom",0)));
        currentClassFloor.setText(String.valueOf(getIntent().getIntExtra("classroomFloor",0)) + "th floor");
        currentClassStudents.setText(String.valueOf(getIntent().getIntExtra("classroomStudentsInfo",0)) + " students");
    }

    @Override
    public void onSuccess(String messageAlert) {

    }
}