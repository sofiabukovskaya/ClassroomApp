package com.example.classroomapp.classroom.addClassroom;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.classroomapp.R;
import com.example.classroomapp.classroom.mainPageClassroom.MainActivity;

public class AddClassroomActivity extends AppCompatActivity implements AddClassroomContract.View {
    private AddClassroomContract.Presenter addClassroomPresenter;

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

        addClassroomPresenter = new AddClassroomPresenter(this, getApplicationContext());

        addNewClassroomButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog = ProgressDialog.show(AddClassroomActivity.this,"Adding new class","loading...");
                addClassroomPresenter.addButtonWasClicked(addNameClassroom.getText().toString().trim(), Integer.parseInt(addRoomNumber.getText().toString().trim()),
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

}