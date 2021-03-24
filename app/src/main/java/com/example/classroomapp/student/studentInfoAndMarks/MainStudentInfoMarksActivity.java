package com.example.classroomapp.student.studentInfoAndMarks;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.classroomapp.R;
import com.example.classroomapp.student.studentInfoAndMarks.markInfo.MarksInfoFragment;
import com.example.classroomapp.student.studentInfoAndMarks.stitisticInfo.StatisticsInfoFragment;
import com.example.classroomapp.student.studentInfoAndMarks.studentInfo.StudentInfoFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainStudentInfoMarksActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_student_info_marks_activty);
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(navigationItemSelectedListener);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new StudentInfoFragment()).commit();
    }
    private BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selectedFragment = null;
            switch (item.getItemId()){
                case R.id.nav_person:
                    selectedFragment = new StudentInfoFragment();
                    break;
                case R.id.nav_marks:
                    selectedFragment = new MarksInfoFragment();
                    break;
                case R.id.nav_statistics:
                    selectedFragment = new StatisticsInfoFragment();
                    break;
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,selectedFragment).commit();
            return true;
        }
    };
}