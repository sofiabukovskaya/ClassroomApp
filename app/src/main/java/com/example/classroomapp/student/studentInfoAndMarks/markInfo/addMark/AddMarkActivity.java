package com.example.classroomapp.student.studentInfoAndMarks.markInfo.addMark;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.classroomapp.R;
import com.example.classroomapp.classroom.mainPageClassroom.MainActivity;
import com.example.classroomapp.student.addStudent.AddStudentActivity;
import com.example.classroomapp.student.addStudent.GenderSpinnerAdapter;
import com.example.classroomapp.student.studentInfoAndMarks.markInfo.MarksInfoFragment;

import java.util.Calendar;

public class AddMarkActivity extends AppCompatActivity implements AddMarkContract.View {


    private AddMarkContract.Presenter addMarkPresenter;
    private Spinner spinnerSubject, spinnerMark;
    private Button addDataMark, addNewMark;
    private TextView showSelectedData;
    private String[] spinnerValueSubject;
    private Integer[] spinnerValueMark;
    private SubjectSpinnerAdapter subjectSpinnerAdapter;
    private MarkSpinnerAdapter markSpinnerAdapter;
    public Integer day, month, year;
    Integer positionStudentId;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_mark);
        spinnerSubject = findViewById(R.id.spinner_subjectName);
        spinnerMark = findViewById(R.id.spinner_markType);
        addDataMark = findViewById(R.id.button_add_data_mark);
        addNewMark = findViewById(R.id.button_add_new_Mark);
        showSelectedData = findViewById(R.id.textView_dataMark);
        addMarkPresenter = new AddMarkPresenter(this, getApplicationContext());
        initialiseSpinners();
        positionStudentId = getIntent().getIntExtra("studentId",0);
        addDataMark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Handler handler = new Handler(Looper.getMainLooper());
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        createCalendarForAddDate();
                    }
                });

            }
        });

        addNewMark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                progressDialog = ProgressDialog.show(AddMarkActivity.this,"Adding new mark","loading...");
                addMarkPresenter.addButtonWasClicked(spinnerSubject.getSelectedItem().toString(), Integer.valueOf(spinnerMark.getSelectedItem().toString()),
                        showSelectedData.getText().toString(),positionStudentId);
            }
        });
    }

    public void initialiseSpinners(){
        spinnerValueSubject = new String[]{
                "Algebra",
                "Biology",
                "Informatics",
                "History",
                "Chemistry",
                "Geometry",
                "English"
        };

        spinnerValueMark = new Integer[]{
                1,
                2,
                3,
                4,
                5
        };

        subjectSpinnerAdapter = new SubjectSpinnerAdapter(this,R.layout.support_simple_spinner_dropdown_item);
        subjectSpinnerAdapter.addAll(spinnerValueSubject);
        subjectSpinnerAdapter.add("Subject");
        spinnerSubject.setAdapter(subjectSpinnerAdapter);

        markSpinnerAdapter = new MarkSpinnerAdapter(this, R.layout.support_simple_spinner_dropdown_item);
        markSpinnerAdapter.addAll(spinnerValueMark);
        markSpinnerAdapter.add(0);
        spinnerMark.setAdapter(markSpinnerAdapter);
    }

    public void createCalendarForAddDate(){
        final Calendar calendar = Calendar.getInstance();
        day = calendar.get(Calendar.DAY_OF_MONTH);
        month = calendar.get(Calendar.MONTH);
        year = calendar.get(Calendar.YEAR);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            DatePickerDialog datePickerDialog = new DatePickerDialog(AddMarkActivity.this, new DatePickerDialog.OnDateSetListener() {
                @SuppressLint("SetTextI18n")
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    showSelectedData.setText(year+"/"+(month+1)+"/"+dayOfMonth);
                }
            },year,month,day);
            datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
            datePickerDialog.show();
        }
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
                        Toast.makeText(AddMarkActivity.this, messageAlert, Toast.LENGTH_SHORT).show();
//                        progressDialog.dismiss();
                        startActivity(new Intent(AddMarkActivity.this, MarksInfoFragment.class));
                    }
                },1000);
            }
        });
    }
}