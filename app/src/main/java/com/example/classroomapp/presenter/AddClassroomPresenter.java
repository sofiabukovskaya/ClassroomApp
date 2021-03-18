package com.example.classroomapp.presenter;

import android.content.Context;

import com.example.classroomapp.contract.AddClassroomContract;
import com.example.classroomapp.contract.EditClassroomContract;
import com.example.classroomapp.data.ClassroomRepository;
import com.example.classroomapp.model.ClassroomModel;

public class AddClassroomPresenter implements AddClassroomContract.Presenter {


    AddClassroomContract.View view;
    AddClassroomContract.Repository repository;
    ClassroomModel classroomModel;

    public AddClassroomPresenter(AddClassroomContract.View view, Context context) {
        this.view = view;
        this.repository = new ClassroomRepository(context);
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
}
