package com.example.classroomapp.student.studentInfoAndMarks.stitisticInfo;

import com.example.classroomapp.model.ClassroomModel;

import java.util.List;

public interface StatisticsInfoContract {

    interface View {
        void onSuccess();
    }

    interface Presenter{
        int getCountForMarkValue(Integer markValue);
    }

    interface Repository{
        int getCountOfMark(Integer markValue);
    }
}
