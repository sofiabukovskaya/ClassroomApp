package com.example.classroomapp.classroom.editClassroom;

import android.content.Context;

import com.example.classroomapp.data.classroomData.ClassroomRepository;
import com.example.classroomapp.model.ClassroomModel;
import com.example.classroomapp.student.addStudent.AddStudentActivity;

public class EditClassPresenter implements EditClassroomContract.Presenter {

    EditClassroomContract.View view;
    EditClassroomContract.Repository repository;
    ClassroomModel classroomModel;

    public EditClassPresenter(EditClassroomContract.View callback, Context context) {
        this.view = callback;
        this.repository = new ClassroomRepository(context);
    }

    @Override
    public void editButtonWasClicked(Integer id, String name, int room, int floor) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                classroomModel = new ClassroomModel(id, name, room, floor,0);
                repository.updateClass(classroomModel);
                view.onSuccess("Edit!");
            }
        }).start();
    }

}
