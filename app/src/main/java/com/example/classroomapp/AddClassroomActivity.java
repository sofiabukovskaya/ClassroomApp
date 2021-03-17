package com.example.classroomapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.classroomapp.contract.MainContract;
import com.example.classroomapp.model.ClassroomModel;
import com.example.classroomapp.presenter.MainPresenter;

public class AddClassroomActivity extends AppCompatActivity implements MainContract.View {
    private MainContract.Presenter presenter;

    private EditText addNameClassroom;
    private EditText addRoomNumber;
    private EditText addFloorNumber;
    public Button addNewClassroomButton;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_classroom);
        addNameClassroom = findViewById(R.id.add_name_classroom);
        addRoomNumber = findViewById(R.id.add_room_number);
        addFloorNumber = findViewById(R.id.add_floor);
        addNewClassroomButton = findViewById(R.id.addClassroomButton);

        presenter = new MainPresenter(this, getApplicationContext());

        addNewClassroomButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog = ProgressDialog.show(AddClassroomActivity.this,"Adding new class","loading...");
                presenter.addButtonWasClicked(addNameClassroom.getText().toString().trim(), Integer.parseInt(addRoomNumber.getText().toString().trim()),
                        Integer.parseInt(addFloorNumber.getText().toString().trim()));
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
                        Toast.makeText(AddClassroomActivity.this, messageAlert, Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                        startActivity(new Intent(AddClassroomActivity.this, MainActivity.class));
                    }
                },2000);
            }
        });
    }

    @Override
    public void updateListAfterDeleting(int position) {
    }

    @Override
    public void showCurrentClass(ClassroomModel currentClass) {

    }

    @Override
    public void editCurrentClassShow(ClassroomModel currentClass) {

    }
}