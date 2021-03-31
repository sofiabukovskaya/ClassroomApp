package com.example.classroomapp.student.studentInfoAndMarks.markInfo;

import android.content.Context;

import com.example.classroomapp.data.markData.MarkRepository;
import com.example.classroomapp.model.MarkModel;
import com.example.classroomapp.model.StudentModel;
import com.example.classroomapp.student.addStudent.AddStudentContract;

import java.util.List;

public class MarkInfoPresenter implements MarkInfoContract.Presenter {

    MarkInfoContract.View view;
    MarkInfoContract.Repository repository;

    public MarkInfoPresenter(MarkInfoContract.View callback, Context context) {
        this.view = callback;
        this.repository = new MarkRepository(context);
    }

    @Override
    public List<MarkModel> loadAllDataInRecyclerView(int studentId) {
        return repository.getMarksForCurrentStudent(studentId);
    }

    @Override
    public void alertToDeleteMark(int position) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                repository.deleteMark(position);
                view.onSuccess("Your mark is deleted!");
            }
        }).start();
    }
}
