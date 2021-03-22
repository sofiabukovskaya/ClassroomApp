package com.example.classroomapp.student.mainPageStudent;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.classroomapp.classroom.mainPageClassroom.MainContract;
import com.example.classroomapp.data.studentData.DataBaseStudent;
import com.example.classroomapp.data.studentData.StudentRepository;
import com.example.classroomapp.student.mainPageStudent.CurrentClassAndStudentsContract;
import com.example.classroomapp.model.StudentModel;

import java.util.ArrayList;
import java.util.List;

public class CurrentClassAndStudentsPresenter implements CurrentClassAndStudentsContract.Presenter {

    CurrentClassAndStudentsContract.View view;
    CurrentClassAndStudentsContract.Repository studentRepository;

    public CurrentClassAndStudentsPresenter(CurrentClassAndStudentsContract.View view, Context context) {
        this.view = view;
        this.studentRepository = new StudentRepository(context);
    }

    @Override
    public List<StudentModel> loadAllDataInRecyclerView(int classroomId) {
            return studentRepository.getStudentsFromCurrentClass(classroomId);
    }

    @Override
    public void alertToDeleteClass(int position) {
    }
}
