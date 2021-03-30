package com.example.classroomapp.student.studentInfoAndMarks;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;

import com.example.classroomapp.R;
import com.example.classroomapp.student.studentInfoAndMarks.markInfo.MarksInfoFragment;
import com.example.classroomapp.student.studentInfoAndMarks.stitisticInfo.StatisticsInfoFragment;
import com.example.classroomapp.student.studentInfoAndMarks.studentInfo.StudentInfoFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainStudentInfoMarksActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    Integer studentId, studentAge;
    String firstNameStudent, secondNameStudent, middleNameStudent, genderStudent;
    StudentInfoFragment studentInfoFragment = new StudentInfoFragment();
    MarksInfoFragment marksInfoFragment = new MarksInfoFragment();
    StatisticsInfoFragment statisticsInfoFragment = new StatisticsInfoFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main_student_info_marks_activty);
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(navigationItemSelectedListener);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,studentInfoFragment).commit();

        getInformationFromActivity();
        sendInformationToFragment();

        Bundle bundle1 = new Bundle();
        bundle1.putInt("studentId",studentId);
        marksInfoFragment.setArguments(bundle1);
    }
    private BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selectedFragment = null;
            switch (item.getItemId()){
                case R.id.nav_person:
                    selectedFragment = studentInfoFragment;
                    break;
                case R.id.nav_marks:
                    selectedFragment = marksInfoFragment;
                    break;
                case R.id.nav_statistics:
                    selectedFragment = statisticsInfoFragment;
                    break;
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,selectedFragment).commit();
            return true;
        }
    };

    public void getInformationFromActivity(){

                Intent intent = getIntent();
                 studentId = intent.getIntExtra("studentId",0);
                firstNameStudent = intent.getStringExtra("studentFirstName");
                secondNameStudent = intent.getStringExtra("studentLastName");
                middleNameStudent = intent.getStringExtra("studentMiddleName");
                genderStudent = intent.getStringExtra("studentGender");
                studentAge = intent.getIntExtra("studentAge",0);
    }

    public void sendInformationToFragment(){

        new Thread(new Runnable() {
            @Override
            public void run() {
                Bundle bundle = new Bundle();
                bundle.putInt("studentId",studentId);
                bundle.putString("studentFirstName", firstNameStudent);
                bundle.putString("studentSecondName", secondNameStudent);
                bundle.putString("studentMiddleName", middleNameStudent);
                bundle.putString("studentGender",  genderStudent);
                bundle.putInt("studentAge",studentAge);
                studentInfoFragment.setArguments(bundle);
            }
        }).start();

    }
}