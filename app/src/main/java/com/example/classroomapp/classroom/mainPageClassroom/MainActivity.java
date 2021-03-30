package com.example.classroomapp.classroom.mainPageClassroom;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuItemCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.provider.SyncStateContract;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.classroomapp.R;
import com.example.classroomapp.classroom.addClassroom.AddClassroomActivity;
import com.example.classroomapp.classroom.editClassroom.EditClassActivity;
import com.example.classroomapp.model.ClassroomModel;
import com.example.classroomapp.student.mainPageStudent.CurrentClassAndStudentsActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements MainContract.View, ClassroomAdapter.CallBackPosition, SearchView.OnQueryTextListener {

    private MainContract.Presenter mainContractPresenter;
    private RecyclerView recyclerView;
    private FloatingActionButton floatingActionButton;
    private ClassroomAdapter classroomAdapter;
    private ProgressDialog progressDialog;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerView);

        floatingActionButton = findViewById(R.id.floatingActionButton);
        mainContractPresenter = new MainPresenter(this, this);
        classroomAdapter = new ClassroomAdapter(getApplicationContext(), mainContractPresenter.loadAllDataInRecyclerView(), recyclerView, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(classroomAdapter);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddClassroomActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_menu, menu);

        MenuItem searchItem = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setQueryHint("Search class by name");
        searchView.setOnQueryTextListener(this);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.sortItems){
            sortOptionDialog();
        }
        return super.onOptionsItemSelected(item);
    }

    public void loadSortedClassDataASC() {
        classroomAdapter = new ClassroomAdapter(getApplicationContext(), mainContractPresenter.orderItemsDataByClassASC(), recyclerView, this);
        recyclerView.setAdapter(classroomAdapter);
    }
    public void loadSortedClassDataDESC() {
        classroomAdapter = new ClassroomAdapter(getApplicationContext(), mainContractPresenter.orderItemsDataByClassDESC(), recyclerView, this);
        recyclerView.setAdapter(classroomAdapter);
    }
    public void loadSortedCabinetDataASC() {

        classroomAdapter = new ClassroomAdapter(getApplicationContext(), mainContractPresenter.orderItemsDataByCabinetASC(), recyclerView, this);
        recyclerView.setAdapter(classroomAdapter);
    }
    public void loadSortedCabinetDataDESC() {
        classroomAdapter = new ClassroomAdapter(getApplicationContext(), mainContractPresenter.orderItemsDataByCabinetDESC(), recyclerView, this);
        recyclerView.setAdapter(classroomAdapter);
    }



    private void sortOptionDialog() {
        String[] options = {"Classroom name ASC", "Classroom name DECS", "Cabinet number ASC", "Cabinet number DECS"};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Sort by")
                .setItems(options, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (which ==0){
                            loadSortedClassDataASC();
                        } if(which==1){
                            loadSortedClassDataDESC();
                        } if(which ==2) {
                            loadSortedCabinetDataASC();
                        }
                        if (which == 3) {
                            loadSortedCabinetDataDESC();
                        }
                    }
                })
                .create().show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        classroomAdapter = new ClassroomAdapter(getApplicationContext(), mainContractPresenter.loadAllDataInRecyclerView(), recyclerView, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(classroomAdapter);
    }

    @Override
    public void onSuccess(String messageAlert) {
        Handler handler = new Handler(getMainLooper());
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(MainActivity.this, messageAlert, Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        }, 1000);
    }

    @Override
    public void deleteClassGetPosition(int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Are you sure you want to delete this classroom?")
                .setMessage("Please, select")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        progressDialog = ProgressDialog.show(MainActivity.this, "Deleting class", "deleting...");
                        mainContractPresenter.alertToDeleteClass(position);
                        classroomAdapter.notifyItemRemoved(position);
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this, "Okay, your classroom in safe", Toast.LENGTH_SHORT).show();
                    }
                });
        builder.create().show();
    }

    @Override
    public void showCurrentClass(ClassroomModel classroomModel) {
        Intent intent = new Intent(MainActivity.this, CurrentClassAndStudentsActivity.class);
        intent.putExtra("classroomId",classroomModel.getId());
        intent.putExtra("classroomName", classroomModel.getClassroomName());
        intent.putExtra("classroomRoom", classroomModel.getClassroomRoomNumber());
        intent.putExtra("classroomFloor", classroomModel.getClassroomFloor());
        intent.putExtra("classroomStudentCount", classroomModel.getStudentCount());
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    @Override
    public void editCurrentClass(ClassroomModel classroomModel) {
        Intent intent = new Intent(MainActivity.this, EditClassActivity.class);
        intent.putExtra("classroomId",classroomModel.getId());
        intent.putExtra("classroomName", classroomModel.getClassroomName());
        intent.putExtra("classroomRoom", classroomModel.getClassroomRoomNumber());
        intent.putExtra("classroomFloor", classroomModel.getClassroomFloor());
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        classroomAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        newText = newText.toLowerCase();
        ArrayList<ClassroomModel> filteredNewList = new ArrayList<>();
        mainContractPresenter.loadAllDataInRecyclerView();
        for(ClassroomModel classroomModel: mainContractPresenter.loadAllDataInRecyclerView()) {
            String classModelName = classroomModel.getClassroomName().toLowerCase();
            if(classModelName.contains(newText)) {
                filteredNewList.add(classroomModel);
            }
        }
        classroomAdapter.setFilter(filteredNewList);
        return true;
    }
}