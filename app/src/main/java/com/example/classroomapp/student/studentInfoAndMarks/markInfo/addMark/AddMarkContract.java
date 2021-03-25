package com.example.classroomapp.student.studentInfoAndMarks.markInfo.addMark;

import com.example.classroomapp.model.MarkModel;
import com.example.classroomapp.model.StudentModel;

public interface AddMarkContract {
    interface View {
        void onSuccess(String messageAlert);
    }

    interface Presenter{
        void addButtonWasClicked(String name, String lastName, String middleName, String gender, int age, int position);
    }

    interface Repository{
        long addMark(MarkModel markModel);
    }
}
