package com.example.classroomapp;

import android.app.ProgressDialog;
import android.content.Context;

import java.util.List;

public class MainPresenter implements MainContract.Presenter {

    MainContract.View view;
    MainContract.Repository repository;
    ClassroomModel classroomModel;


    public MainPresenter(MainContract.View view, Context context) {
        this.view = view;
        this.repository =  new Repository(context);
    }

    @Override
    public void addButtonWasClicked(String name, int room, int floor) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                classroomModel = new ClassroomModel(0, name, room, floor,0);
                repository.addClasses(classroomModel);
                view.onSuccess("New class is added!");
            }
        }).start();

    }

    @Override
    public List<ClassroomModel> loadAllDataInRecyclerView() {
        return repository.getListFromDataBase();
    }

    @Override
    public void alertToDeleteClass(int position) {
        repository.deleteClass(position);
        view.updateListAfterDeleting(position);
    }

    @Override
    public void editData(int position) {
        view.updateData(position);
    }
}
