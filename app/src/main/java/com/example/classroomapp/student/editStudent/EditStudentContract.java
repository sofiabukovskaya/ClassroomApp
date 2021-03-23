package com.example.classroomapp.student.editStudent;

import com.example.classroomapp.model.ClassroomModel;
import com.example.classroomapp.model.StudentModel;

public interface EditStudentContract {

    interface View {
        void onSuccess(String messageAlert);
    }

    interface Presenter{
        void editButtonWasClicked(Integer id, String firstName, String lastName, String middleName, String gender, int age);
    }

    interface Repository{
        long updateStudent(StudentModel studentModel);
    }
}
