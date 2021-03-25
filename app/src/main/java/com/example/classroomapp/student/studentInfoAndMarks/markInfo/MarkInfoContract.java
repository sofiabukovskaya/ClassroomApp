package com.example.classroomapp.student.studentInfoAndMarks.markInfo;

import com.example.classroomapp.model.ClassroomModel;
import com.example.classroomapp.model.MarkModel;
import com.example.classroomapp.model.StudentModel;

import java.util.List;

public interface MarkInfoContract {
    interface View {
        void onSuccess(String messageAlert);
    }

    interface Presenter{
        List<MarkModel> loadAllDataInRecyclerView(int position);
        void alertToDeleteMark(int position);
    }

    interface Repository{
        List<MarkModel> getMarksForCurrentStudent(int studentId);
        void deleteMark(int position);
    }
}
