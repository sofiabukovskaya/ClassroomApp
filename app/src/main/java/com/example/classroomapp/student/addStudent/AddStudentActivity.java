package com.example.classroomapp.student.addStudent;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import com.example.classroomapp.R;

public class AddStudentActivity extends AppCompatActivity implements AddStudentContract.View{

    private TextView firstNameStudent, lastNameStudent, middleNameStudent, ageStudent;
    private Spinner genderSpinner;
    private String[] spinnerValueGender;
    private GenderSpinnerAdapter genderSpinnerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);

        firstNameStudent = findViewById(R.id.add_first_name_student);
        lastNameStudent = findViewById(R.id.add_last_name_student);
        middleNameStudent = findViewById(R.id.add_middle_name);
        ageStudent = findViewById(R.id.add_age_student);

        genderSpinner = findViewById(R.id.spinner_gender);
        spinnerValueGender = new String[]{
                "Female",
                "Male"
        };

        genderSpinnerAdapter = new GenderSpinnerAdapter(this,R.layout.support_simple_spinner_dropdown_item);
        genderSpinnerAdapter.addAll(spinnerValueGender);
        genderSpinnerAdapter.add("Gender");
        genderSpinner.setAdapter(genderSpinnerAdapter);

    }

    @Override
    public void onSuccess(String messageAlert) {

    }
}