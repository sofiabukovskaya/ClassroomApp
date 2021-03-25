package com.example.classroomapp.student.mainPageStudent;

import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuItemCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.os.PersistableBundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.classroomapp.R;
import com.example.classroomapp.classroom.mainPageClassroom.MainActivity;
import com.example.classroomapp.model.ClassroomModel;
import com.example.classroomapp.model.StudentModel;
import com.example.classroomapp.student.addStudent.AddStudentActivity;
import com.example.classroomapp.student.editStudent.EditStudentActivity;
import com.example.classroomapp.student.studentInfoAndMarks.MainStudentInfoMarksActivity;
import com.example.classroomapp.student.studentInfoAndMarks.studentInfo.StudentInfoFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class CurrentClassAndStudentsActivity extends AppCompatActivity implements CurrentClassAndStudentsContract.View, StudentAdapter.CallBackPosition, SearchView.OnQueryTextListener {

    private CurrentClassAndStudentsContract.Presenter currentClassAndStudentPresenter;

    private TextView currentClassID, currentClassName,currentClassCabinet, currentClassFloor, currentClassStudents;
    private FloatingActionButton floatingActionButton;
    private StudentAdapter studentAdapter;
    private RecyclerView recyclerViewStudents;
    private Integer classroomId;
    private ProgressDialog progressDialog;

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
        currentClassAndStudentPresenter = new CurrentClassAndStudentsPresenter(this, getApplicationContext());
        classroomId = getIntent().getIntExtra("classroomId",0);
        studentAdapter = new StudentAdapter(getApplicationContext(), currentClassAndStudentPresenter.loadAllDataInRecyclerView(classroomId), recyclerViewStudents, this);
        recyclerViewStudents.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewStudents.setAdapter(studentAdapter);
        getInformationAboutCurrentClassroom();
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Intent intent = new Intent(CurrentClassAndStudentsActivity.this, AddStudentActivity.class);
                    intent.putExtra("classroomPosition",classroomId);
                    startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_menu, menu);

        MenuItem searchItem = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setQueryHint("Search student by first name");
        searchView.setOnQueryTextListener(this);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
    @Override
    protected void onResume() {
        super.onResume();
        getInformationAboutCurrentClassroom();
    }
    @SuppressLint("SetTextI18n")
    public void getInformationAboutCurrentClassroom(){
        currentClassID.setText(String.valueOf(getIntent().getIntExtra("classroomId",0)));
        currentClassName.setText(String.valueOf(getIntent().getStringExtra("classroomName")));
        currentClassCabinet.setText("# " +String.valueOf(getIntent().getIntExtra("classroomRoom",0)));
        currentClassFloor.setText(String.valueOf(getIntent().getIntExtra("classroomFloor",0)) + "th floor");
        currentClassStudents.setText(String.valueOf(getIntent().getIntExtra("classroomStudentCount",0)) + " students");
    }

    @Override
    public void deleteStudentGetPosition(int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(CurrentClassAndStudentsActivity.this);
        builder.setTitle("Are you sure you want to delete this student from a classroom?")
                .setMessage("Please, select")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        progressDialog = ProgressDialog.show(CurrentClassAndStudentsActivity.this,"Deleting student","deleting...");
                        currentClassAndStudentPresenter.alertToDeleteClass(position);
                        studentAdapter.notifyItemRemoved(position);
                        studentAdapter.notifyDataSetChanged();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(CurrentClassAndStudentsActivity.this, "Okay, your student in safe", Toast.LENGTH_SHORT).show();
                    }
                });
        builder.create().show();
    }

    @Override
    public void showCurrentStudent(StudentModel studentModel) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(CurrentClassAndStudentsActivity.this, MainStudentInfoMarksActivity.class);
                intent.putExtra("studentId",studentModel.getStudentId());
                intent.putExtra("studentFirstName", studentModel.getFirstName());
                intent.putExtra("studentLastName", studentModel.getLastName());
                intent.putExtra("studentMiddleName", studentModel.getMiddleName());
                intent.putExtra("studentGender", studentModel.getStudentGender());
                intent.putExtra("studentAge", studentModel.getStudentAge());
                startActivity(intent);
            }
        }).start();
    }

    @Override
    public void editStudentInformation(StudentModel studentModel) {
        Intent intent = new Intent(CurrentClassAndStudentsActivity.this, EditStudentActivity.class);
        intent.putExtra("studentId",studentModel.getStudentId());
        intent.putExtra("studentFirstName", studentModel.getFirstName());
        intent.putExtra("studentLastName", studentModel.getLastName());
        intent.putExtra("studentMiddleName", studentModel.getMiddleName());
        intent.putExtra("studentGender", studentModel.getStudentGender());
        intent.putExtra("studentAge", studentModel.getStudentAge());
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        studentAdapter.notifyDataSetChanged();
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
                        Toast.makeText(CurrentClassAndStudentsActivity.this, messageAlert, Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                    }
                }, 1000);
            }
        });
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        newText = newText.toLowerCase();
        ArrayList<StudentModel> filteredNewList = new ArrayList<>();
        currentClassAndStudentPresenter.loadAllDataInRecyclerView(classroomId);
        for(StudentModel studentModel: currentClassAndStudentPresenter.loadAllDataInRecyclerView(classroomId)) {
            String firstNameStudent = studentModel.getFirstName().toLowerCase();
            if(firstNameStudent.contains(newText)) {
                filteredNewList.add(studentModel);
            }
        }
        studentAdapter.setFilter(filteredNewList);
        return true;
    }
}