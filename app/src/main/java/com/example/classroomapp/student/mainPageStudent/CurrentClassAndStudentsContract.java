package com.example.classroomapp.student.mainPageStudent;

import android.database.Cursor;

import com.example.classroomapp.model.ClassroomModel;
import com.example.classroomapp.model.StudentModel;

import java.util.List;

public interface CurrentClassAndStudentsContract {

    interface View {
        void onSuccess(String messageAlert);
    }

    interface Presenter{
        List<StudentModel> loadAllDataInRecyclerView(int position);
        void alertToDeleteClass(int position);
        List<StudentModel> orderItemsDataByFirstNameASC();
        List<StudentModel> orderItemsDataByFirstNameDESC();
        List<StudentModel> orderItemsDataBySecondNameASC();
        List<StudentModel> orderItemsDataBySecondNameDESC();
    }

    interface Repository{
        List<StudentModel> getStudentsFromCurrentClass(int classroomId);
        void deleteStudentFromClass(int position);
        List<StudentModel> orderItemsByFirstName();
        List<StudentModel> orderItemsByFirstNameDESC();
        List<StudentModel> orderItemsBySecondNameACS();
        List<StudentModel> orderItemsBySecondNameDESC();
    }
}
