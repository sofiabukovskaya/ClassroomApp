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

public class EditClassActivity extends AppCompatActivity implements MainContract.View{
    private MainContract.Presenter presenter;

    private EditText editClassroomName, editClassroomRoom, editClassroomFloor;
    private Button updateClassroomButton;
    private ProgressDialog progressDialog;
    private Integer currentClassId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_class);

        editClassroomName = findViewById(R.id.edit_name_classroom);
        editClassroomRoom = findViewById(R.id.edit_room_number);
        editClassroomFloor = findViewById(R.id.edit_floor);
        updateClassroomButton = findViewById(R.id.editClassroomButton);
        presenter = new MainPresenter(this, getApplicationContext());
        setEditsCurrentClass();
        updateClassroomButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog = ProgressDialog.show(EditClassActivity.this,"Editing class","editing...");
                presenter.editButtonWasClicked(currentClassId,editClassroomName.getText().toString().trim(), Integer.parseInt(editClassroomRoom.getText().toString().trim()),
                        Integer.parseInt(editClassroomFloor.getText().toString().trim()));
            }
        });
    }


    public void setEditsCurrentClass(){
        Intent intent = getIntent();
        currentClassId = intent.getIntExtra("classroomId",0);
        editClassroomName.setText(String.valueOf(intent.getStringExtra("classroomName")));
        editClassroomRoom.setText(String.valueOf(intent.getIntExtra("classroomRoom",0)));
        editClassroomFloor.setText(String.valueOf(intent.getIntExtra("classroomFloor",0)));
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
                        startActivity(new Intent(EditClassActivity.this, MainActivity.class));
                        Toast.makeText(EditClassActivity.this, messageAlert, Toast.LENGTH_LONG).show();
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