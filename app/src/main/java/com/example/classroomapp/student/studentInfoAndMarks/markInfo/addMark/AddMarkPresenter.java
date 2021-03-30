package com.example.classroomapp.student.studentInfoAndMarks.markInfo.addMark;

import android.content.Context;

import com.example.classroomapp.data.markData.MarkRepository;
import com.example.classroomapp.model.MarkModel;
import com.example.classroomapp.model.StudentModel;
import com.example.classroomapp.student.addStudent.AddStudentContract;
import com.example.classroomapp.student.studentInfoAndMarks.markInfo.MarkInfoContract;

import java.util.List;

public class AddMarkPresenter implements AddMarkContract.Presenter{


    AddMarkContract.View view;
    AddMarkContract.Repository repository;
    MarkModel markModel;

    public AddMarkPresenter(AddMarkContract.View callback, Context context) {
        this.view = callback;
        this.repository = new MarkRepository(context);
    }

    @Override
    public void addButtonWasClicked(String subjectName, Integer markValue, String dateMark, int studentId) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                markModel = new MarkModel(0, subjectName, markValue, dateMark, null, studentId);
                repository.addMark(markModel);
                view.onSuccess("New mark is added!");
            }
        }).start();
    }
}
