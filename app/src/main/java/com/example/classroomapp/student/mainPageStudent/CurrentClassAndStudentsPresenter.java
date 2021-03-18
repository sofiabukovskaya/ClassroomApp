package com.example.classroomapp.student.mainPageStudent;

import com.example.classroomapp.student.mainPageStudent.CurrentClassAndStudentsContract;
import com.example.classroomapp.model.StudentModel;

import java.util.List;

public class CurrentClassAndStudentsPresenter implements CurrentClassAndStudentsContract.Presenter {

    @Override
    public List<StudentModel> loadAllDataInRecyclerView() {
        return null;
    }

    @Override
    public void alertToDeleteClass(int position) {

    }
}
