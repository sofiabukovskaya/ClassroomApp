package com.example.classroomapp.student.studentInfoAndMarks.markInfo.addMark;

import com.example.classroomapp.model.MarkModel;
import com.example.classroomapp.model.StudentModel;

public interface AddMarkContract {

    interface View {
        void onSuccess(String messageAlert);
    }

    interface Presenter{
        void addButtonWasClicked(String subjectName, Integer markValue, String dateMark, int studentId);
    }

    interface Repository{
        long addMark(MarkModel markModel);
    }
}
