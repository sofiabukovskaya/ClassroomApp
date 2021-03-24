package com.example.classroomapp.student.editStudent;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.classroomapp.R;
import com.example.classroomapp.classroom.editClassroom.EditClassActivity;
import com.example.classroomapp.classroom.mainPageClassroom.MainActivity;
import com.example.classroomapp.student.addStudent.AddStudentContract;
import com.example.classroomapp.student.addStudent.GenderSpinnerAdapter;
import com.example.classroomapp.student.mainPageStudent.CurrentClassAndStudentsActivity;

public class EditStudentActivity extends AppCompatActivity implements EditStudentContract.View {

    private EditText firstNameStudent, secondNameStudent, middleNameStudent, ageStudent;
    private Spinner genderStudent;
    private Button editStudentInfo;
    private GenderSpinnerAdapter genderSpinnerAdapter;
    private String[] spinnerValueGender;
    private EditStudentContract.Presenter editStudentPresenter;
    private Integer currentStudentId;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_student);
        setLayoutItems();
        getDataFromActivity();
        editStudentInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog = ProgressDialog.show(EditStudentActivity.this,"Editing student","editing...");
                editStudentPresenter.editButtonWasClicked( currentStudentId,firstNameStudent.getText().toString().trim(), secondNameStudent.getText().toString().trim(),
                        middleNameStudent.getText().toString().trim(),genderStudent.getSelectedItem().toString(), Integer.parseInt(ageStudent.getText().toString().trim()));
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
                        progressDialog.dismiss();
                        startActivity(new Intent(EditStudentActivity.this, MainActivity.class));
                        Toast.makeText(EditStudentActivity.this, messageAlert, Toast.LENGTH_LONG).show();
                    }
                },2000);
            }
        });
    }

    public void getDataFromActivity(){
        currentStudentId = getIntent().getIntExtra("studentId",0);
        firstNameStudent.setText(String.valueOf(getIntent().getStringExtra("studentFirstName")));
        secondNameStudent.setText(String.valueOf(getIntent().getStringExtra("studentLastName")));
        middleNameStudent.setText(String.valueOf(getIntent().getStringExtra("studentMiddleName")));
        ageStudent.setText(String.valueOf(getIntent().getIntExtra("studentAge",0)));

    }

    public void setLayoutItems(){
        firstNameStudent = findViewById(R.id.edit_firstName_student);
        secondNameStudent = findViewById(R.id.edit_secondName_student);
        middleNameStudent = findViewById(R.id.edit_middleName_student);
        ageStudent = findViewById(R.id.edit_age_student);
        genderStudent = findViewById(R.id.edit_gender_studentSpinner);
        editStudentInfo = findViewById(R.id.editStudentButton);

        spinnerValueGender = new String[]{
                "Female",
                "Male"
        };

        genderSpinnerAdapter = new GenderSpinnerAdapter(this,R.layout.support_simple_spinner_dropdown_item);
        genderSpinnerAdapter.addAll(spinnerValueGender);
        genderSpinnerAdapter.add("Gender");
        genderStudent.setAdapter(genderSpinnerAdapter);
        editStudentPresenter = new EditStudentPresenter(this, getApplicationContext());
    }
}