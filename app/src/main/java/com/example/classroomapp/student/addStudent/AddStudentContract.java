package com.example.classroomapp.student.addStudent;

import android.database.Cursor;

import com.example.classroomapp.model.ClassroomModel;
import com.example.classroomapp.model.StudentModel;

public interface AddStudentContract {

    interface View {
        void onSuccess(String messageAlert);
    }

    interface Presenter{
        void addButtonWasClicked(String name, String lastName, String middleName, String gender, int age, int position);
    }

    interface Repository{
        long addStudent(StudentModel studentModel);
    }
}
