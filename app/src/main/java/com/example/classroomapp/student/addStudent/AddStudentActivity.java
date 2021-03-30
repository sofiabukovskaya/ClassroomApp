package com.example.classroomapp.student.addStudent;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.classroomapp.R;
import com.example.classroomapp.classroom.addClassroom.AddClassroomActivity;
import com.example.classroomapp.classroom.addClassroom.AddClassroomPresenter;
import com.example.classroomapp.classroom.mainPageClassroom.ClassroomAdapter;
import com.example.classroomapp.classroom.mainPageClassroom.MainActivity;
import com.example.classroomapp.student.mainPageStudent.CurrentClassAndStudentsActivity;

public class AddStudentActivity extends AppCompatActivity implements AddStudentContract.View {

    private AddStudentContract.Presenter addStudentPresenter;
    private TextView firstNameStudent, lastNameStudent, middleNameStudent, ageStudent;
    private Spinner genderSpinner;
    private String[] spinnerValueGender;
    private GenderSpinnerAdapter genderSpinnerAdapter;
    private Button addStudentButton;
    private ProgressDialog progressDialog;
    Integer positionClass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);

        firstNameStudent = findViewById(R.id.add_first_name_student);
        lastNameStudent = findViewById(R.id.add_last_name_student);
        middleNameStudent = findViewById(R.id.add_middle_name);
        ageStudent = findViewById(R.id.add_age_student);
        addStudentButton = findViewById(R.id.add_student_button);
        genderSpinner = findViewById(R.id.spinner_gender);

        spinnerValueGender = new String[]{
                "Female",
                "Male"
        };

        genderSpinnerAdapter = new GenderSpinnerAdapter(this,R.layout.support_simple_spinner_dropdown_item);
        genderSpinnerAdapter.addAll(spinnerValueGender);
        genderSpinnerAdapter.add("Gender");
        genderSpinner.setAdapter(genderSpinnerAdapter);
        addStudentPresenter = new AddStudentPresenter(this, getApplicationContext());
        positionClass = getIntent().getIntExtra("classroomPosition",0);

        addStudentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog = ProgressDialog.show(AddStudentActivity.this,"Adding new student","loading...");
                addStudentPresenter.addButtonWasClicked(firstNameStudent.getText().toString(),lastNameStudent.getText().toString(),
                        middleNameStudent.getText().toString(),genderSpinner.getSelectedItem().toString(), Integer.parseInt(ageStudent.getText().toString()), positionClass);
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
                        Toast.makeText(AddStudentActivity.this, messageAlert, Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                        startActivity(new Intent(AddStudentActivity.this, MainActivity.class));
                    }
                },1000);
            }
        });
    }
}