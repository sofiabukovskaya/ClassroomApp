package com.example.classroomapp.student.addStudent;

import android.content.Context;

import com.example.classroomapp.classroom.addClassroom.AddClassroomContract;
import com.example.classroomapp.data.studentData.StudentRepository;
import com.example.classroomapp.model.ClassroomModel;
import com.example.classroomapp.model.StudentModel;

public class AddStudentPresenter implements AddStudentContract.Presenter {

    AddStudentContract.View view;
    AddStudentContract.Repository repository;
    StudentModel studentModel;

    public AddStudentPresenter(AddStudentContract.View callback, Context context) {
        this.view = callback;
        this.repository = new StudentRepository(context);
    }

    @Override
    public void addButtonWasClicked(String name, String lastName, String middleName, String gender, int age, int position) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                studentModel = new StudentModel(0, name, lastName, middleName, gender,age, position);
                repository.addStudent(studentModel);
                view.onSuccess("New class is added!");
            }
        }).start();
    }
}
