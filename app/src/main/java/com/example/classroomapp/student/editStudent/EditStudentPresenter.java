package com.example.classroomapp.student.editStudent;

import android.content.Context;

import com.example.classroomapp.classroom.editClassroom.EditClassroomContract;
import com.example.classroomapp.data.classroomData.ClassroomRepository;
import com.example.classroomapp.data.studentData.StudentRepository;
import com.example.classroomapp.model.ClassroomModel;
import com.example.classroomapp.model.StudentModel;

public class EditStudentPresenter implements EditStudentContract.Presenter {


    EditStudentContract.View view;
    EditStudentContract.Repository repository;
    StudentModel studentModel;

    public EditStudentPresenter(EditStudentContract.View callback, Context context) {
        this.view = callback;
        this.repository = new StudentRepository(context);
    }

    @Override
    public void editButtonWasClicked(Integer id, String firstName, String lastName, String middleName, String gender, int age) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                studentModel = new StudentModel(id, firstName,lastName, middleName, gender,age,0);
                repository.updateStudent(studentModel);
                view.onSuccess("Edit!");
            }
        }).start();
    }
}
