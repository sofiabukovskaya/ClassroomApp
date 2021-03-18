package com.example.classroomapp.data.studentData;

import com.example.classroomapp.student.mainPageStudent.CurrentClassAndStudentsContract;
import com.example.classroomapp.model.ClassroomModel;

import java.util.List;

public class StudentRepository implements CurrentClassAndStudentsContract.Repository {


    public StudentRepository() {

    }

    @Override
    public List<ClassroomModel> getListFromDataBase() {
        return null;
    }
}
