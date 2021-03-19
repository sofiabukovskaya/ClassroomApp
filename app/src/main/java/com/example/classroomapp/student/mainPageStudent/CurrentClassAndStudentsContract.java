package com.example.classroomapp.student.mainPageStudent;

import com.example.classroomapp.model.ClassroomModel;
import com.example.classroomapp.model.StudentModel;

import java.util.List;

public interface CurrentClassAndStudentsContract {

    interface View {
        void onSuccess(String messageAlert);
    }

    interface Presenter{
        List<StudentModel> loadAllDataInRecyclerView();
        void alertToDeleteClass(int position);
    }

    interface Repository{
        List<StudentModel> getAllStudentsFromDatabase();
        List<StudentModel> getStudentsFromCurrentClass();
        void deleteStudentFromClass(int position);
        boolean IsTableEmpty();
    }
}
