package com.example.classroomapp.student.studentInfoAndMarks.stitisticInfo;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.widget.TextView;

import com.example.classroomapp.R;
import com.example.classroomapp.student.mainPageStudent.CurrentClassAndStudentsContract;
import com.example.classroomapp.student.mainPageStudent.CurrentClassAndStudentsPresenter;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;


public class StatisticsInfoFragment extends Fragment implements StatisticsInfoContract.View{

    private StatisticsInfoContract.Presenter statisticsPresenter;
    GraphView graphView;
    LineGraphSeries<DataPoint> series;

    public final static int MARK_VALUE1 = 1;
    public final static int MARK_VALUE2 = 2;
    public final static int MARK_VALUE3 = 3;
    public final static int MARK_VALUE4 = 4;
    public final static int MARK_VALUE5 = 5;

    public StatisticsInfoFragment() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        statisticsPresenter = new StatisticsInfoPresenter(this, getContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_statisticks_info, container, false);
        graphView = rootView.findViewById(R.id.graph);
        series = new LineGraphSeries<DataPoint>(new DataPoint[]{
                new DataPoint(MARK_VALUE1, statisticsPresenter.getCountForMarkValue(MARK_VALUE1)),
                new DataPoint(MARK_VALUE2, statisticsPresenter.getCountForMarkValue(MARK_VALUE2)),
                new DataPoint(MARK_VALUE3, statisticsPresenter.getCountForMarkValue(MARK_VALUE3)),
                new DataPoint(MARK_VALUE4, statisticsPresenter.getCountForMarkValue(MARK_VALUE4)),
                new DataPoint(MARK_VALUE5, statisticsPresenter.getCountForMarkValue(MARK_VALUE5)),

        });
        graphView.setTitle("Graph for streaming success of student");
        graphView.setTitleColor(R.color.purple_500);
        graphView.setTitleTextSize(30);

//        graphView.getViewport().setScrollable(true);
//        graphView.getViewport().setScalable(true);

        series.setColor(R.color.purple_700);
        graphView.addSeries(series);
        return rootView;
    }


    @Override
    public void onSuccess() {

    }
}