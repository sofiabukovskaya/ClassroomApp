package com.example.classroomapp.student.studentInfoAndMarks.stitisticInfo;

import android.content.Context;

import com.example.classroomapp.classroom.mainPageClassroom.MainContract;
import com.example.classroomapp.data.classroomData.ClassroomRepository;
import com.example.classroomapp.data.markData.MarkRepository;

public class StatisticsInfoPresenter implements StatisticsInfoContract.Presenter {


    StatisticsInfoContract.View view;
    StatisticsInfoContract.Repository repository;


    public StatisticsInfoPresenter(StatisticsInfoContract.View callback, Context context) {
        this.view = callback;
        this.repository =  new MarkRepository(context);

    }

    @Override
    public int getCountForMarkValue(Integer markValue) {
       return repository.getCountOfMark(markValue);

    }
}
